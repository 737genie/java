package socketTest;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class GameServer {
    private static final int PORT = 8889;
    private static final int MAX_PLAYERS = 25;
    
    private ServerSocket serverSocket;
    private GameRoom gameRoom;
    private ExecutorService executorService;
    private AtomicInteger connectedPlayers;
    private boolean serverRunning;
    private Scanner serverConsole;
    
    public GameServer() {
        this.gameRoom = new GameRoom();
        this.executorService = Executors.newFixedThreadPool(MAX_PLAYERS + 3);
        this.connectedPlayers = new AtomicInteger(0);
        this.serverRunning = false;
        this.serverConsole = new Scanner(System.in);
    }
    
    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        serverRunning = true;
        
        System.out.println("🎭 =============================");
        System.out.println("   마피아42 게임 서버 시작!");
        System.out.println("=============================");
        System.out.println("📡 포트: " + PORT);
        System.out.println("👥 최대 플레이어: " + MAX_PLAYERS + "명");
        System.out.println("⚡ 서버 상태: 준비 완료");
        System.out.println();
        
        printServerCommands();
        
        // 📌 서버 콘솔 명령어 처리 스레드
        executorService.submit(this::handleServerConsole);
        
        // GameRoom 스레드 시작
        executorService.submit(gameRoom);
        
        // 서버 모니터링
        executorService.submit(this::monitorServer);
        
        // 클라이언트 접속 처리
        while (serverRunning && !serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                
                if (connectedPlayers.get() >= MAX_PLAYERS) {
                    sendErrorAndClose(clientSocket, "서버가 가득 찼습니다.");
                    continue;
                }
                
                System.out.println("🔗 새로운 클라이언트 접속: " + clientSocket.getInetAddress()
                    + " (현재: " + connectedPlayers.incrementAndGet() + "/" + MAX_PLAYERS + ")");
                
                executorService.submit(() -> handleNewPlayer(clientSocket));
                
            } catch (IOException e) {
                if (serverRunning) {
                    System.err.println("⚠️ 클라이언트 접속 처리 중 오류: " + e.getMessage());
                }
            }
        }
    }
    
    private void printServerCommands() {
        System.out.println("📋 ===== 서버 관리자 명령어 =====");
        System.out.println("🚀 start    - 게임 강제 시작");
        System.out.println("📊 status   - 서버 상태 확인");
        System.out.println("👥 players  - 플레이어 목록");
        System.out.println("📢 say <msg> - 전체 공지");
        System.out.println("🛑 stop     - 게임 강제 종료");
        System.out.println("❌ quit     - 서버 종료");
        System.out.println("==============================");
        System.out.println();
    }
    
    // 📌 서버 콘솔 명령어 처리
    private void handleServerConsole() {
        while (serverRunning) {
            try {
                System.out.print("SERVER> ");
                String input = serverConsole.nextLine().trim();
                
                if (input.isEmpty()) continue;
                
                processServerCommand(input);
                
            } catch (Exception e) {
                System.err.println("콘솔 오류: " + e.getMessage());
            }
        }
    }
    
    private void processServerCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        
        switch (command) {
            case "start":
                handleStartCommand();
                break;
                
            case "status":
                handleStatusCommand();
                break;
                
            case "players":
                handlePlayersCommand();
                break;
                
            case "say":
                if (parts.length > 1) {
                    handleSayCommand(parts[1]);
                } else {
                    System.out.println("❌ 사용법: say <메시지>");
                }
                break;
                
            case "stop":
                handleStopCommand();
                break;
                
            case "quit":
            case "exit":
                handleQuitCommand();
                break;
                
            default:
                System.out.println("❓ 알 수 없는 명령어: " + command);
                printServerCommands();
        }
    }
    
    private void handleStartCommand() {
        System.out.println("🎯 게임 시작 요청 처리 중...");
        
        int playerCount = gameRoom.getPlayerCount();
        boolean gameInProgress = gameRoom.isGameStarted();
        
        System.out.println("📊 현재 상태:");
        System.out.println("   플레이어 수: " + playerCount + "명");
        System.out.println("   게임 상태: " + (gameInProgress ? "진행중" : "대기중"));
        
        if (gameInProgress) {
            System.out.println("❌ 게임 시작 실패: 이미 게임이 진행 중입니다.");
            return;
        }
        
        if (playerCount < 4) {
            System.out.println("❌ 게임 시작 실패: 최소 4명이 필요합니다.");
            System.out.println("💡 현재 " + playerCount + "명, " + (4 - playerCount) + "명 더 필요합니다.");
            return;
        }
        
        System.out.println("🚀 게임 시작 조건 충족! 게임을 시작합니다...");
        gameRoom.forceStartGame();
        System.out.println("✅ 게임 시작 완료!");
    }
    
    private void handleStatusCommand() {
        System.out.println("📊 ===== 서버 상태 =====");
        System.out.println("🌐 서버 포트: " + PORT);
        System.out.println("📡 서버 상태: 실행중");
        System.out.println("👥 접속 플레이어: " + connectedPlayers.get() + "/" + MAX_PLAYERS + "명");
        System.out.println("🎮 게임방 인원: " + gameRoom.getPlayerCount() + "명");
        System.out.println("🎯 게임 상태: " + (gameRoom.isGameStarted() ? "진행중" : "대기중"));
        
        if (gameRoom.getPlayerCount() >= 4 && !gameRoom.isGameStarted()) {
            System.out.println("🚀 게임 시작 가능! 'start' 명령어로 시작하세요.");
        }
        System.out.println("========================");
    }
    
    private void handlePlayersCommand() {
        int playerCount = gameRoom.getPlayerCount();
        
        System.out.println("👥 ===== 플레이어 목록 =====");
        System.out.println("📊 총 플레이어: " + playerCount + "명");
        
        if (playerCount == 0) {
            System.out.println("😴 접속한 플레이어가 없습니다.");
        } else {
            gameRoom.printPlayerList();
            
            if (playerCount < 4) {
                System.out.println("⚠️ 게임 시작을 위해 " + (4 - playerCount) + "명 더 필요합니다.");
            } else if (!gameRoom.isGameStarted()) {
                System.out.println("🚀 게임 시작 준비 완료!");
            }
        }
        System.out.println("===========================");
    }
    
    private void handleSayCommand(String message) {
        System.out.println("📢 전체 공지 전송: " + message);
        gameRoom.broadcastServerMessage(message);
        System.out.println("✅ 공지 전송 완료");
    }
    
    private void handleStopCommand() {
        if (!gameRoom.isGameStarted()) {
            System.out.println("❌ 중지할 게임이 없습니다.");
            return;
        }
        
        System.out.println("🛑 게임 강제 종료 중...");
        gameRoom.forceStopGame();
        System.out.println("✅ 게임이 강제 종료되었습니다.");
    }
    
    private void handleQuitCommand() {
        System.out.println("🛑 서버 종료 중...");
        
        if (gameRoom.getPlayerCount() > 0) {
            gameRoom.broadcastServerMessage("🛑 서버가 곧 종료됩니다.");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
        }
        
        try {
            stop();
            System.out.println("✅ 서버 종료 완료. 안녕히 가세요!");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("❌ 서버 종료 중 오류: " + e.getMessage());
            System.exit(1);
        }
    }
    
    // 📌 연결 안정성 개선된 플레이어 처리
    private void handleNewPlayer(Socket clientSocket) {
        BufferedReader in = null;
        PrintWriter out = null;
        String nickname = null;
        
        try {
            // 📌 BufferedReader/PrintWriter로 안정성 확보
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
            
            // 📌 소켓 설정 (연결 안정성)
            clientSocket.setKeepAlive(true);
            clientSocket.setSoTimeout(300000); // 5분 타임아웃
            
            // 환영 메시지
            out.println("WELCOME:🎭 마피아42 게임에 오신 것을 환영합니다!");
            out.println("NICKNAME_REQUEST:닉네임을 입력해주세요 (2-10자, 한글/영문/숫자):");
            
            // 닉네임 수신 및 처리
            String rawInput = in.readLine();
            if (rawInput == null) {
                System.out.println("❌ 클라이언트가 연결을 끊었습니다.");
                return;
            }
            
            // CHAT: 프리픽스 제거
            if (rawInput.startsWith("CHAT:")) {
                nickname = rawInput.substring(5).trim();
            } else {
                nickname = rawInput.trim();
            }
            
            System.out.println("📝 수신된 닉네임: [" + nickname + "]");
            
            // 닉네임 검증
            if (!isValidNickname(nickname)) {
                out.println("ERROR:유효하지 않은 닉네임입니다. (2-10자, 한글/영문/숫자만)");
                Thread.sleep(2000);
                return;
            }
            
            if (gameRoom.isNicknameTaken(nickname)) {
                out.println("ERROR:이미 사용중인 닉네임입니다: " + nickname);
                Thread.sleep(2000);
                return;
            }
            
            // Player 객체 생성
            Player player = new Player(clientSocket, out, in);
            player.setNickname(nickname);
            
            // 게임방 입장
            if (gameRoom.addPlayer(player)) {
                player.sendMessage("JOIN_SUCCESS:✅ 입장 성공! 현재 " + gameRoom.getPlayerCount() + "명");
                player.sendMessage("WAITING:⏳ 서버 관리자의 게임 시작 명령을 기다리는 중...");
                
                System.out.println("✅ " + nickname + "님 입장 (현재: " + gameRoom.getPlayerCount() + "명)");
                
                // 메시지 처리 스레드 시작
                executorService.submit(() -> handlePlayerMessages(player));
                
            } else {
                player.sendMessage("ERROR:게임방 입장 실패 (게임 진행 중)");
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            System.err.println("⚠️ 플레이어 처리 중 오류: " + e.getMessage());
        } finally {
            if (nickname == null || !gameRoom.hasPlayerByNickname(nickname)) {
                connectedPlayers.decrementAndGet();
                closeConnection(in, out, clientSocket);
            }
        }
    }
    
    // 📌 연결 안정성 개선된 메시지 처리
    private void handlePlayerMessages(Player player) {
        try {
            String message;
            while (player.isConnected() && (message = player.receiveMessage()) != null) {
                // 빈 메시지나 ping 무시
                if (message.trim().isEmpty() || message.startsWith("PING")) {
                    continue;
                }
                
                System.out.println("📨 [" + player.getNickname() + "] " + message);
                gameRoom.handlePlayerAction(player, message);
            }
        } catch (IOException e) {
            System.out.println("💔 " + player.getNickname() + "님 연결 종료: " + e.getMessage());
        } finally {
            gameRoom.removePlayer(player);
            connectedPlayers.decrementAndGet();
            System.out.println("👥 현재 플레이어: " + gameRoom.getPlayerCount() + "명");
            System.out.print("SERVER> ");
        }
    }
    
    private void monitorServer() {
        while (serverRunning) {
            try {
                Thread.sleep(60000); // 1분마다
                
                // 연결 상태 체크
                if (gameRoom.getPlayerCount() != connectedPlayers.get()) {
                    System.out.println("⚠️ 연결 상태 불일치 감지 - 정리 중...");
                    gameRoom.cleanupDisconnectedPlayers();
                }
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    private boolean isValidNickname(String nickname) {
        return nickname != null && 
               nickname.length() >= 2 && 
               nickname.length() <= 10 &&
               nickname.matches("[가-힣a-zA-Z0-9]+");
    }
    
    private void sendErrorAndClose(Socket socket, String error) {
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            out.println("ERROR:" + error);
            Thread.sleep(1000);
            socket.close();
        } catch (Exception e) {
            System.err.println("오류 전송 실패: " + e.getMessage());
        }
    }
    
    private void closeConnection(BufferedReader in, PrintWriter out, Socket socket) {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.err.println("연결 종료 오류: " + e.getMessage());
        }
    }
    
    public void stop() throws IOException {
        serverRunning = false;
        if (serverSocket != null) serverSocket.close();
        if (executorService != null) executorService.shutdown();
    }
    
    public static void main(String[] args) {
        System.out.println("🎭 마피아42 게임 서버 부팅 중...");
        
        try {
            new GameServer().start();
        } catch (IOException e) {
            System.err.println("❌ 서버 시작 실패: " + e.getMessage());
            System.exit(1);
        }
    }
}