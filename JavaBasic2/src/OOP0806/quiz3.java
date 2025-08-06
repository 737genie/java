package OOP0806; // 카피바라의 스파 온도 조절 시스템

abstract class Spa {
	protected int temperature;
	public Spa(int temperature) {this.temperature=temperature;}
	public void getTemperature() {System.out.println("온도를 조절합니다.");}
	public abstract void adjustTemp();
}

class HotSpring extends Spa {
	public HotSpring(int temperature) {super(temperature);}
	@Override
	public void adjustTemp() {
		this.temperature+=2;
		System.out.println("온천수 공급! HotSpring 현재 온도: "+this.temperature+"도");
	}
}

class Sauna extends Spa {
	public Sauna(int temperature) {super(temperature);}
	@Override
	public void adjustTemp() {
		this.temperature+=5;
		System.out.println("뜨거운 돌 추가! Sauna 현재 온도: "+this.temperature+"도");
	}
}

public class quiz3 {

	public static void main(String[] args) {
		Spa[] Spas = {new HotSpring(40), new Sauna(80)};
		
		for(int i=1; i<=3; i++) {
			System.out.println("--- "+i+"차 온도 조절 ---");
			for(Spa s: Spas) {
				s.adjustTemp();
			}
		}

	}

}
