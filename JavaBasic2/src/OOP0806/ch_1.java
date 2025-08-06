package OOP0806; // 탱고의 RPG 파티 레이드

abstract class PartyMember {
	String name;
	private int HP;
	int maxHp;
	public String getName() {return name;}
	public int getHP() {return HP;}
	
	public PartyMember(String name) {this.name=name;}
	public void takeDamage(int damage) {};
	public void isAlive() {};
	abstract void status();
}

class Warrior1 extends PartyMember {
	int HP;
	public Warrior1(String name) {super(name);}
	public void attack() {
		System.out.println("몬스터에게 10만큼 피해를 입힙니다.");
	}
	public void status() {
		
	}
}
class Mage1 extends PartyMember {
	int HP;
	public Mage1(String name) {super(name);}
	public void castSpell() {
		System.out.println("몬스터에게 20만큼 피해를 입힙니다.");
	}
	public void status() {
		
	}
}
class Healer extends PartyMember {
	int HP;
	public Healer(String name) {super(name);}
	public void heal() {
		System.out.println("아군의 체력을 15만큼 치유합니다.");
	}
	public void status() {
		
	}
}

class BossMonster {
	int HP = 100;
	public void attack() {
		System.out.println("파티원을 공격합니다.");
	}
}

public class ch_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
