package OOP0806; // 바쁘개의 이동 수단

interface Movable { void move(); }

class Bappugae implements Movable {
	@Override
	public void move() {
		System.out.println("바쁘개가 빠르게 달립니다!");
	}
}

class Bicycle implements Movable {
	@Override
	public void move() {
		System.out.println("자전거가 씽씽 달립니다!");
	}
}


public class quiz2 {

	public static void main(String[] args) {
		
			System.out.println("### 바쁘개의 이동 방법 ###");
			Bappugae gae = new Bappugae();
			Bicycle ja = new Bicycle();
			
			gae.move();
			ja.move();
	}

}
