package OOP;

public class OOPExam {

	public static void main(String[] args) {
		// 객체지향 프로그래밍
		// -> 반복작업 시 유리함, 재사용 가능
		// 객체 : 현실에 존재하는 모든 물질, 사물
		// css 파일 따로 만들어서 불러오는 것처럼 클래스를 만들고 불러오는 느낌
		
		
		// 클래스 - 객체를 만드는 설계도
		// 인스턴스 - 클래스를 통해 생성된 객체
		
		
		// 객체 지향
		KakaoImmo char1 = new KakaoImmo();
		KakaoImmo char2 = new KakaoImmo();
		KakaoImmo char3 = new KakaoImmo();

		char1.name="누렁이";
		char1.character="ISFP";
		char1.color="누렁";
		char1.age=3;
		
		System.out.println(char1.name);
		System.out.println(char1.character);
		System.out.println(char1.color);
		System.out.println(char1.age);
		
		char1.eat();
		char1.intro();
		char1.moving();
		
		System.out.println("================");
		
		char2.name="바쁘개";
		char2.character="ESFP";
		char2.color="주황색";
		char2.age=5;
		
		System.out.println(char2.name);
		System.out.println(char2.character);
		System.out.println(char2.color);
		System.out.println(char2.age);
		
		char2.eat();
		char2.intro();
		char2.moving();
		
		System.out.println("================");
		
		char3.name="와플곰";
		char3.character="INTJ";
		char3.color="와플색";
		char3.age=25;
		
		System.out.println(char3.name);
		System.out.println(char3.character);
		System.out.println(char3.color);
		System.out.println(char3.age);
		
		char3.eat();
		char3.intro();
		char3.moving();
		
		
		// method
		
		// 변수(멤버변수, 정적변수(클래스변수))
		
		// 객체지향 프로그래밍을 현업에 맞게 더 효율적으로 하기 위해서는
		// SOLID 원칙을 지켜야함. (S,O는 필수)
		// 1. SRP(단일 책임 원칙)
		// 2. OCP(개방 폐쇄 원칙)
		
		
		// 객체를 우선 개념적으로 접근
		// 객체는 흔히 속성과 행동으로 구성되는 것이 일반적
		
		// 리모컨
		// 속성 - 색상, 크기
		// 행동 - 버튼 누르기
		
		// 컴퓨터
		// 속성 - 색상, 크기, 무게
		// 행동 - 작동하기, 상호작용?
		
		// 고양이
		// 속성 - 색상, 종류, 무게
		// 행동 - 걷기, 물기, 먹기, 자기

	}

}
