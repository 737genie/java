package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 

public class TangoServerMulti {
	
	private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
	
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8889);
        System.out.println("🏡 탱고네 집 문이 열렸어요! 여러 친구들을 기다립니다.");

        while (true) { // 무한 루프를 돌며 계속해서 클라이언트의 접속을 받는다.
            Socket clientSocket = serverSocket.accept();
            System.out.println("🎉 새로운 친구(" + clientSocket.getInetAddress() + ")가 놀러왔어요!");
            
            // 새로운 친구를 담당할 분신(스레드)을 만들고 대화를 시작시킨다.
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            clientHandler.start();
        }
    }
    // 모든 클라이언트에게 메시지를 보내는 메소드 (브로드캐스트)
    public static void broadcastMessage(String message) {
        // 동기화된 블록에서 리스트를 순회하여 안전하게 메시지 전송
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }
    
    // 클라이언트 핸들러를 리스트에서 제거하는 메소드
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
    
}

// 각 클라이언트와의 대화를 독립적으로 처리할 '분신' 클래스 (Thread 상속)
class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    
    // 이 핸들러에 연결된 클라이언트에게 메시지를 보내는 메소드
    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("안녕! 나는 탱고의 분신이야 네 이름은 뭐니?");
            this.username = in.readLine();
            System.out.println(clientSocket.getInetAddress() + "의 사용자 명이 " 
            + this.username + "으로 설정되었습니다.");
            
            TangoServerMulti.broadcastMessage("[알림] " + username + "님이 채팅방에 입장했습니다.");
            
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(username + " 담당 친구: " + message);
                if ("/quit".equalsIgnoreCase(message)) {
                    out.println("알았어, 다음에 또 봐!");
                    break;
                }
                TangoServerMulti.broadcastMessage("[" + username +"]: " + message);
                //out.println("응응, '" + message + "'! 더 얘기해줘.");
            }
        } catch (Exception e) {
            System.out.println("앗, 친구(" + clientSocket.getInetAddress() + ")와의 연결에 문제가 생겼어요.");
        } finally {
            try {
                System.out.println("👋 친구(" + clientSocket.getInetAddress() + ")가 집으로 돌아갔어요.");
                in.close();
                out.close();
                clientSocket.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}