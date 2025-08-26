package Thread;

public class ConcurrencyExam {

	// 상황: 와플 가게에 마지막 남은 '전설의 와플'이 딱 하나 있습니다. 
	// 이 소식을 들은 누렁이와 망곰이가 동시에 와플을 향해 달려들었습니다!

	// 바쁘개의 걱정: "저 둘이 동시에 와플을 집으려고 하면, 
	// 와플이 반으로 쪼개지거나 누군가는 못 먹는 대참사가 벌어질 텐데! 
	// '한 번에 한 명만' 와플에 손댈 수 있도록 규칙을 정해야 해!"
	
	// == 메서드에 synchronized !!!
	public static void main(String[] args) {
		
		Waffle w1 = new Waffle();
		
//		w1.eatWaffle("누렁이");
//		w1.eatWaffle("망곰");
		
		new Thread(() -> w1.eatWaffle("누렁이")).start(); 
		new Thread(() -> w1.eatWaffle("망곰")).start(); 
	}

}

class Waffle {
	private int waffleCount = 1;
	
	// synchronized
	// 특정 메서드나 코드블럭에 자물쇠 거는 역할
	// -> 스레드 1개가 해당 메서드 호출했으면 다른 스레드는 대기 상태
	public synchronized void eatWaffle(String name) {
		if(waffleCount>0) {
			System.out.println(name+"이(가) 와플을 발견했다.");
			System.out.println(name+"이(가) 와플을 먹기 시작했다. 우걱우걱");
			waffleCount-=1;
			System.out.println(name+"이(가) 와플을 다 먹었다! 남은 와플 수 : "+waffleCount+"개");
		} else {
			System.out.println(name+" : 뭐야 왜 와플이 없는거야");
		}
	}
}
