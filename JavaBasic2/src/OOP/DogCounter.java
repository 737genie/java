package OOP;

class DogWithCounter {
	String name;
	
	// 강아지 설계도에 모든 강아지가 공유할 총 마릿수를 저장하는 변수가 필요함.
	static int dogCnt = 0;
	// 멤버 변수는 인스턴스 별로 값이 다름
	// 클래스 변수는 인스턴스끼리도 값을 공유

	// 객체 새로 만들때마다 마릿수 1씩 증가시킴
	// -> 객체가 생성 될 때마다 부를 수 있는 특별한 메서드 == 생성자 가 있음
	// 생성자는 클래스 이름과 동일하게 설정
	public DogWithCounter(String name) { // 생성자
		this.name=name;
		dogCnt++;
		System.out.println(this.name + "가 태어났어요. 현재 마을의 강아지는 총 "
		+dogCnt+"마리 입니다.");
	}
	
}
public class DogCounter { // 클래스명은 파스칼 표기법으로

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("현재 마을의 강아지 수: "+DogWithCounter.dogCnt);
		
		DogWithCounter whiteDog = new DogWithCounter("흰둥이");
		System.out.println("현재 마을의 강아지 수: "+DogWithCounter.dogCnt);
		
		DogWithCounter hwangu = new DogWithCounter("황구");
		System.out.println("현재 마을의 강아지 수: "+DogWithCounter.dogCnt);
	}
	
}
