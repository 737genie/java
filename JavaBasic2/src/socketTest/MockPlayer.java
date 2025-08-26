package socketTest;

import java.util.List;
import java.util.Random;

public class MockPlayer extends Player {
    private String simulatedInput;
    private StringBuilder receivedMessages;
    private Random random;
    
    public MockPlayer(String nickname, Role role) {
        super();
        setNickname(nickname);
        setRole(role);
        this.receivedMessages = new StringBuilder();
        this.random = new Random();
    }
    
    @Override
    public void sendMessage(String message) {
        receivedMessages.append("[받음] ").append(message).append("\n");
        System.out.println("[" + getNickname() + " 📥] " + message);
    }
    
    @Override
    public String receiveMessage() {
        return simulatedInput;
    }
    
    public void simulateInput(String input) {
        this.simulatedInput = input;
        System.out.println("[" + getNickname() + " 📤] " + input);
    }
    
    public String getReceivedMessages() {
        return receivedMessages.toString();
    }
    
    public void clearReceivedMessages() {
        receivedMessages.setLength(0);
    }
    
    @Override
    public void closeConnection() {
        System.out.println("[" + getNickname() + " 🔌] MockPlayer 연결 종료");
    }
    
    // 🎭 테스트용 시뮬레이션 메소드들
    public void simulateDeath(String cause) {
        die(cause);
        System.out.println("[테스트 💀] " + getNickname() + "이(가) " + cause + "에 의해 사망");
    }
    
    public void simulateVote(String target) {
        simulateInput("VOTE:" + target);
        System.out.println("[테스트 🗳️] " + getNickname() + " → " + target + " 투표");
    }
    
    public void simulateChat(String message) {
        simulateInput("CHAT:" + message);
        System.out.println("[테스트 💬] " + getNickname() + ": " + message);
    }
    
    public void simulateNightAction(String target) {
        simulateInput("NIGHT:" + target);
        System.out.println("[테스트 🌙] " + getNickname() + " → " + target + " 밤 행동");
    }
    
    public void simulateMediumContact(String message) {
        if (getRole() == Role.MEDIUM) {
            simulateInput("MEDIUM:" + message);
            System.out.println("[테스트 🔮] 영매 " + getNickname() + " → 죽은 자들: " + message);
        }
    }
    
    public void simulateDeadChat(String message) {
        if (!isAlive()) {
            simulateInput("DEAD:" + message);
            System.out.println("[테스트 👻] 죽은 " + getNickname() + ": " + message);
        }
    }
    
    public void simulateJudgeDecision(boolean approve) {
        if (getRole() == Role.JUDGE) {
            String decision = approve ? "yes" : "no";
            simulateInput("JUDGE:" + decision);
            System.out.println("[테스트 ⚖️] 판사 " + getNickname() + " 선고: " + (approve ? "승인" : "거부"));
        }
    }
    
    public void simulateHunterRevenge(String target) {
        if (getRole() == Role.HUNTER && !isAlive()) {
            simulateInput("HUNTER:" + target);
            System.out.println("[테스트 🏹] 사냥꾼 " + getNickname() + " 복수 → " + target);
        }
    }
    
    public void simulateThiefSteal(String target) {
        if (getRole() == Role.THIEF) {
            simulateInput("THIEF_STEAL:" + target);
            System.out.println("[테스트 🥷] 도둑 " + getNickname() + " → " + target + " 능력 훔치기");
        }
    }
    
    // 🤖 AI 행동 시뮬레이션 (랜덤)
    public void simulateRandomBehavior(List<String> availableTargets) {
        if (availableTargets.isEmpty()) return;
        
        String randomTarget = availableTargets.get(random.nextInt(availableTargets.size()));
        
        switch (getRole()) {
            case MAFIA:
            case MADAM:
                if (random.nextDouble() < 0.8) { // 80% 확률로 공격
                    simulateNightAction(randomTarget);
                }
                break;
                
            case DOCTOR:
                if (random.nextDouble() < 0.7) { // 70% 확률로 보호
                    simulateNightAction(randomTarget);
                }
                break;
                
            case POLICE:
            case DETECTIVE:
                if (random.nextDouble() < 0.9) { // 90% 확률로 조사
                    simulateNightAction(randomTarget);
                }
                break;
                
            case SPY:
                if (random.nextDouble() < 0.6) { // 60% 확률로 감시
                    simulateNightAction(randomTarget);
                }
                break;
                
            case MEDIUM:
                if (random.nextDouble() < 0.4) { // 40% 확률로 영매 대화
                    simulateMediumContact("누가 당신을 죽였나요?");
                }
                break;
        }
        
        // 랜덤 채팅
        if (random.nextDouble() < 0.3) { // 30% 확률로 채팅
            String[] randomChats = {
                "의심스러운 사람이 있네요...",
                "누가 마피아일까요?",
                "조심스럽게 행동해야겠어요",
                "증거를 찾아봅시다",
                "투표는 신중하게..."
            };
            simulateChat(randomChats[random.nextInt(randomChats.length)]);
        }
    }
    
