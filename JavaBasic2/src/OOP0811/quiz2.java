package OOP0811;

interface Char {String perform();}

class Member implements Char{
	String name;
	String position;
	public Member(String name, String position) {
		this.name=name;
		this.position=position;
	}
	
	public String perform() {
		if(position.equals("보컬")) {
			return name+"("+position+")"+":  (아련) ...내 마음이 들리니...♪";
		}
		else if(position.equals("댄서")) {
			return name+"("+position+")"+":  (파워풀) 붐! 붐! 붐!";
		}
		else if(position.equals("래퍼")) {
			return name+"("+position+")"+":  (속사포) 내가 누구? A-yo! 급하냥 on the stage!";
		}
		else {
			return "그런 포지션은 없어.";
		}
	}
}

class Perform {
	public void start() {
		System.out.println("--- 바쁘개 PD: 자, '캐릭터즈' 공연 시작! 큐! ---");
		Member[] members = {
				new Member("토심이", "보컬"),
				new Member("탱고", "댄서"),
				new Member("급하냥", "래퍼")
		};
		
		for(Member m : members) {
			System.out.println(m.perform());
		}
		System.out.println("--- 바쁘개 PD: 완벽해! 다음 스케줄 가자! 1분 안에 이동! ---");
		
	}
		
}

public class quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Perform p = new Perform();
		p.start();
		
	}

}
