package Quiz1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class challange_0729 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> Money = new ArrayList<Integer>();
		ArrayList<Integer> Paper = new ArrayList<Integer>();
		
		// 사용자에게 거스름돈과 화폐 단위 입력받기.
		Scanner sc = new Scanner(System.in);
		
		System.out.println("거스름돈을 입력하세요.");
		int Money2 = sc.nextInt();
		if (Money2 > 100000 && Money2 >= 0) {
			Money.add(Money2);			
		}
		else {
			System.out.println("거스름돈은 100,000 이하로 입력해주세요.");
		}
		
		System.out.println("갖고 계신 화폐 단위를 입력해주세요.");
		int Paper2 = sc.nextInt();
		Paper.add(Paper2);
		//,로 구분해서 리스트에 저장하고 싶음
		
		// 함수 (money, paper)
		// 거스름돈 - 화폐종류 == 0
		

	}

}
