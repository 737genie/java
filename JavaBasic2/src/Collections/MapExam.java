package Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExam {

	public static void main(String[] args) {
		// Map : key-value 형태로 이루어진 컬렉션 타입
		// key가 필요한 이유
		// 1. 데이터의 구분 - key값은 무결성이 원칙(중복 없음), value는 중복 가능
		// 2. 단건 검색에 유리 - key값을 기준으로 빠르게 접근
		// 3. 현존하는 데이터셋, no-sql, yaml, json등 활용하는 곳이 많음
		 
		// java에서 key-value를 표현하기 위해 쓰는게 Map
		
		// 특징
		// 1. key-value를 한 묶음으로 저장하는 형식
		// 2. key 중복x value 중복o
		// 3. 순서는 없음
		// 4. null을 허용하는 컬렉션도	` 있고 아닌 경우도 있음

		// 종류
		// 1. HashMap(신버전)
		// 	  - 순서 없이 가장 빠른 속도로 데이터 저장, 조회
		//		데이터 순서와 상관없이 key로 신속한 데이터 조회가 최우선일 때 사용
		// 2. LinkedHashMap
		//	  - HashMap + 입력된 순서 기억하는 기능 추가
		// 		입력 순서가 중요한 데이터를 key-value 형태로 관리할 때 사용
		// 3. TreeMap
		//	  - 트리 구조 기반의 map
		//		파이썬 딕셔너리와 유사한 구조 - 사전만들기, 특정범위 데이터 검색 시 사용
		// 4. HashTable(HashMap의 옛 버전)
		
		// 활용 예시
		// 1. key와 value의 관계를 알아보자
		
		Map<String, String> foodMap = new HashMap<>();
		foodMap.put("바쁘냥", "고등어");
		foodMap.put("급하개", "육포");
		foodMap.put("와플곰", "꿀");
		foodMap.put("누렁이", "뼈다구");
		
		String key1 = "누렁이";
		
		System.out.println(key1+"이(가) 좋아하는 간식은? : "+foodMap.get(key1));
		
		
		// 2.
		
		String[] p = {"급하냥", "카피바라", "누렁이"};
		String[] c = {"카피바라", "급하냥"};
		
		Map<String, Integer> pMap = new HashMap<>();
		
		// 구별을 위해 참가자 명단을 순회하며 이름과 인원수를 맵에 저장
		for(String a : p) {
			pMap.put(a, pMap.getOrDefault(a, 0)+1);
		}
		
		// 완주자 명단을 순회하며 맵에서 인원수를 1씩 제거
		for(String b : c) {
			pMap.put(b, pMap.get(b)-1);
		}
		
		// 값이 0이 아닌 즉 1인 선수가 완주하지 못한 선수이기 때문에
		// 값이 1인 선수를 찾아서 리턴
		
		Set<String> s1 = pMap.keySet();
		
		for(String key : s1) {
			// 한 건 이상이면 이 방식은 비효율적
			// Set타입 컬렉션 선언해서 거기 따로 담아두는게 더 좋음
			if(pMap.get(key)!=0) {
				System.out.println("완주하지 못한 선수는 "+key+"입니다.");
			}
		}
		
		
		// set 방식은 이렇게
		
//		Set<String> s1 = new HashSet<>();
//		
//		for(String key : pMap.keySet()) {
//			// 한 건 이상이면 이 방식은 비효율적
//			// Set타입 컬렉션 선언해서 거기 따로 담아두는게 더 좋음
//			if(pMap.get(key)!=0) {
//				s1.add(key);
//			}
//		}
//		
//		System.out.println("완주하지 못한 선수는 "+s1+"입니다.");
		
		
		
		System.out.println(pMap);
	}

}
