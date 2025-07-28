package Quiz1;

import java.util.Scanner;

public class Quiz2 {

	public static void main(String[] args) {
		// 꿀 감별사 와플곰
		
		//사용자에게 입력 받기
		Scanner sc = new Scanner(System.in);
		System.out.println("꿀의 당도를 입력해주세요.");
		int Honey = sc.nextInt();
		System.out.println("꿀의 향을 입력해주세요.");
		String Scent = sc.next();
		
		// 아카시아 문자열 비교를 위해 변수에 할당
		String Acacia = "아카시아";
		
		// 반복문으로 답변 출력하기
		if (Honey >= 80 && Scent.equals(Acacia) == true) {
			System.out.println("최고급 아카시아 꿀이로구나!");
		}
		else if (Honey >= 80 && Scent.equals(Acacia) == false) {
			System.out.println("달콤하지만 향이 아쉽구나.");
		}
		else if (Honey < 80 && Scent.equals(Acacia)) {
			System.out.println("향은 좋은데 당도가 부족해.");
		}
		else {
			System.out.println("이건 그냥 설탕물이잖아!");
		}
		
		sc.close();
	}

}
