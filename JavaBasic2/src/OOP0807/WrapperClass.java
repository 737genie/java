package OOP0807;

import java.util.ArrayList;
import java.util.List;

public class WrapperClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Wrapper Class(포장클래스)
		//  -> 기본타입의 값을 객체로 포장하는 클래스
		//  -> 기본타입을 클래스처럼 바꿔주는거.
		//  (컬렉션에서 기본타입은 사용불가)
		
		// 기본적으로 컬렉션프레임워크는 객체만 저장이 가능.
		//  -> 기본타입은 객체가 아니냐?
		//  -> 큰 범위로보면 모든것이 객체는 맞음 다만 기본타입은 
		//     자바에서 별도로 순수한 값으로만 취급. 그래서 직접담을수 없음.
		
		// 즉 일반적인 값들을 객체로 포장한다 생각하면 편함.
		
		// 기본 타입       -> 포장        래퍼클래스
		//  int							 Integer
		//  long                         Long
		//  double                       Double
		//  float						 Float
		//  boolean						 Boolean
		//  char                         Character
		
		// 오토박싱 : 기본타입 -> 참조타입(래퍼클래스)로 바꾸는것.
		
		Integer test = 100; // ????????
		int test2 = 100;
		
		int result = test;
		
		List list1 = new ArrayList();
		list1.add(9724);
		list1.add(test);
		
		// 언박싱 : 래퍼클래스 -> 기본타입
		// 만약 일반타입으로 객체에 있는 값을 받을때 문제가 생긴다면
		// 래퍼클래스를 활용하여 언박싱후 저장해줄 필요가 있음.
		int val1 = (Integer) list1.get(1);
		
		// 래퍼클래스에서 일반타입으로 변경할때는 (Integer)보다 메서드사용을 권장.
		// intValue(), doubleValue()
		
		// 퀴즈
		String str1 = "9724";
		// str1번에 있는 문자 9724를 숫자 9724로 바꿔주세요.
		// 힌트 : char 타입을 활용하는 방법이 있음.
		//       아스키 코드에 대해 확인해보기.
		
		int num1 = Integer.parseInt(str1); // 문자>숫자
		System.out.println(num1);
		
//	    int result2 = 0;
//	    int len = str1.length();
//	    for (int i = 0; i < len; i++) {
//	        char c = str1.charAt(i);
//	        int digit = c - '0';
//	        result2 = result2 * 10 + digit;
//	    }
//	    Integer change = result2;
//	    System.out.println(change.getClass().getName());
		
		// 참조 타입: 지금까지 만든 모든 클래스, 컬렉션이나 String 같은 객체들
		// -> 기본타입은 실질적인 리터럴 값들만 저장
		// -> 참조타입은 객체가 저장된 메모리의 주소를 저장하는 형태
		
	}
	

}