package OOP0801;

public class Calculator {
	static int calculationCount = 0;
	
	static double add(double a, double b) {
		calculationCount++;
		double result = a+b;
		System.out.println(a+" + "+b+" = "+result+"\r계산 횟수: "+calculationCount);
		return result;
	}
	static double subtract(double a, double b) {
		calculationCount++;
		double result = a-b;
		System.out.println(a+" - "+b+" = "+result+"\r계산 횟수: "+calculationCount);
		return result;
	}
	static double multiply(double a, double b) {
		calculationCount++;
		double result = a*b;
		System.out.println(a+" * "+b+" = "+result+"\r계산 횟수: "+calculationCount);
		return result;
	}
	static double divide(double a, double b) {
		if(b<=0) {
			System.out.println("나눌 수 없는 수를 입력하셨습니다.");
			return 0;
		}
		else {
			calculationCount++;
			double result = a/b;
			System.out.println(a+" / "+b+" = "+result+"\r계산 횟수: "+calculationCount);
			return result;
		}
	}
		
	public static void main(String[] args) {
		// 계산기
		Calculator.add(1, 3);
		Calculator.divide(5, 0);
		Calculator.divide(15, 3);
		Calculator.multiply(5, 4);
		Calculator.subtract(10, 5);

	}

}
