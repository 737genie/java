package OOP0807;

import java.util.ArrayList;
import java.util.List;

public class GenericExam {

	public static void main(String[] args) {
		
		// generic = 타입의 파라미터화
		// -> 실행 시 구체적으로 해당하는 타입을 결정한다는 의미 
		
		// 제네릭이 구체적으로 필요한 이유 (컬렉션)
		List list1 = new ArrayList();
		
		list1.add("1234");
		list1.add(5678);
		// 타입 지정 안 해주면 추후에 문제 발생할 가능성 높음 
		// -> 제네릭은 타입 안정성을 높임
		// -> 불필요한 형변환을 제거 할 수 있음
		
		System.out.println(list1.get(0));
		System.out.println(list1.get(1));
		// ============================================================
		
		// 제네릭이 반드시 필요한가?
		// -> 걍 애초부터 통제해서 쓰면 오류났을때 디버깅하기 수월하겠지ㅇㅇ
		
		
	}

}
