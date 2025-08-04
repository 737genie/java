package OOP0804;

public class Animal {

	// 캐릭터들을 표현하기 위한 클래스를 만들고
	// 이 Animal 클래스는 캐릭터들이 동물계열인것을 활용하여
	// 모든 동물들이 공통으로 가질 속성과 행동을 정의할것.
	//  -> 동물들은 이 클래스를 상속받을것.
	
	// 필드 영역
	// 속성 :
	String name;
	String fSnack;
	String howl;
	
	// 생성자 영역
	// 꼭 안써도되는 속성들을 정의
	public Animal(String name, String fSnack) {
		this.name = name;
		this.fSnack = fSnack;
	}
	// 메서드 영역
	// 동물들이 하는 행동.
	void eat() {
		System.out.println("우걱우걱");
	}
	
	void introduce() {
		System.out.println("내 이름은 " + name + " 좋아하는 간식은 " +fSnack+ "입니다.");
	}
	
	void howl() {
		System.out.println(howl);
	}
}
// 구현해야하는게 뭐지?
// -> 나는 누렁이를 객체로 만들고싶어.
// Animal을 이미 선언할 준비가 완료.(코드 썼다 가정)
// -> 굳이 누렁이 관련 메서드나 맴버변수를 더 쓸게 있나?
//    (누렁이는 좀 이상하고 특별하니까 2족보행하면서 춤춘다 쳐보자
// 누렁이 객체를 구현하는법
// 1. Dog 클래스의 인스턴스로.
// 2. Dog를 또 상속받는 Noorung 클래스를 만들기.
//    (누렁이의 특유 행동이 있어서) -> 누렁이는 일반 개들과 다르게 춤을 춘다.
// 3. 생성자를 통해 이름과 간식은 굳이 따로 입력 x
// 4. 개들은 짖는게 조금 다르기때문에 howl메서드는 재정의.
// 5. 추가할 속성 당장은 없음.
// 6. 누렁이의 경우는 춤을 춘다 정도 추가
//     -> 누렁이의 특이 속성은 있는가?( 파란색 개 목걸이정도?)


class Dog1 extends Animal{

	public Dog1(String name, String fSnack) {
		super(name, fSnack);
		// TODO Auto-generated constructor stub
	}
	
	
	public void howl() {
		System.out.println("멍멍멍왈왈왈컹컹컹");
	}
}

class Noorung extends Dog1{
	
	String neck;

	public Noorung(String name, String fSnack) {
		super(name, fSnack);
		// TODO Auto-generated constructor stub
	}
	
	public void howl() {
		System.out.println("누렁누렁");
	}
	
	public void dance() {
		System.out.println("둠칫 두둠칫.");
	}
}



