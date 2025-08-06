package OOP0806;

//=== 1. 역할 정의 (인터페이스) ===
interface Performer {void perform();}
interface Cheerable {void cheer();}

interface Activity {void start();} // 축제활동 관리 인터페이스
//-> 모든 축제 활동을 하나의 추상적 역할로 부여하여 다루기

class PerformanceActivity implements Activity {
	
	// performers라는 인스턴스를 다른 곳에서 쓸 확률?
	// 여기서만 쓰고 걍 값은 리턴처리 하면 됨
	private Performer[] performers;
	
	public PerformanceActivity(Performer[] p) {
		this.performers = p;
	}
	
	@Override
	public void start() {
		System.out.println("--- 🎶 신나는 공연 시간입니다! ---");
	     for (Performer performer : performers) {
	         performer.perform();
	     }
	}
}
class CheerActivity implements Activity {
	
	private Cheerable[] cheerers;
	
	public CheerActivity(Cheerable[] c) {
		this.cheerers = c;
	}
	
	@Override
	public void start() {
		System.out.println("\n--- 👏 뜨거운 응원 시간입니다! ---");
	     for (Cheerable cheerer : cheerers) {
	         cheerer.cheer();
	}
	
}
}
//=== 2. 공통 조상 정의 (추상 클래스) ===
abstract class Animal {
 private String name;
 public Animal(String name) { this.name = name; }
 public String getName() { return name; }
}

//=== 3. 캐릭터 클래스 정의 ===
class Dog extends Animal implements Performer, Cheerable {
 public Dog(String name) { super(name); }

 @Override
 public void perform() {System.out.println("공연: " + getName() + "이(가) 멋지게 원반을 물어옵니다!");}

 @Override
 public void cheer() {System.out.println("응원: " + getName() + "이(가) 신나서 멍! 멍! 짖습니다!");}
}

class Cat extends Animal implements Performer {
 public Cat(String name) { super(name); }

 @Override
 public void perform() {System.out.println("공연: " + getName() + "이(가) 우아하게 캣타워 꼭대기에 오릅니다.");}
}

class MC implements Cheerable {
 private String name;
 public MC(String name) { this.name = name; }

 @Override
 public void cheer() {
     System.out.println("응원: 사회자 " + this.name + "이(가) 외칩니다. '최고! 최고!'");
 }
}

//=== 4. 메인 클래스: 축제 진행 ===
public class Festival {
 public static void main(String[] args) {
	 System.out.println("🎊 캐릭터 마을 축제를 시작합니다! 🎊");

     // 공연자 팀을 Performer 타입으로 관리 (다형성)
     Performer[] performers = { new Dog("누렁이"), new Cat("탱고") };

     // 응원단 팀을 Cheerable 타입으로 관리 (다형성)
     Cheerable[] cheerers = { new Dog("누렁이"), new MC("잔망루피") };
     
     Activity[] activities = { 
    		 new PerformanceActivity(performers), 
    		 new CheerActivity(cheerers) 
    		 };
     
     for (Activity a: activities) {
    	 a.start();
     }
     }
}

