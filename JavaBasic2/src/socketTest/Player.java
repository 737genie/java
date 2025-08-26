package socketTest;

import java.io.*;
import java.net.Socket;

public class Player {
    private String nickname;
    private Role role;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    // 게임 상태
    private boolean alive;
    private boolean connected;
    private boolean protected_;
    private boolean investigated;
    private boolean canVote;
    private boolean soldierShield;
    private boolean hasUsedAbility;
    
    // 특수 상태
    private String deathMessage;
    private Role stolenRole;
    private String lastNightTarget;
    private String spyInformation;
    
    // 📌 안정성 개선된 생성자
    public Player(Socket socket, PrintWriter out, BufferedReader in) {
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.connected = true;
        initializeDefaultValues();
    }
    
    // 기본 생성자 (MockPlayer용)
    public Player() {
        this.connected = false;
        initializeDefaultValues();
    }
    
    private void initializeDefaultValues() {
        this.alive = true;
        this.protected_ = false;
        this.investigated = false;
        this.canVote = true;
        this.soldierShield = false;
        this.hasUsedAbility = false;
        this.deathMessage = "";
        this.lastNightTarget = "";
        this.spyInformation = "";
    }

    // 📌 안전한 메시지 전송 (한글 지원)
    public synchronized void sendMessage(String message) {
        if (!connected || out == null) return;
        
        try {
            out.println(message);
            out.flush();
        } catch (Exception e) {
            System.err.println("메시지 전송 오류 [" + nickname + "]: " + e.getMessage());
            connected = false;
        }
    }

    // 📌 안전한 메시지 수신 (한글 지원)
    public String receiveMessage() throws IOException {
        if (!connected || in == null) {
            throw new IOException("연결이 끊어졌습니다.");
        }
        
        try {
            String message = in.readLine();
            if (message == null) {
                connected = false;
                throw new IOException("클라이언트 연결 종료");
            }
            return message;
        } catch (IOException e) {
            connected = false;
            throw e;
        }
    }

    public void closeConnection() {
        connected = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("연결 종료 중 오류 [" + nickname + "]: " + e.getMessage());
        }
    }

    public boolean attemptKill(String cause) {
        // 군인 보호막 체크
        if (role == Role.SOLDIER && soldierShield && "마피아".equals(cause)) {
            soldierShield = false;
            sendMessage("SOLDIER_SHIELD:🛡️ 군인의 보호막이 공격을 막았습니다!");
            return false;
        }
        
        // 의사 보호 체크
        if (protected_ && "마피아".equals(cause)) {
            sendMessage("DOCTOR_SAVE:⚕️ 의사의 보호로 공격을 막았습니다!");
            return false;
        }
        
        // 실제 죽음 처리
        die(cause);
        return true;
    }

    public void die(String cause) {
        this.alive = false;
        this.canVote = false;
        this.deathMessage = cause + "에 의해 사망";
        sendMessage("DEATH:" + cause);
    }

    public void resetNightStatus() {
        this.protected_ = false;
        this.hasUsedAbility = false;
        this.lastNightTarget = "";
    }

    // Getters and Setters
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { 
        this.role = role; 
        if (role == Role.SOLDIER) {
            this.soldierShield = true;
        }
    }
    
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    
    public boolean isConnected() { return connected; }
    public void setConnected(boolean connected) { this.connected = connected; }
    
    public boolean isProtected() { return protected_; }
    public void setProtected(boolean protected_) { this.protected_ = protected_; }
    
    public boolean isInvestigated() { return investigated; }
    public void setInvestigated(boolean investigated) { this.investigated = investigated; }
    
    public boolean canVote() { return canVote && alive; }
    public void setCanVote(boolean canVote) { this.canVote = canVote; }
    
    public boolean hasSoldierShield() { return soldierShield; }
    public void setSoldierShield(boolean soldierShield) { this.soldierShield = soldierShield; }
    
    public boolean hasUsedAbility() { return hasUsedAbility; }
    public void setHasUsedAbility(boolean hasUsedAbility) { this.hasUsedAbility = hasUsedAbility; }
    
    public String getDeathMessage() { return deathMessage; }
    public void setDeathMessage(String deathMessage) { this.deathMessage = deathMessage; }
    
    public Role getStolenRole() { return stolenRole; }
    public void setStolenRole(Role stolenRole) { this.stolenRole = stolenRole; }
    
    public String getLastNightTarget() { return lastNightTarget; }
    public void setLastNightTarget(String lastNightTarget) { this.lastNightTarget = lastNightTarget; }
    
    public String getSpyInformation() { return spyInformation; }
    public void setSpyInformation(String spyInformation) { this.spyInformation = spyInformation; }

    public Socket getSocket() { return socket; }
    public BufferedReader getBufferedReader() { return in; }
}