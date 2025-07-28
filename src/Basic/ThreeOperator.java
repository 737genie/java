package Basic;

import java.util.Scanner;

public class ThreeOperator {

	public static void main(String[] args) {
		// 삼항 연산자
		// 조건식인데 짧고 간결한 분기를 표현할 때 사용
		
		// 복잡한 분기 > if 문
		// 간결한 분기 > 삼항연산자 (중첩으로 사용하지 않기)
		
		// 삼항 연산자 사용법
		// 변수 = (조건식) ? 조건식이 참일경우 실행되는코드: 거짓일경우 실행되는코드;
		// 주의 사항: 
		//  실행되는 코드들의 타입은 반드시 같아야 한다. (의도치 않은 형변환 막음)
		//  출력 문법 사용 불가 
		//  중첩하여 쓰지 않기
		//  표현식 내부에서 ++ 쓰지 않기
		//  참 거짓 값은 최대한 간단히 작성 > 다른 연산자와 혼용 금지
		

		
		// example
		// 과속 측정 카메라 구현
		// 80 초과하면 잡음
		
		// 1. 차량의 속도를 입력받는다 가정.
		// 2. 만약 차량의 속도가 80을 초과했다면 과속 딱지 부여
		// 3. 만약 80 이하라면 정상 운행입니다. 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("차량의 속도를 입력해주세요.");
		int CarSpeed = sc.nextInt();
		
		
		String result = (CarSpeed > 80) ? 
				"속도가 80을 초과하였으므로 과속 딱지를 부여합니다.":
				"정상 운행입니다.";
		
//		int result = (CarSpeed > 80) ? 80: 70;
		
		System.out.println(result);
				
		sc.close();
	}

}
