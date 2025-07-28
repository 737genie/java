package Basic;

public class Operator {

	public static void main(String[] args) {
		// 연산자, 기본 입출력
		// 표준 출력 (값을 출력할 때 쓰는 문법)
		// println :: 문장 출력 후 다음 줄로 넘김 (개행)
		// printf, print :: 줄 바꿈 없이 쭉
		//  -> 둘의 차이점:
		//  -> printf는 서식 지정출력이 가능 %s(문자), %d(숫자), %f(실수)
//		System.out.print("hi");
//		System.out.println("점심시간");
//		System.out.printf("일단 채우기");
//		System.out.println("한 줄 더");
//
//		System.out.println("=======================");
//		System.out.println("hi");
//		System.out.println("점심시간");
//		System.out.println("일단 채우기");
//		System.out.println("한 줄 더");
		
		
		
		
		// 연산자
		// 1. 산술연산자 == + - * /
		int a = 20, b = 30;
		
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		
		
		
		// 2. 대입 == 변수에 값을 할당 / 연산과 함께 할당할 때 사용
		// +=, -=
		a+=b;
		
		String msg1 = "Gunchim";
		String msg2 = "SSak";
		
		msg1+=msg2;
		System.out.println(msg1);
		
		
		
		// 3. 비교
		// 4. 논리
		// 5. 비트
		// 6. 삼항
		// 7. 크기 검사(자바 전용)
		// 8. 단항
		// 9. 그 외 ...
		
	}

}
