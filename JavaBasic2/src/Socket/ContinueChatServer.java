package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ContinueChatServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("탱고 : 접속자를 기다립니다.");
		
		Socket cliSocket = serverSocket.accept();
		
		System.out.println("누렁이 접속 완료 대화 시작!");
		BufferedReader in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream(), StandardCharsets.UTF_8));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(cliSocket.getOutputStream(), StandardCharsets.UTF_8), true);
		
		String msg;
		Scanner sc = new Scanner(System.in);
		
        // 클라이언트로부터 메시지를 계속 읽습니다.
        while ((msg = in.readLine()) != null) {
            System.out.println("🐶 누렁이: " + msg);

            // "잘가" 라는 메시지를 받으면 대화를 종료합니다.
            if ("잘가".equalsIgnoreCase(msg)) {
                out.println("그래, 너도 잘 가! 다음에 또 놀자~");
                break;
            }
            
            
            String usrInput;
            usrInput = sc.nextLine();
        	out.println(usrInput);
            
            // 받은 메시지에 대한 답장을 보냅니다.
            //out.println("'" + msg + "'라고? 재미있네! 또 다른 얘기 해줘!");
        }
		
		
		
		
		
		
		
	}

}