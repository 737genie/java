package STREAM;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamRefactoring {

	public static void main(String[] args) {
		// 리팩토링 : 코드의 기능은 유지하지만 코드의 구조는 개선하는 과정
		// -> 코드 4대 요소 높이기(가독성, 유지보수성, 재사용성, 확장성)
		// -> 변수명, 메서드로 분리, 중복 코드 제거
	
		// 리버스 엔지니어링 : 기존 제품이나 시스템 분석하여 구조 기능 동작원리 등을 이해 ->
		// 				  이를 바탕으로 새로운 제품 개발 혹은 기존 제품 개선하는 과정
			// 원래 있는거 분석하고 뜯어고치던가 아니면 새로운거 만들던가
		
		
		
		// -> 밑의 코드의 약점 : 전체적인 흐름 파악이 어려움
//        // 1. 참가자 이름(Key)과 인원수(Value)를 기록할 Map 생성
//        Map<String, Integer> participantMap = new HashMap<>();
//        
//        // 2. 참가자 명단을 돌면서 인원수를 센다.
//        // getOrDefault: 이름이 있으면 기존 값+1, 없으면 0+1
//        for (String p : participant) {
//            participantMap.put(p, participantMap.getOrDefault(p, 0) + 1);
//        }
//        
//        // 3. 완주자 명단을 돌면서 인원수를 1씩 뺀다.
//        for (String c : completion) {
//            participantMap.put(c, participantMap.get(c) - 1);
//        }
//        
//        // 4. 최종적으로 Map을 돌면서 값이 0이 아닌 (즉, 1인) 선수를 찾는다.
//        for (String key : participantMap.keySet()) {
//            if (participantMap.get(key) != 0) {
//                return key;
//            }
//        }
//        
//        return "";
		
		
		// 스트림을 활용해서 어떤 데이터를 원하는 가, 를 선언하는 방식으로 접근
		// 자연어 계획
		// 1. 마라톤 참가자의 명단과 완주자 명단 정리가 필요
		// 1-1. 참가자 명단 정리 -> 그룹화 기준 - 이름 
		// 1-2. 완주자 명단
		// 2. 
        
        // -> Stream API
//        Map<String, Long> participantCounts = Arrays.stream(participant)
//                .collect(Collectors.groupingBy(
//                    p -> p, // 그룹화 기준: 이름 그 자체
//                    Collectors.counting() // 그룹화 방법: 개수 세기
//                ));
//
//            // 2. 완주자 명단을 스트림으로 변환 후, Map에서 인원수를 1씩 뺀다.
//            Arrays.stream(completion)
//                .forEach(c -> participantCounts.put(c, participantCounts.get(c) - 1));
//
//            // 3. Map에서 최종적으로 인원수가 0이 아닌 참가자를 찾는다.
//            return participantCounts.entrySet().stream() // Map을 다시 스트림으로
//                .filter(entry -> entry.getValue() != 0) // 값이 0이 아닌 항목만 필터링
//                .map(Map.Entry::getKey)                 // 항목에서 이름(Key)만 추출
//                .findFirst()                            // 첫 번째로 찾은 항목을
//                .orElse("");                            // 반환 (없으면 빈 문자열)
		
		
		//  
		
		
	}

}
