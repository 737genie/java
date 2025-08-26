package socketTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors; // 📌 Java 8 호환성을 위해 추가

public class GameRoom implements Runnable {
    // 플레이어 관리
    private final List<Player> players = new CopyOnWriteArrayList<>();
    private final List<Player> deadPlayers = new CopyOnWriteArrayList<>();
    private final Set<String> usedNicknames = ConcurrentHashMap.newKeySet();
    
    // 게임 상태
    private volatile boolean gameStarted = false;
    private volatile boolean gameEnded = false;
    private volatile boolean manualStartRequested = false; // 📌 수동 시작 플래그
    private int day = 0;
    
    // 투표 및 액션 관리
    private final Map<String, String> dayVotes = new ConcurrentHashMap<>(); // 투표자 -> 대상
    private final Map<String, String> nightActions = new ConcurrentHashMap<>(); // 행동자 -> 대상
    private final Map<String, List<String>> spyReports = new ConcurrentHashMap<>();
    
    // 특수 상태
    private String lastKilledPlayer = "";
    private boolean judgeUsed = false;
    private String judgeDecision = "";
    
    // 타이머
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    
    // 📌 자동시작 관련 상수 제거 - 수동시작만 허용
    private static final int MAX_PLAYERS = 15;
    private static final int DAY_DISCUSSION_TIME = 180; // 3분
    private static final int DAY_VOTE_TIME = 60; // 1분
    private static final int NIGHT_TIME = 120; // 2분
    private static final int MEDIUM_TIME = 30; // 30초

    @Override
    public void run() {
        System.out.println("🎮 GameRoom 실행 - 완전 수동 시작 모드");
        
        while (!gameEnded) {
            try {
                Thread.sleep(1000); // 1초마다 체크
                
                // 📌 자동 시작 로직 완전 제거 - 오직 수동으로만 시작
                if (!gameStarted && manualStartRequested && players.size() >= 4) {
                    startGame();
                    manualStartRequested = false;
                }
                
                // 게임이 시작된 후 승리 조건 체크
                if (gameStarted && !gameEnded) {
                    checkWinCondition();
                }
                
            } catch (InterruptedException e) {
                System.err.println("GameRoom 스레드 중단: " + e.getMessage());
                break;
            } catch (Exception e) {
                System.err.println("GameRoom 처리 중 오류: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        scheduler.shutdown();
    }

    public synchronized boolean addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS || gameStarted || usedNicknames.contains(player.getNickname())) {
            return false;
        }

        players.add(player);
        usedNicknames.add(player.getNickname());
        
        // 📌 자동시작 알림 제거 - 단순 입장 알림만
        broadcastToAll("PLAYER_JOIN:" + player.getNickname() + "님이 입장했습니다. (" +
            players.size() + "/" + MAX_PLAYERS + ")");

        return true;
    }

    public synchronized void removePlayer(Player player) {
        if (player != null) {
            players.remove(player);
            deadPlayers.remove(player);
            usedNicknames.remove(player.getNickname());
            
            if (!gameEnded && players.size() > 0) {
                broadcastToAll("PLAYER_LEAVE:" + player.getNickname() + "님이 나갔습니다. (" +
                    players.size() + "/" + MAX_PLAYERS + ")");
            }

            player.closeConnection();
            
            // 게임 중 최소 인원 미달시 종료
            if (gameStarted && players.size() < 2) {
                endGame("INSUFFICIENT_PLAYERS", "플레이어 부족으로 게임 종료");
            }
        }
    }
    
    // 📌 서버 관리자 전용 수동 시작
    public synchronized void forceStartGame() {
        if (gameStarted || gameEnded) {
            System.out.println("❌ 게임 시작 실패: 이미 시작되었거나 종료됨");
            return;
        }
        
        if (players.size() < 4) {
            System.out.println("❌ 게임 시작 실패: 최소 4명 필요 (현재: " + players.size() + "명)");
            return;
        }
        
        manualStartRequested = true;
        broadcastToAll("SERVER_MSG:📢 관리자가 게임 시작을 명령했습니다!");
        System.out.println("🚀 관리자 명령으로 게임 시작 요청됨");
    }

    private void startGame() {
        gameStarted = true;
        gameEnded = false;
        day = 1;
        
        System.out.println("🎯 [관리자] 게임 시작! 참가자 " + players.size() + "명");
        
        // 역할 배정
        assignRoles();
        
        // 게임 시작 안내
        broadcastToAll("GAME_START:🎭 마피아42 게임이 시작됩니다!");
        broadcastToAll("GAME_INFO:총 " + players.size() + "명의 플레이어가 참여합니다.");
        
        // 역할 정보 전송
        for (Player player : players) {
            player.sendMessage("ROLE:" + player.getRole().getKoreanName() + ":" + player.getRole().name());
            player.sendMessage("ROLE_DESC:" + player.getRole().getDescription());
        }

        // 마피아팀 정보 공유
        notifyMafiaMembers();
        
        // 첫 번째 낮 시작
        scheduler.schedule(this::startDayPhase, 3, TimeUnit.SECONDS);
    }

    private void assignRoles() {
        List<Role> roles = JobManager.assignRoles(players.size());
        
        // 플레이어 셔플
        Collections.shuffle(players);
        
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setRole(roles.get(i));
        }

        System.out.println("✅ 역할 배정 완료:");
        Map<Role, Long> roleCount = players.stream()
            .collect(Collectors.groupingBy(
                Player::getRole,
                Collectors.counting()
            ));
        roleCount.forEach((role, count) ->
            System.out.println("  " + role.getColoredName() + ": " + count + "명"));
    }

