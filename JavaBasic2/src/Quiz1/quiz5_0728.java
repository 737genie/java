package Quiz1;

import java.util.Scanner;

public class quiz5_0728 {

	public static void main(String[] args) {
		// 5. 와플곰의 보물 상자
		// 와플곰이 세 개의 다이얼이 달린 보물 상자를 발견했습니다.
		Scanner sc = new Scanner(System.in);
		
		System.out.println("와플곰이 세 개의 다이얼이 달린 보물 상자를 발견했습니다.");
		System.out.println("보물 상자는 특정 조건을 달성하지 않으면 열리지 않습니다.");
		System.out.println("첫번째 다이얼의 색깔을 골라주세요.(숫자)");
		System.out.println("1. 빨강, 2. 파랑, 3. 노랑");
		
		int FirstButton = sc.nextInt();
		
		System.out.println("첫번째 다이얼: " + FirstButton);
		System.out.println("==============================");
		System.out.println("두번째 다이얼의 색깔을 골라주세요.(숫자)");
		System.out.println("1. 빨강, 2. 파랑, 3. 노랑");
		
		int SecondButton = sc.nextInt();
		
		System.out.println("첫번째 다이얼: " + FirstButton);
		System.out.println("두번째 다이얼: " + SecondButton);
		System.out.println("==============================");
		System.out.println("세번째 다이얼의 색깔을 골라주세요.(숫자)");
		System.out.println("1. 빨강, 2. 파랑, 3. 노랑");
		
		int ThirdButton = sc.nextInt();

		
		if (FirstButton==1 && SecondButton==1 && ThirdButton==1) {
			System.out.println("==============================");
			System.out.println("보물 상자가 열렸다!!!");
		}
		else if (FirstButton != SecondButton && SecondButton != ThirdButton && FirstButton != ThirdButton) {
			System.out.println("==============================");
			System.out.println("보물 상자가 열렸다!!!");
		}
		else {
			System.out.println("보물 상자는 열리지 않았습니다.");
		}
	}

}
