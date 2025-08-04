package Quiz1;

import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {
		// Q1. 토심이의 심경 변화
		
		//사용자 입력을 문자열로 받기
		Scanner sc = new Scanner(System.in);
		System.out.println("오늘은 무슨 요일인가요? 토심이의 기분을 알려드립니다 ... ");
		System.out.println("월, 화, 수, 목, 금, 토, 일 중에 한 가지를 골라 답해주세요.");
		String UserInput = sc.next();
		
		//반복문으로 응답 
		switch (UserInput) {
		case "금" :
			System.out.println("드디어 금요일! 신난다!");
			break;
		case "토" :
			System.out.println("행복한 주말이야!(활짝)");
			break;
		case "일" :
			System.out.println("행복한 주말이야!(활짝)");
			break;
		default:
			System.out.println("아직 주말이 멀었어...(시무룩)");
		}
		
		sc.close();

	}

}