    private void notifyMafiaMembers() {
        // 📌 toList() -> collect(Collectors.toList()) 변경
        List<Player> mafiaPlayers = players.stream()
            .filter(p -> p.getRole().getTeam() == Role.Team.MAFIA)
            .collect(Collectors.toList());
            
        if (mafiaPlayers.size() > 1) {
            StringBuilder mafiaList = new StringBuilder("MAFIA_MEMBERS:🔴 동료 마피아: ");
            for (Player mafia : mafiaPlayers) {
                mafiaList.append(mafia.getNickname()).append("(").append(mafia.getRole().getKoreanName()).append(") ");
            }

            for (Player mafia : mafiaPlayers) {
                mafia.sendMessage(mafiaList.toString());
            }
        }
    }

    private void startDayPhase() {
        dayVotes.clear();
        judgeUsed = false;
        
        // 밤 상태 초기화
        for (Player player : players) {
            player.resetNightStatus();
        }

        broadcastToAll("DAY_START:☀️ === " + day + "일차 낮이 시작되었습니다 ===");
        broadcastToAll("DAY_INFO:🗣️ 토론 시간: " + DAY_DISCUSSION_TIME + "초");
        
        if (!lastKilledPlayer.isEmpty()) {
            broadcastToAll("DEATH_ANNOUNCEMENT:💀 어젯밤 " + lastKilledPlayer + "님이 사망했습니다.");
            lastKilledPlayer = "";
        }

        // 생존자 현황
        broadcastPlayerStatus();
        
        // 토론 시간 타이머
        scheduler.schedule(this::startVotePhase, DAY_DISCUSSION_TIME, TimeUnit.SECONDS);
    }

    private void startVotePhase() {
        dayVotes.clear();
        
        // 📌 toList() -> collect(Collectors.toList()) 변경
        List<String> alivePlayers = players.stream()
            .filter(Player::isAlive)
            .map(Player::getNickname)
            .collect(Collectors.toList());

        broadcastToAll("VOTE_START:🗳️ 투표를 시작합니다! (" + DAY_VOTE_TIME + "초)");
        broadcastToAll("VOTABLE_PLAYERS:" + String.join(",", alivePlayers));
        broadcastToAll("VOTE_INFO:의심스러운 플레이어에게 투표하세요. '/vote [플레이어명]' 명령어 사용");

        // 투표 시간 타이머
        scheduler.schedule(this::processVoteResult, DAY_VOTE_TIME, TimeUnit.SECONDS);
    }

