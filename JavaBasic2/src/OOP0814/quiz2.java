package OOP0814;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Cleaning {
	Set<String> fWord = new HashSet<>();
	List<String> comment = new ArrayList<>();
	public Cleaning() {
		fWord.add("바보");
		fWord.add("해삼");
		fWord.add("멍청이");
		comment.add("너 정말 바보 아니야?");
		comment.add("오늘 날씨 좋다~");
		comment.add("이 해삼 멍청이 같은 녀석!");		
	}
	
	public void heal() {
		for(String a : comment) {
			for(String b : fWord) {
				StringBuilder bclean = new StringBuilder();
				bclean.append(b);
				bclean.replace(1, b.length(), "*");
				b = bclean.toString();
				if(a.contains(b)) {
					StringBuilder aclean = new StringBuilder();
					aclean.append(a);
					int start = a.indexOf(b);
					int end = a.indexOf(b) + b.length();
					aclean.replace(start, end, b);
					a = aclean.toString();
				}
			}
			System.out.println(comment);
		}
	}
	
	
}

public class quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cleaning ex = new Cleaning();
		
		ex.heal();
		
		
		
	}

}
