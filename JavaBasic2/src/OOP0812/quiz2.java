package OOP0812;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Dancer {
	String name;
	int perform;
	public Dancer(String name, int perform) {
		this.name=name;
		this.perform=perform;
	}
}

public class quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Dancer> applicants = new ArrayList<>();
        applicants.add(new Dancer("토심이", 75));
        applicants.add(new Dancer("누렁이", 95));
        applicants.add(new Dancer("바쁘개", 85));
        applicants.add(new Dancer("카피바라", 60));
        applicants.add(new Dancer("급하냥", 90));

        System.out.println("### 탱고의 댄스 배틀 멤버 선발 ###");
        Set<String> Selected2 = new TreeSet<>();
        
        
        for(int i=0; i<applicants.size(); i++) {
        	if(applicants.get(i).perform >= 80) {
        		Selected2.add(applicants.get(i).name);
        	}
        }
        
        System.out.println(Selected2);
	}

}
