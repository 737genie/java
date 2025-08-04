package Quiz1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class challange_0730 {

	public static void main(String[] args) {
		// 캐릭터 마을 무역왕
		
		Scanner sc = new Scanner(System.in);
		
		// 시세 변동
		List<Integer> honey = IntStream.rangeClosed(80, 120).boxed().collect(Collectors.toList());
		List<Integer> carrot = IntStream.rangeClosed(40, 60).boxed().collect(Collectors.toList());
		
		// 확률 
		List<String> percent = new ArrayList<>();
		percent.add("success");
		percent.add("fail");
		
		// 초기 자본 설정
		int money = 1000;
		int honeyCount = 0;
		int carrotCount = 0;

		int day = 0;
		
		
		while(true) {
			day++;
			
			Random random = new Random();
			int honeyRandom = random.nextInt(honey.size());
			int carrotRandom = random.nextInt(carrot.size());
			
			int todayhoney = honey.get(honeyRandom);
			int todaycarrot = carrot.get(carrotRandom);
			
			
			if(day>=8) {
				System.out.println("7일이 지났으므로 무역을 종료합니다.");
				System.out.println("       당신의 최종 자본: \r       자본금("+money+"원)\r       꿀("+honeyCount+"개)\r       당근("+carrotCount+"개)");
				break;
			}
			else {
				System.out.println("--------- 캐릭터 마을 무역 "+day+"일차 ---------");
				System.out.println("         현재 당신의 자본: \r         자본금("+money+"원)\r         꿀("+honeyCount+"개)\r         당근("+carrotCount+"개)");
				System.out.println("------------------------------------");
				System.out.println("거래할 상인을 선택해주세요. (숫자)");
				System.out.println("1.와플곰  2.토심이  3.누렁이  4.급하냥");
				int UserChoose = sc.nextInt();
				
				switch(UserChoose) {
				case 1:
					System.out.println("------------------------------------");
					System.out.println("당신은 와플곰을 선택하였습니다.");
					System.out.println("와플곰은 무조건 오늘의 시세대로 꿀을 판매합니다.");
					System.out.println("오늘의 꿀 시세: "+todayhoney+"원");
					System.out.println("------------------------------------");
					System.out.println("꿀을 몇 개 구매하시겠습니까? 자본금: "+money+"원");
					int UserHoney = sc.nextInt();
					if (UserHoney*todayhoney > money) {
						System.out.println("------------------------------------");
						System.out.println("돈이 부족합니다. 거래에 실패하였습니다.");
						continue;
					}
					else {
						System.out.println("------------------------------------");
						System.out.println("꿀을 "+UserHoney+"개 구매하였습니다.");
						honeyCount += UserHoney;
						money -= todayhoney * UserHoney;
						continue;
					}
				case 2: 
					System.out.println("------------------------------------");
					System.out.println("당신은 토심이를 선택하였습니다.");
					System.out.println("토심이는 무조건 오늘의 시세대로 당근을을 판매합니다.");
					System.out.println("오늘의 당근 시세: "+todaycarrot+"원");
					System.out.println("------------------------------------");
					System.out.println("당근을 몇 개 구매하시겠습니까? 자본금: "+money+"원");
					int UserCarrot = sc.nextInt();
					if (UserCarrot*todaycarrot > money) {
						System.out.println("------------------------------------");
						System.out.println("돈이 부족합니다. 거래에 실패하였습니다.");
						continue;
					}
					else {
						System.out.println("------------------------------------");
						System.out.println("당근을 "+UserCarrot+"개 구매하였습니다.");
						carrotCount += UserCarrot;
						money -= todaycarrot * UserCarrot;
						continue;
					}
				case 3:
					double nurungh = todayhoney * 0.9;
					double nurungc = todaycarrot * 0.9;
					System.out.println("------------------------------------");
					System.out.println("당신은 누렁이를 선택하였습니다.");
					System.out.println("누렁이는 오늘의 시세보다 10% 싼 가격에 당신의 모든 물품을 전부 구매합니다.");
					System.out.println("오늘의 물품 시세 \r꿀: "+todayhoney+"원 \r당근: "+todaycarrot+"원");
					System.out.println("--> 누렁이의 매입가 \r꿀: "+(int)nurungh+"원 \r당근: "+(int)nurungc+"원");
					System.out.println("------------------------------------");
					System.out.println("판매 하시겠습니까?");
					System.out.println("! 주의 : 한 번 판매하면 돌이킬 수 없습니다.");
					System.out.println("당신의 자본: 꿀 "+honeyCount+"개, 당근 "+carrotCount+"개");
					System.out.println("------------------------------------");
					System.out.println("1.예 2.아니오");
					int UserSell = sc.nextInt();
					
					switch(UserSell) {
					case 1: 
						System.out.println("------------------------------------");
						System.out.println("누렁이에게 모든 물건을 판매합니다.");
						honeyCount = 0;
						carrotCount = 0;
						money += ((int)nurungh*honeyCount)+((int)nurungc*carrotCount);
						System.out.println("자본금이 "+money+"원이 되었습니다.");
						continue;
					
					case 2:
						System.out.println("------------------------------------");
						System.out.println(day+"일차 거래를 종료합니다.");
						continue;
					
					default:
						System.out.println("------------------------------------");
						System.out.println("입력이 잘못되었습니다. 거래에 실패하였습니다.");
						continue;
					}
				case 4:
					double hanyangh = todayhoney * 1.2;
					double hanyangc = todaycarrot * 1.2;
					double hanyangh2 = todayhoney * 0.8;
					double hanyangc2 = todaycarrot * 0.8;
					
					System.out.println("------------------------------------");
					System.out.println("당신은 급하냥을 선택하였습니다.");
					System.out.println("급하냥에게 흥정을 시도할 수 있습니다.");
					System.out.println("시도하시겠습니까?");
					System.out.println("------------------------------------");
					System.out.println("1.예 2.아니오");
					int UserChoose2 = sc.nextInt();
					
					switch(UserChoose2) {
					case 1:
						int perRandom = random.nextInt(percent.size());
						String per = percent.get(perRandom);
						if(per.equals("success")) {
							System.out.println("------------------------------------");
							System.out.println("당신은 흥정에 성공하였습니다.");
							System.out.println("시세보다 20% 비싼 가격에 물건을 팔 수 있습니다.");
							System.out.println("오늘의 물품 시세 \r꿀: "+todayhoney+"원 \r당근: "+todaycarrot+"원");
							System.out.println("--> 급하냥의 매입가 \r꿀: "+(int)hanyangh+"원 \r당근: "+(int)hanyangc+"원");
							System.out.println("------------------------------------");
							System.out.println("당신의 자본: 꿀 "+honeyCount+"개");
							System.out.println("꿀을 몇 개 판매하시겠습니까?");
							int UserHoney2 = sc.nextInt();
							if (UserHoney2 > honeyCount) {
								System.out.println("------------------------------------");
								System.out.println("꿀이 부족합니다. 꿀 판매에 실패했습니다.");
							} else {
								money += UserHoney2 * (int)hanyangh;
								honeyCount -= UserHoney2;
							}
							System.out.println("------------------------------------");
							System.out.println("당신의 자본: 당근 "+carrotCount+"개");
							System.out.println("당근을 몇 개 판매하시겠습니까?");
							int UserCarrot2 = sc.nextInt();
							if (UserCarrot2 > carrotCount) {
								System.out.println("------------------------------------");
								System.out.println("당근이 부족합니다. 당근 판매에 실패했습니다.");
							} else {
								money += UserCarrot2 * (int)hanyangc;
								carrotCount -= UserCarrot2;
							}
							System.out.println("------------------------------------");
							System.out.println("자본금이 "+money+"원이 되었습니다.");
							continue;
						}
						else if(per.equals("fail")){
							System.out.println("------------------------------------");
							System.out.println("당신은 흥정에 실패하였습니다.");
							System.out.println("급하냥이 화를 냅니다. 시세보다 20% 싼 가격에 물건을 팔아야 합니다.");
							System.out.println("오늘의 물품 시세 \r꿀: "+todayhoney+"원 \r당근: "+todaycarrot+"원");
							System.out.println("--> 급하냥의 매입가 \r꿀: "+(int)hanyangh2+"원 \r당근: "+(int)hanyangc2+"원");
							System.out.println("------------------------------------");
							System.out.println("당신의 자본: 꿀 "+honeyCount+"개");
							System.out.println("꿀을 몇 개 판매하시겠습니까?");
							int UserHoney3 = sc.nextInt();
							money += UserHoney3 * (int)hanyangh2;
							honeyCount -= UserHoney3;
							
							System.out.println("------------------------------------");
							System.out.println("당신의 자본: 당근 "+carrotCount+"개");
							System.out.println("당근을 몇 개 판매하시겠습니까?");
							int UserCarrot3 = sc.nextInt();
							money += UserCarrot3 * (int)hanyangc2;
							carrotCount -= UserCarrot3;
							
							System.out.println("------------------------------------");
							System.out.println("자본금이 "+money+"원이 되었습니다.");
							continue;
						}
					case 2:
						System.out.println("------------------------------------");
						System.out.println("흥정을 시도하지 않습니다.");
						System.out.println(day+"일차 거래를 종료합니다.");
						continue;
					default:
						System.out.println("------------------------------------");
						System.out.println("입력이 잘못되었습니다. 거래에 실패하였습니다.");
						continue;
				}
				default:
					System.out.println("------------------------------------");
					System.out.println("입력이 잘못되었습니다. 거래에 실패하였습니다.");
					continue;
				}
				
				
			}
		}
			
	}
}


