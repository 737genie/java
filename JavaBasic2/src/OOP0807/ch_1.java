package OOP0807;

abstract class Event {
	private int budget = 500000000;
	public int getBudget() {return budget;}
	abstract void process();
	abstract void getCost();
}

class Concert extends Event {
	int person;
	int cost;
	
	public Concert(int person) {
		this.person = person;
		this.cost=cost();
	}
	public int cost() {return person*99000;}
	public void process() {System.out.println("콘서트가 시작됩니다!");}
	public void getCost() {
	System.out.println(" - 재즈 콘서트 (비용: "+this.cost+"원, 인원: "+person+"명)");}
}

class Exhibition extends Event {
	int rental = 800000;
	int cost;
	int person;
	public Exhibition(int person) {this.person=person; this.cost=cost();}
	public int cost() {return rental;}
	public void process() {System.out.println("전시회가 시작됩니다!");}
	public void getCost() {
		System.out.println(" - 전시회 (비용: "+this.cost+"원, 인원: "+person+"명)");}
}

class Party extends Event {
	int person;
	int cost;
	int drink = 100000;
	public Party(int person) {
		this.person=person;
		this.cost=cost();
	}
	public int cost() {return person*drink;}
	public void process() {System.out.println("전시회가 시작됩니다!");}
	public void getCost() {
		System.out.println(" - 전시회 (비용: "+this.cost+"원, 인원: "+person+"명)");}
}



public class ch_1 {

	public static void main(String[] args) {

		Concert c1 = new Concert(50);
		Exhibition e1 = new Exhibition(80);
		Party p1 = new Party(100);
		
		System.out.println("### 토심이의 이벤트 플래너 (총 예산: "+c1.getBudget()+"원) ###");
		

	}

}
