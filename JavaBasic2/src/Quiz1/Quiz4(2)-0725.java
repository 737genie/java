package Quiz1;

import java.util.Random;
import java.util.Scanner;

public class Quiz4 {

	public static void main(String[] args) {
		// 탱고의 탐험 준비
		
		Scanner sc = new Scanner(System.in);
		System.out.println("춤추는 고양이 탱고는 탐험을 떠나려고 합니다.");
		System.out.println("탐험 장소와 날씨에 따라 준비물이 달라집니다.");
		
		// 사용자에게 어디로 떠날 것인지 숫자로 입력 받기
		System.out.println("어디로 떠나시겠습니까? (숫자로 답변해주세요.)");
		System.out.println("1. 숲, 2. 바다");
		
		int UserInput = sc.nextInt();
		
		// 랜덤으로 날씨를 추출하는 구문 제작
		String[] Weather = {"맑음", "비"};
		Random random = new Random();
		
		String Result = Weather[random.nextInt(2)];
		
		// 중복되는 반응이 없으므로 switch-case 구문을 통해 응답 출력
		switch (UserInput) {
		case 1:
			System.out.println("1번 숲으로 탐험을 떠날 준비를 합니다.");
			if (Result == "비") {
				System.out.println("숲의 날씨는 비가 내리고 있습니다.");
				System.out.println("필요한 준비물: 우비, 장화");				
			} else {
				System.out.println("숲의 날씨는 맑습니다.");
				System.out.println("필요한 준비물: 선글라스, 나침반");
			}
			break;
		case 2:
			System.out.println("2번 바다으로 탐험을 떠날 준비를 합니다.");
			if (Result == "비") {
				System.out.println("바다에 비가 내리고 있습니다.");
				System.out.println("오늘은 탐험을 쉽니다 ...");
			} else {
				System.out.println("바다의 날씨는 맑습니다.");
				System.out.println("필요한 준비물: 수영복, 선크림");
			}
			break;
		default:
			System.out.println("선택지에 없는 곳입니다.");
		}
	}

}

