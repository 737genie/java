package Util;

import java.util.List;
import java.util.Random;

public class Quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 퀴즈: 누렁이의 랩네임 생성기
		Random random = new Random();
		
		List<String> fav = List.of("배고픈 ", "뼈다귀 러버 ", "주인 산책견 ");
		
		StringBuilder rapName = new StringBuilder("MC ");
		int count = 0;
		
		while(count<2) {
			count ++;
			int ran = random.nextInt(fav.size());
			String ran2 = fav.get(ran);
			rapName.append(ran2);
		}

		
		System.out.println("### MC 누렁이, 랩네임 만들기 ###");
		System.out.println("누렁이: 정했다! 내 이름은... "+rapName+"!");
		
		
	}

}
