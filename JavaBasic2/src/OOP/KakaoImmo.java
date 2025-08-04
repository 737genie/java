package OOP;

public class KakaoImmo {

	// 누렁이 객체
	// 속성: 캐릭터 이름, 성격, 색상
	// 행동
	
	// 클래스 - 필드, 메서드	
	// 필드 - 속성
	// --> 필드, 메서드에 public을 적용해줘야 다른 파일에 가져다 쓸 수 있음
	public String name;
	public String character;
	public String color;
	public int age;
	
	// 메서드 - 행동
	public void moving() {
		System.out.println("누다다다 배달이요");
	}
	
	public void intro() {
		System.out.println("왈왈 나는 "+name+"이고 "+age+"살이야. 나는 뼈다구를 좋아해");
		// System.out.println("하이 나는 누렁이");
	}
	
	public void eat() {
		System.out.println("와구와구");
	}
	
}
