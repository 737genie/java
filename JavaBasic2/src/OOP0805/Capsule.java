package OOP0805;

// 추후 Spring에서 사용할 Lombok이라는 라이브러리를 이용하면
// 개발자들이 작성할 필요없이 어노테이션을 통해
// 코드를 작성한 것과 같은 효과를 볼 수 있음

// @Getter <- 요런거
// @Builder <- 요런거

public class Capsule {
	
	// 멤버 변수는 외부에서 접근하기 힘들게 private으로 부여
	private String test1;
	private int test2;
	private int test3;
	
	// getter, setter
	public String getTest1() {
		return test1;
	}
	public void setTest1(String test1) {
		this.test1 = test1;
	}
	public int getTest2() {
		return test2;
	}
	public void setTest2(int test2) {
		this.test2 = test2;
	}
	public int getTest3() {
		return test3;
	}
	public void setTest3(int test3) {
		this.test3 = test3;
	}
	
	// 현업에서 쓰는 캡슐화 예시
	// getter , setter
	
	// 클래스 멤버변수에 직접적인 접근을 방어하기 위해 사용하는 개념
	// -> 메서드를 통해서만 클래스 멤버변수로의 접근을 허용하겠다, 는 의미
	
	// 왜? -> 예상치 못한, 불필요한 접근을 막기 위해
	
	// 현업에서 setter는 지양하는 편
	// setter의 문제점
	// 1. 객체는 기본적으로 불변성
	// -> setter가 있으면 객체 상태가 언제든지 바뀔 수 있음
	// -> 그러므로 setter가 있으면 캡슐화의 의미가 사라질 수 있음
	// 2. 의도 파악이 어려움(유지보수성 이슈)
	// 3. 캡슐화의 원칙 - 정보 은닉이 필수인데, setter는 public과 비슷하여 진짜 캡슐화로 보기 어려움
	
	// setter 대체
	// 1. 생성자 활용
	
	
	// 2. lombok 라이브러리를 이용하여 빌더 패턴 활용
	
	
	// 3. 의미있는 비즈니스 메서드를 직접 만들기
	//    -> 메서드 이름을 명확하게 만들어서 setter의 역할을 진행
	public void inputName(String param1) {
		this.test1 = param1;
	}
	
	// 4. 불변 객체 with 메서드를 활용
	
	
}


