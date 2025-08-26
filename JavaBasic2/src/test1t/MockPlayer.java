package test1t;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MockPlayer extends Player {
    private List<String> sentMessages = new ArrayList<>();
    private List<String> receivedInputs = new ArrayList<>();
    private int currentInputIndex = 0;

    public MockPlayer(int id) throws IOException {
        super(); // 부모의 기본 생성자 호출
        setConnected(false); // Mock이므로 연결되지 않음
    }

    @Override
    public synchronized void sendMessage(String message) {
        sentMessages.add(message);
        System.out.println("[MOCK-" + getNickname() + "] 수신: " + message);
    }

    @Override
    public String receiveMessage() throws IOException {
        if (currentInputIndex < receivedInputs.size()) {
            return receivedInputs.get(currentInputIndex++);
        }
        return null;
    }

    public void setReceivedInputs(List<String> inputs) {
        this.receivedInputs = inputs;
        this.currentInputIndex = 0;
    }

    public List<String> getSentMessages() {
        return new ArrayList<>(sentMessages);
    }

    public void clearSentMessages() {
        sentMessages.clear();
    }

    @Override
    public void closeConnection() {
        // Mock에서는 실제 연결 종료 작업 불필요
        setConnected(false);
    }
}
