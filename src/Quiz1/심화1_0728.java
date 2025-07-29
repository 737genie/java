package Quiz1;

import java.util.ArrayList;
import java.util.Arrays;

public class 심화1_0728 {

	public static void main(String[] args) {
		// 바쁘개의 업무 처리
		// 중요도와 업무력
		ArrayList<Integer> List = new ArrayList<Integer>(
				Arrays.asList(10, 8, 5, 12, 3, 9)
		);
		ArrayList<Integer> Work = new ArrayList<Integer>(
				Arrays.asList(20)
				);
		
		
		System.out.println("좋아, 내 업무력은 20이야. 첫 번째 업무를 보자.");
		for (int i=0; i<List.size(); i++) {
			int list2 = List.get(i);
			int work2 = Work.get(0);
			if (work2 >= list2) {
				System.out.println((i+1)+"번째 업무 중요도는 "+list2+". 내 업무력 "+work2+"보다 작으니까 처리 가능! 처리하자.");
				System.out.println("           (남은 업무력: "+work2+" - "+list2+" = "+(work2-list2)+")");
				Work.set(0, work2-list2);
			}
			else {
				System.out.println((i+1)+"번째 업무 중요도는 "+list2+" 어라? 내 남은 업무력은 "+work2+"인데... 이건 처리 못하겠네. 건너뛰자.");
			}
		}
		
		System.out.println("이제 모든 업무를 확인했군. 최종적으로 남은 내 업무력은 "+Work.get(0)+"이야.");
		

	}

}
