package Util;

import java.util.ArrayList;
import java.util.List;

// 람다 활용 예시
// 1. 요청사항 : 캐릭터 인기 투표 결과를 토대로 데이터를 정렬 
// 기준: 귀여움 수치 높은 순서대로

class Character {
	String name;
	int cuteness;
	public Character(String name, int cuteness) {
		this.name=name;
		this.cuteness=cuteness;
	}
	
	// toString 메서드는 최상위 클래스 Object에서 갖고 있음
	// 객체값이 바로 보고싶을땐 Object에서 오버라이드 해서 갖고온다는 개념으로 활용
	@Override
	public String toString() {
		return name + "(귀여움 : " + cuteness + ")";
	}
}

public class LambdaExam {
	
	public static void main(String[] args) {
		List<Character> characters = new ArrayList<>();
		characters.add(new Character("토순이", 50));
		characters.add(new Character("바쁘개", 60));
		characters.add(new Character("급하냥", 70));
		characters.add(new Character("와플곰", 85));
		characters.add(new Character("누렁이", 75));
		
		// 두 객체 값을 비교하여 정렬한다.
		// c2 - c1의 의미 : 내림차순 정렬
		// c1 - c2 는 오름차순 정렬
		characters.sort((c1, c2)-> c2.cuteness - c1.cuteness);
		System.out.println("탱고 : 인기투표 결과는?");
		System.out.println(characters);
		
	}
	
}
