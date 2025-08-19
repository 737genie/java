package Util;

enum Mood {
	HAPPY, SAD, ANGRY
}

public class EnumExam {
    public static void printMenuInfo(String category) {
        if (category.equals("디저트")) {
            System.out.println("달콤한 디저트 코너입니다!");
        } else if (category.equals("음료")) {
            System.out.println("시원한 음료 코너입니다!");
        } else {
            System.out.println("든든한 식사 코너입니다!");
        }
    }

	public static void main(String[] args) {
		// Enum
		// - 서로 관련있는 상수들의 집합을 정의하는 데이터 타입(상수 모음집)
		// ex. 기분 - 행복, 슬픔, 화남 
		
		
		// 왜 써야하는가?
		// -> enum은 단순히 상수값을 저장하기 위해 쓰는 개념이 아님
		// -> 사용자의 실수를 줄이는 것이 목표
		// -> 단순 값 나열이 아니고 필드, 생성자, 메서드도 가질 수 있는 특수한 ^클래스^
		// -> 개발자, 사용자에게 명확한 선택지를 줄 수 있음
		// -> 기술적으로는 데이터 처리 로직에 대한 캡슐화까지 노릴 수 있음
		// :: 유연하고 확장성 있는 설계를 진행할 수 있음 > enum 내부 메서드를 활용
		
		printMenuInfo("디저트");
		
		
		// 퀴즈
		// 정의해둔 enum클래스 기준으로 
		// 다음의 출력 결과와 비슷하게 출력해보기
		
		System.out.println("--- 당신의 일주일 감정 요약 ---");
		DayOfWeek.MONDAY.printMood();
		DayOfWeek.TUESDAY.printMood();
		DayOfWeek.WEDNESDAY.printMood();
		DayOfWeek.THURSDAY.printMood();
		DayOfWeek.FRIDAY.printMood();
		DayOfWeek.SATURDAY.printMood();
		DayOfWeek.SUNDAY.printMood();
		
		System.out.println("------------------------------------");
		for(DayOfWeek d : DayOfWeek.values()) {
			d.printMood();
		}
		
		// enum 활용 예시 (현업)
		
		// 퀴즈
		// 바쁘개의 절규: "고객 등급별로 할인율을 계산하는 로직이 있는데, "
		// 'BRONZE'일 땐 1%, 'SILVER'일 땐 5%, 'GOLD'일 땐 10%... 
		// 이렇게 if-else가 끝도 없이 늘어나요! 
		// 나중에 'VIP' 등급이 추가되면 또 if문을 고쳐야 하잖아요! 이건 악몽이에요!"
		
		
		
		
	}
	
	
}
