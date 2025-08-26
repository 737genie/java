package test1t;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestGameClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8888;
    private String playerName;
    private boolean isBot;
    private Random random = new Random();

    public TestGameClient(String playerName, boolean isBot) {
        this.playerName = playerName;
        this.isBot = isBot;
    }

    public void connect() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("🔗 " + playerName + " 서버 연결 성공!");
            
            // 메시지 수신 스레드
            Thread receiver = new Thread(() -> receiveMessages(in));
            receiver.setDaemon(true);
            receiver.start();
            
            // 닉네임 전송
            Thread.sleep(1000);
            out.println(playerName);
            
            if (isBot) {
                runBot(out);
            } else {
                runManual(out, scanner);
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("[" + playerName + "] 연결 오류: " + e.getMessage());
        }
    }

    private void receiveMessages(BufferedReader in) {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[" + playerName + "] 📨 " + message);
                
                // 봇 자동 응답
                if (isBot) {
                    handleBotResponse(message);
                }
            }
        } catch (IOException e) {
            System.err.println("[" + playerName + "] 연결 종료");
        }
    }

    private void handleBotResponse(String message) {
        // 봇의 자동 응답 로직은 별도 스레드에서 처리
    }

    private void runBot(PrintWriter out) {
        System.out.println("[" + playerName + "] 봇 모드로 실행");
        
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000 + random.nextInt(10000)); // 5-15초 간격
                    
                    // 랜덤 채팅
                    if (random.nextDouble() < 0.3) {
                        String[] chats = {
                            "안녕하세요!", "의심스러운 사람이 있나요?", 
                            "증거가 있나요?", "조심스럽게 투표합시다",
                            "누가 마피아일까요?", "정보를 공유해요"
                        };
                        String chat = chats[random.nextInt(chats.length)];
                        out.println("CHAT:" + chat);
                        System.out.println("[" + playerName + "] 💬 " + chat);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void runManual(PrintWriter out, Scanner scanner) {
        System.out.println("[" + playerName + "] 수동 모드 - 명령어를 입력하세요");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            out.println(input);
        }
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            String name = args[0];
            boolean isBot = Boolean.parseBoolean(args[1]);
            new TestGameClient(name, isBot).connect();
        } else {
            // 다중 봇 실행
            System.out.println("🤖 10명의 봇 클라이언트를 생성합니다...");
            List<Thread> botThreads = new ArrayList<>();
            
            for (int i = 1; i <= 10; i++) {
                final int playerId = i;
                Thread botThread = new Thread(() -> {
                    new TestGameClient("봇" + playerId, true).connect();
                });
                botThreads.add(botThread);
                botThread.start();
                
                try {
                    Thread.sleep(1000); // 1초 간격으로 접속
                } catch (InterruptedException e) {
                    break;
                }
            }
            
            System.out.println("✅ 모든 봇이 접속을 시도했습니다!");
            System.out.println("💡 서버에서 'start' 명령어로 게임을 시작하세요!");
        }
    }
}
