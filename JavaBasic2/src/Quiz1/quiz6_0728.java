package Quiz1;

import java.util.Scanner;

public class quiz6_0728 {

	public static void main(String[] args) {
		// 6. 바쁘개와 와플곰의 회의 시간 정하기
		Scanner sc = new Scanner(System.in);
		
		System.out.println("바쁘개와 와플곰이 회의를 하려 합니다.");
		System.out.println("둘의 개인 일정과 겹치지 않게 시간을 정해야 합니다.");
		System.out.println("=======================================");
		System.out.println("회의 시간을 입력해주세요. (0-23h 중 숫자로만 입력)");
		int Time = sc.nextInt();
		
		if (Time==12) {
			System.out.println(Time+"시는 "+"와플곰이 꿀을 먹는 시간입니다.");
		}
		else if (Time==15) {
			System.out.println(Time+"시는 "+"와플곰이 꿀을 먹는 시간입니다.");
		}
		else if (Time<9) {
			System.out.println(Time+"시는 "+"바쁘개가 잠을 자는 시간입니다.");
		}
		else if (Time>13) {
			if (Time<23) {
				System.out.println(Time+"시는 "+"바쁘개의 개인 정비 시간입니다.");
			}
			else {System.out.println("시간 입력이 잘못 되었습니다.");}
		}
		else if (Time>23) {
			System.out.println("시간 입력이 잘못 되었습니다.");
		}
		else {
			System.out.println("회의 가능!");
		}
		

	}

}
