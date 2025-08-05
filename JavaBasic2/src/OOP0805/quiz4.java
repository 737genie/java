package OOP0805;

class Dog2 {
	String name;
	
	public Dog2(String name) {
		this.name = name;
	}

	void bark() {
		System.out.println(name+": 멍멍!");
	}
}

class SuperNureongi extends Dog2 {
	
	public SuperNureongi(String name) {
		super(name);
	}

	
	@Override
	void bark() {
		System.out.println(name+": 슈퍼-멍멍! 세상을 구하겠다!");
	}
	
	void fly() {
		System.out.println(name+"가 하늘을 힘차게 날아오릅니다!");
	}
}

public class quiz4 {

	public static void main(String[] args) {
		// 진화하는 누렁이
		Dog2 dog1 = new Dog2("옆집 강아지");
		SuperNureongi dog2 = new SuperNureongi("슈퍼 누렁이");
		
		System.out.println("### 진화 전과 후 ###");
		System.out.printf("일반 상태 -> ");
		dog1.bark();
		System.out.printf("진화 상태 -> ");
		dog2.bark();
		
		System.out.println("\r### 슈퍼 누렁이만의 능력! ###");
		dog2.fly();

	}

}
