package OOP0805;

// 추상화

// Animal 클래스의 makeSound
// 동물이 소리내는 건 맞지만 굳이 써야할까?
// -> 동물들별로 울음 소리가 다르니까 어차피 상속받아 쓸 거 아님?
//  -> 그러니까 울음 소리는 해당 클래스에서 알아서 처리하면 되잖아?
//   -> so, 최상위 클래스에는 코드 쓰지말고 메서드만 정의하고 밑에서 알아서 처리하게 두자

//class Animal {
//    private String name;
//    public Animal(String name) { this.name = name; }
//    public String getName() { return name; }
//    public void eat(String food) { System.out.println(this.name + "이(가) " + food + "을(를) 먹습니다."); }
//    public void makeSound() { System.out.println("동물이 소리를 냅니다."); }
//}

// Animal 클래스에 추상화 적용
// * abstract 위치: 접근제어자 abstract class
abstract class Animal {
	private String name;
	public Animal(String name) { this.name = name; }
	public String getName() { return name; }
	public void eat(String food) { System.out.println(this.name + "이(가) " + food + "을(를) 먹습니다."); }
	public abstract void makeSound(); // abstract > 이 메서드는 반드시 구현해야함
	public abstract void performTalent();
}

class Dog1 extends Animal {
	public Dog1(String name) {super(name);}
	@Override
	public void makeSound() {System.out.println(getName()+": 멍멍");}
	public void performTalent() {System.out.println(getName()+"의 장기: 원반 물어오기!");}
}

class Bear extends Animal {
	public Bear(String name) {super(name);}
	@Override
	public void makeSound() {System.out.println(getName()+": 왁");}
	public void performTalent() {System.out.println(getName()+"의 장기: 1초만에 꿀 1리터 퍼먹기!");}
}

class Tiger extends Animal {
	public Tiger(String name) {super(name);}
	@Override
	public void makeSound() {System.out.println(getName()+": 우왕");}
	public void performTalent() {System.out.println(getName()+"의 장기: 콘푸로스트 먹기!");}
}



//class Dog1 extends Animal {
//    public Dog1(String name) { super(name); }
//    @Override
//    public void makeSound() { System.out.println(getName() + ": 멍멍!"); }
//    public void wagTail() { System.out.println(getName() + "이(가) 신나게 꼬리를 흔듭니다. ♪"); }
//}
//
//class Cat extends Animal {
//    public Cat(String name) { super(name); }
//    @Override
//    public void makeSound() { System.out.println(getName() + ": 야옹~"); }
//    public void knead() { System.out.println(getName() + "이(가) 만족스럽게 꾹꾹이를 합니다."); }
//}

// 추상화와 인터페이스
public class AbstractExam {
	// 추상화: 내용보다는 핵심적인 개념을 추려내는 것
	// -> 추상화 과정에서 공통적인 것들을 추려내는 것
	
	// 추상화의 핵심
	// 1. 필요한 것만 추출
	// 2. 어떻게 보다는 무엇에 집중
	
	// --- 추상화 관련 용어 ---
	// 1. 추상클래스 - 규칙(필수 조항을 규정할 수 있음), 설계 안내서, 미완성 설계도
	//  -> 추상클래스를 기반으로 제대로 된 클래스를 만들기
	//   -> 특징
	// 		1) 인스턴스 생성 x
	// 		2) 추상메서드 최소 1개 이상
	//  	3) 일반 메서드가 추상메서드 호출 가능
	// 		4) 현업에서 공통 개발자들이 추상클래스(추상메서드가 포함된)를 만들어 주면
	//         이런 메서드들을 만들어 달라는 의미로 씀
	// 		
	// 2. 추상메서드 
	
	public static void main(String[] args) {
		System.out.println("캐릭터 마을 장기자랑 대회!");
		Animal[] contest = {
				new Dog1("누렁이"),
				new Bear("와플곰"),
				new Tiger("호돌이")
		};
		
		for(Animal con : contest) {
			con.makeSound();
			con.performTalent();
		}
	}
}
