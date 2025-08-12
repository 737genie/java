package Collections;

import java.util.Scanner;

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
//			정상적으로 실행될 때 진행되는 영역(로직)
//		}catch(Exception e) { 일반 Exception은 가장 마지막에 사용
//			비정상적 동작, 예측하지 못한 상황을 발견했을 때 진행되는 영역
//		}catch(NullPointerException ne) {
//			NPE 라고 불림
//		}
//		finally {
//			항상 실행
		
		
		// 예시
		// 1. 0으로 나누기
//		try {
//			int test = 10/0;
//			System.out.println(test);
//			System.out.println(1);
//			System.out.println(1);
//			System.out.println(1);
//			System.out.println(1);
//			
//		}catch(Exception e) {
//			System.out.println("0으로 나눌 수 없습니다."); // 예외 발생할 경우 바로 예외처리로 이동
//		}
		
		
		// if와 다른 점?
		// if는 예측이 가능한 상황
			//> 로직의 분기 자체를 표현
		// 예외처리는 예측 불가능한, 혹은 예상치 못한 비상상황을 처리하는 개념
			//> 네트워크 연결 끊김, DB오류 등
		
		
		// 예외처리 만들어 보기
		// 와플곰의 키오스크
		// 키오스크에서 예상 가능한 시나리오 : 포장 or 매장식사
		// 예측 불가 시나리오 : 수량을 문자로 입력하는 경우
	    Scanner scanner = new Scanner(System.in);
	
	    System.out.println("와플곰: '어서 오세요! 와플은 2달러입니다.'");
	    System.out.print("수량을 입력해주세요: ");
	    String quantityInput = scanner.nextLine();
	
	    System.out.print("포장하시겠어요? (y/n): ");
	    String packagingInput = scanner.nextLine();
		
	    try {
	    	// 문자열에서 숫자로 바꾸는 것 자체가 데이터의 변조가 일어날 가능성이 큼
	    	int result = Integer.parseInt(quantityInput);
	    	double totPrice = 2.0 * result;
	    	
	    	System.out.println("총 금액은 $"+totPrice+"입니다.");
	    	
	    	if(packagingInput.equals("y")||packagingInput.equals("Y")) {
	    		System.out.println("포장하겠습니다.");
	    		break;
	    	}else {
	    		System.out.println("매장에서 드실 수 있도록 준비해드릴게요.");
	    		break;
	    	}	    	
	    }catch(NumberFormatException nfe) {
	    	//System.out.println(nfe.getMessage());
	    	System.out.println("와플곰: 수량은 숫자로만 입력하셔야 합니다. 다시 주문해주세요.");
	    }catch(NullPointerException ne) {
	    	
	    }
	    
	    System.out.println("이용해주셔서 감사합니다.");
	    
	    // 예외 처리 Tip
	    // 1. 예외 처리의 경우는 어색한 블록 라인이 추가되는 것이 코드상의 이질감을 부름
	    // 	  -> 내가 평소처럼 코드 짜고 예외 처리가 필요하다 싶으면 그때 사용하기
	    // 2. 한번에 다 쓰려고 하지말고 테스트 해보면서 자주 발생한 예외를 체크하자
	    //    만약을 대비해 마지막에는 catch에 Exception을 사용
	    
	    
	    // 자주 보게될 주요한 예외 클래스
	    // 1. IllegalArgumentException (unchecked Exception) - 잘못된 인수
	    // 2. IllegalStateException - 객체의 현재 상태가 적절치 않을 때(객체가 메서드에 안어울릴때)
	    // 3. FileNotFoundException - 파일을 불러오려 했는데 찾을 수 없을 때
	    // 4. NPE - null 값을 받았을때
	    // 5. 배열(ArrayIndexOutofBound) - 정해진 배열의 크기보다 크거나 음수 index를 받았을 때
	    
		
	    
	    
	    
	}

}
