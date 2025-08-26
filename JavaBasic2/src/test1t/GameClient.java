package test1t;

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
            System.out.println("🎭 마피아42 게임 클라이언트 v4.0");
            System.out.println("🔗 서버 연결 중... (" + SERVER_HOST + ":" + SERVER_PORT + ")");
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            
            socket.setKeepAlive(true);
            socket.setSoTimeout(300000);
            connected = true;
            System.out.println("✅ 서버 연결 성공!");

            Thread messageReceiver = new Thread(this::receiveMessages);
            messageReceiver.setDaemon(true);
            messageReceiver.start();

            handleUserInput();
        } catch (IOException e) {
            System.err.println("❌ 서버 연결 실패: " + e.getMessage());
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
            case "PLAYER_JOIN":
                System.out.println("👋 " + content);
                break;
            case "PLAYER_LEAVE":
                System.out.println("👋 " + content);
                break;
            case "SERVER_MSG":
                System.out.println("📢 " + content);
                break;
            case "GAME_START":
                System.out.println("🎮 " + content);
                break;
            case "ROLE":
                String[] roleInfo = content.split(":");
                if (roleInfo.length >= 2) {
                    try {
                        currentRole = Role.valueOf(roleInfo[1]);
                        System.out.println("🎭 당신의 역할: " + roleInfo[0]);
                    } catch (IllegalArgumentException e) {
                        System.out.println("🎭 당신의 역할: " + content);
                    }
                }
                break;
            case "ROLE_DESC":
                System.out.println("📋 " + content);
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
            case "DEATH_ANNOUNCEMENT":
                System.out.println("💀 " + content);
                break;
            case "GAME_STATUS":
                System.out.println("📊 " + content);
                break;
            case "VOTE_START":
                System.out.println("\n🗳️ " + content);
                break;
            case "VOTE_INFO":
                System.out.println("ℹ️ " + content);
                break;
            case "VOTE_PROGRESS":
                System.out.println("📊 " + content);
                break;
            case "VOTE_EARLY_END":
                System.out.println("⚡ " + content);
                break;
            case "VOTABLE_PLAYERS":
                System.out.println("📋 투표 가능: " + content);
                break;
            case "VOTE_CAST":
                System.out.println("📊 " + content);
                break;
            case "VOTE_CHANGED":
                System.out.println("🔄 " + content);
                break;
            case "VOTE_RESULT":
                System.out.println("📊 " + content);
                break;
            case "JUDGE_NOTICE":
                System.out.println("⚖️ " + content);
                break;
            case "EXECUTION":
                System.out.println("⚱️ " + content);
                break;
            case "HUNTER_REVENGE":
                System.out.println("🏹 " + content);
                break;
            case "NIGHT_START":
                System.out.println("\n🌙 " + content);
                break;
            case "NIGHT_INFO":
                System.out.println("ℹ️ " + content);
                break;
            case "NIGHT_END":
                System.out.println("🌅 " + content);
                break;
            case "NIGHT_ACTION":
                System.out.println("🌟 " + content);
                break;
            case "NIGHT_PROGRESS":
                System.out.println("🌙 " + content);
                break;
            case "NIGHT_EARLY_END":
                System.out.println("⚡ " + content);
                break;
            case "TARGET_LIST":
                System.out.println("🎯 대상: " + content);
                break;
            case "ACTION_CONFIRM":
                System.out.println("✅ " + content);
                break;
            case "POLICE_RESULT":
                System.out.println("👮 " + content);
                break;
            case "DETECTIVE_RESULT":
                System.out.println("🕵️ " + content);
                break;
            case "SPY_RESULT":
                System.out.println("👁️ " + content);
                break;
            case "JOURNALIST_RESULT":
                System.out.println("📰 " + content);
                break;
            case "DOCTOR_RESULT":
                System.out.println("⚕️ " + content);
                break;
            case "SOLDIER_SHIELD":
                System.out.println("🛡️ " + content);
                break;
            case "DOCTOR_SAVE":
                System.out.println("⚕️ " + content);
                break;
            case "JUDGE_POWER":
                System.out.println("⚖️ " + content);
                System.out.println("💡 /judge yes 또는 /judge no 로 선고하세요!");
                break;
            case "JUDGE_DECISION":
                System.out.println("⚖️ " + content);
                break;
            case "JUDGE_REVEALED":
                System.out.println("⚖️ " + content);
                break;
            case "HUNTER_TARGET":
                System.out.println("🏹 " + content);
                System.out.println("💡 /hunter [플레이어명] 으로 복수하세요!");
                break;
            case "HUNTER_KILL":
                System.out.println("🏹 " + content);
                break;
            case "THIEF_SUCCESS":
                System.out.println("🥷 " + content);
                break;
            case "MEDIUM_START":
                System.out.println("🔮 " + content);
                break;
            case "MEDIUM_INFO":
                System.out.println("🔮 " + content);
                break;
            case "MEDIUM_GUIDE":
                System.out.println("🔮 " + content);
                break;
            case "MEDIUM_MESSAGE":
                System.out.println("🔮 " + content);
                break;
            case "MEDIUM_SENT":
                System.out.println("🔮 " + content);
                break;
            case "DEAD_INFO":
                System.out.println("💀 " + content);
                break;
            case "DEAD_TO_MEDIUM":
                System.out.println("💀 " + content);
                break;
            case "DEAD_CHAT":
                System.out.println("👻 " + content);
                break;
            case "DEAD_SENT":
                System.out.println("👻 " + content);
                break;
            case "MAFIA_CHAT":
                System.out.println("🔴 " + content);
                break;
            case "CHAT":
                System.out.println("💬 " + content);
                break;
            case "DEATH":
                isAlive = false;
                System.out.println("💀 당신이 " + content + "로 사망했습니다.");
                break;
            case "GAME_END":
                String[] endInfo = content.split(":", 2);
                if (endInfo.length >= 2) {
                    System.out.println("\n🏆 " + endInfo[1]);
                }
                break;
            case "ROLE_REVEAL":
                System.out.println("🎭 " + content);
                break;
            case "GAME_STATS":
                System.out.println("📊 " + content);
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
                    sendMessage(input);
                } else {
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
                
            case "/judge":
                if (parts.length > 1 && currentRole == Role.JUDGE && isAlive) {
                    sendMessage("JUDGE:" + parts[1]);
                    System.out.println("⚖️ 판사 선고: " + parts[1]);
                } else {
                    System.out.println("사용법: /judge yes|no (판사만)");
                }
                break;
                
            case "/hunter":
                if (parts.length > 1 && currentRole == Role.HUNTER && !isAlive) {
                    sendMessage("HUNTER:" + parts[1]);
                    System.out.println("🏹 사냥꾼 복수: " + parts[1]);
                } else {
                    System.out.println("사용법: /hunter [플레이어명] (죽은 사냥꾼만)");
                }
                break;
                
            case "/steal":
                if (parts.length > 1 && currentRole == Role.THIEF && isAlive) {
                    sendMessage("THIEF_STEAL:" + parts[1]);
                    System.out.println("🥷 도둑 능력 훔치기: " + parts[1]);
                } else {
                    System.out.println("사용법: /steal [플레이어명] (도둑만)");
                }
                break;
                
            case "/medium":
                if (parts.length > 1 && currentRole == Role.MEDIUM && isAlive) {
                    sendMessage("MEDIUM:" + parts[1]);
                    System.out.println("🔮 죽은 자들에게: " + parts[1]);
                } else {
                    System.out.println("사용법: /medium [메시지] (영매만)");
                }
                break;
                
            case "/dead":
                if (parts.length > 1 && !isAlive) {
                    sendMessage("DEAD:" + parts[1]);
                    System.out.println("👻 죽은 자 채팅: " + parts[1]);
                } else {
                    System.out.println("사용법: /dead [메시지] (죽은 자만)");
                }
                break;
                
            case "/mafia":
                if (parts.length > 1 && isAlive && currentRole != null && currentRole.getTeam() == Role.Team.MAFIA) {
                    sendMessage("MAFIA_CHAT:" + parts[1]);
                    System.out.println("🔴 마피아 채팅: " + parts[1]);
                } else {
                    System.out.println("사용법: /mafia [메시지] (마피아만)");
                }
                break;
                
            case "/help":
            case "/도움말":
                printHelp();
                break;
                
            case "/quit":
            case "/종료":
                disconnect();
                break;
                
            default:
                System.out.println("❓ 알 수 없는 명령어: " + cmd);
                System.out.println("💡 /help 명령어로 도움말을 확인하세요!");
        }
    }

    private void printGameCommands() {
        System.out.println("\n📋 === 게임 명령어 ===");
        System.out.println("💬 채팅: [메시지] 입력");
        System.out.println("🗳️ 투표: /vote [플레이어명]");
        System.out.println("🌙 밤행동: /night [플레이어명]");
        System.out.println("⚖️ 판사: /judge yes|no");
        System.out.println("🏹 사냥꾼: /hunter [플레이어명]");
        System.out.println("🥷 도둑: /steal [플레이어명]");
        System.out.println("🔮 영매: /medium [메시지]");
        System.out.println("👻 죽은자: /dead [메시지]");
        System.out.println("🔴 마피아: /mafia [메시지]");
        System.out.println("❓ 도움말: /help");
        System.out.println("🚪 종료: /quit");
        System.out.println("===================\n");
    }

    private void printHelp() {
        System.out.println("\n📋 === 마피아42 게임 도움말 ===");
        System.out.println("💬 일반 채팅: 그냥 메시지 입력");
        System.out.println("🗳️ /vote [플레이어명] - 투표하기");
        System.out.println("🌙 /night [플레이어명] - 밤 능력 사용");
        System.out.println("⚖️ /judge yes|no - 판사 선고 (판사만)");
        System.out.println("🏹 /hunter [플레이어명] - 사냥꾼 복수 (죽은 사냥꾼만)");
        System.out.println("🥷 /steal [플레이어명] - 도둑 능력 훔치기 (도둑만)");
        System.out.println("🔮 /medium [메시지] - 영매 메시지 (영매만)");
        System.out.println("👻 /dead [메시지] - 죽은 자 채팅 (죽은 자만)");
        System.out.println("🔴 /mafia [메시지] - 마피아 전용 채팅 (마피아만)");
        System.out.println("❓ /help - 이 도움말 보기");
        System.out.println("🚪 /quit - 게임 종료");
        System.out.println("================================");
        
        if (currentRole != null) {
            System.out.println("🎭 현재 역할: " + currentRole.getColoredName());
            System.out.println("📖 역할 설명: " + currentRole.getDescription());
            System.out.println("⚡ 능력 설명: " + currentRole.getAbilityDescription());
        }
        System.out.println();
    }

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
