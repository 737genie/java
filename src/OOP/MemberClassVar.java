package OOP;

class Dog {
	// 각각의 강아지 객체가 고유하게 가질 변수들을 선언
	String name; // 멤버변수
	int age; // 맴버변수
}

public class MemberClassVar {

	
	public static void main(String[] args) {
		Dog nureong = new Dog(); // 생성자
		nureong.name="누렁";
		nureong.age=3;
		
		System.out.println("첫번째 개 프로필");
		System.out.println("이름: " + nureong.name);
		System.out.println("나이: " + nureong.age);
		
		System.out.println("==========================");
		// 퀴즈
		Dog baekgu = new Dog();
		baekgu.name="백구";
		baekgu.age=15;

		System.out.println("두번째 개 프로필");
		System.out.println("이름: " + baekgu.name);
		System.out.println("나이: " + baekgu.age);
		
	}
}
