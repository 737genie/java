package Basic;

import java.util.Scanner;

public class SwitchExam {

	public static void main(String[] args) {
		// switch case
		// 다중분기를 깔끔하게 처리하는 제어 구문
		
		// if문과 비교할 때, switch는 직관적으로 값을 기재
		
		// 기본 문법
//		switch() {
//		
//		case 1:
//			
//			break;
//			
//		case 2:
//			
//			break;
//		default: // 어떤 케이스에도 속하지 않는 경우 실행될 코드
//			
//			
//		}
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력해주세요.");
		int Number = sc.nextInt();
		
		switch (Number) {
		case 1: 
			System.out.println("오늘은 월요일입니다.");
			break;
		case 2: 
			System.out.println("오늘은 화요일입니다.");
			break;
		case 3: 
			System.out.println("오늘은 수요일입니다.");
			break;
		case 4: 
			System.out.println("오늘은 목요일입니다.");
			break;
		case 5: 
			System.out.println("오늘은 금요일입니다.");
			break;
		case 6: 
			System.out.println("오늘은 토요일입니다.");
			break;
		case 7: 
			System.out.println("오늘은 일요일입니다.");
			break;
		default:
			System.out.println("입력이 잘못되었습니다.");
			break;
		}
		
		
		
		// 이런식으로도 쓸 수 있음
		// 대신 default를 무조건 작성
		String day = sc.nextLine();
		String result = switch(day) {
			case "1" -> "월요일";
			default -> "ddd";
		};
		System.out.println(result);
		
		

	}

}
