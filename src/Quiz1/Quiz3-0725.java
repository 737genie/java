package Quiz1;

import java.util.Scanner;

public class Quiz3 {

	public static void main(String[] args) {
		// 누렁이의 간식 시간
		
		// 사용자에게 시간 입력 받기
		Scanner sc = new Scanner(System.in);
		System.out.println("현재 시간을 입력해주세요. (0~23)");
		int UserTime = sc.nextInt();
		
		// 반복문으로 응답 출력
		if (UserTime < 12) {
			System.out.println("아침엔 역시 우유껌이지!");
		} else if (UserTime >= 12 && UserTime < 16) {
			System.out.println("나른한 오후엔 육포 스틱!");
		} else if (UserTime >= 16) {
			System.out.println("저녁엔 뼈다귀가 최고야!");
		}
		sc.close();
	}

}
