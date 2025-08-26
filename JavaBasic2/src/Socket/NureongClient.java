package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NureongClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("13.124.201.233", 51312);
		
		System.out.println("서버와 연결 완료 대화를 시작해 주세요.");
		
		 // 서버로부터 메시지를 읽기 위한 스트림 (UTF-8 인코딩 명시)
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        // 서버로 메시지를 보내기 위한 스트림 (UTF-8 인코딩 명시)
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);		
		
        Scanner sc = new Scanner(System.in);
		
        String usrInput;
        
        while(true) {
        	System.out.print("누렁이 : ");
        	usrInput = sc.nextLine();
        	out.println(usrInput);
        	
        	// 서버로부터 온 응답을 읽고 출력
        	String serverResponse = serverIn.readLine();
        	System.out.println("탱고 : " + serverResponse);
        	
        }
        
        
        
        
	}
	
}