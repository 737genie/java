//package OOP0805;
//
//class Animal {
//    private String name;
//    public Animal(String name) { this.name = name; }
//    public String getName() { return name; }
//    public void eat(String food) { System.out.println(this.name + "이(가) " + food + "을(를) 먹습니다."); }
//    public void makeSound() { System.out.println("동물이 소리를 냅니다."); }
//}
//
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
//
//public class PolyMorphismExam {
//	// 다형성
//	// 하나의 객체가 여러가지 형태를 가질 수 있음
//	
//	// 다형성을 이해하기 위해서는
//	// 명제의 참 거짓을 잘 구분하면 됨
//	
//	public static void main(String[] args) {
//	
//	// 동물들의 퍼레이드 참가 쇼
//	// 시나리오
////	'동물'들을 담을 하나의 큰 바구니를 준비한다.
//	Animal[] animals = new Animal[2];
////	이 바구니는 Dog, Cat 등 특정 동물이 아닌, 모든 종류의 Animal을 담을 수 있어야 한다.
//	
////	퍼레이드 참가자들을 바구니에 넣는다.
////	Animal 바구니에 '누렁이'(Dog 객체)를 넣는다.
//	animals[0] = new Dog1("누렁이");
////	Animal 바구니에 '탱고'(Cat 객체)를 넣는다.
//	animals[1] = new Cat("탱고");
//
////	퍼레이드를 진행한다!
//	System.out.println("캐릭터 마을 퍼레이드 시작!");
////	바구니에 담긴 동물들을 처음부터 끝까지 한 마리씩 살펴본다.
//	for (Animal animal : animals) {
//		System.out.println("다음 참가자: "+animal.getName());
////	각 동물에게 똑같이 "소리 내!" (makeSound()) 라고 명령한다.
////	결과를 확인한다. (누렁이는 "멍멍!", 탱고는 "야옹~" 소리를 내야 함)
//		animal.makeSound();
//// 클래스를 기반으로 배열을 만들면 임시변수 영역도 클래스를 기반으로 한 임시 인스턴스로 둠
////	특별 장기자랑 시키기 (타입 확인 및 변환)
//		if(animal instanceof Dog1) {
//			((Dog1) animal).wagTail();
//		}
//		else if(animal instanceof Cat) {
//			((Cat) animal).knead();
//		}
//		else {
//			System.out.println("동물 타입이 없습니다.");
//		}
//	}
//	
//	
////
////	퍼레이드 도중, 각 동물의 특별한 장기를 시키고 싶어졌다.
////
////	바구니에서 나온 동물이 만약 '강아지'라면, "꼬리 쳐봐!" (wagTail()) 라고 명령한다.
//	}
//}
