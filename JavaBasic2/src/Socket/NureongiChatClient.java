package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NureongiChatClient {
    public static void main(String[] args) {
        final String SERVER_IP = "192.168.4.45";
        final int SERVER_PORT = 8889;
        Socket socket = null;

        try {
            // 1. 서버에 접속
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("📞 탱고의 채팅 서버에 연결되었습니다.");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           
            // 콘솔(터미널) 자체의 인코딩 설정도 UTF-8이어야 한글 입력이 깨지지 않습니다.
            Scanner scanner = new Scanner(System.in);
            
            // 서버에서 닉네임 요청 메세지를 던질거임.
            String serverPrompt = in.readLine();
            System.out.println("서버 : " + serverPrompt);
            
            System.out.print(">>> 내 닉네임은 : ");
            String nickname = scanner.nextLine();
            out.println(nickname);
            
            
            // 2. 서버로부터 메시지를 받는 스레드 생성 및 시작
            //MessageReceiver receiver = new MessageReceiver(socket);
            MessageReceiver receiver = new MessageReceiver(in);
            receiver.start();

            // 3. 사용자 입력을 받아 서버로 메시지를 보내는 로직 (UTF-8 인코딩 명시)
            
           
            
            // 처음 입력하는 내용은 사용자명으로 전송
//            String firstInput = scanner.nextLine();
//            out.println(firstInput);
            System.out.println("채팅 시작 가능!");
            // 이후 내용은 채팅 메시지로 전송
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
                if ("/quit".equalsIgnoreCase(message)) {
                    break; // /quit 입력 시 루프 종료
                }
            }

        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
                System.out.println("📱 서버와의 연결을 종료합니다.");
            } catch (Exception e) {
                // ignore
            }
        }
    }
}

// 서버로부터 메시지를 수신하는 역할을 담당하는 스레드
class MessageReceiver extends Thread {
    //private Socket socket;
    private BufferedReader in;

    public MessageReceiver(BufferedReader in) {
        //this.socket = socket;
        try {
            // 스트림 생성 시 UTF-8 인코딩 명시
            this.in = in;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message;
            // 서버로부터 메시지가 올 때까지 계속 대기하며, 받으면 화면에 출력
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (Exception e) {
            // 서버 연결이 끊기면 스레드 종료
            System.out.println("서버와의 연결이 끊겼습니다.");
        }
    }
}