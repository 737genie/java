package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//람다를 활용한 데이터 그룹화
//Map.computeIfAbsent() : 맵에 특정키가 없을 경우에만 주어진 람다를 실행하고 
//							그 결과를 값으로 넣어주는 method

// 예시: 와플곰의 창고정리
// 사과, 우유, 밀가루, 오렌지, 치즈 / 과일, 곡물, 유제품
// -> if문으로 일일이 구분하려니 귀찮음



public class LambdaExam12 {
	public static String getCategory(String param1) { // method는 클래스 내부에
		if(param1.equals("사과") || param1.equals("오렌지")) return "과일";
		if(param1.equals("우유") || param1.equals("치즈")) return "유제품";
		return "곡물";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] ingredients = {"사과", "우유", "밀가루", "오렌지", "치즈"};
		Map<String, List<String>> pantry = new HashMap<>();
		
		for(String i : ingredients) {
			String category = getCategory(i);
// *computeIfAbsent : 맵에 특정키가 존재하면 아무것도 안함, 없으면 제공된 람다식 실행하고 맵에 추가
// 만약 category 라는 key가 맵에 없다면
// key -> new ArrayList<>() 이 코드를 실행해서
// 새로운 리스트를 만들고 값으로 넣어라.
			pantry.computeIfAbsent(category, key 
					-> new ArrayList<>()).add(i);
		}
		
		System.out.println("와플곰의 창고 정리 결과");
		System.out.println(pantry);
		
	}

	

}
