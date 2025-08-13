package OOP0813;

import java.util.ArrayList;
import java.util.List;

class ArkFullException extends Exception {
	public ArkFullException(String msg) {
		super(msg);
	}
}

class Member {
	String name;
	int score;
	public Member(String name, int score) {
		this.name=name;
		this.score=score;
	}
}

class Select1 {
	public void select() throws ArkFullException{
		List<Member> mem = new ArrayList<>();
		
		mem.add(new Member ("와플곰", 95));
		mem.add(new Member ("누렁이", 90));
		mem.add(new Member ("토심이", 85));
		mem.add(new Member ("탱고", 75));
		mem.add(new Member ("바쁘개", 20));
		
		mem.sort((a1, a2)-> a2.score - a1.score);
		
		List<Member> last = new ArrayList<>();
		for(Member a: mem) {
			if(last.size()>2) {
				System.out.println(a.name+ " 탑승 시도 ...");
				throw new ArkFullException("탑승 실패! 원인: "+a.name+"을(를) 더 태울 수 없습니다! 방주가 꽉 찼어요!");
			} else {
				last.add(a);
				System.out.println(a.name+ " 탑승 시도...");
				System.out.println(" > 탑승 성공!");
				
			}
			
		}
	}	
}
public class quiz3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Select1 s1 = new Select1();
		
		try {
			s1.select();
		}catch(ArkFullException afe) {
			System.out.println(afe.getMessage());
		}
		
		
	}

}
