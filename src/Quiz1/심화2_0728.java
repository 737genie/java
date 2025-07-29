package Quiz1;

import java.util.ArrayList;
import java.util.Arrays;

public class 심화2_0728 {

	public static void main(String[] args) {
		// 급하냥의 택배 배송
		// 성격 급한 고양이 급하냥은 택배를 배송해야 합니다. 배송 경로가 문자열로 주어지며
		// 각 문자는 동서남북 한 칸 이동을 의미합니다. 
		// 하지만 특정 구역은 '특별 구역'이라 추가 행동이 필요합니다.
		ArrayList<Integer> coordinate = new ArrayList<Integer>(
				Arrays.asList(0, 0)
		);
		
		// 배송 문자열
		ArrayList<String> delivery = new ArrayList<String>(
				Arrays.asList("R","R","U","U","D","D","L","R","L")
				);
		
		
		for (int i=0; i<delivery.size(); i++) {
			int x = coordinate.get(0);
			int y = coordinate.get(1);
			String d = delivery.get(i);
			if (d.equals("U")) {
				System.out.println("위로 1칸 움직입니다.");
				coordinate.set(1, y+1);
				System.out.println("현재 급하냥의 좌표: "+coordinate+"\r");
				if (y==1) {
					System.out.println("헥헥... 언덕은 힘들어! \r");
				}
			}
			else if (d.equals("D")) {
				System.out.println("아래로 1칸 움직입니다.");
				coordinate.set(1, y-1);
				System.out.println("현재 급하냥의 좌표: "+coordinate+"\r");
			}
			else if (d.equals("R")) {
				System.out.println("오른쪽으로 1칸 움직입니다.");
				coordinate.set(0, x+1);
				System.out.println("현재 급하냥의 좌표: "+coordinate+"\r");
			}
			else if (d.equals("L")) {
				System.out.println("왼쪽으로 1칸 움직입니다.");
				coordinate.set(0, x-1);
				System.out.println("현재 급하냥의 좌표: "+coordinate+"\r");
			}
		}
		System.out.println("============================================");
		System.out.println("배송이 완료 되었습니다. 현재 급하냥의 좌표: "+coordinate);
		
	}

}
