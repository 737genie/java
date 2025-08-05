package OOP0805;

class Character {
	void performAction() {
		System.out.println("캐릭터가 일반 행동을 합니다.");
	}
}

class WaffleBear extends Character {
	@Override
	void performAction() {
		System.out.println("와플곰이 꿀맛을 감별합니다. \r와플곰: 음~ 달콤해!");
	}
}

class Capybara extends Character {
	@Override
	void performAction() {
		System.out.println("카피바라가 온천에서 평화롭게 명상합니다... \r카피바라: 음~ 평화롭군.");
	}
}


public class quiz3 {

	public static void main(String[] args) {
		// 캐릭터 특별한 행동
		WaffleBear waff = new WaffleBear();
		Capybara cap = new Capybara();
		
		System.out.println("### 캐릭터들의 특별한 행동 시간! ###");
		waff.performAction();
		cap.performAction();

	}

}