    // 🧪 테스트 시나리오들
    public static void runBasicTest() {
        System.out.println("🧪 === MockPlayer 기본 테스트 시작 ===\n");
        
        // 다양한 직업 테스트 플레이어들 생성
        MockPlayer medium = new MockPlayer("영매테스터", Role.MEDIUM);
        MockPlayer mafia = new MockPlayer("마피아테스터", Role.MAFIA);
        MockPlayer police = new MockPlayer("경찰테스터", Role.POLICE);
        MockPlayer doctor = new MockPlayer("의사테스터", Role.DOCTOR);
        MockPlayer judge = new MockPlayer("판사테스터", Role.JUDGE);
        MockPlayer hunter = new MockPlayer("사냥꾼테스터", Role.HUNTER);
        MockPlayer spy = new MockPlayer("스파이테스터", Role.SPY);
        MockPlayer thief = new MockPlayer("도둑테스터", Role.THIEF);
        MockPlayer citizen = new MockPlayer("시민테스터", Role.CITIZEN);
        
        System.out.println("1️⃣ 플레이어 생성 완료:");
        MockPlayer[] players = {medium, mafia, police, doctor, judge, hunter, spy, thief, citizen};
        for (MockPlayer player : players) {
            System.out.println("   🎭 " + player.getNickname() + " (" + player.getRole().getColoredName() + ")");
        }
        
        // 채팅 테스트
        System.out.println("\n2️⃣ 채팅 테스트:");
        citizen.simulateChat("안녕하세요 여러분!");
        mafia.simulateChat("누가 의심스러운지 찾아봅시다...");
        police.simulateChat("증거를 모아야겠어요.");
        
        // 투표 테스트
        System.out.println("\n3️⃣ 투표 테스트:");
        citizen.simulateVote("마피아테스터");
        police.simulateVote("마피아테스터");
        doctor.simulateVote("스파이테스터");
        
        // 밤 행동 테스트
        System.out.println("\n4️⃣ 밤 행동 테스트:");
        mafia.simulateNightAction("경찰테스터");
        doctor.simulateNightAction("경찰테스터");
        police.simulateNightAction("마피아테스터");
        spy.simulateNightAction("의사테스터");
        
        // 죽음 시뮬레이션
        System.out.println("\n5️⃣ 죽음 시뮬레이션:");
        citizen.simulateDeath("마피아");
        hunter.simulateDeath("투표");
        
        // 특수 능력 테스트
        System.out.println("\n6️⃣ 특수 능력 테스트:");
        medium.simulateMediumContact("누가 당신을 죽였나요?");
        citizen.simulateDeadChat("마피아가 저를 죽였어요!");
        judge.simulateJudgeDecision(false);
        hunter.simulateHunterRevenge("마피아테스터");
        thief.simulateThiefSteal("의사테스터");
        
        System.out.println("\n✅ === MockPlayer 기본 테스트 완료 ===");
    }
    
    public static void runRandomSimulation(int playerCount) {
        System.out.println("🎲 === " + playerCount + "명 랜덤 시뮬레이션 시작 ===\n");
        
        // 역할 배정
        List<Role> roles = JobManager.assignRoles(playerCount);
        MockPlayer[] players = new MockPlayer[playerCount];
        
        for (int i = 0; i < playerCount; i++) {
            players[i] = new MockPlayer("플레이어" + (i + 1), roles.get(i));
        }
        
        System.out.println("🎭 플레이어 구성:");
        for (MockPlayer player : players) {
            System.out.println("   " + player.getNickname() + ": " + player.getRole().getColoredName());
        }
        
        // 5턴 시뮬레이션
        for (int turn = 1; turn <= 5; turn++) {
            System.out.println("\n🔄 === " + turn + "턴 시뮬레이션 ===");
            
            // 살아있는 플레이어들
            List<String> aliveNames = new java.util.ArrayList<>();
            for (MockPlayer player : players) {
                if (player.isAlive()) {
                    aliveNames.add(player.getNickname());
                }
            }
            
            if (aliveNames.size() <= 2) {
                System.out.println("⚠️ 게임 종료 조건 도달");
                break;
            }
            
            // 각 플레이어 랜덤 행동
            for (MockPlayer player : players) {
                if (player.isAlive()) {
                    List<String> targets = new java.util.ArrayList<>(aliveNames);
                    targets.remove(player.getNickname()); // 자신 제외
                    player.simulateRandomBehavior(targets);
                }
            }
            
            // 랜덤하게 1-2명 죽이기
            Random random = new Random();
            if (random.nextDouble() < 0.6 && aliveNames.size() > 3) {
                MockPlayer victim = players[random.nextInt(players.length)];
                if (victim.isAlive()) {
                    victim.simulateDeath("마피아");
                }
            }
        }
        
        // 최종 결과
        System.out.println("\n📊 === 최종 결과 ===");
        long aliveCitizens = 0, aliveMafia = 0;
        for (MockPlayer player : players) {
            String status = player.isAlive() ? "🟢 생존" : "💀 사망";
            System.out.println("   " + player.getNickname() + ": " + 
                             player.getRole().getColoredName() + " " + status);
            if (player.isAlive()) {
                if (player.getRole().getTeam() == Role.Team.CITIZEN) aliveCitizens++;
                else aliveMafia++;
            }
        }
        
        System.out.println("\n🏆 결과: 시민팀 " + aliveCitizens + "명, 마피아팀 " + aliveMafia + "명");
        if (aliveMafia == 0) {
            System.out.println("🎉 시민팀 승리!");
        } else if (aliveMafia >= aliveCitizens) {
            System.out.println("🎉 마피아팀 승리!");
        } else {
            System.out.println("🤝 게임 진행 중...");
        }
        
        System.out.println("✅ === 랜덤 시뮬레이션 완료 ===");
    }
    
    public static void main(String[] args) {
        System.out.println("🎮 MockPlayer 테스트 프로그램 시작!\n");
        
        // 기본 테스트
        runBasicTest();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 대규모 시뮬레이션 테스트
        runRandomSimulation(14);
        
        System.out.println("\n🎯 모든 테스트가 완료되었습니다!");
        System.out.println("실제 게임서버 테스트를 원하시면 GameServer.java를 실행하세요.");
    }
}