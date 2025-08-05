package OOP0805;

class Item {
	String name;
	int weight;
	
	public Item(String name, int weight) {
		this.name=name;
		this.weight=weight;
	}
	
	int calculateFee() {
		int result = weight*50;
		return result;
	}
}

class FragileItem extends Item {
	int packingFee;
	
	public FragileItem(String name, int weight, int packingFee) {
		super(name, weight);
		this.packingFee=packingFee;
	}
	
	@Override
	int calculateFee() {
		int result = weight*50 + packingFee;
		return result;
	}
}

class OversizedItem extends Item {
	public OversizedItem(String name, int weight) {
		super(name, weight);
	}
	
	@Override
	int calculateFee() {
		if(weight>10) {
			int result = weight*50 + 2000;
			return result;
		}
		else {
			return 0;
		}
	}
}

public class quiz5 {
	
//	"취급주의 품목 배송비는 "+(weight*50)+(packingFee)+"원 입니다."
//	if(weight>10) {
//		System.out.println("무게가 10kg 초과하였으므로 추가 배송비 2000원이 부과됩니다.");
//		System.out.println("배송비는 "+weight*50+2000+"원 입니다.");
//	}
//	else {
//		System.out.println("10kg 넘는 물건만 배달할 수 있습니다. 일반 배송을 이용해주세요.");
	
	

	public static void main(String[] args) {
		// 다중 배송 시스템
		Item book = new Item("일반 책", 5);
		FragileItem dojagi = new FragileItem("도자기", 10, 5000);
		OversizedItem computer = new OversizedItem("컴퓨터 모니터", 15);
		OversizedItem ran = new OversizedItem("과자", 5);
		
		System.out.println(book.name+" 배송비: "+book.calculateFee()+"원");
		System.out.println(dojagi.name+" 배송비: "+dojagi.calculateFee()+"원");
		System.out.println(computer.name+" 배송비: "+computer.calculateFee()+"원");
		System.out.println(ran.name+" 배송비: "+ran.calculateFee()+"원");
		
		System.out.println("총 배송비 합계: "+(book.calculateFee()+dojagi.calculateFee()+computer.calculateFee())+"원");

	}

}
