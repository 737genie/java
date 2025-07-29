package Basic;

import java.util.Scanner;

public class SwitchExam2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1월부터 12월 중 하나를 입력해주세요.");
		String Month = sc.next();
		
		switch (Month) {
		case "1월":
		case "3월": 
		case "5월": 
		case "7월": 
		case "8월": 
		case "10월":
		case "12월": 
			System.out.println(Month + "은 31일까지 있습니다.");
			break;
		
		case "4월":
		case "6월":
		case "9월":
		case "11월":
			System.out.println(Month+"은 30일까지 있습니다.");
			break;
		
		case "2월":
			System.out.println("2월은 특별한 달입니다.");
			break;
		
		default:
			System.out.println("그런 달은 존재하지 않습니다.");
		};
		
		

	}

}
