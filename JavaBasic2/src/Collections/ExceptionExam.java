package Collections;

public class ExceptionExam {

	public static void main(String[] args) {
		// 에러: 의도치 않게 프로그램 종료되는 것 - 하드웨어적 이슈
		// 예외: 사용자의 잘못된 조작, 개발자 실수로 인한 오류 - 소프트웨어적 이슈
		// 		-> 해결: 예외처리 추가하기
		// 오류 - 에러 / 예외
		
		
		// 예외 종류
		// 1. 일반 예외(Checked exception)
		// - 예외 처리 없으면 컴파일 에러 발생
		
		
		// 2. 실행 예외(Runtime-exception)
		// - 예외 처리 생략해도 컴파일 되는 예외
		// (ArrayIndex, NumberFormat, NullPointerException)
		
		
		// 예외 처리 하는 법
//		try {
//			
//		}catch(Exception e) {
//			
//		}
		
		
		// 예시
		// 1. 0으로 나누기
		try {
			int test = 10/0;
			System.out.println(test);
			System.out.println(1);
			System.out.println(1);
			System.out.println(1);
			System.out.println(1);
			
		}catch(Exception e) {
			System.out.println("0으로 나눌 수 없습니다."); // 예외 발생할 경우 바로 예외처리로 이동
		}
		
		
		// if와 다른 점?
		// if는 예측이 가능한 상황
			//> 로직의 분기 자체를 표현
		// 예외처리는 예측 불가능한, 혹은 예상치 못한 비상상황을 처리하는 개념
			//> 네트워크 연결 끊김, DB오류 등
		
	}

}
