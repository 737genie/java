package Util;

import java.util.ArrayList;
import java.util.List;

public class Object {

	// 유틸리티 클래스 
	// -> 특정 분야의 반복적이거나 어려운 작업에 쓰이는 유용한 기능들을 모아둔 클래스
	// -> 쉽고 효율적으로 처리 가능
	
	// 특징
	// 1. 대부분 static 메서드
	//   - 인스턴스 생성 없이 클래스명.메서드명()으로 쓰는 경우가 많음
	
	
	// Object 클래스
	// - 자바의 최상위 클래스
	// - 주로 null값 관리에 사용됨 (NPE 방지)
	
	// NPE는 언제 발생하는가?
	// 1. null 값이 들어간 인스턴스의 메서드나 멤버변수를 호출할 때
	// 2. null 객체로 equals 기반 비교시.
	// :: null값이 들어간 객체를 접근할 때 에러 발생, 근데 null값 자체를 비교하는 건 가능
	// ex. if(null1 == null) 가능
	// ex. if(null1.equals(null)) 불가능
	
	String null1 = null;
//		System.out.println(null1.length());
	
	
	
	
	public static void main(String[] args) {
		// Object의 메서드들
		// 1. toString() : 객체의 주소값(해시코드 값) 
		// 해시코드 값 - 데이터의 고유한 식별자를 생성하기 위해 사용하는 값
		// ! 데이터를 빠르게 검색할 수 있지만 서로 다른 데이터가 동일한 해시코드를 갖는 경우가 발생
		// --> 객체의 정보를 커스터마이징한 문자열로 표현하기 위해 정의된 메서드
		// ==> 현업에서 디버깅할 때 많이 씀 (운영에서는 xx)
		// -> __str__()
		
		// 2. equals : 참조된 해시코드를 비교
		String str1 = new String("test1");
		String str2 = new String("test1");
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode()); // 위와 같음
		// !해시코드가 같다고 equals가 무조건 true는 아님
		
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode()); // 위랑 다름
		
		// 3. getClass() : 클래스 정보를 리턴하는 메서드
		// 3-1. getName() : java.lang.String
		// 3-2. getSimpleName() : String
		
		// 4. Arrays : 배열을 다루기 위한 기능을 제공
		// Arrays.toString() : 배열 해시코드 아니고 문자열 자체
		// sort() : 배열의 정렬
		// binarySearch(배열명, 찾을값) : 배열에서 특정값 찾는 메서드
		// equals(배열1, 배열2) : 두 배열이 내용과 순서가 똑같은지 비교
		// asList(요소들, ..., ...) : 일반 데이터를 List 타입으로 변경
		// 
		
//		와플곰의 요청: "오늘 판매된 와플들의 칼로리가 int 배열로 있어요. "
//		이 중에서 200칼로리가 넘는 '고칼로리 와플'이 몇 개인지, 
//		그리고 그 와플들의 평균 칼로리는 얼마인지 빨리 계산해서 보고서에 써야 해!"
		
		int[] calories = {150, 350, 250, 400, 180};
		List<Integer> highCalories = new ArrayList<>();
		
		for(int c : calories) {
			if(c>=200) {
				highCalories.add(c);
			}
		}
		
		int sum1 = 0;
		for(int a : highCalories) {
			sum1 += a;
		}
		
		System.out.println("고칼로리 와플의 개수: "+highCalories.size()+"개");
		System.out.println("고칼로리 와플들의 평균 칼로리: "+sum1);
		
		
		
		
	}

}
