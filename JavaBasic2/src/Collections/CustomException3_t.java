package Collections;

import java.util.HashSet;
import java.util.Set;

class RegistException extends Exception{
	public RegistException(String msg) {super(msg);}
}

class IdException extends RegistException{
	public IdException(String msg) {
		super(msg);
	}
}
class PwdException extends RegistException{
	public PwdException(String msg) {
		super(msg);
	}
}
class EmailException extends RegistException{
	public EmailException(String msg) {
		super(msg);
	}
}

class UserService{
	private Set<String> existingUserNames = new HashSet<>();
	public UserService() {
		existingUserNames.add("nureongi");
		existingUserNames.add("tosim2");
		existingUserNames.add("wafflegom");
	}
	public void register(
			String username, String pwd, String email
			) throws RegistException, IdException, PwdException, EmailException {
		System.out.println("[System] : "+username+"님의 회원가입 요청 처리 중 ...");

		if(existingUserNames.contains(username)) {
			System.out.println(username+"은 이미 사용 중인 아이디입니다.");
		}
		
		if(pwd.length()<8) {
			System.out.println("비밀번호는 8자 이상이어야 합니다.");
		}
		if(!email.contains("@")) {
			System.out.println("올바른 이메일 형식이 아닙니다.");
		}
		
		System.out.println("["+username+"]님 가입이 완료되었습니다.");
		existingUserNames.add(username);
	}
}

public class CustomException3_t {
	public static void main(String[] args) throws RegistException, IdException, PwdException, EmailException {
	    UserService userService = new UserService();
	
	    // 시도 1: 아이디 중복 (잔망루피가 누렁이 아이디로 가입 시도)
	    tryToRegister(userService, "nureongi", "password123", "loopy@village.com");
	
	    // 시도 2: 비밀번호 규칙 위반
	    tryToRegister(userService, "zanmang_loopy", "1234", "loopy@village.com");
	    
	    // 시도 3: 이메일 형식 위반
	    tryToRegister(userService, "zanmang_loopy", "password123", "loopy_is_cute");
	
	    // 시도 4: 성공
	    tryToRegister(userService, "zanmang_loopy", "password123", "loopy@village.com");
	}
	
	public static void tryToRegister(
			UserService userService, String username, String pwd, String email) {
		
		try {
			userService.register(username, pwd, email);
		}catch(IdException ie){
			System.out.println("아이디 에러: "+ie.getMessage());
		}catch(PwdException pe){
			System.out.println("패스워드 에러: "+pe.getMessage());
		}catch(EmailException ee){
			System.out.println("이메일 에러: "+ee.getMessage());
		}catch(RegistException re){
			System.out.println("가입에 실패했습니다. 관리자에게 문의 바랍니다.");
		}catch(Exception e) {
			e.getMessage();
		}
	}
}
