package Basic;

import java.util.Random;
import java.util.Scanner;

public class RandomNumIf {

	public static void main(String[] args) {
		Random random = new Random();
		int RandomNum = random.nextInt(100)+1;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("랜덤 숫자 맞추기 게임!");
		System.out.println("1~100 사이의 숫자를 입력해주세요.");
		int UserInput = sc.nextInt();
		
		while (UserInput == 0) {
			if (UserInput == RandomNum) {
				System.out.println("정답입니다!");
				break;
			} else if (RandomNum > UserInput) {
				System.out.println("너무 작습니다.");
				System.out.println("숫자를 다시 입력해주세요.");
			} else {
				System.out.println("너무 큽니다.");
				System.out.println("숫자를 다시 입력해주세요.");
			}
			
		}
		
		sc.close();

	}

}
