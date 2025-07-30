package Basic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HardExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 바쁘개의 멀티태스킹 시뮬레이션
		
		Scanner sc = new Scanner(System.in);
		// 초기 상태 변수 할당
		int Merchandise = 0;
		int Developement = 0;
		int Design = 0;
		int Skill = 500;
		
		
		// 목표 상태 변수 할당
		int TargetMer = 50;
		int TargetDev = 100;
		int TargetDesign = 70;
		
		// 컨디션 랜덤 추출
		ArrayList<String> Condition = new ArrayList<String>();
		Condition.add("최상");
		Condition.add("보통");
		Condition.add("나쁨");
		
		
		int day = 0;
		while(true) {
			Random random = new Random();
			int randomIndex = random.nextInt(Condition.size());
			String randomCondition = Condition.get(randomIndex);

			day++;
			System.out.println("\r<업무 "+day+"일차> "+"오늘의 상태는 "+randomCondition);
			System.out.println("현재 업무 진행도: 기획("+Merchandise+"/"+TargetMer+"), 개발("+Developement+"/"+TargetDev+"), 디자인("+Design+"/"+TargetDesign+")\r나의 업무력: ("+Skill+")");
			System.out.println("기획, 개발, 디자인 업무 중 하나를 선택해주세요.");
			String Choose = sc.next();
			
			if(Choose.equals("기획")) {
				if (Skill>=10) {
					System.out.println("기획 (+10), 업무력 (-10)");
					Merchandise += 10;
					Skill -= 10;
				} else {
					System.out.println("! 업무력이 부족합니다.");
				}
			}
			else if (Choose.equals("개발")) {
				if(randomCondition.equals("최상")) {
					if (Skill>=20) {
						System.out.println("개발 (+20), 업무력 (-20)");
						Developement += 20;
						Skill -= 20;
					} else {
						System.out.println("! 업무력이 부족합니다.");
					}
				}
				else if(randomCondition.equals("보통")) {
					if (Skill>=15) {
						System.out.println("개발 (+10), 업무력 (-15)");
						Developement += 10;
						Skill -= 15;
					} else {
						System.out.println("! 업무력이 부족합니다.");
					}
				}
				else if(randomCondition.equals("나쁨")) {
					if (Skill>=10) {
						System.out.println("개발 (+5), 업무력 (-10)");
						Developement += 5;
						Skill -= 10;
					} else {
						System.out.println("! 업무력이 부족합니다.");
					}
				}
			}
			else if (Choose.equals("디자인")) {
				if(randomCondition.equals("최상")) {
					if (Skill>=15) {
						System.out.println("디자인 (+15), 업무력 (-15)");
						Design += 15;
						Skill -= 15;
					} else {
						System.out.println("! 업무력이 부족합니다.");
					}
				}
				else if(randomCondition.equals("보통")) {
					if (Skill>=10) {
						System.out.println("디자인 (+10), 업무력 (-10)");
						Design += 10;
						Skill -= 10;
					} else {
						System.out.println("! 업무력이 부족합니다.");
					}
				}
				else if(randomCondition.equals("나쁨")) {
					System.out.println("작업 불가.");
				}
			} 
			
			if (day==30) {
				System.out.println("\r"+day+"일이 지났습니다. 프로젝트를 종료합니다.");
				System.out.println("프로젝트 현황: 기획("+Merchandise+"/"+TargetMer+"), 개발("+Developement+"/"+TargetDev+"), 디자인("+Design+"/"+TargetDesign+")");
				System.out.println("나의 업무력: ("+Skill+")");
				break;
			}
			else if (Merchandise>=TargetMer && Developement>=TargetDev && Design>=TargetDesign) {
				System.out.println(day+"일 만에 세 프로젝트의 목표를 모두 달성하였습니다. 프로젝트를 종료합니다.");
				System.out.println("프로젝트 현황: 기획("+Merchandise+"), 개발("+Developement+"), 디자인("+Design+")");
				System.out.println("나의 업무력: ("+Skill+")");
				break;
			}
			
		}
		
		
		
	
		
	}

}
