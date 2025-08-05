package OOP0805;

class Dog3 {
	String name;
public Dog3(String name) { this.name=name; }
public void makeSound() { System.out.println(this.name + ": 멍멍!"); }
public void wagTail() { System.out.println(this.name + "이(가) 신나게 꼬리를 흔듭니다. ♪"); }
}

class Cat {
	String name;
public Cat(String name) { this.name=name; }
public void makeSound() { System.out.println(this.name+ ": 야옹~"); }
public void knead() { System.out.println(this.name + "이(가) 만족스럽게 꾹꾹이를 합니다."); }
}

class BusyDog extends Dog3 implements Deliver{ // implements 다중 상속 구현 (인터페이스로 멤버변수의 중복을 보완)
	public BusyDog(String name) { super(name); }

	@Override
	public void deliver(String destination) {
		// TODO Auto-generated method stub
		System.out.println(name + "이(가) 헐레벌떡 " + destination + "(으)로 배달을 완료했습니다! (" + COMPANY_NAME + ")");
	}
	
}

class HastyCat extends Cat implements Deliver{ // implements 다중 상속 구현 (인터페이스로 멤버변수의 중복을 보완)
	public HastyCat(String name) { super(name); }
	
	@Override
	public void deliver(String destination) {
		// TODO Auto-generated method stub
		System.out.println(name + "이(가) 헐레벌떡 " + destination + "(으)로 배달을 완료했습니다! (" + COMPANY_NAME + ")");
	}
	
}

public class InterfaceExam {

	public static void main(String[] args) {
		// --- 인터페이스 ---
		// - 추상화를 구조적으로 돕는 도구
		// - 객체 사용 설명서
		// - "더 강력한 추상 클래스"
		
		// 인터페이스의 특징
		// 1. 생성자를 선언, 정의할 수 없음 > 멤버 변수는 무조건 상수 형태로
		//    -> 인터페이스의 멤버 변수는 무조건 public static final이 선언 되어야 함
		// 	  -> 인터페이스에서 선언되는 멤버 변수는 반드시 필요한 값이라는 의미
		// 2. 인터페이스에 선언되는 메서드는 대부분 abstract 키워드가 필요
		//    -> private이 의미가 없음
		//	  -> 접근 제어자는 public, protected를 대부분 사용
		// 3. default라는 키워드가 따로 있음
		//
		
//				== 카피바라 온천으로 배달 업무를 시작합니다! ==
//		바쁘개 바쁘개이(가) 헐레벌떡 카피바라 온천(으)로 배달을 완료했습니다! (번개 택배)
//		급하냥 급하냥이(가) 가장 빠른 길로 카피바라 온천에 배달 완료! (번개 택배)
		
        Deliver[] workers = new Deliver[2];
        workers[0] = new BusyDog("바쁘개");
        workers[1] = new HastyCat("급하냥");

        String destination = "카피바라 온천";
        System.out.println("== " + destination + "으로 배달 업무를 시작합니다! ==");

        for (Deliver worker : workers) {
            // worker가 실제 BusyDog인지 HastyCat인지 신경 쓸 필요 없이
            // Deliverable이라는 '능력'을 기준으로 명령을 내린다.
            worker.deliver(destination);
        }
		

	}

}
