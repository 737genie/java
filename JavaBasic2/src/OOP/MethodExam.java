package OOP;

import java.util.Scanner;

public class MethodExam {
	
	public static void gugu() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1~15 사이의 숫자를 입력해주세요.");
			int num = sc.nextInt();
			
			if(num>15 || num<1) {
				System.out.println("숫자를 다시 입력해주세요.");
			} else {
				for(int i=1; i<10; i++) {
					System.out.println(num+" * "+i+" = "+num*i);
				} break;
		}
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 메서드 : 클래스 안에 정의된 함수
		// 함수 : 입력에 대한 작업의 결과를 출력해주는 기능의 모음
		// - 특정 용도의 코드를 한 곳에 모아둔 것
		
		// 함수와 메서드의 차이 : 클래스 포함 유무
		
		// 사용자로부터 1-15 숫자를 입력받아
		// 입력받은 숫자의 1~9까지의 곱셈 결과를 출력하는 코드 작성
		
		// MethodExam.gugu();
		
		// 메서드를 쓰는 이유: 재사용성 증대를 위해
		// 사용자가 호출하지 않는 이상 먼저 동작하진 않음
		// -> 예외: 생성자
		
		// 메서드 용어 정리
		// 1. parameter(매개변수), argument(인수)
		//  argument(인수) : 메서드로 전달할 값을 적는 부분
		//  parameter : 메서드 호출 시 인수로부터 전달된 값을 받아오는 변수
		
		// 파라미터 사용 시 주의 사항
		// 1. 인수와 파라미터 타입 일치
		// 2. 인수 넣은 순서대로 파라미터도 그대로 받아옴
		// 3. 파라미터들의 타입이 다 똑같지는 않음
		// 4. 파라미터와 인수의 개수는 기본적으로는 동일
		
		MethodExam.washingMachine("다우니", "고급정장", 9724); // MethodExam이라는 클래스의 test라는 메서드를 호출
		
		// 2. 리턴, 리턴 타입
			// 위의 메서드 washingMachine은 사실 결과가 없는 메서드
			// 컴퓨터 입장에서는 메서드를 호출 했을 때 단순히 결과 출력만 되는 경우는
			// 결과값이 없다, 라고 판단 (메서드에 void가 들어가면 그렇게 판단함)
		
			// 메서드에 꼭 결과값이 있어야 할 필요는 없음
		
			// 그럼 결과값이 있는 메서드는 뭐지?
		// 리턴(반환값) : 메서드 호출 시 나오는 값
		// 리턴 타입: 어떠한 메서드를 호출했을 때 리턴되는 값의 타입을 지정하는 키워드
		// -> 리턴 타입으로 올 수 있는 것들 (일반 타입, 클래스, 참조 타입)
		// 리턴타입과 리턴값 타입 일치해야함
		// 그런데 리턴타입이 void면 리턴값은 없어야함
		
		
		int result = returntest(return2());
		System.out.println(result);
		// 전역 변수, 전역 메서드 
		// 전역 변수(클래스 변수, 정적 변수) - 인스턴스가 생기지 않아도 공용으로 쓸 수 있음
		// -> 메인 메서드 내부에서는 사용 불가 (1.8)
		
		// 전역 메서드
		// 메인을 제외한 static 키워드가 붙어있는 메서드
		// 전역 변수와 마찬가지로 클래스만 지칭되면 언제 어디서나 쓸 수 있음
		// -> 인스턴스 없어도 공용으로 쓸 수 있다는 것
		// 추후 배울 접근 제어자도 영향을 끼침
		
		// 메인 메서드
		// 자바 런타임이 자동으로 호출하는 메서드
		// -> 프로그램 실행 시 가장 먼저 수행하는 메서드!!!
		// 
		
	
	}
	
	public static void washingMachine(String detergent, String clothes, int turn) {
		System.out.println(detergent);
		System.out.println(clothes);
		System.out.println(turn);
	}
	
	public static int returntest(int param1) {
		if(param1 < 10000) {
			return 20000;
		}
		else {
			return param1;
		}
	}
	
	public static int return2() {
		return 1353121;
	}

}
