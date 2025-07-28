package Basic;

import java.util.Scanner;

public class Quiz0725 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("색을 입력하세요.");
		String Color = sc.next();
		
		switch(Color) {
		
		case "red":
			System.out.println("red");
			break;
		case "green":
			System.out.println("green");
			break;
		case "blue":
			System.out.println("blue");
			break;
		default:
			System.out.println("unknown");
		}
		
		sc.close();

	}

}
