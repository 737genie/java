package OOP0801;

public class OLExam2 {
	
	// 사용자 개인정보를 저장(회원가입)
	private String name;
	private String email;
	private int age;
	private String phone;
	
	

	public OLExam2(String name, String email, int age, String phone) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
	}

	public OLExam2() {
		this.name = "none";
		this.email = "none@google.com";
		this.age = 0;
		this.phone = "010-1234-5678";
	}


	public OLExam2(String name) {
		this(); // 기본생성자 호출 코드 (기본값 세팅)
		this.name = name;
	}
	
	public OLExam2(String name, String email) {
		this();
		this.name = name;
		this.email = email;
	}

	public OLExam2(String name, String email, int age) {
		this();
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public void display() {
		System.out.println("회원정보");
		System.out.println("이름:" + name);
		System.out.println("이메일:" + email);
		System.out.println("나이:" + age);
		System.out.println("전화번호:" + phone);
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 생성자 오버로딩(Entity, DTO, VO)
		// DTO - (Data Transfer Object)
		// VO - (Value Object) 
		// 현업에서 핸드폰번호를 문자열 타입으로 처리하는 이유
		// "-", 데이터의 변조를 막기위해, 현업에서 정수형, 실수형타입을 선언하는 경우는
		//  그 수치를 계산하느냐 아니냐의 기준도 있음.
		//  (핸드폰 번호는 정해져있는 문자열의 조합으로 보기때문)
		
//		OLExam2 member1 = new OLExam2();
//		member1.display();
		
		OLExam2 member2 = new OLExam2("토심이");
		member2.display();
		
		OLExam2 member3 = new OLExam2("잔망루피", "gunchimssak@pororo.com");
		member3.display();		
		
		OLExam2 member4 = new OLExam2("누렁이", "guardDog@noorung.com", 3);
		member4.display();
		
	}

}