    private void processVoteResult() {
        // 투표 집계
        Map<String, Integer> voteCount = new HashMap<>();
        for (String vote : dayVotes.values()) {
            voteCount.put(vote, voteCount.getOrDefault(vote, 0) + 1);
        }

        // 결과 발표
        broadcastToAll("VOTE_RESULT:📊 투표 결과:");
        voteCount.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(entry ->
                broadcastToAll("VOTE_RESULT:" + entry.getKey() + ": " + entry.getValue() + "표"));
        
        // 최다 득표자 찾기
        String mostVoted = voteCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("");
            
        if (mostVoted.isEmpty() || voteCount.get(mostVoted) == 0) {
            broadcastToAll("VOTE_RESULT:아무도 처형되지 않았습니다.");
            scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
            return;
        }

        // 판사 능력 체크
        Optional<Player> judge = players.stream()
            .filter(p -> p.isAlive() && p.getRole() == Role.JUDGE)
            .findFirst();
            
        if (judge.isPresent() && !judgeUsed) {
            // 판사에게 선고 기회 제공
            broadcastToAll("JUDGE_NOTICE:⚖️ 판사님, 선고하시겠습니까?");
            judge.get().sendMessage("JUDGE_POWER:" + mostVoted + ":선고하시겠습니까? /judge yes 또는 /judge no");
            // 판사 결정 대기 (10초)
            scheduler.schedule(() -> executePlayer(mostVoted), 10, TimeUnit.SECONDS);
        } else {
            executePlayer(mostVoted);
        }
    }

    private void executePlayer(String targetName) {
        Player target = findPlayerByNickname(targetName);
        if (target == null || !target.isAlive()) {
            scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
            return;
        }

        // 처형 실행
        target.die("투표");
        deadPlayers.add(target);
        broadcastToAll("EXECUTION:⚱️ " + targetName + "님이 투표로 처형되었습니다.");
        broadcastToAll("EXECUTION:💀 " + targetName + "님의 역할은 " + target.getRole().getColoredName() + "이었습니다.");
        
        // 사냥꾼 복수 능력
        if (target.getRole() == Role.HUNTER) {
            processHunterRevenge(target);
        } else {
            scheduler.schedule(this::startNightPhase, 5, TimeUnit.SECONDS);
        }
    }

    private void processHunterRevenge(Player hunter) {
        broadcastToAll("HUNTER_REVENGE:🏹 사냥꾼 " + hunter.getNickname() + "님의 복수 능력이 발동됩니다!");
        
        // 📌 toList() -> collect(Collectors.toList()) 변경
        List<String> targets = players.stream()
            .filter(Player::isAlive)
            .map(Player::getNickname)
            .collect(Collectors.toList());
            
        if (!targets.isEmpty()) {
            hunter.sendMessage("HUNTER_TARGET:복수할 대상을 선택하세요: " + String.join(",", targets));
            hunter.sendMessage("HUNTER_INFO:'/hunter [플레이어명]' 명령어로 대상을 지정하세요. (10초 제한)");
            // 10초 후 자동 진행
            scheduler.schedule(this::startNightPhase, 10, TimeUnit.SECONDS);
        } else {
            scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
        }
    }

    private void startNightPhase() {
        nightActions.clear();
        spyReports.clear();
        
        broadcastToAll("NIGHT_START:🌙 === " + day + "일차 밤이 시작되었습니다 ===");
        broadcastToAll("NIGHT_INFO:⏰ 밤 시간: " + NIGHT_TIME + "초");

        // 각 직업별 밤 행동 안내
        sendNightActionRequests();

        // 밤 시간 타이머
        scheduler.schedule(this::processNightActions, NIGHT_TIME, TimeUnit.SECONDS);
    }

