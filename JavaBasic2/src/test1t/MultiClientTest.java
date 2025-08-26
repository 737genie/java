package test1t;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientTest {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8888;
    private static final int CLIENT_COUNT = 14;
    
    public static void main(String[] args) {
        System.out.println("🧪 === 14명 동시 접속 테스트 시작 ===");
        
        ExecutorService executor = Executors.newFixedThreadPool(CLIENT_COUNT);
        List<TestBot> bots = new ArrayList<>();
        
        // 14명의 봇 생성 및 접속
        for (int i = 1; i <= CLIENT_COUNT; i++) {
            TestBot bot = new TestBot("플레이어" + i, i);
            bots.add(bot);
            executor.submit(bot);
            
            try {
                Thread.sleep(500); // 0.5초 간격으로 접속
            } catch (InterruptedException e) {
                break;
            }
        }
        
        System.out.println("✅ 모든 봇이 접속을 시도했습니다.");
        System.out.println("💡 서버 콘솔에서 'start' 명령어로 게임을 시작하세요!");
        
        // 봇들의 상태 모니터링
        executor.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(10000); // 10초마다
                    long activeBots = bots.stream().filter(TestBot::isActive).count();
                    System.out.println("📊 활성 봇: " + activeBots + "/" + CLIENT_COUNT);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }
    
    static class TestBot implements Runnable {
        private String name;
        private int id;
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private Random random = new Random();
        private boolean active = false;
        
        public TestBot(String name, int id) {
            this.name = name;
            this.id = id;
        }
        
        public boolean isActive() {
            return active;
        }
        
        @Override
        public void run() {
            try {
                // 서버 연결
                socket = new Socket(SERVER_IP, SERVER_PORT);
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                
                active = true;
                System.out.println("🔗 " + name + " 연결 성공");
                
                // 메시지 수신 스레드
                Thread receiver = new Thread(this::receiveMessages);
                receiver.setDaemon(true);
                receiver.start();
                
                // 닉네임 전송 (자동 응답)
                Thread.sleep(1000);
                out.println(name);
                
                // 봇 행동 시뮬레이션
                simulateGameplay();
                
            } catch (Exception e) {
                System.err.println("❌ " + name + " 오류: " + e.getMessage());
                active = false;
            }
        }
        
        private void receiveMessages() {
            try {
                String message;
                while (active && (message = in.readLine()) != null) {
                    System.out.println("[" + name + "] 📨 " + message);
                    
                    // 특정 메시지에 자동 응답
                    if (message.contains("VOTE_START")) {
                        // 3-7초 후 랜덤 투표
                        scheduleAction(() -> {
                            String target = "플레이어" + (random.nextInt(CLIENT_COUNT) + 1);
                            if (!target.equals(name)) {
                                out.println("VOTE:" + target);
                                System.out.println("[" + name + "] 🗳️ 투표: " + target);
                            }
                        }, 3000 + random.nextInt(4000));
                        
                    } else if (message.contains("NIGHT_ACTION")) {
                        // 5-10초 후 랜덤 밤 행동
                        scheduleAction(() -> {
                            String target = "플레이어" + (random.nextInt(CLIENT_COUNT) + 1);
                            if (!target.equals(name)) {
                                out.println("NIGHT:" + target);
                                System.out.println("[" + name + "] 🌙 밤행동: " + target);
                            }
                        }, 5000 + random.nextInt(5000));
                        
                    } else if (message.contains("JUDGE_POWER")) {
                        // 판사 결정
                        scheduleAction(() -> {
                            String decision = random.nextBoolean() ? "yes" : "no";
                            out.println("JUDGE:" + decision);
                            System.out.println("[" + name + "] ⚖️ 판사 결정: " + decision);
                        }, 2000 + random.nextInt(3000));
                        
                    } else if (message.contains("HUNTER_TARGET")) {
                        // 사냥꾼 복수
                        scheduleAction(() -> {
                            String target = "플레이어" + (random.nextInt(CLIENT_COUNT) + 1);
                            if (!target.equals(name)) {
                                out.println("HUNTER:" + target);
                                System.out.println("[" + name + "] 🏹 사냥꾼 복수: " + target);
                            }
                        }, 1000 + random.nextInt(2000));
                        
                    } else if (message.contains("MAFIA_MEMBERS")) {
                        // 마피아 전용 채팅 테스트
                        scheduleAction(() -> {
                            String[] mafiaChats = {
                                "동료들, 누구를 노려야 할까요?",
                                "경찰이 누구인지 찾아야 해요",
                                "의사를 먼저 제거합시다",
                                "조심스럽게 행동해요"
                            };
                            String chat = mafiaChats[random.nextInt(mafiaChats.length)];
                            out.println("MAFIA_CHAT:" + chat);
                            System.out.println("[" + name + "] 🔴 마피아채팅: " + chat);
                        }, 5000 + random.nextInt(10000));
                        
                    } else if (message.contains("DEAD_CHAT") && message.contains("영매와 대화")) {
                        // 죽은 자 채팅
                        scheduleAction(() -> {
                            String[] deadChats = {
                                "마피아가 저를 죽였어요!",
                                "경찰을 조심하세요",
                                "판사가 누군지 알아요",
                                "의심스러운 사람이 있었어요"
                            };
                            String chat = deadChats[random.nextInt(deadChats.length)];
                            out.println("DEAD:" + chat);
                            System.out.println("[" + name + "] 👻 죽은자채팅: " + chat);
                        }, 2000 + random.nextInt(5000));
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ " + name + " 수신 오류: " + e.getMessage());
                active = false;
            }
        }
        
        private void scheduleAction(Runnable action, int delay) {
            new Thread(() -> {
                try {
                    Thread.sleep(delay);
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        private void simulateGameplay() throws InterruptedException {
            // 주기적으로 채팅 메시지 전송
            String[] chatMessages = {
                "안녕하세요!", "의심스러운 사람이 있네요", "증거가 있나요?",
                "조심스럽게 투표합시다", "누가 마피아일까요?", "정보를 공유해요",
                "밤에 무슨 일이 있었나요?", "투표 신중하게 하세요"
            };
            
            while (active) {
                Thread.sleep(15000 + random.nextInt(30000)); // 15-45초 간격
                
                if (random.nextDouble() < 0.3) { // 30% 확률로 채팅
                    String chat = chatMessages[random.nextInt(chatMessages.length)];
                    out.println("CHAT:" + chat);
                    System.out.println("[" + name + "] 💬 " + chat);
                }
            }
        }
    }
}
