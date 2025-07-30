package Quiz1;

import java.util.Scanner;

public class WhileForExam2 {

	public static void main(String[] args) {
		// 로그인 시도
		// gunchim, 9724
		
		String id = "gunchim";
		String pwd = "9724";
		
		Scanner sc = new Scanner(System.in);
		
		int chance = 0;
		while (true) {
			
			if (chance == 3) {
				System.out.println("로그인 3회 실패하였습니다. 프로그램을 종료합니다.");
				break;
			}
			else {
				System.out.println("현재 로그인 시도 횟수: "+chance+"회");
				System.out.println("아이디를 입력해주세요");
				String UserId = sc.next();
				
				System.out.println("패스워드를 입력해주세요");
				String UserPwd = sc.next();
				
				if (UserId.equals(id) && UserPwd.equals(pwd)) {
					System.out.println("로그인에 성공하였습니다.");
					break;
				} 
				else if (UserId.equals(id) && UserPwd != pwd) {
					System.out.println("비밀번호가 틀렸습니다.");
					chance++;
				}
				else if (UserId != id) {
					System.out.println("존재하지 않는 아이디입니다.");
					chance++;
				}
			}
		}
		
		
		

	}

}
