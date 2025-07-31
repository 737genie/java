package Quiz1;

import OOP.MethodExam;

class WaffleBear {
	String name;
	String todayWaffle;
	
	public WaffleBear(String name, String waffle) {
		this.name = name;
		this.todayWaffle = waffle;
	}
	
	public void recommend() {
		System.out.println("["+name+"] 와플곰의 추천! 오늘의 와플은 바로 ["+todayWaffle+"]입니다!");
	}
	
}

class DeliveryCourier {
	static String companyName = "캐릭터 마을 특송";
	static int totalDeliveries = 0;
	
	String name;
	
	public void deliver() {
		totalDeliveries++;
		System.out.println("["+companyName+"] 소속 ["+name+"] 기사님이 배송을 완료했습니다. (총 배송 건수: ["+totalDeliveries+"]건)");
	}
}

class OnsenGuest {
	static int CAPACITY = 3;
	static int currentGuests = 0;
	
	String name;

	public void enter() {
		if(currentGuests<CAPACITY) {
		currentGuests++;
		System.out.println("["+name+"]님이 온천에 들어옵니다. (현재 인원: ["+currentGuests+"]/["+CAPACITY+"]");
	}else {
			System.out.println("--- ["+name+"] 입장 시도 ---");
			System.out.println("["+name+"]님, 죄송합니다. 온천이 꽉 찼어요!");
		}
	}
	
	public void leave() {
		currentGuests--;
		System.out.println("--- ["+name+"] 퇴장 ---");
		System.out.println("["+name+"]님이 온천에서 나옵니다. (현재 인원: ["+currentGuests+"]/["+CAPACITY+"]");		
	}
}

		

public class quiz1_0731 {

	public static void main(String[] args) {
		WaffleBear one = new WaffleBear("와플곰","초코 바나나 와플");
		one.recommend();

		System.out.println("=================================");
		DeliveryCourier nyang = new DeliveryCourier();
		DeliveryCourier dog = new DeliveryCourier();
		
		nyang.name = "바쁘냥";
		dog.name = "바쁘개";
		
		nyang.deliver();
		dog.deliver();
		
		System.out.println("=================================");
		OnsenGuest cap = new OnsenGuest();
		OnsenGuest nureong = new OnsenGuest();
		OnsenGuest tango = new OnsenGuest();
		OnsenGuest gom = new OnsenGuest();
		
		cap.name = "카피바라";
		nureong.name = "누렁이";
		tango.name = "탱고";
		gom.name = "와플곰";

		cap.enter();
		nureong.enter();
		tango.enter();
		gom.enter();
		tango.leave();
		gom.enter();
		
		MethodExam.gugu();
	}

}
