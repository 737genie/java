package OOP0804;

// 캐릭터 마을에 입주하는 녀석들을 객체로 구현.
// 누렁이, 바쁘개, 탱고, 급하냥
// 이름, 좋아하는 간식(필드)

// 먹기, 자기소개하기, 울기

class Character{
	String name;
	String fSnack;
	String howl;
	
	public Character(String name, String fSnack) {
		this.name = name;
		this.fSnack = fSnack;
	}

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

class Dog extends Character{

	String skill;
	// 필드는 부모에 있으면 공유한다.
	// 추가로 필요한 필드(맴버변수)가 있으면 추가로 작성하면 된다.
	
	// 메서드는 당연히 부모에 선언되어있는 메서드는 그대로 사용가능.
	public Dog(String name, String fSnack) {
		super(name, fSnack);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void eat() {
		System.out.println("와구와구허겁지겁");
	}
	
	@Override
	public void howl() {
		System.out.println("멍멍멍왈왈왈컹컹컹");
	}
}

class Cat extends Character{

	public Cat(String name, String fSnack) {
		super(name, fSnack);
		// TODO Auto-generated constructor stub
	}
	
	public void eat() {
		System.out.println("까드득까드득");
	}
	
	public void howl() {
		System.out.println("냐오오오옹");
	}
	
}

class Bear{
}

class Rabbit{
}

class kapybara{
}

//class Bird extends Character{
//
//	public Bird(String name, String fSnack) {
//		super(name, fSnack);
//		// TODO Auto-generated constructor stub
//	}
//	
//}


public class InheritanceExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 상속 : 물려받는거
		//  -> 부모로부터 자식이 물려받는것.
		//  -> 부모 클래스로 부터 자식클래스가 메서드들을 물려받는것.
		
		// 아니 근데 그냥 클래스들 각자 다 따로 만들면 되는거 아님?
		//  -> 상속을 잘 활용하면 코드의 재사용성과 확장성을 증대하는 효과가 있음.
		//  -> OCP준수에도 많은 도움을 줌
		
		// 상속관련된 주의사항
		// 상속을 실전에서 잘 활용하기 위해서는
		// 클래스의 관계도 자체가 아주 세밀하게 설계되어있어야함.
		// 상속을 설계할때에는 당연히 현실세계 개념과 거의 일치하는 형태로 가는게 유리.
		// 부모클래스에 많은기능들을 부여하진않음.
		//  -> 부모클래스는 모든 자식클래스들이 공통적으로 쓸만한것들만 부여.
		// 상속은 대신 필요한만큼만 해야함.
		//  -> 상속을 불필요하게 남용하다가 재사용성을 역으로 해칠수 있음
		//     (성능 이슈를 유발할수도 있다)
		
		// 오버라이딩 위에 붙은 어노테이션 요구안해도 쓰세요....
		
		
		Dog noorung = new Dog("누렁이", "뼈다구");
		
		Cat speedCat = new Cat("급하냥", "치킨");
		
		noorung.eat();
		noorung.introduce();
		noorung.howl();
		
		speedCat.eat();
		speedCat.howl();
		
		
	}

}