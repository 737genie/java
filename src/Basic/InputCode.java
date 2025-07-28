package Basic;

import java.util.Scanner;

public class InputCode {

	public static void main(String[] args) {
		// Scanner
		// 사용자로부터 다양한 자료형이나 값들을 입력받는 객체(클래스)
		// 현업에서 안 씀
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("자기소개 프로그램");
		// 저장할 변수의 타입 신경 쓰기
		// 문자열은 next, nextLine 등의 표기법들이 있음
		System.out.println("당신의 이름을 입력하세요.");
		String name = sc.nextLine(); // 사용자가 입력할 때까지 대기
		
		System.out.println("당신의 나이를 입력하세요.");
		int age = sc.nextInt();
		
		if (age>40) {
			System.out.printf("안녕하세요 %s님! 나이가 %d세 시군요. 나이가 많으시네요", name, age);
		}else if(age<=40) {
			System.out.printf("안녕하세요 %s님! 나이가 %d세 시군요. 어리시네요", name, age);
		}
		
		sc.close();
		
		// 입력 받을 때에는 BufferedReader라는 객체 활용하는 것을 권장
	}

}
