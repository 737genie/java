package OOP0813;

//class ArithmeticException extends Exception {
//	public ArithmeticException(String msg) {
//		super(msg);
//	}
//}

class calculatorTosim {
	private int sadness = 100;
	public void calculate(int num) throws ArithmeticException {
		int result = sadness / num;
		if(num==0) {
			throw new ArithmeticException("0으로 나눌 수 없습니다.");
		}else {
			System.out.println("슬픔을 "+num+"번 나누면... "+result+"만큼의 위로를 얻는구나...");
		}
	}
}

public class quiz1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculatorTosim tosim2 = new calculatorTosim();
		int count = 0;
		while(count<1) {
			try {
				tosim2.calculate(10);
				tosim2.calculate(5);
				tosim2.calculate(0);
			}catch (ArithmeticException ae) {
				System.out.println("슬픔을 0으로 나눌 순 없어... 이건... 이건 그냥 무한한 슬픔이야... 흑...");
				break;
			}finally {
				tosim2.calculate(2);
				System.out.println("토심이: (훌쩍) 그래도... 계산을 끝마쳤어...");
				count++;
			}
			
		}
	}

}
