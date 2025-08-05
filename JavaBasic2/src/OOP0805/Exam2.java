package OOP0805;

// 캡슐화가 필요한 이유
class Rabbit {
	
	private String name;
	private String mood;
	
	public void moodByWeather(String weather) {
		// 날씨에 따라 기분을 변경하는 로직
        if (weather.equals("맑음")) {
            // this를 찍은 이유 : 현재 멤버 변수의 접근 제어자가 private이라서
        	this.mood = "행복한";
            System.out.println("날씨가 맑아서 토심이의 기분이 행복해졌어요!");
        } else if (weather.equals("비")) {
            this.mood = "우울한";
            System.out.println("비가 와서 토심이의 기분이 우울해졌어요...");
        } else {
            this.mood = "그냥 그런";
            System.out.println("토심이는 그냥 그런 기분이에요.");
        }
	}
	
	public Rabbit(String name, String mood) {
		super();
		this.name = name;
		this.mood = mood;
	}
	
	public String getName() {
		return name;
	}

	public String getMood() {
		return mood;
	}

	void jump() {
		System.out.println(name+"이(가) "+mood+" 기분으로 깡총 깡총 뜁니다");
	}
}

public class Exam2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Rabbit tosim2 = new Rabbit("토심", "완전 행복한");
		
		// private을 쓰지 않으면 ...
		// tosim2.mood = "거지같은"; // 외부 접근으로 바꿨다 가정
		// 아무나 못 바꾸도록 설정해야함
		
		
//		Rabbit tosim2 = new Rabbit();
//		tosim2.setName("메롱");
//		tosim2.setMood("완전 별로");
		
		System.out.println(tosim2.getMood());
		tosim2.moodByWeather("맑음");

		
		tosim2.jump();
		
		
	}

}
