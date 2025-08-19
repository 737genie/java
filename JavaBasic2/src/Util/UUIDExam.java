package Util;

import java.util.UUID;

public class UUIDExam {

	public static void main(String[] args) {
		// UUID : 절대 겹치지 않는 이름표 생성기 - 고유 식별자를 생성해줌
		// 사용처 > 
		
		String item1 = "누렁이의 금가루 바른 뼈다구";
		String item2 = "급하냥의 초 거대 참치캔";
		
		UUID tak1 = UUID.randomUUID();
		UUID tak2 = UUID.randomUUID();
		System.out.println(item1+"에 코드값 "+tak1+"을 부여하겠습니다.");
	}

}
