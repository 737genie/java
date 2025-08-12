package Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RuleException extends Exception {
	public RuleException(String msg) {
		super(msg);
	}
}

class rule {
	public void id() throws RuleException{
		List<String> originId = new ArrayList<>();
		originId.add("nureongi");
		originId.add("tosim2");
		originId.add("wafflegom");
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력해주세요.");
		String userId = sc.next();
		for(String str: originId) {
			if(str.equals(userId)){
				throw new RuleException("중복된 아이디입니다.");
			}
		}	
	}
	
	public void pwd() throws RuleException{
		Scanner sc = new Scanner(System.in);
		System.out.println("비밀번호를 입력해주세요.");
		String userPwd = sc.next();
		if(userPwd.length() < 8) {
			throw new RuleException("비밀번호는 8자 이상이어야 합니다.");
		}
	}
	
	public void email() throws RuleException{
		Scanner sc = new Scanner(System.in);
		System.out.println("이메일을 입력해주세요.");
		String userEmail = sc.next();
		if(!userEmail.contains("@")) {
			throw new RuleException("이메일 형식이 틀렸습니다.");
		}
	}
}

public class CustomException3 {

	public static void main(String[] args) {
		rule register = new rule();
		try {
			register.id();
			register.pwd();
			register.email();
		} catch(RuleException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
