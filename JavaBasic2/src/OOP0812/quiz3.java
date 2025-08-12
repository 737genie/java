package OOP0812;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quiz3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, List<String>> Meditation = new HashMap<>();
		
		List<String> tosim2 = new ArrayList<>();
		List<String> tango = new ArrayList<>();
		List<String> nurung = new ArrayList<>();
		
		tosim2.add("물소리 명상");
		tosim2.add("새벽 명상");
		tango.add("새벽 명상");
		nurung.add("저녁 명상");
		
		Meditation.put("토심이", tosim2);
		Meditation.put("탱고", tango);
		Meditation.put("누렁이", nurung);
		
		String key[] = {"토심이", "탱고", "누렁이"};
		
		System.out.println("### 카피바라의 명상 클럽 회원 관리 ###\r");
		
		System.out.println("--- 1. 회원별 참여 세션 목록 ---");
		for(int i=0; i<key.length; i++) {
			System.out.println(key[i]+": "+Meditation.get(key[i]));
		}
		
		System.out.println("--- 2. '새벽 명상' 세션 참여자 조회 ---");
		for(int i=0; i<key.length; i++) {
			List<String> a = Meditation.get(key[i]);
			if(a.equals("새벽 명상")) {
				System.out.println(" - "+key[i]);
			}
		}
		
	}

}
