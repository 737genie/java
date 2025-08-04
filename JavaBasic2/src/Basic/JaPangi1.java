package Basic;

import java.util.Scanner;

public class JaPangi1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 뽑고 싶은 음료수의 금액을 확인한다.
		// 뽑고싶은 음료수의 금액확인 = 변수에 값을 할당.
		//  -> 사용자에게 이 내용을 보여줄수 있어야함.
		int cola = 10000;
		int cider = 5500;
		int water = 2500;
		
		System.out.println("자판기에요");
		System.out.println("음료수 가격입니다. 음료수를 선택해주세요.");
		System.out.println("(콜라 : 1, 사이다 2, 물 3)");
		System.out.println("콜라 가격 : " + cola);
		System.out.println("사이다 가격 : " + cider);
		System.out.println("물 가격 : " + water);
			
//		2. 음료수의 가격에 맞춰 돈을 넣는다.
		// 사용자로부터 음료수 선택을 받아야함.
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		System.out.println(choose + "번의 항목을 선택했습니다.");
		// 사용자로부터 돈을 입력받아야함.
		System.out.println("돈을 입력해주세요.");
		int money = sc.nextInt();

		
//		2-1. 만약 금액이 맞지 않다면 금액에 맞는 음료수를 다시 정한다.
		// 음료수 선택(콜라)를 했으나 가격이 안맞는 경우.
		if(choose==1 && cola>money) {
						
			System.out.println("음료수를 뽑을수 없습니다. 다시 선택하세요.");
		}
		// 음료수 선택(사이다)를 했으나 가격이 안맞는경우
		else if(choose==2 && cider>money) {
			
			System.out.println("사이다를 뽑을수 없습니다. 다시 선택하세요.");
		}
		// 음료수 선택(물)를 했으나 가격이 안맞는경우
		else if(choose==3 && water>money) {
			
			System.out.println("물을 뽑을수 없습니다. 다시 선택하세요.");
		}
		
//		3. 넣은 금액이 음료수의 금액과 같거나 많은지 확인한다.
		// 어떤 음료수를 선택했는가 확인이 우선.
		// 음료수와 투입된 금액을 비교.
		
//		4. 뽑고자 하는 음료수의 버튼을 누른다.
//		5. 음료수를 꺼낸다.
		if(choose==1 && cola<=money) {
			
			System.out.println("금액이 충분합니다.");
			System.out.println("콜라를 선택하셨습니다.");
			System.out.println("콜라를 뽑았습니다.");			
			
//			6. 잔돈이 남았다면 잔돈을 받는다.
			int charge = money - cola;
			// 잔돈이 남았느냐 아니냐를 어떤 조건식을 줘야하지?
			// 
			if(charge > 0) {
				System.out.println("잔돈 " + charge + "를 받았습니다.");
			}else {
				System.out.println("거스름돈 없어요.");
			}
			
		}else if(choose==2 && cider<=money) {
			
			System.out.println("금액이 충분합니다.");
			System.out.println("사이다를 선택하셨습니다.");
			System.out.println("사이다를 뽑았습니다.");	
			
//			6. 잔돈이 남았다면 잔돈을 받는다.
			int charge = money - cider;
			// 잔돈이 남았느냐 아니냐를 어떤 조건식을 줘야하지?
			// 
			if(charge > 0) {
				System.out.println("잔돈 " + charge + "를 받았습니다.");
			}else {
				System.out.println("거스름돈 없어요.");
			}
			
		}else if(choose==3 && water<=money) {
			
			System.out.println("금액이 충분합니다.");
			System.out.println("물을 선택하셨습니다.");
			System.out.println("물을 뽑았습니다.");	
			
//			6. 잔돈이 남았다면 잔돈을 받는다.
			int charge = money - water;
			// 잔돈이 남았느냐 아니냐를 어떤 조건식을 줘야하지?
			// 
			if(charge > 0) {
				System.out.println("잔돈 " + charge + "를 받았습니다.");
			}else {
				System.out.println("거스름돈 없어요.");
			}
			
		}	
	}

}