    private void sendNightActionRequests() {
        // 📌 toList() -> collect(Collectors.toList()) 변경
        List<String> aliveTargets = players.stream()
            .filter(Player::isAlive)
            .map(Player::getNickname)
            .collect(Collectors.toList());
            
        for (Player player : players) {
            if (!player.isAlive()) continue;
            
            switch (player.getRole()) {
                case MAFIA:
                case MADAM:
                    player.sendMessage("NIGHT_ACTION:🔪 누구를 제거하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",", 
                        aliveTargets.stream()
                            .filter(name -> !name.equals(player.getNickname()))
                            .collect(Collectors.toList())));
                    break;
                    
                case DOCTOR:
                    player.sendMessage("NIGHT_ACTION:⚕️ 누구를 보호하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",", aliveTargets));
                    break;
                    
                case POLICE:
                    player.sendMessage("NIGHT_ACTION:👮 누구를 조사하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",", 
                        aliveTargets.stream()
                            .filter(name -> !name.equals(player.getNickname()))
                            .collect(Collectors.toList())));
                    break;
                    
                case DETECTIVE:
                    player.sendMessage("NIGHT_ACTION:🔍 누구의 행동을 추적하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",", 
                        aliveTargets.stream()
                            .filter(name -> !name.equals(player.getNickname()))
                            .collect(Collectors.toList())));
                    break;
                    
                case SPY:
                    player.sendMessage("NIGHT_ACTION:🕵️ 누구의 행동을 감시하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",", 
                        aliveTargets.stream()
                            .filter(name -> !name.equals(player.getNickname()))
                            .collect(Collectors.toList())));
                    break;
                    
