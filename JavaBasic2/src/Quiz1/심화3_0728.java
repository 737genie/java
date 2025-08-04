package Quiz1;

import java.util.Scanner;

public class 심화3_0728 {

	public static void main(String[] args) {
		// 심화3
		Scanner sc = new Scanner(System.in);
		
		System.out.println("오늘의 날씨를 입력해주세요. ex. 비, 맑음, 흐림");
		String Weather = sc.next();
		System.out.println("오늘의 미세먼지 수치를 입력해주세요.");
		int Dust = sc.nextInt();
		
		if (Dust>=80) {
			System.out.println("오늘은 산책 금지!(미세먼지 최악)");
		} else if (Dust<80) {
			if (Weather.equals("비")) {
				System.out.println("우비 입고 짧은 산책 가능!");
			}
			else if (Weather.equals("맑음")) {
				System.out.println("최고의 산책 날씨! 공놀이 준비!");
			}
			else if (Weather.equals("흐림")) {
				System.out.println("무난한 날씨! 동네 한 바퀴 산책 가능!");
			}
			else {
				System.out.println("날씨 입력이 잘못 되었습니다.");
			}
		} else if (Dust<0) {
			System.out.println("미세먼지 수치 입력이 잘못 되었습니다.");
		}

	}

}
