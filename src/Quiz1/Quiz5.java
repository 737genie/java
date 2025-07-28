package Quiz1;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz5 {

	public static void main(String[] args) {
		// 친구들의 피크닉 바구니 채우기
		Scanner sc = new Scanner(System.in);
		
		// 빈 바구니 만들기
		ArrayList<String> Bag = new ArrayList<String>();
		
		// 바구니 배열 길이
		int length = Bag.size();
		
		
		System.out.println("친구들이 피크닉을 위해 음식을 준비했습니다!");
		System.out.println("준비된 음식 중 마음에 드는 음식을 골라 바구니에 담아주세요.");
		System.out.println("바구니에는 3개의 음식만 들어갈 수 있습니다.");
		
		// 반복문으로 음식 바구니 채우는 로직 작성
		while (true) {
			System.out.println("============================");
			System.out.println("음식 목록: 스테이크, 당근, 꿀, 피자");
			System.out.println("바구니에 채워진 음식 개수: " + length);
			
			
			String UserInput = sc.next();
			
			// 바구니에 든 음식의 숫자대로 반복문 조건 작성
			if (length == 0 | length == 1) {
				switch (UserInput) {
				case "스테이크" :
					System.out.println("스테이크는 누렁이가 좋아한다.");
					Bag.add("스테이크");
					break;
				case "당근":
					System.out.println("당근은 토심이가 좋아한다.");
					Bag.add("당근");
					break;
				case "꿀":
					System.out.println("꿀은 와플곰이 좋아한다.");
					Bag.add("꿀");
					break;
				case "피자":
					System.out.println("피자는 모두가 좋아한다.");
					Bag.add("피자");
					break;
				default:
					System.out.println("없는 음식입니다. 다시 골라주세요.");
					break;
				}
			} else if (length == 2) {
				switch (UserInput) {
				case "스테이크" :
					System.out.println("스테이크는 누렁이가 좋아한다.");
					Bag.add("스테이크");
					System.out.println("바구니가 가득 찼습니다! 피크닉 준비를 종료합니다.");
					break;
				case "당근":
					System.out.println("당근은 토심이가 좋아한다.");
					Bag.add("당근");
					System.out.println("바구니가 가득 찼습니다! 피크닉 준비를 종료합니다.");
					break;
				case "꿀":
					System.out.println("꿀은 와플곰이 좋아한다.");
					Bag.add("꿀");
					System.out.println("바구니가 가득 찼습니다! 피크닉 준비를 종료합니다.");
					break;
				case "피자":
					System.out.println("피자는 모두가 좋아한다.");
					Bag.add("피자");
					System.out.println("바구니가 가득 찼습니다! 피크닉 준비를 종료합니다.");
					break;
				default: 
					System.out.println("없는 음식입니다. 다시 골라주세요.");
					return;
				} break;
			}
				
			
		}

	}

}
