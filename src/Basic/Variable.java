package Basic;

public class Variable {

	public static void main(String[] args) {
		
		
		//long appleBox = 3_000_000_000L;
		//System.out.println("뇌물로 "+appleBox+" 받았습니다");
		
		
		// long appleBox = 3_000_000_000L;
		// 17버전부터 underbar 사용 가능
		
		// 변수 주의 사항
		// 1. 이름 잘 짓기
		// 2. 팀플시 정해진 변수명을 잘 따를 것
		// 3. 자바에서는 타입을 상황에 맞게 써야함
		// 4. 변수 표기법 잘 지키기
		//  - PascalCase, camelCase, SNAKE_CASE
		// 5. 변수명 사이에 공백 X
		
		// 변수 작성법
		// 타입 변수명 = 값;
		
		// 변수에 붙는 타입
		// 일반, 참조
		// 일반 타입 (primitive type) 
		 // 종류 - 논리, 문자, 정수, 실수
		
		 // boolean 논리 = 참, 거짓
		 // char 문자 = '한 글자'만 표현 / *(String - 일반 타입처럼 쓰는 참조 타입 = "여러 글자" 표현)
		 // byte, short, int, long 정수
		 // float, double 실수
		
		 // boolean, byte - 1byte
		  // true, false (-128~127 byte)
		
		
		 // char, short - 2byte
		  // \u0000-\uFFFF(대소문자 알파벳 한 개씩) (char)
		  // -32,768 ~ 32,767(short)
		
		
		 // int, float - 4byte
		  // -2,147,483,648 ~ 2,147,483,647
		 // long, double - 8byte
		 
		 // var (java 17부터 추가) - 물건 크기에 맞도록 변하는 상자
		
		// 참조 타입 (reference type)  
		
		// example
		byte age = 26;
		short joinYear = 1895;
		int haveMoney = 100_000_000;
		long gotoAmerica = 242342342342433L; //마지막에 꼭 L붙이기
		
		
		//double - 초고밀 정밀도가 필요할 때
		 // 원주율 계산, 천문학적인 초 거대 데이터 처리
		//float - 메모리 아껴야 할 때
		 // 게임개발, 그래픽처리, 물리엔진(물리계산), 대량의 실수데이터 처리
		float temperature = 34.2f;
		double pi = 3.141592;
		
		
		char grade = 'A';
		String name = "JJJ"; // 참조타입
		
		boolean tAndF = true;
		
		// java는 재선언 불가, 재할당은 가능
		age = 52; //가능
		//var age = 54; //불가능
		
		var studentName = "김해진";
		var age2 = 9724;
		
		//var subjects = List.of("수학", "과학");
		
		// 타입 캐스팅: 타입 자체를 변경하는 것
		// 자동 타입 캐스팅		
		byte smallByte = 10;
		int mediumInt = smallByte;    // byte-int 자동 변환
		long bigLong = mediumInt;     // int-long 자동 변환
		double realDouble = bigLong;  // long-double 자동 변환
		
		
		// 강제 타입 캐스팅
		double originalDouble = 123.456;
		int castInt = (int) originalDouble;  // 소수점 손실
		byte castByte = (byte) castInt;      // 범위 내 안전
		
		
		// 형변환의 경우 정수, 실수만 가능
		// 문자는 문자끼리, 숫자는 숫자끼리 
		// 숫자를 문자화 시킬 수는 없음
		
		// 오버플로우
		// 값이 일부 소실될 수 있음
		int maxInt = 21000000;
		byte overflow = (byte) maxInt;
		short overflow2 = (short) maxInt;
		
		System.out.println("===오버플로우 테스트===");
		System.out.println("원래 값: " + maxInt);
		System.out.println("byte 변환: " + overflow);
		System.out.println("short 변환: " + overflow2);
		
		
		
	}

}
