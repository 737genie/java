package STREAM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class StreamIOExam {
	
	// 사진 옮기기 기능 만들기
	public static void main(String[] args) throws Exception{
		
		FileInputStream in = new FileInputStream("C:\\ssak\\1.jpg");
		FileOutputStream out = new FileOutputStream("C:\\backup\\1bak.jpg");
		
		int data; // 한 바이트씩 데이터를 저장하는 변수
		System.out.println("사진 백업 시작");
		
		
		// in.read (FileInputStream의 메소드 in) : 바이트 데이터 읽어서 정수값으로 리턴
		// -> 그래서 임시변수를 int로 줌
		
		// 한 바이트씩 읽다가 데이터가 없으면(file 끝에 도달하면) 반복을 종료함
		// 모든 파일들은 파일의 끝에 도달하면 무조건 -1을 리턴하게 된다.
		while((data = in.read()) != -1) {
			out.write(data); // 읽어온 정수값의 데이터를 OutputStream에 보내주는 것
		}
		// 입력 스트림에서 가져온 데이터를 출력 스트림으로 복사까지 진행
		System.out.println("백업 완료");
		
	}
}
