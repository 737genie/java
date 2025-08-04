package OOP0801;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculator.add(1, 3);
		
		RabbitReport tosim1 = new RabbitReport("행복", 9);
		RabbitReport tosim2 = new RabbitReport("슬픔", 5);
		RabbitReport tosim3 = new RabbitReport("행복", 1);
		System.out.println("토심이 기분: "+tosim1.getDailyReport());
		System.out.println("토심이 기분: "+tosim2.getDailyReport());
		System.out.println("토심이 기분: "+tosim3.getDailyReport());
	}

}
