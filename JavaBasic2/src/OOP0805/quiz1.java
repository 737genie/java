package OOP0805;


class NormalDelivery {
	static int distance;
	
	public NormalDelivery(int distance) {
		this.distance=distance;
	}
	
	static int calculateFee() {
		int result = distance * 100;
		System.out.println("일반 배송: 가격은 "+result+"원 입니다.");
		return result;
	}
}

class UrgentDelivery extends NormalDelivery {
	static int urgencyLevel;
	
//	public UrgentDelivery(int distance) {
//		super(distance);
//	}
	
	public UrgentDelivery(int distance, int urgencyLevel) {
		super(distance);
		this.urgencyLevel = urgencyLevel;
	}

	static int calculateFee() {
		int result = (distance * 100) + (urgencyLevel * 1000);
		System.out.println("긴급 배송! 가격은 "+result+"원 입니다.");
		return result;
	}


}


public class quiz1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NormalDelivery norm1 = new NormalDelivery(100);
		UrgentDelivery urgen1 =  new UrgentDelivery(100, 1000);
		
		norm1.calculateFee();
		urgen1.calculateFee();
		
	}

}
