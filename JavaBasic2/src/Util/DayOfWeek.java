package Util;

public enum DayOfWeek {
    MONDAY("월요일", "피곤해..."),
    TUESDAY("화요일", "조금 나아졌어!"),
    WEDNESDAY("수요일", "수요팅!"),
    THURSDAY("목요일", "목요일의 기적!"),
    FRIDAY("금요일", "파티 타임! 🎉"),
    SATURDAY("토요일", "행복해..."),
    SUNDAY("일요일", "내일이 월요일이라니...");
	
	
	// 필드 정의
	// 멤버 변수 and 클래스 변수(static)
	private final String day; // 멤버 변수
	private final String mood; // 멤버 변수
	
	// enum 생성자 특징 : 현업에서는 반드시 private으로 정의
	// -> enum은 상수 형태이니까 불필요한 인스턴스 생성을 방지할 필요가 있음
	// -> 접근은 무조건 enum 클래스로만 진행해야함
	private DayOfWeek(String string, String string2) {
		this.day = string;
		this.mood = string2;
	}

	// 메서드를 굳이 써야하나?
	// 
	public String getDay() {
		return day;
	}

	public String getMood() {
		return mood;
	}
	
	public void printMood() {
		System.out.println(this.day +"의 당신의 기분은? : "+this.mood);
	}
	
	
}
