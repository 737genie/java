package OOP0804;

class BakingTool {
	String toolName;
	int temperature;
	
	public BakingTool(String toolName, int temperature) {
		this.toolName = toolName;
		this.temperature = temperature;
		System.out.println("도구: "+toolName+", 온도: "+temperature+"도");
	}
	
	void start() {
		System.out.println("포근포근~ 시작하구마");
	}
	void stop() {
		System.out.println("달콤하게 끝났구마");
	}
}

class Oven extends BakingTool {
	String item;
	
	public Oven(String toolName, int temperature) {
		super(toolName, temperature);
	}
	
	void bake(String item) {
		System.out.println(item+"처럼 달달하게 굽는구마");
	}
}

class Mixer extends BakingTool {
	String ingredient1;
	String ingredient2;
	
	public Mixer(String toolName, int temperature) {
		super(toolName, temperature);
	}
	
	void mix(String ingredient1, String ingredient2) {
		System.out.println(ingredient1+"과 "+ingredient2+" 섞는 중이구마");
	}
}



public class quiz2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Oven bbang = new Oven("주걱", 100);
		bbang.bake("d");
		bbang.start();
		bbang.stop();
		
		Mixer mix2 = new Mixer("주걱", 100);
		mix2.mix("딸기","솜사탕");
		
	}

}
