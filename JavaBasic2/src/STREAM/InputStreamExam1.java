package STREAM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputStreamExam1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			InputStream iStream = new FileInputStream("C:\\ssak\\test.txt");
//			int readByte;
//			byte[] b = new byte[2];
//			int i=0;
//			
//			while((readByte = iStream.read(b)) != -1) {
////				i++;
////				System.out.println("읽은 바이트수 "+readByte);
////				String s = new String(b);
////				System.out.println("읽어낸 문자 "+(char)readByte);
//			// 파일 내용 받아서 볼 수 있음 근데 실행에서 보려면 번역 필요
//				String str = new String(b, 0, readByte, "UTF-8");
//				System.out.println(str);
//				// 위처럼 UTF-8 유니코드를 string타입 변수에 처음 생성 시 선언해두면
//				// 디코딩이 잘 됨
//				// byte 타입 배열의 크기도 영향을 끼치는 점이 있음 -> 불안요소들이 존재함
//				
//			}
//			
			BufferedReader reader = new BufferedReader(new FileReader("C:\\ssak\\test.txt"));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
