package Quiz1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class diaryEntries {
	String mood;
	String message;
	public diaryEntries(String m, String m2) {mood=m; message=m2;}
	public String getMessage() {return message;}
}

public class quiz11_0818 {

	public static void main(String[] args) {
		
		List<diaryEntries> tosim2 = List.of(
				new diaryEntries("HAPPY", "오늘 햇살이 좋아서 기분이 날아갈 것 같았다!"),
				new diaryEntries("HAPPY", "누렁이가 맛있는 쿠키를 나눠줬다. 최고의 친구다!"),
				new diaryEntries("SAD", "오늘 회사에서 발표를 망쳤어. 준비를 정말 열심히 했는데..."),
				new diaryEntries("ANGRY", "내가 하는 일은 늘 가볍게 여겨지는 기분이야.")
				);

		List<String> happyMemories = 
				tosim2.stream()
				.filter(a->"HAPPY".equals(a.mood))
				.map(diaryEntries::getMessage)
				.collect(Collectors.toList());
		
		System.out.println("--- 토심이의 행복 회고록 ---");
		System.out.println(happyMemories);
		
	}

}
