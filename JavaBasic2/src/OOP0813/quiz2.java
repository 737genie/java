package OOP0813;

import java.util.ArrayList;
import java.util.List;

class FakeHoneyDetectedException extends Exception {
	public FakeHoneyDetectedException(String msg) {
		super(msg);
	}
}

class WaffleBear {
	List<String> honeyList = List.of("아카시아꿀", "밤꿀", "수상한 설탕물");
	
	public void start() throws FakeHoneyDetectedException{
		for(String a : honeyList) {
			if(a.equals("수상한 설탕물")) {
				System.out.println("와플곰: ("+a+"을(를) 맛보며...)");
				throw new FakeHoneyDetectedException("수상한 설탕물");
			}else {
				System.out.println("와플곰: ("+a+"을(를) 맛보며...)");
			}
		}
	}
}
public class quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WaffleBear waf = new WaffleBear();
		try {
			waf.start();
		}catch(FakeHoneyDetectedException fde) {
			System.out.println("와플곰: 으아아아!!!!");
			System.out.println("원인: 이 "+fde.getMessage()+" 따위가 감히 내 혀를 더럽히다니!");
		}
	}

}
