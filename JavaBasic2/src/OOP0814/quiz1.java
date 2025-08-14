package OOP0814;

import java.util.ArrayList;
import java.util.List;

class DragonRampageException extends Exception {
	public DragonRampageException(String msg) {
		super(msg);
	}
}

class DragonSpell {
	List<String> spell = new ArrayList<>();
	public DragonSpell() {
		spell.add("라어들잠");
		spell.add("은깊");
		spell.add("연심");
		spell.add("에속");
//		spell.add("방해");
	}
	public void spell() throws DragonRampageException {
		
		StringBuilder complete = new StringBuilder();
		for(String a : spell) {
			complete.append(a);
			complete.append(" ");
		}
		complete = complete.reverse();
		String com = complete.toString();
		
		if(com.contains("해방")) {
			throw new DragonRampageException("--- 잘못된 주문 시도 ---\r봉인 실패! 크아아아! 흑염룡이 깨어난다!");
		}else {
			throw new DragonRampageException("--- 올바른 주문 시도 ---\r봉인 성공! 완성된 주문: ["+com+" ]");
		}
		
	}
}




public class quiz1 {
	public static void main(String[] args) throws DragonRampageException{
		DragonSpell dragon = new DragonSpell();
		
		try {
			dragon.spell();			
		}catch(DragonRampageException dre){
			System.out.println(dre.getMessage());
		}
		
	}
}
