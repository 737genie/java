package Util;

import java.util.ArrayList;
import java.util.List;

// 두번째 예시
// 잔망루피의 아이디어 회의
// 요청사항: 파티의 아이디어를 받는 중, 근데 아이디어 중 이상한 것들이 있음
// 		  재미점수 5점 미만인 것들 목록에서 삭제

class PartyIdea {
    String idea;
    int funScore;
    public PartyIdea(String i, int s) { idea = i; funScore = s; }
    @Override public String toString() { return idea + "(재미: " + funScore + ")"; }
}


public class LambdaExam11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<PartyIdea> party = new ArrayList<>();
		party.add(new PartyIdea("숨 쉬기", 0));
		party.add(new PartyIdea("불꽃놀이", 10));
		party.add(new PartyIdea("복불복 놀이", 6));
		party.add(new PartyIdea("술 먹기", 8));
		party.add(new PartyIdea("UFC", 2));
		party.add(new PartyIdea("WWE", 3));
		
		System.out.println(party);
		System.out.println("잔망루피 : 쓸데없는 아이디어가 너무 많아 5점 미만 거른다.");
		
		// removeIf 조건식 간소화 method
		party.removeIf(party1 -> party1.funScore<5);
		System.out.println(party);
		
	}

}
