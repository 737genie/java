package socketTest;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    private static final String SERVER_HOST = "192.168.1.41";
    private static final int SERVER_PORT = 8889;
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private boolean connected = false;
    private String nickname = "";
    private Role currentRole = null;
    private boolean isAlive = true;
    private boolean nicknamePhase = true;
    
    public GameClient() {
        scanner = new Scanner(System.in);
    }
    
    public void connect() {
        try {
            System.out.println("🎭 마피아42 게임 클라이언트 v2.5");
            System.out.println("🔗 서버 연결 중... (" + SERVER_HOST + ":" + SERVER_PORT + ")");
            
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            
            // 📌 안정성 개선: BufferedReader/PrintWriter 사용
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            
            // 연결 안정성 설정
            socket.setKeepAlive(true);
            socket.setSoTimeout(300000); // 5분 타임아웃
            
            connected = true;
            
            System.out.println("✅ 서버 연결 성공!");
            
            // 메시지 수신 스레드 시작
            Thread messageReceiver = new Thread(this::receiveMessages);
            messageReceiver.setDaemon(true);
            messageReceiver.start();
            
            // 사용자 입력 처리
            handleUserInput();
            
        } catch (IOException e) {
            System.err.println("❌ 서버 연결 실패: " + e.getMessage());
            System.out.println("서버가 실행 중인지 확인해주세요.");
        }
    }
    
    private void receiveMessages() {
        try {
            String message;
            while (connected && (message = in.readLine()) != null) {
                processServerMessage(message);
            }
        } catch (IOException e) {
            if (connected) {
                System.err.println("⚠️ 서버와의 연결이 끊어졌습니다: " + e.getMessage());
                connected = false;
                System.out.println("💡 클라이언트를 재시작해주세요.");
            }
        }
    }
    
    private void processServerMessage(String message) {
        String[] parts = message.split(":", 2);
        if (parts.length < 2) {
            System.out.println(message);
            return;
        }
        
        String messageType = parts[0];
        String content = parts[1];
        
        switch (messageType) {
            case "WELCOME":
                System.out.println("🎭 " + content);
                break;
                
            case "NICKNAME_REQUEST":
                System.out.println("📝 " + content);
                System.out.print(">> 닉네임 입력: ");
                break;
                
            case "JOIN_SUCCESS":
                System.out.println("✅ " + content);
                nicknamePhase = false;
                printGameCommands();
                break;
                
            case "WAITING":
                System.out.println("⏳ " + content);
                break;
                
            case "NOTICE":
                System.out.println("📢 " + content);
                break;
                
            case "SERVER_MSG":
                System.out.println("📢 " + content);
                break;
                
            case "GAME_START":
                System.out.println("🎮 " + content);
                break;
                
            case "ROLE":
                System.out.println("🎭 당신의 역할: " + content);
                break;
                
            case "ROLE_INFO":
                System.out.println("📋 " + content);
                break;
                
            case "ROLE_DESC":
                System.out.println("💭 " + content);
                break;
                
            case "MAFIA_MEMBERS":
                System.out.println("🤝 " + content);
                break;
                
            case "DAY_START":
                System.out.println("\n☀️ " + content);
                break;
                
            case "DAY_INFO":
                System.out.println("ℹ️ " + content);
                break;
                
            case "STATUS":
                System.out.println("📊 " + content);
                break;
                
            case "VOTE_START":
                System.out.println("\n🗳️ " + content);
                break;
                
            case "VOTE_INFO":
                System.out.println("ℹ️ " + content);
                break;
                
            case "VOTE_LIST":
                System.out.println("📋 " + content);
                break;
                
            case "VOTE_CAST":
                System.out.println("📊 " + content);
                break;
                
            case "VOTE_END":
                System.out.println("⏰ " + content);
                break;
                
            case "VOTE_RESULT":
                System.out.println("📊 " + content);
                break;
                
            case "EXECUTION":
                System.out.println("⚱️ " + content);
                break;
                
            case "ROLE_REVEAL":
                System.out.println("🎭 " + content);
                break;
                
            case "NIGHT_START":
                System.out.println("\n🌙 " + content);
                break;
                
            case "NIGHT_ACTION":
                System.out.println("🌟 " + content);
                break;
                
            case "ACTION_CONFIRM":
                System.out.println("✅ " + content);
                break;
                
            case "NIGHT_END":
                System.out.println("🌅 " + content);
                break;
                
            case "DEATH":
                System.out.println("💀 " + content);
                break;
                
            case "SAVE":
                System.out.println("⚕️ " + content);
                break;
                
            case "INVESTIGATION":
                System.out.println("🔍 " + content);
                break;
                
            case "DETECTIVE_RESULT":
                System.out.println("🕵️ " + content);
                break;
                
            case "SPY_RESULT":
                System.out.println("👁️ " + content);
                break;
                
            case "DOCTOR_RESULT":
                System.out.println("⚕️ " + content);
                break;
                
            case "CHAT":
                System.out.println("💬 " + content);
                break;
                
            case "GAME_END":
                String[] endInfo = content.split(":", 2);
                if (endInfo.length >= 2) {
                    System.out.println("\n🏆 " + endInfo[1]);
                }
                break;
                
            case "FINAL_ROLES":
                System.out.println("🎭 " + content);
                break;
                
            case "RESET":
                System.out.println("🔄 " + content);
                nicknamePhase = false;
                isAlive = true;
                currentRole = null;
                break;
                
            case "ERROR":
                System.err.println("❌ 오류: " + content);
                if (nicknamePhase) {
                    System.out.println("3초 후 클라이언트를 종료합니다...");
                    try {
                        Thread.sleep(3000);
                        disconnect();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                break;
                
            default:
                System.out.println(message);
        }
    }
    
    private void handleUserInput() {
        while (connected) {
            try {
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) continue;
                
                if (nicknamePhase) {
                    // 📌 닉네임 입력 단계 - 직접 전송
                    sendMessage(input);
                } else {
                    // 게임 진행 단계
                    if (input.startsWith("/")) {
                        processCommand(input);
                    } else {
                        sendMessage("CHAT:" + input);
                    }
                }
                
            } catch (Exception e) {
                System.err.println("입력 처리 중 오류: " + e.getMessage());
            }
        }
    }
    
    private void processCommand(String command) {
        String[] parts = command.split(" ", 2);
        String cmd = parts[0].toLowerCase();
        
        switch (cmd) {
            case "/vote":
                if (parts.length > 1 && isAlive) {
                    sendMessage("VOTE:" + parts[1]);
                    System.out.println("✅ " + parts[1] + "님에게 투표했습니다.");
                } else {
                    System.out.println("사용법: /vote [플레이어명]");
                }
                break;
                
            case "/night":
                if (parts.length > 1 && isAlive && currentRole != null && currentRole.needsNightAction()) {
                    sendMessage("NIGHT:" + parts[1]);
                    System.out.println("✅ 밤 행동 대상: " + parts[1]);
                } else {
                    System.out.println("사용법: /night [플레이어명] (능력이 있는 직업만)");
                }
                break;
                
            case "/help":
                printHelp();
                break;
                
            case "/quit":
                disconnect();
                break;
                
            default:
                System.out.println("❓ 알 수 없는 명령어. /help로 도움말 확인");
        }
    }
    
    private void printGameCommands() {
        System.out.println("\n📋 === 게임 명령어 ===");
        System.out.println("💬 채팅: [메시지] 입력");
        System.out.println("🗳️ 투표: /vote [플레이어명]");
        System.out.println("🌙 밤행동: /night [플레이어명]");
        System.out.println("❓ 도움말: /help");
        System.out.println("🚪 종료: /quit");
        System.out.println("===================\n");
    }
    
    private void printHelp() {
        System.out.println("\n📋 === 명령어 도움말 ===");
        System.out.println("💬 채팅: 그냥 메시지 입력");
        System.out.println("🗳️ /vote [플레이어명] - 투표");
        System.out.println("🌙 /night [플레이어명] - 밤 능력 사용");
        System.out.println("❓ /help - 도움말");
        System.out.println("🚪 /quit - 게임 종료");
        System.out.println("=======================\n");
        
        if (currentRole != null) {
            System.out.println("🎭 당신의 역할: " + currentRole.getColoredName());
            System.out.println("📖 설명: " + currentRole.getDescription() + "\n");
        }
    }
    
    // 📌 안전한 메시지 전송 (한글 지원)
    private void sendMessage(String message) {
        if (!connected || out == null) return;
        
        try {
            out.println(message);
            out.flush();
        } catch (Exception e) {
            System.err.println("메시지 전송 오류: " + e.getMessage());
            connected = false;
        }
    }
    
    private void disconnect() {
        connected = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("연결 종료 중 오류: " + e.getMessage());
        }
        System.out.println("👋 게임을 종료합니다. 안녕히 가세요!");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.connect();
    }
}