                case THIEF:
                    if (player.getStolenRole() != null) {
                        player.sendMessage("NIGHT_ACTION:🥷 훔친 능력(" + player.getStolenRole().getKoreanName() + ")을 사용하시겠습니까?");
                        player.sendMessage("TARGET_LIST:" + String.join(",", 
                            aliveTargets.stream()
                                .filter(name -> !name.equals(player.getNickname()))
                                .collect(Collectors.toList())));
                    }
                    break;
            }
        }
    }

    private void processNightActions() {
        broadcastToAll("NIGHT_END:🌅 밤이 지나갑니다...");
        
        // 1단계: 보호 능력 먼저 처리
        for (Map.Entry<String, String> action : nightActions.entrySet()) {
            Player actor = findPlayerByNickname(action.getKey());
            Player target = findPlayerByNickname(action.getValue());
            
            if (actor == null || target == null || !actor.isAlive()) continue;
            
            if (actor.getRole() == Role.DOCTOR) {
                target.setProtected(true);
                actor.sendMessage("DOCTOR_RESULT:✅ " + target.getNickname() + "님을 보호했습니다.");
            }
        }

        // 2단계: 공격 능력 처리
        List<Player> killedPlayers = new ArrayList<>();
        for (Map.Entry<String, String> action : nightActions.entrySet()) {
            Player actor = findPlayerByNickname(action.getKey());
            Player target = findPlayerByNickname(action.getValue());
            
            if (actor == null || target == null || !actor.isAlive()) continue;
            
            switch (actor.getRole()) {
                case MAFIA:
                case MADAM:
                    if (target.attemptKill("마피아")) {
                        killedPlayers.add(target);
                        lastKilledPlayer = target.getNickname();
                    }
                    break;
                    
                case THIEF:
                    if (actor.getStolenRole() != null && actor.getStolenRole().getTeam() == Role.Team.MAFIA) {
                        if (target.attemptKill("마피아")) {
                            killedPlayers.add(target);
                            lastKilledPlayer = target.getNickname();
                        }
                    }
                    break;
            }
        }

        // 3단계: 조사 능력 처리
        for (Map.Entry<String, String> action : nightActions.entrySet()) {
            Player actor = findPlayerByNickname(action.getKey());
            Player target = findPlayerByNickname(action.getValue());
            
            if (actor == null || target == null || !actor.isAlive()) continue;
            
            switch (actor.getRole()) {
                case POLICE:
                    String policeResult = target.getRole().getTeam() == Role.Team.MAFIA ? "마피아" : "시민";
                    actor.sendMessage("POLICE_RESULT:👮 " + target.getNickname() + "님은 " + policeResult + "입니다.");
                    break;
                    
                case DETECTIVE:
                    String detectiveResult = target.getLastNightTarget().isEmpty() ?
                        "아무에게도 능력을 사용하지 않았습니다" :
                        target.getLastNightTarget() + "님에게 능력을 사용했습니다";
                    actor.sendMessage("DETECTIVE_RESULT:🔍 " + target.getNickname() + "님은 " + detectiveResult + ".");
                    break;
                    
                case SPY:
                    List<String> spyInfo = new ArrayList<>();
                    for (Map.Entry<String, String> otherAction : nightActions.entrySet()) {
                        if (otherAction.getKey().equals(target.getNickname())) {
                            spyInfo.add(target.getNickname() + "님이 " + otherAction.getValue() + "님에게 능력 사용");
                        }
                    }
                    String spyResult = spyInfo.isEmpty() ?
                        target.getNickname() + "님은 밤에 아무 행동을 하지 않았습니다" :
                        String.join(", ", spyInfo);
                    actor.sendMessage("SPY_RESULT:🕵️ " + spyResult);
                    break;
            }
        }

        // 죽은 플레이어들 처리
        for (Player killed : killedPlayers) {
            deadPlayers.add(killed);
        }

        // 영매 시간 또는 다음 날로 진행
        if (hasMedium() && !deadPlayers.isEmpty()) {
            scheduler.schedule(this::startMediumPhase, 3, TimeUnit.SECONDS);
        } else {
            day++;
            scheduler.schedule(this::startDayPhase, 3, TimeUnit.SECONDS);
        }
    }

    private void startMediumPhase() {
        Optional<Player> medium = players.stream()
            .filter(p -> p.isAlive() && p.getRole() == Role.MEDIUM)
            .findFirst();
            
        if (medium.isPresent() && !deadPlayers.isEmpty()) {
            broadcastToAll("MEDIUM_START:🔮 === 영매 시간 ===");
            broadcastToAll("MEDIUM_INFO:📿 영매가 죽은 자들과 소통을 시도합니다...");
            
            // 영매에게 죽은 자들의 정보 제공
            StringBuilder deadInfo = new StringBuilder("DEAD_INFO:💀 죽은 자들: ");
            for (Player dead : deadPlayers) {
                deadInfo.append(dead.getNickname())
                    .append("(")
                    .append(dead.getRole().getKoreanName())
                    .append(" - ")
                    .append(dead.getDeathMessage())
                    .append(") ");
            }

            medium.get().sendMessage(deadInfo.toString());
            // 죽은 자들에게 안내
            broadcastToDead("DEAD_CHAT:👻 영매와 대화할 수 있습니다. '/dead [메시지]' 명령어 사용");
            // 영매에게 안내
            medium.get().sendMessage("MEDIUM_GUIDE:'/medium [메시지]' 명령어로 죽은 자들에게 메시지를 보낼 수 있습니다.");
        }

        // 영매 시간 타이머
        scheduler.schedule(() -> {
            day++;
            startDayPhase();
        }, MEDIUM_TIME, TimeUnit.SECONDS);
    }

    public synchronized void handlePlayerAction(Player player, String action) {
        if (player == null || action == null) return;
        
        String[] parts = action.split(":", 2);
        if (parts.length < 2) return;
        
        String actionType = parts[0];
        String content = parts[1];
        
        switch (actionType) {
            case "CHAT":
                if (player.isAlive()) {
                    broadcastToAll("CHAT:" + player.getNickname() + ": " + content);
                }
                break;
                
            case "VOTE":
                if (player.canVote()) {
                    dayVotes.put(player.getNickname(), content);
                    broadcastToAll("VOTE_CAST:🗳️ " + player.getNickname() + "님이 투표했습니다.");
                }
                break;
                
            case "NIGHT":
                if (player.isAlive() && player.getRole().needsNightAction()) {
                    nightActions.put(player.getNickname(), content);
                    player.setLastNightTarget(content);
                    player.sendMessage("ACTION_CONFIRM:✅ 밤 행동이 등록되었습니다.");
                }
                break;
                
            case "JUDGE":
                if (player.getRole() == Role.JUDGE && !judgeUsed) {
                    processJudgeDecision(player, content);
                }
                break;
                
            case "HUNTER":
                if (player.getRole() == Role.HUNTER && !player.isAlive()) {
                    processHunterTarget(player, content);
                }
                break;
                
            case "THIEF_STEAL":
                if (player.getRole() == Role.THIEF) {
                    processThiefSteal(player, content);
                }
                break;
                
            case "MEDIUM":
                if (player.getRole() == Role.MEDIUM && player.isAlive()) {
                    broadcastToDead("MEDIUM_MESSAGE:🔮 영매: " + content);
                }
                break;
                
            case "DEAD":
                if (!player.isAlive()) {
                    broadcastToDead("DEAD_CHAT:👻 " + player.getNickname() + ": " + content);
                }
                break;
        }
    }

    private void processJudgeDecision(Player judge, String decision) {
        judgeUsed = true;
        judgeDecision = decision;
        
        if ("yes".equalsIgnoreCase(decision)) {
            broadcastToAll("JUDGE_DECISION:⚖️ 판사님이 처형에 동의했습니다.");
        } else {
            broadcastToAll("JUDGE_DECISION:⚖️ 판사님이 처형을 거부했습니다! 아무도 죽지 않습니다.");
            scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
        }

        broadcastToAll("JUDGE_REVEALED:⚖️ " + judge.getNickname() + "님이 판사임이 밝혀졌습니다!");
    }

    private void processHunterTarget(Player hunter, String targetName) {
        Player target = findPlayerByNickname(targetName);
        if (target != null && target.isAlive()) {
            target.die("사냥꾼의 복수");
            deadPlayers.add(target);
            broadcastToAll("HUNTER_KILL:🏹 " + hunter.getNickname() + "님의 복수로 " + targetName + "님이 사망했습니다!");
        }
    }

    private void processThiefSteal(Player thief, String targetName) {
        Player target = findPlayerByNickname(targetName);
        if (target != null) {
            thief.setStolenRole(target.getRole());
            thief.sendMessage("THIEF_SUCCESS:🥷 " + targetName + "님의 능력(" + target.getRole().getKoreanName() + ")을 훔쳤습니다!");
        }
    }

    private boolean checkWinCondition() {
        long aliveCitizens = players.stream()
            .filter(p -> p.isAlive() && p.getRole().getTeam() == Role.Team.CITIZEN)
            .count();
            
        long aliveMafia = players.stream()
            .filter(p -> p.isAlive() && p.getRole().getTeam() == Role.Team.MAFIA)
            .count();
            
        if (aliveMafia == 0) {
            endGame("CITIZEN_WIN", "🎉 시민팀이 승리했습니다! 모든 마피아를 제거했습니다!");
            return true;
        } else if (aliveMafia >= aliveCitizens) {
            endGame("MAFIA_WIN", "🎉 마피아팀이 승리했습니다! 마피아가 시민과 같거나 많아졌습니다!");
            return true;
        }
        
        return false;
    }

    private void endGame(String winType, String message) {
        gameEnded = true;
        broadcastToAll("GAME_END:" + winType + ":" + message);
        System.out.println("🏁 " + message);
        
        // 모든 플레이어의 역할 공개
        broadcastToAll("ROLE_REVEAL:=== 최종 역할 공개 ===");
        players.forEach(player ->
            broadcastToAll("ROLE_REVEAL:" + player.getNickname() + ": " +
                player.getRole().getColoredName() + " (" +
                (player.isAlive() ? "생존" : "사망") + ")"));

        // 게임 통계
        broadcastGameStats();

        // 10초 후 게임 리셋
        scheduler.schedule(this::resetGame, 10, TimeUnit.SECONDS);
    }
    
    private void resetGame() {
        gameStarted = false;
        gameEnded = false;
        manualStartRequested = false;
        day = 0;
        dayVotes.clear();
        nightActions.clear();
        deadPlayers.clear();
        lastKilledPlayer = "";
        judgeUsed = false;
        judgeDecision = "";
        
        for (Player player : players) {
            player.setRole(null);
            player.setAlive(true);
        }
        
        broadcastToAll("RESET:게임이 종료되었습니다. 서버 관리자가 새 게임을 시작할 때까지 기다려주세요.");
        System.out.println("🔄 게임 상태 초기화 완료. 수동 시작 대기 중...");
    }

    private void broadcastGameStats() {
        broadcastToAll("GAME_STATS:=== 게임 통계 ===");
        broadcastToAll("GAME_STATS:총 플레이어: " + players.size() + "명");
        broadcastToAll("GAME_STATS:게임 일수: " + day + "일");
        broadcastToAll("GAME_STATS:생존자: " + players.stream().filter(Player::isAlive).count() + "명");
        broadcastToAll("GAME_STATS:사망자: " + deadPlayers.size() + "명");
    }

    // 📌 서버 지원 메소드들
    public boolean isGameStarted() {
        return gameStarted;
    }
    
    public synchronized void forceStopGame() {
        if (!gameStarted || gameEnded) return;
        
        gameEnded = true;
        broadcastToAll("GAME_END:ADMIN_STOP:관리자가 게임을 종료했습니다.");
        
        // 즉시 리셋
        scheduler.schedule(this::resetGame, 3, TimeUnit.SECONDS);
        System.out.println("🛑 관리자 명령으로 게임 강제 종료됨");
    }
    
    public void broadcastServerMessage(String message) {
        broadcastToAll("SERVER_MSG:" + message);
    }
    
    public void cleanupDisconnectedPlayers() {
        List<Player> toRemove = players.stream()
            .filter(p -> !p.isConnected())
            .collect(Collectors.toList());
            
        for (Player player : toRemove) {
            System.out.println("🧹 연결 끊어진 플레이어 정리: " + player.getNickname());
            removePlayer(player);
        }
    }

    // 유틸리티 메소드들
    private Player findPlayerByNickname(String nickname) {
        return players.stream()
            .filter(p -> p.getNickname().equals(nickname))
            .findFirst()
            .orElse(null);
    }

    private boolean hasMedium() {
        return players.stream()
            .anyMatch(p -> p.isAlive() && p.getRole() == Role.MEDIUM);
    }

    private void broadcastToAll(String message) {
        for (Player player : players) {
            if (player.isConnected()) {
                player.sendMessage(message);
            }
        }
    }

    private void broadcastToDead(String message) {
        for (Player player : deadPlayers) {
            if (player.isConnected()) {
                player.sendMessage(message);
            }
        }
    }

    private void broadcastPlayerStatus() {
        long alive = players.stream().filter(Player::isAlive).count();
        long aliveCitizens = players.stream()
            .filter(p -> p.isAlive() && p.getRole().getTeam() == Role.Team.CITIZEN)
            .count();
        long aliveMafia = players.stream()
            .filter(p -> p.isAlive() && p.getRole().getTeam() == Role.Team.MAFIA)
            .count();
            
        broadcastToAll("GAME_STATUS:📊 생존자: " + alive + "명 | 시민팀: " + aliveCitizens + "명 | 마피아팀: " + aliveMafia + "명");
    }

    // Getter 메소드들
    public int getPlayerCount() { 
        return players.size(); 
    }
    
    public boolean isNicknameTaken(String nickname) { 
        return usedNicknames.contains(nickname); 
    }
    
    public boolean hasPlayerByNickname(String nickname) {
        return players.stream()
            .anyMatch(p -> p.getNickname().equals(nickname));
    }
    
    public void printPlayerList() {
        for (Player player : players) {
            String role = gameStarted && player.getRole() != null ? 
                " (" + player.getRole().getKoreanName() + ")" : "";
            String status = player.isAlive() ? "🟢" : "💀";
            System.out.println("  " + status + " " + player.getNickname() + role);
        }
    }
}