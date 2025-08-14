package STREAM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StreamIOExam2 {

	public static void main(String[] args) throws Exception {
		// 부스터 스트림(보조 스트림) : 기존 입출력 스트림에 연결하여 성능 향상 혹은 유용한 기능 추가 해주는 스트림
		
		// BufferedReader, BufferedWriter 
		// -> I/O 횟수를 획기적으로 줄여 성능을 크게 향상 시킨 보조스트림
		// -> 특히 엑셀, txt파일 읽을 때 유용함 (한 글자가 아니고 한 줄씩 읽기 때문에)
		
		
		// ex1. java로 엑셀 파일 만들기
		// 고객 데이터를 전달받았는데 이걸 한줄씩 읽어서 이름과 포인트 분리 후
		// 포인트가 1000점 이상인 우수고객 명단만 뽑아서 보고하는 프로그램
		String fileName = "C:\\ssak\\test.csv";
		List<String> vipCustomer = new ArrayList<>();
		List<String> customers = new ArrayList<>();
		
		
		// try-with-resources 문법
		// 파일 전송, 네트워크 소켓, db 연결 등 외부 자원과 연결할때
		// 사용 끝나면 자동으로 자원을 닫아주는 기능 제공
		try 
			(BufferedWriter writer 
					= new BufferedWriter(new OutputStreamWriter
							(new FileOutputStream(fileName), StandardCharsets.UTF_8))) { // 파일 만들기
			writer.write("탱고, 550");
			writer.newLine();
			writer.write("와플곰, 3500");
			writer.newLine();
			writer.write("바쁘개, 750");
			writer.newLine();
			writer.write("누렁이, 2500");
			writer.newLine();
			writer.flush();
			System.out.println("파일 생성 완료");
			
			
			
//			customers = List.of(
//					"이름, 가격",
//					"탱고, 550",
//					"와플곰, 3500",
//					"바쁘개, 750",
//					"누렁이, 2500"
//					);
//			Files.write(Paths.get(fileName), customers, StandardCharsets.UTF_8);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("우수고객 필터링 시작");
		BufferedReader br 
		= new BufferedReader
				(new InputStreamReader
						(new FileInputStream(fileName), StandardCharsets.UTF_8)); // 파일 만들기
		
	
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(","); // 쉼표 기준으로 문자열을 자름
            String name = data[0];
            int points = Integer.parseInt(data[1].trim()); // 공백 제거 후 숫자로 변환

            if (points >= 1000) {
                vipCustomer.add(name);
            }
        }
        
        System.out.println(vipCustomer);
	
	}

}
