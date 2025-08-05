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
	public abstract void makeSound();
}

class Dog1 extends Animal {
    public Dog1(String name) { super(name); }
    @Override
    public void makeSound() { System.out.println(getName() + ": 멍멍!"); }
    public void wagTail() { System.out.println(getName() + "이(가) 신나게 꼬리를 흔듭니다. ♪"); }
}

class Cat extends Animal {
    public Cat(String name) { super(name); }
    @Override
    public void makeSound() { System.out.println(getName() + ": 야옹~"); }
    public void knead() { System.out.println(getName() + "이(가) 만족스럽게 꾹꾹이를 합니다."); }
}

// 추상화와 인터페이스
public class AbstractExam {
	// 추상화: 내용보다는 핵심적인 개념을 추려내는 것
	// -> 추상화 과정에서 공통적인 것들을 추려내는 것
	
	// 추상화의 핵심
	// 1. 필요한 것만 추출
	// 2. 어떻게 보다는 무엇에 집중
}
