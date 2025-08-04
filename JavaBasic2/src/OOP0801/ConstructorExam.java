package OOP0801;

class Dog {
	String name;
	int age;
	String color;
	public Dog(String name, int age, String color) {
		this.name=name;
		this.age=age;
		this.color=color;
	}
}

public class ConstructorExam {

	public static void main(String[] args) {
		// 생성자
		// 생성자는 객체가 만들어지는 순간에 최적의 상태를 갖추도록 보장하는 개념
		// 생성자는 클래스와 이름이 똑같아야 함
		// 생성자 사용하는 이유:
		// 잘못된 혹은 불완전한 데이터가 들어오는 걸 막을 수 있다 (안전성 향상)
		
		// this :
		// 본인을 가리키게 돕는 키워드
		// 멤버 변수 별로 인스턴스들을 구분하는 키워드
		// -> this는 멤버 변수들(전체 Dog)이 어느 인스턴스(변수 name, age, color)로 갈지 정해준다.
		// 인스턴스를 구분하는 키워드를 this라고 한다.
		
		// 새로 태어난 강아지들을 관리하는 관리실
		// 주의사항 : 태어난 강아지들은 다 이름을 부여할 것
		// Q. 새로 태어난 강아지들을 관리하는 관리실을 만들거야
		//    견주들은 새로 태어난 강아지들에게 무조건 이름을 지어줘야해
		//    
		
		Dog dog1 = new Dog("백구", 0, "흰색");
		
		
		Dog nurung = new Dog("누렁이", 4, "누렁색");
		

	}

}
