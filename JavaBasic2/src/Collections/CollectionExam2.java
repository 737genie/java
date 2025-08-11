package Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionExam2 {

	public static void main(String[] args) {
		// 컬렉션 프레임워크 응용
		
		// 2. 팀 목록 - 이차원 배열과 유사
		// import 단축키 ctrl + shift + o 
		List<List<Integer>> teams = new ArrayList<>();
		
		List<Integer> teamA = new ArrayList<>();
		teamA.add(1);
		teamA.add(2);
		teamA.add(3);
		
		List<Integer> teamB = new ArrayList<>();
		teamB.add(4);
		teamB.add(5);
		teamB.add(6);
		
		teams.add(teamA);
		teams.add(teamB);
		
		System.out.println(teamA);
		System.out.println(teamB);
		System.out.println(teams);
		System.out.println(teams.get(0).get(1));
		System.out.println(teams.get(1));
		
		
		// 3. 
		
		Map<String, Map<String, Integer>> menu = new HashMap<>();
		Map<String, Integer> dessert = new HashMap<>();
		dessert.put("와플", 4500);
		dessert.put("아이스크림", 3000);
		
		Map<String, Integer> drinks = new HashMap<>();
		drinks.put("커피", 2500);
		drinks.put("주스", 5000);
		
		menu.put("디저트", dessert);
		menu.put("음료", drinks);
		
        System.out.println("--- 와플곰's 레스토랑 메뉴 ---");
        // 맵의 모든 키(카테고리)를 순회
//        for (String category : menu.keySet()) {
//            System.out.println("[" + category + "]");
//            // 해당 카테고리의 메뉴 목록(리스트)을 가져옴
//            List<String> items = menu.get(category);
//            for (String item : items) {
//                System.out.println("- " + item);
//            }
//        }
//		
	}

}
