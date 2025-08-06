package OOP0806;

class UserManage {
	String name;
	String email;
	String password;
	public UserManage(String name, String email, String password) {
		this.name=name;
		this.email=email;
		this.password=password;}
	public void createUser() {
		System.out.println("{"+name+"}님의 가입이 성공적으로 진행 되었습니다.");
		RegisterMail mail = new RegisterMail();
		mail.CheckMail(email);
		LogManage log = new LogManage();
		log.createLog(name);
		}
	public void deleteUser() {
		System.out.println("{"+email+"}유저가 삭제 되었습니다.");
		LogManage del = new LogManage();
		del.deleteLog(email);
	}
}

class RegisterMail {
	public void CheckMail(String email) {System.out.println(email+"로 가입 메일 발송");}
}

class LogManage {
	public void createLog(String name) {System.out.println("{"+name+"}님의 가입 로그");}
	public void deleteLog(String email) {System.out.println("{"+email+"}유저 로그 삭제");}
}

public class SRPExam2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManage user1 = new UserManage("aaa", "aaa@nagdf.com", "aaasdff");
		user1.createUser();
		user1.deleteUser();
	}

}
