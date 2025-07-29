package Quiz1;

import java.util.Scanner;

public class quiz3_0728 {

	public static void main(String[] args) {
		// 3. 토심이의 외출 준비
		// 토심이는 외출할 때 날씨와 기분에 따라 옷차림이 달라집니다.
		Scanner sc = new Scanner(System.in);
		
		System.out.println("오늘 날씨는 어때? ex. 맑음, 흐림, 비");
		String Weather = sc.next();
		
		System.out.println("오늘 기분은 어때? ex. 신남, 슬픔, 그저그래");
		String Feeling = sc.next();
		
		if (Weather.equals("맑음")) {
			if (Feeling.equals("신남")) {
				System.out.println("소풍가기 좋은 날이야! 예쁜 리본을 해야지!");
			}
			else {
				System.out.println("오늘은 그냥 집에 있을래...");
			}
		}
		else {
			System.out.println("오늘은 그냥 집에 있을래...");
		}

	}

}
