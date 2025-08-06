package OOP0806; // 탱고의 모험가 길드

interface Attackable{void attack();}

class Warrior implements Attackable{
	String name;
	public Warrior(String name) {this.name = name;}
	public void attack() {System.out.println("용맹한 "+name+"(전사): 칼로 베어버린 후 콱! 하고 물어버립니다.");}
}

class Mage implements Attackable{
	String name;
	public Mage(String name) {this.name=name;}
	public void attack() {System.out.println("지혜로운 "+name+"(법사): 마법사가 파이어볼을 발사합니다!");}
}

public class quiz4 {

	public static void main(String[] args) {

		Attackable[] Attacker = {new Warrior("누렁이"), new Mage("토심이")};
		System.out.println("### 탱고의 길드원, 총 공격! ###");
		for(Attackable a : Attacker) {
			a.attack();
		}
		
	}

}
