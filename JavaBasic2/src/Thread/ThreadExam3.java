package Thread;

class Character11 implements Runnable {
	 private String name;
	    public Character11(String name) { this.name = name; }

	    // Runnable 인터페이스의 run() 메서드를 구현하여 '임무'를 정의
	    @Override
	    public void run() {
	        for (int i = 1; i <= 3; i++) {
	            System.out.println(name + "이(가) " + i + "번째 꿀단지를 핥는 중... 챱챱...");
	            try { Thread.sleep(800); } catch (InterruptedException e) {}
	        }
	        System.out.println(name + " 임무 완료!");
	    }
}

public class ThreadExam3 {
	public static void main(String[] args) {
		// Runnable 인터페이스 구현 방식
		// 클래스 상속 받을 때랑 방식이 다르니 숙지해둘것
		
		// Character11 클래스를 기반으로 만들어진 인스턴스를 Thread에 전달
		Thread nureongi = new Thread(new Character11("누렁이"));
 		Thread waffleBear = new Thread(new Character11("와플곰"));
		
 		nureongi.start();
 		waffleBear.start();
 		
	}
}
