package OOP0812;

import java.util.ArrayList;
import java.util.List;


public class quiz1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> Bag = new ArrayList<>();
		System.out.println("### 누렁이의 마트 쇼핑 시뮬레이션 ###");
		System.out.println("누렁이: 오, 뼈다귀다! (담기)");
		Bag.add("뼈다귀");
		System.out.println("누렁이: 맛있는 육포! (담기)");
		Bag.add("육포");
		System.out.println("누렁이: 어? 저쪽에도 뼈다귀가? 더 맛있어 보이는데? (또 담기)");
		Bag.add("뼈다귀");
		
		System.out.println("--- 계산대 ---");
		System.out.println("누렁이가 최종적으로 담은 물건 목록:");
		System.out.println(" - "+Bag.get(0));
		System.out.println(" - "+Bag.get(1));
		System.out.println(" - "+Bag.get(2));
		System.out.println("(점원: ...똑같은 걸 두 개나 사셨네요)");
	}

}
