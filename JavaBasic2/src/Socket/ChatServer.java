package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	
	//private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

	
	
	
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999);
		
		BufferedReader in = null;
		PrintWriter out = null;
		
		
		System.out.println(" 탱고의 멀티 채팅 서버 오픈! ");
		
		// 탱고가 집에서 방 하나에 손님 맞을 준비를 끝냈음
		// (서버가 클라이언트에게 제공할 공간과 클라이언트의 요청을 받을준비가 끝났다)
		
		// -> 손님이 올때까지 기다리기
		
		while(true) {
			// 클라이언트의 접속을 받을 준비.
			Socket clientSocket = serverSocket.accept();
			System.out.println("새로운 친구 "+ clientSocket.getInetAddress()+ " 가 접속을 시도합니다.");
		
			// 손님이 왔으니 맞이를 할거
			//  -> 클라이언트와 데이터를 주고받을 입출력 스트림이라는것을 생성.
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);
			
            // 4. 클라이언트가 메세지를 보냈다라는 가정하.
            String msg = in.readLine();
            System.out.println(clientSocket.getInetAddress() +"의 메세지 : " + msg);
			
			out.println("탱고 : 웰컴 투 마이 홈");
			
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
            System.out.println("💤 대화를 종료하고 잠자리에 들어요.");
		}
		
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}