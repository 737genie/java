package test1t;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class GameRoom implements Runnable {
    // 플레이어 관리
    private final List<Player> players = new CopyOnWriteArrayList<>();
    private final List<Player> deadPlayers = new CopyOnWriteArrayList<>();
    private final Set<String> usedNicknames = ConcurrentHashMap.newKeySet();
    
    // 게임 상태
    private volatile boolean gameStarted = false;
    private volatile boolean gameEnded = false;
    private volatile boolean manualStartRequested = false;
    private volatile String currentPhase = "WAITING";
    private int day = 0;

    // 투표 및 액션 관리
    private final Map<String, String> dayVotes = new ConcurrentHashMap<>();
    private final Map<String, String> nightActions = new ConcurrentHashMap<>();

    // 특수 상태 관리
    private String lastKilledPlayer = "";
    private boolean judgeUsed = false;
    private String judgeDecision = "";
    private Player currentJudge = null;
    private Player currentHunter = null;
    private boolean waitingForJudge = false;
    private boolean waitingForHunter = false;
    
    // 스케줄러 및 타이머
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private volatile ScheduledFuture<?> currentTimer = null;

    // 게임 설정
    private static final int MAX_PLAYERS = 15;
    private static final int DAY_DISCUSSION_TIME = 30; // 30초 (테스트용)
    private static final int DAY_VOTE_TIME = 20; // 20초 (테스트용)
    private static final int NIGHT_TIME = 30; // 30초 (테스트용)
    private static final int MEDIUM_TIME = 15; // 15초 (테스트용)

    @Override
    public void run() {
        System.out.println("🎮 GameRoom 실행 - 완전 수동 시작 모드");
        while (!gameEnded) {
            try {
                Thread.sleep(1000);
                
                // 게임 시작 조건 체크
                if (!gameStarted && manualStartRequested && players.size() >= 4) {
                    startGame();
                    manualStartRequested = false;
                }
                
                // 승리 조건 체크
                if (gameStarted && !gameEnded) {
                    checkWinCondition();
                }
                
            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                System.err.println("GameRoom 처리 중 오류: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scheduler.shutdown();
    }

    // ======================== 플레이어 관리 ========================
    
    public synchronized boolean addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS || gameStarted || usedNicknames.contains(player.getNickname())) {
            return false;
        }

        players.add(player);
        usedNicknames.add(player.getNickname());
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
                broadcastToAll("PLAYER_LEAVE:" + player.getNickname() + "님이 나갔습니다.");
            }
            
            player.closeConnection();
        }
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

    // ======================== 게임 시작 및 초기화 ========================
    
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
        currentPhase = "GAME_START";
        day = 1;
        
        System.out.println("🎯 [관리자] 게임 시작! 참가자 " + players.size() + "명");
        
        // 역할 배정
        assignRoles();
        
        // 게임 시작 안내
        broadcastToAll("GAME_START:🎭 마피아42 게임이 시작됩니다!");
        
        // 역할 정보 전송
        for (Player player : players) {
            player.sendMessage("ROLE:" + player.getRole().getKoreanName() + ":" + player.getRole().name());
            player.sendMessage("ROLE_DESC:" + player.getRole().getDescription());
        }
        
        // 마피아팀 정보 공유
        notifyMafiaMembers();
        
        // 첫 번째 낮 시작
        currentTimer = scheduler.schedule(this::startDayPhase, 3, TimeUnit.SECONDS);
    }

    private void assignRoles() {
        List<Role> roles = JobManager.assignRoles(players.size());
        Collections.shuffle(players);
        
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setRole(roles.get(i));
        }

        System.out.println("✅ 역할 배정 완료:");
        Map<Role, Long> roleCount = players.stream()
                .collect(Collectors.groupingBy(Player::getRole, Collectors.counting()));
        
        roleCount.forEach((role, count) ->
                System.out.println("  " + role.getColoredName() + ": " + count + "명"));
    }

    private void notifyMafiaMembers() {
        List<Player> mafiaPlayers = players.stream()
                .filter(p -> p.getRole().getTeam() == Role.Team.MAFIA)
                .collect(Collectors.toList());
        
        if (mafiaPlayers.size() > 1) {
            StringBuilder mafiaList = new StringBuilder("MAFIA_MEMBERS:🔴 동료 마피아: ");
            for (Player mafia : mafiaPlayers) {
                mafiaList.append(mafia.getNickname())
                        .append("(").append(mafia.getRole().getKoreanName()).append(") ");
            }
            
            for (Player mafia : mafiaPlayers) {
                mafia.sendMessage(mafiaList.toString());
            }
        }
    }

    // ======================== 낮 단계 ========================
    
    private void startDayPhase() {
        currentPhase = "DAY_DISCUSSION";
        dayVotes.clear();
        judgeUsed = false;
        waitingForJudge = false;
        waitingForHunter = false;
        currentJudge = null;
        currentHunter = null;

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

        broadcastPlayerStatus();
        currentTimer = scheduler.schedule(this::startVotePhase, DAY_DISCUSSION_TIME, TimeUnit.SECONDS);
    }

    private void startVotePhase() {
        currentPhase = "DAY_VOTE";
        dayVotes.clear();
        
        List<String> alivePlayers = players.stream()
                .filter(Player::isAlive)
                .map(Player::getNickname)
                .collect(Collectors.toList());

        broadcastToAll("VOTE_START:🗳️ 투표를 시작합니다! (" + DAY_VOTE_TIME + "초)");
        broadcastToAll("VOTABLE_PLAYERS:" + String.join(",", alivePlayers));
        broadcastToAll("VOTE_INFO:의심스러운 플레이어에게 투표하세요. '/vote [플레이어명]' 명령어 사용");

        currentTimer = scheduler.schedule(this::processVoteResult, DAY_VOTE_TIME, TimeUnit.SECONDS);
    }

    private void processVoteResult() {
        if (waitingForJudge || waitingForHunter) {
            currentTimer = scheduler.schedule(this::processVoteResult, 5, TimeUnit.SECONDS);
            return;
        }

        currentPhase = "VOTE_PROCESSING";

        // 투표 집계
        Map<String, Integer> voteCount = new HashMap<>();
        for (String vote : dayVotes.values()) {
            voteCount.put(vote, voteCount.getOrDefault(vote, 0) + 1);
        }

        // 결과 발표
        broadcastToAll("VOTE_RESULT:📊 투표 결과:");
        if (voteCount.isEmpty()) {
            broadcastToAll("VOTE_RESULT:아무도 투표하지 않았습니다.");
        } else {
            voteCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry ->
                            broadcastToAll("VOTE_RESULT:" + entry.getKey() + ": " + entry.getValue() + "표"));
        }

        // 최다 득표자 찾기
        String mostVoted = voteCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        if (mostVoted.isEmpty() || voteCount.get(mostVoted) == 0) {
            broadcastToAll("VOTE_RESULT:아무도 처형되지 않았습니다.");
            currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
            return;
        }

        // 동점자 확인
        final int maxVotes = voteCount.get(mostVoted);
        List<String> topVoted = voteCount.entrySet().stream()
                .filter(entry -> entry.getValue() == maxVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (topVoted.size() > 1) {
            broadcastToAll("VOTE_RESULT:동점으로 아무도 처형되지 않았습니다. (" + 
                    String.join(", ", topVoted) + ")");
            currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
            return;
        }

        // 판사 능력 체크
        Optional<Player> judge = players.stream()
                .filter(p -> p.isAlive() && p.getRole() == Role.JUDGE)
                .findFirst();

        if (judge.isPresent() && !judgeUsed) {
            currentJudge = judge.get();
            waitingForJudge = true;
            currentPhase = "WAITING_JUDGE";
            
            broadcastToAll("JUDGE_NOTICE:⚖️ 판사님, 선고하시겠습니까?");
            judge.get().sendMessage("JUDGE_POWER:" + mostVoted + ":선고하시겠습니까?");
            
            // 10초 후 자동 진행
            currentTimer = scheduler.schedule(() -> {
                if (waitingForJudge) {
                    waitingForJudge = false;
                    executePlayer(mostVoted);
                }
            }, 10, TimeUnit.SECONDS);
        } else {
            executePlayer(mostVoted);
        }
    }

    private void executePlayer(String targetName) {
        Player target = findPlayerByNickname(targetName);
        if (target == null || !target.isAlive()) {
            currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
            return;
        }

        target.die("투표");
        deadPlayers.add(target);
        
        broadcastToAll("EXECUTION:⚱️ " + targetName + "님이 투표로 처형되었습니다.");
        broadcastToAll("EXECUTION:💀 " + targetName + "님의 역할은 " + target.getRole().getColoredName() + "이었습니다.");

        // 사냥꾼 복수 능력
        if (target.getRole() == Role.HUNTER) {
            processHunterRevenge(target);
        } else {
            currentTimer = scheduler.schedule(this::startNightPhase, 5, TimeUnit.SECONDS);
        }
    }

    private void processHunterRevenge(Player hunter) {
        currentHunter = hunter;
        waitingForHunter = true;
        currentPhase = "WAITING_HUNTER";
        
        broadcastToAll("HUNTER_REVENGE:🏹 사냥꾼 " + hunter.getNickname() + "님의 복수 능력이 발동됩니다!");
        
        List<String> targets = players.stream()
                .filter(Player::isAlive)
                .map(Player::getNickname)
                .collect(Collectors.toList());

        if (!targets.isEmpty()) {
            hunter.sendMessage("HUNTER_TARGET:복수할 대상을 선택하세요: " + String.join(",", targets));
            
            // 10초 후 자동 진행
            currentTimer = scheduler.schedule(() -> {
                if (waitingForHunter) {
                    waitingForHunter = false;
                    startNightPhase();
                }
            }, 10, TimeUnit.SECONDS);
        } else {
            waitingForHunter = false;
            currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
        }
    }

    // ======================== 밤 단계 ========================
    
    private void startNightPhase() {
        if (waitingForJudge || waitingForHunter) {
            currentTimer = scheduler.schedule(this::startNightPhase, 2, TimeUnit.SECONDS);
            return;
        }

        currentPhase = "NIGHT";
        nightActions.clear();

        broadcastToAll("NIGHT_START:🌙 === " + day + "일차 밤이 시작되었습니다 ===");
        broadcastToAll("NIGHT_INFO:⏰ 밤 시간: " + NIGHT_TIME + "초");
        
        sendNightActionRequests();
        currentTimer = scheduler.schedule(this::processNightActions, NIGHT_TIME, TimeUnit.SECONDS);
    }

    private void sendNightActionRequests() {
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

                case JOURNALIST:
                    player.sendMessage("NIGHT_ACTION:📰 누구를 취재하시겠습니까?");
                    player.sendMessage("TARGET_LIST:" + String.join(",",
                            aliveTargets.stream()
                                    .filter(name -> !name.equals(player.getNickname()))
                                    .collect(Collectors.toList())));
                    break;

                case THIEF:
                    if (player.getStolenRole() != null) {
                        player.sendMessage("NIGHT_ACTION:🥷 훔친 능력(" +
                                player.getStolenRole().getKoreanName() + ")을 사용하시겠습니까?");
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
        currentPhase = "NIGHT_PROCESSING";
        broadcastToAll("NIGHT_END:🌅 밤이 지나갑니다...");

        // 1단계: 보호 능력 처리
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
                    if (actor.getStolenRole() != null &&
                            actor.getStolenRole().getTeam() == Role.Team.MAFIA) {
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

                case JOURNALIST:
                    String journalistInfo = "📰 " + target.getNickname() + "님 정보: " +
                            (target.isAlive() ? "생존" : "사망") +
                            ", 최근 행동: " + (target.getLastNightTarget().isEmpty() ? "없음" : target.getLastNightTarget());
                    actor.sendMessage("JOURNALIST_RESULT:" + journalistInfo);
                    break;
            }
        }

        // 죽은 플레이어들 처리
        for (Player killed : killedPlayers) {
            deadPlayers.add(killed);
        }

        // 영매 시간 또는 다음 날로 진행
        if (hasMedium() && !deadPlayers.isEmpty()) {
            currentTimer = scheduler.schedule(this::startMediumPhase, 3, TimeUnit.SECONDS);
        } else {
            day++;
            currentTimer = scheduler.schedule(this::startDayPhase, 3, TimeUnit.SECONDS);
        }
    }

    // ======================== 영매 단계 ========================
    
    private void startMediumPhase() {
        currentPhase = "MEDIUM";
        Optional<Player> medium = players.stream()
                .filter(p -> p.isAlive() && p.getRole() == Role.MEDIUM)
                .findFirst();

        if (medium.isPresent() && !deadPlayers.isEmpty()) {
            broadcastToAll("MEDIUM_START:🔮 === 영매 시간 ===");
            broadcastToAll("MEDIUM_INFO:📿 영매가 죽은 자들과 소통을 시도합니다...");

            StringBuilder deadInfo = new StringBuilder("DEAD_INFO:💀 죽은 자들: ");
            for (Player dead : deadPlayers) {
                deadInfo.append(dead.getNickname())
                        .append("(").append(dead.getRole().getKoreanName())
                        .append(" - ").append(dead.getDeathMessage()).append(") ");
            }

            medium.get().sendMessage(deadInfo.toString());
            broadcastToDead("DEAD_CHAT:👻 영매와 대화할 수 있습니다. '/dead [메시지]' 명령어 사용");
            medium.get().sendMessage("MEDIUM_GUIDE:'/medium [메시지]' 명령어로 죽은 자들에게 메시지를 보낼 수 있습니다.");
        }

        currentTimer = scheduler.schedule(() -> {
            day++;
            startDayPhase();
        }, MEDIUM_TIME, TimeUnit.SECONDS);
    }

    // ======================== 플레이어 행동 처리 ========================
    
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
                if (player.canVote() && currentPhase.equals("DAY_VOTE")) {
                    String oldVote = dayVotes.get(player.getNickname());
                    dayVotes.put(player.getNickname(), content);
                    
                    if (oldVote != null) {
                        broadcastToAll("VOTE_CHANGED:🔄 " + player.getNickname() + "님이 투표를 변경했습니다.");
                    } else {
                        broadcastToAll("VOTE_CAST:🗳️ " + player.getNickname() + "님이 투표했습니다.");
                    }
                    
                    // 조기 종료 체크 (모든 생존자가 투표했을 때)
                    checkEarlyVoteEnd();
                }
                break;

            case "NIGHT":
                if (player.isAlive() && player.getRole().needsNightAction() && currentPhase.equals("NIGHT")) {
                    nightActions.put(player.getNickname(), content);
                    player.setLastNightTarget(content);
                    player.sendMessage("ACTION_CONFIRM:✅ 밤 행동이 등록되었습니다.");
                    
                    // 조기 종료 체크 (모든 능력자가 행동했을 때)
                    checkEarlyNightEnd();
                }
                break;

            case "JUDGE":
                if (player == currentJudge && waitingForJudge && !judgeUsed) {
                    processJudgeDecision(player, content);
                }
                break;

            case "HUNTER":
                if (player == currentHunter && waitingForHunter && !player.isAlive()) {
                    processHunterTarget(player, content);
                }
                break;

            case "THIEF_STEAL":
                if (player.getRole() == Role.THIEF && player.isAlive()) {
                    processThiefSteal(player, content);
                }
                break;

            case "MEDIUM":
                if (player.getRole() == Role.MEDIUM && player.isAlive()) {
                    broadcastToDead("MEDIUM_MESSAGE:🔮 영매 " + player.getNickname() + ": " + content);
                    player.sendMessage("MEDIUM_SENT:✅ 죽은 자들에게 메시지를 보냈습니다.");
                }
                break;

            case "DEAD":
                if (!player.isAlive()) {
                    broadcastToDead("DEAD_CHAT:👻 " + player.getNickname() + ": " + content);
                    
                    // 영매에게도 전송
                    Optional<Player> medium = players.stream()
                            .filter(p -> p.isAlive() && p.getRole() == Role.MEDIUM)
                            .findFirst();
                    
                    if (medium.isPresent()) {
                        medium.get().sendMessage("DEAD_TO_MEDIUM:💀 " + player.getNickname() + ": " + content);
                    }
                    
                    player.sendMessage("DEAD_SENT:✅ 죽은 자 채팅을 보냈습니다.");
                }
                break;

            case "MAFIA_CHAT":
                if (player.isAlive() && player.getRole().getTeam() == Role.Team.MAFIA) {
                    broadcastToMafia("MAFIA_CHAT:🔴 " + player.getNickname() + ": " + content);
                }
                break;
        }
    }

    // ======================== 특수 행동 처리 ========================
    
    private void processJudgeDecision(Player judge, String decision) {
        judgeUsed = true;
        waitingForJudge = false;
        judgeDecision = decision;
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }

        if ("yes".equalsIgnoreCase(decision)) {
            broadcastToAll("JUDGE_DECISION:⚖️ 판사님이 처형에 동의했습니다.");
            broadcastToAll("JUDGE_REVEALED:⚖️ " + judge.getNickname() + "님이 판사임이 밝혀졌습니다!");
            // 처형 진행
            currentTimer = scheduler.schedule(() -> {
                // 최다 득표자를 다시 찾아서 처형
                String mostVoted = findMostVotedPlayer();
                if (!mostVoted.isEmpty()) {
                    executePlayer(mostVoted);
                }
            }, 2, TimeUnit.SECONDS);
        } else {
            broadcastToAll("JUDGE_DECISION:⚖️ 판사님이 처형을 거부했습니다! 아무도 죽지 않습니다.");
            broadcastToAll("JUDGE_REVEALED:⚖️ " + judge.getNickname() + "님이 판사임이 밝혀졌습니다!");
            currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
        }
    }

    private void processHunterTarget(Player hunter, String targetName) {
        waitingForHunter = false;
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }
        
        Player target = findPlayerByNickname(targetName);
        if (target != null && target.isAlive()) {
            target.die("사냥꾼의 복수");
            deadPlayers.add(target);
            broadcastToAll("HUNTER_KILL:🏹 " + hunter.getNickname() +
                    "님의 복수로 " + targetName + "님이 사망했습니다!");
        }

        currentTimer = scheduler.schedule(this::startNightPhase, 3, TimeUnit.SECONDS);
    }

    private void processThiefSteal(Player thief, String targetName) {
        Player target = findPlayerByNickname(targetName);
        if (target != null && target.isAlive() && thief.getStolenRole() == null) {
            thief.setStolenRole(target.getRole());
            thief.sendMessage("THIEF_SUCCESS:🥷 " + targetName + "님의 능력(" +
                    target.getRole().getKoreanName() + ")을 훔쳤습니다!");
        }
    }

    // ======================== 조기 종료 체크 ========================
    
    private void checkEarlyVoteEnd() {
        long aliveCount = players.stream().filter(Player::isAlive).count();
        if (dayVotes.size() >= aliveCount) {
            if (currentTimer != null) {
                currentTimer.cancel(false);
                currentTimer = null;
            }
            broadcastToAll("VOTE_EARLY_END:⚡ 모든 플레이어가 투표하여 투표가 조기 종료됩니다!");
            currentTimer = scheduler.schedule(this::processVoteResult, 2, TimeUnit.SECONDS);
        }
    }

    private void checkEarlyNightEnd() {
        List<Player> nightActors = players.stream()
                .filter(p -> p.isAlive() && p.getRole().needsNightAction())
                .collect(Collectors.toList());
        
        if (nightActions.size() >= nightActors.size()) {
            if (currentTimer != null) {
                currentTimer.cancel(false);
                currentTimer = null;
            }
            broadcastToAll("NIGHT_EARLY_END:⚡ 모든 능력자가 행동하여 밤이 조기 종료됩니다!");
            currentTimer = scheduler.schedule(this::processNightActions, 2, TimeUnit.SECONDS);
        }
    }

    // ======================== 승리 조건 및 게임 종료 ========================
    
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
        currentPhase = "GAME_END";
        gameEnded = true;
        
        // 타이머 정리
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }

        broadcastToAll("GAME_END:" + winType + ":" + message);
        System.out.println("🏁 " + message);

        // 모든 플레이어의 역할 공개
        broadcastToAll("ROLE_REVEAL:=== 최종 역할 공개 ===");
        players.forEach(player ->
                broadcastToAll("ROLE_REVEAL:" + player.getNickname() + ": " +
                        player.getRole().getColoredName() + " (" +
                        (player.isAlive() ? "생존" : "사망") + ")"));

        broadcastGameStats();
        currentTimer = scheduler.schedule(this::resetGame, 10, TimeUnit.SECONDS);
    }

    private void resetGame() {
        currentPhase = "WAITING";
        gameStarted = false;
        gameEnded = false;
        manualStartRequested = false;
        day = 0;
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }
        
        dayVotes.clear();
        nightActions.clear();
        deadPlayers.clear();
        lastKilledPlayer = "";
        judgeUsed = false;
        judgeDecision = "";
        currentJudge = null;
        currentHunter = null;
        waitingForJudge = false;
        waitingForHunter = false;

        for (Player player : players) {
            player.setRole(null);
            player.setAlive(true);
            player.resetNightStatus();
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
        
        // 팀별 통계
        long citizenWins = players.stream()
                .filter(p -> p.getRole().getTeam() == Role.Team.CITIZEN && p.isAlive())
                .count();
        long mafiaWins = players.stream()
                .filter(p -> p.getRole().getTeam() == Role.Team.MAFIA && p.isAlive())
                .count();
        
        broadcastToAll("GAME_STATS:생존 시민: " + citizenWins + "명");
        broadcastToAll("GAME_STATS:생존 마피아: " + mafiaWins + "명");
    }

    // ======================== 관리자 제어 기능 ========================
    
    public String getCurrentPhase() { 
        return currentPhase; 
    }
    
    public int getCurrentDay() { 
        return day; 
    }
    
    public String getPhaseInfo() {
        switch (currentPhase) {
            case "DAY_DISCUSSION": return "토론 시간 진행 중";
            case "DAY_VOTE": return "투표 진행 중 (" + dayVotes.size() + "명 투표 완료)";
            case "NIGHT": return "밤 시간 진행 중 (" + nightActions.size() + "개 행동 완료)";
            case "MEDIUM": return "영매 시간 진행 중";
            case "WAITING_JUDGE": return "판사 결정 대기 중";
            case "WAITING_HUNTER": return "사냥꾼 복수 대기 중";
            default: return "";
        }
    }

    public int getAlivePlayerCount() {
        return (int) players.stream().filter(Player::isAlive).count();
    }

    public int getDeadPlayerCount() {
        return deadPlayers.size();
    }

    public synchronized boolean forceToVotePhase() {
        if (!gameStarted || gameEnded || waitingForJudge || waitingForHunter) {
            return false;
        }
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }
        
        broadcastToAll("SERVER_MSG:📢 관리자가 투표 단계로 이동시켰습니다!");
        startVotePhase();
        return true;
    }

    public synchronized boolean forceToNightPhase() {
        if (!gameStarted || gameEnded || waitingForJudge || waitingForHunter) {
            return false;
        }
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }
        
        broadcastToAll("SERVER_MSG:📢 관리자가 밤 단계로 이동시켰습니다!");
        startNightPhase();
        return true;
    }

    public synchronized boolean skipCurrentPhase() {
        if (!gameStarted || gameEnded || waitingForJudge || waitingForHunter) {
            return false;
        }
        
        if (currentTimer != null) {
            currentTimer.cancel(false);
            currentTimer = null;
        }
        
        broadcastToAll("SERVER_MSG:📢 관리자가 현재 단계를 스킵했습니다!");
        
        switch (currentPhase) {
            case "DAY_DISCUSSION": 
                startVotePhase(); 
                break;
            case "DAY_VOTE": 
                processVoteResult(); 
                break;
            case "NIGHT": 
                processNightActions(); 
                break;
            case "MEDIUM": 
                day++; 
                startDayPhase(); 
                break;
            default: 
                return false;
        }
        return true;
    }

    public void printVoteStatus() {
        if (dayVotes.isEmpty()) {
            System.out.println("📊 아직 투표한 플레이어가 없습니다.");
            return;
        }

        System.out.println("📊 투표 현황:");
        long totalVoters = players.stream().filter(Player::isAlive).count();
        System.out.println("📈 투표 진행률: " + dayVotes.size() + "/" + totalVoters + "명");

        Map<String, Integer> voteCount = new HashMap<>();
        for (String vote : dayVotes.values()) {
            voteCount.put(vote, voteCount.getOrDefault(vote, 0) + 1);
        }

        System.out.println("🗳️ 득표 현황:");
        voteCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue() + "표"));

        System.out.println("👥 투표자 목록:");
        dayVotes.forEach((voter, target) -> 
            System.out.println("  " + voter + " → " + target));
    }

    public void printNightActionStatus() {
        if (nightActions.isEmpty()) {
            System.out.println("🌙 아직 밤 행동을 한 플레이어가 없습니다.");
            return;
        }

        List<Player> nightActors = players.stream()
                .filter(p -> p.isAlive() && p.getRole().needsNightAction())
                .collect(Collectors.toList());

        System.out.println("🌙 밤 행동 현황:");
        System.out.println("📈 행동 진행률: " + nightActions.size() + "/" + nightActors.size() + "명");

        System.out.println("🌟 행동 목록:");
        nightActions.forEach((actor, target) -> {
            Player actorPlayer = findPlayerByNickname(actor);
            String roleName = actorPlayer != null ? actorPlayer.getRole().getKoreanName() : "알 수 없음";
            System.out.println("  " + actor + "(" + roleName + ") → " + target);
        });

        System.out.println("⏳ 대기 중인 능력자:");
        nightActors.stream()
                .filter(p -> !nightActions.containsKey(p.getNickname()))
                .forEach(p -> System.out.println("  " + p.getNickname() + "(" + p.getRole().getKoreanName() + ")"));
    }

    public synchronized void forceStopGame() {
        if (!gameStarted || gameEnded) return;
        
        gameEnded = true;
        broadcastToAll("GAME_END:ADMIN_STOP:관리자가 게임을 종료했습니다.");
        currentTimer = scheduler.schedule(this::resetGame, 3, TimeUnit.SECONDS);
        System.out.println("🛑 관리자 명령으로 게임 강제 종료됨");
    }

    // ======================== 유틸리티 메서드 ========================
    
    private Player findPlayerByNickname(String nickname) {
        return players.stream()
                .filter(p -> p.getNickname().equals(nickname))
                .findFirst()
                .orElse(null);
    }
    
    private String findMostVotedPlayer() {
        Map<String, Integer> voteCount = new HashMap<>();
        for (String vote : dayVotes.values()) {
            voteCount.put(vote, voteCount.getOrDefault(vote, 0) + 1);
        }
        
        return voteCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
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

    private void broadcastToMafia(String message) {
        for (Player player : players) {
            if (player.isConnected() && player.getRole() != null && 
                player.getRole().getTeam() == Role.Team.MAFIA) {
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

    // ======================== 서버 관리 메서드 ========================
    
    public boolean isGameStarted() {
        return gameStarted;
    }

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
        if (players.isEmpty()) {
            System.out.println("  📭 플레이어가 없습니다.");
            return;
        }
        
        for (Player player : players) {
            String role = gameStarted && player.getRole() != null ?
                    " (" + player.getRole().getKoreanName() + ")" : "";
            String status = player.isAlive() ? "🟢" : "💀";
            String connection = player.isConnected() ? "🔗" : "❌";
            System.out.println("  " + status + connection + " " + player.getNickname() + role);
        }
    }

    public void broadcastServerMessage(String message) {
        broadcastToAll("SERVER_MSG:" + message);
    }

    // ======================== 디버그 및 테스트 메서드 ========================
    
    public void printGameState() {
        System.out.println("=== 게임 상태 디버그 ===");
        System.out.println("게임 시작: " + gameStarted);
        System.out.println("게임 종료: " + gameEnded);
        System.out.println("현재 단계: " + currentPhase);
        System.out.println("현재 일차: " + day + "일차");
        System.out.println("플레이어 수: " + players.size() + "명");
        System.out.println("생존자 수: " + getAlivePlayerCount() + "명");
        System.out.println("사망자 수: " + getDeadPlayerCount() + "명");
        System.out.println("투표 수: " + dayVotes.size() + "개");
        System.out.println("밤 행동 수: " + nightActions.size() + "개");
        System.out.println("판사 사용: " + judgeUsed);
        System.out.println("판사 대기: " + waitingForJudge);
        System.out.println("사냥꾼 대기: " + waitingForHunter);
        System.out.println("=====================");
    }

    public Map<String, Object> getGameStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("gameStarted", gameStarted);
        stats.put("currentPhase", currentPhase);
        stats.put("day", day);
        stats.put("playerCount", players.size());
        stats.put("aliveCount", getAlivePlayerCount());
        stats.put("deadCount", getDeadPlayerCount());
        stats.put("voteCount", dayVotes.size());
        stats.put("nightActionCount", nightActions.size());
        
        // 역할별 통계
        Map<Role, Long> roleStats = players.stream()
                .filter(p -> p.getRole() != null)
                .collect(Collectors.groupingBy(Player::getRole, Collectors.counting()));
        stats.put("roleDistribution", roleStats);
        
        return stats;
    }
}
