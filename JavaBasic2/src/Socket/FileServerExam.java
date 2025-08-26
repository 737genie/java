package Socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerExam {

	
	public static void main(String[] args) throws Exception{
		// 파일을 저장할 폴더를 지정.
		String storagePath = "C:\\ssak";
		File storageDir = new File(storagePath);
		
        // 2. 폴더가 존재하지 않으면 새로 생성합니다.
        if (!storageDir.exists()) {
            storageDir.mkdirs(); // mkdirs()는 필요한 모든 상위 폴더도 함께 생성합니다.
            System.out.println("📂 저장 폴더 '" + storagePath + "'를 생성했습니다.");
        }
        
		ServerSocket serverSocket = new ServerSocket(9876);
		System.out.println("탱고 : 파일전송 받아요!");
		
		Socket cliSocket = serverSocket.accept();
		System.out.println("누렁이 : 선물이야 !" + cliSocket.getInetAddress());
		
		//File.separator : 파일 경로의 디렉토리 구분자를 나타내는 정적 상수.
		//  -> 운영체제별로 경로를 표현하는 방식이 달라서
		//     운영체제별로 경로 표현식을 맞추기 위해 쓰는 상수.
		String filePath = storagePath + File.separator + "result1.jpg";
		FileOutputStream fos = new FileOutputStream(filePath);
		
		// 클라이언트로부터 데이터를 받을 스트림.
		InputStream in = cliSocket.getInputStream();
		
		// 실질적으로 파일을 받아올 영역
		byte[] buffer = new byte[4096];
		int bytesRead;
		
		System.out.println("누렁이의 선물 포장을 푸는중");

		while((bytesRead = in.read()) !=-1 ) {
			System.out.println(bytesRead);
			fos.write(buffer, 0 , bytesRead); // 읽어들인 바이트 데이터를 출력스트림에 그대로 써주는 메서드.
							
		}
		System.out.println("선물 다받음.");
		
        fos.close();
        in.close();
        cliSocket.close();
        serverSocket.close();
		
		
	}
	
	
	
}