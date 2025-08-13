package Util;

import java.util.List;

public class StringExam {

	public static void main(String[] args) {
		// 자바와 문자열
		// String StringBuffer StringBuilder
		// -> 가장 적절한 녀석을 쓰느냐 못쓰느냐에 따라 성능차이가 생각보다 많이 남
		
		// String의 특징
		// 1. 불변 : 만들어진 String 객체는 내용이 변하지 않는다.
		// -> 기준: 프로그램 실행 되는 동안
		String a ="군침이";
		String b ="싹도노";
		
		// 2. + 연산 가능 :
		// 기존의 문자열이 바뀌는 게 아니고 새로운 객체가 생성 됨
		System.out.println(a+b);
		
		// 공간 관련 예시
		String noname = "";
		System.out.println("초기 noname의 주소" + System.identityHashCode(noname));
		
		noname = noname+"집에";
		System.out.println("1차 데이터 추가" + System.identityHashCode(noname));
		
		// 3. String은 불변이라 불필요한 공간 낭비가 발생할 수 있음
		//    따라서 성능 상 손해를 보는 경우도 많아짐
		// 대처 : 가변형 String -> StringBuffer, StringBuilder
		
		// 가변형 String 
		// -> 작업 공간(주소)이 부여되면 그 곳에서만 움직임
		// 쓰는 법
		StringBuffer sb = new StringBuffer("test");
		StringBuilder sb2 = new StringBuilder("test2");
		
		// .append 문자열 추가
		sb.append("테스트"); //test테스트
		sb2.append("테스트2"); //test2테스트2
		
		// .insert 해당 인덱스에 문자열 추가
		sb.insert(2, "중간"); //te중간st테스트
		
		// .reverse 거꾸로 출력
//		System.out.println(sb.reverse()); //트스테ts간중et
		
		// .replace(첫인덱스, 끝인덱스, 대체할 문자열)
//		System.out.println(sb.replace(0, 0, noname)); //집에te중간st테스트
//		System.out.println(sb.replace(1, 2, noname)); //t집에중간st테스트
//		System.out.println(sb.replace(1, 4, noname)); //t집에st테스트
		
		
		// * String 전용 (가변형에는 없음)
		// .toUpperCase, toLowerCase : 영문 대소문자 전환
		String sb3 = "testabcd";
		System.out.println(sb3.toUpperCase());
		
		// -> 가변형에도 비슷한 메서드를 구현하고 싶다면
		// .setCharAt(해당인덱스, '문자') - 해당 인덱스에 있는 문자를 변경하고 싶을 때 사용
		sb2.setCharAt(0, 'A'); //Aest2테스트2
		System.out.println(sb2);
		
		
		// 가변형 String 언제 써야하나?
		// = 스레드 활용 환경이 아니라면 > Builder
		// = 멀티스레드 환경에서는 > Buffer가 유리함
		
		// StringBuilder 활용 예시
		
		// 시나리오 - 바쁘개의 일일 배송 보고서 만들기
		// 하루의 배송 내역을 정리하여 하나의 긴 문자열로 보고서 제출이 필요한 상황
		
		List<String> deliveries = 
				List.of("뼈다구", "참치캔", "무지개 꿀단지", "오예스"); 
		// .of() 리스트에 한번에 담을 수 있다
		
		StringBuilder report = new StringBuilder("=== 일일 배송 보고서 ===");
		for(String item: deliveries) {
			report.append("\r - 상품명 : ").append(item).append(" - 배송완료");
		}
		
		String finalRepo = report.toString();
		System.out.println(finalRepo);
		System.out.println(report);
		
		
		
		
	}

}
