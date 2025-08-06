package OOP0806; // 토심이의 기분

import java.util.Scanner;

class TosimMood {
	private int mood=50;
	public int getMood() { return mood; }
	public void setMood(int mood) {
		if(mood>=0 && mood<=100) {
			this.mood = mood;
			System.out.println("토심이의 기분이 "+mood+"로 변경되었습니다.");
		}
		else {
			System.out.println("잘못된 기분 값입니다! 0에서 100 사이로 입력해주세요.");
		}
	}
}

public class quiz1 {

	public static void main(String[] args) {
		TosimMood tosim2 = new TosimMood();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("### 토심이의 기분 관리 프로그램 ###");
		System.out.println("현재 토심이의 기분: "+tosim2.getMood());
		System.out.println("토심이의 기분을 0~100 사이로 지정해주세요.");
		int mood = sc.nextInt();
		tosim2.setMood(mood);
		
		System.out.println("최종 토심이의 기분: "+tosim2.getMood());
		
		
		
		

	}

}
