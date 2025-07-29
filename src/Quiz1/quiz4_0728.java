package Quiz1;

import java.util.Scanner;

public class quiz4_0728 {

	public static void main(String[] args) {
		// 4. 누렁이의 밥그릇 상태
		Scanner sc = new Scanner(System.in);
		
		System.out.println("누렁이의 밥 그릇 상태는? ex. 가득참, 절반남음, 비었음");
		String Food = sc.next();
		
		if (Food.equals("가득참")) {
			System.out.println("헤헤, 행복해! 꼬리를 흔든다.");
		}
		else if (Food.equals("절반남음")) {
			System.out.println("음... 나쁘지 않군. 킁킁거린다.");
		}
		else if (Food.equals("비었음")) {
			System.out.println("밥 줘!!! 밥그릇을 긁는다.");
		}
		else {
			System.out.println("그게 뭐야? 먹는 건가? 갸우뚱한다.");
		}

	}

}
