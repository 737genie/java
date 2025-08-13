package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringExam2 {

	public static void main(String[] args) {
//		 바쁘개의 JSON 보고서 생성기
//		 바쁘개의 절규: "아, 시간이 없어 죽겠는데! "
//		 마을의 모든 캐릭터 정보를 JSON 형식으로 만들어서 서버에 보내야 해요! 
//		 '누렁이', '탱고', '와플곰'... 수백 명의 데이터를 
//		 { "name": "이름", "favoriteFood": "음식" } 
//		 이런 식으로 쉼표까지 다 맞춰서 만들어야 하는데, 
//		 String으로 더하기를 하다가 제 머리가 먼저 터지겠어요! 
//		 마지막 쉼표는 빼야 하는데, 코드가 스파게티처럼 꼬여서 디버깅하다가 날 새겠다고요!"
		
//        Map<String, String> characterData = Map.of(
//                "누렁이", "꿀 와플",
//                "탱고", "참치캔",
//                "카피바라", "오렌지"
//        );
//
//        String json = "{\n  \"characters\": [\n";
//        
//        int count = 0;
//        for (Map.Entry<String, String> entry : characterData.entrySet()) {
//            json += "    {\n";
//            json += "      \"name\": \"" + entry.getKey() + "\",\n";
//            json += "      \"favoriteFood\": \"" + entry.getValue() + "\"\n";
//            json += "    }";
//            
//            // 마지막 요소가 아닐 때만 쉼표를 추가하는 끔찍한 로직
//            if (count < characterData.size() - 1) {
//                json += ",\n";
//            }
//            count++;
//        }
//        
//        json += "\n  ]\n}";
//
//        System.out.println(json);
//        System.out.println("\n바쁘개: '으악! 쉼표 하나 잘못 찍어서 서버가 거부하면 어떡하지? 이 코드는 쳐다보기도 싫어!'");
		
        Map<String, String> characterData = Map.of(
                "누렁이", "꿀 와플",
                "탱고", "참치캔",
                "카피바라", "오렌지"
            );

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("  \"characters\": [\n");

            for (Map.Entry<String, String> entry : characterData.entrySet()) {
                jsonBuilder.append("    {\n");
                jsonBuilder.append("      \"name\": \"").append(entry.getKey()).append("\",\n");
                jsonBuilder.append("      \"favoriteFood\": \"").append(entry.getValue()).append("\"\n");
                jsonBuilder.append("    },\n"); // 일단 쉼표를 다 붙인다!
            }

            // 마지막에 추가된 불필요한 쉼표와 개행문자를 삭제
            if (!characterData.isEmpty()) {
                jsonBuilder.delete(jsonBuilder.length() - 2, jsonBuilder.length());
            }
            
            jsonBuilder.append("\n  ]\n");
            jsonBuilder.append("}");

            System.out.println(jsonBuilder.toString());
            System.out.println("\n바쁘개: '이거야! 로직이 깔끔해서 실수할 걱정이 없잖아! 1000명 데이터도 문제없겠어!'");
		
		
		
		
		
	}

}
