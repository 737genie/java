package OOP0805;

class Dog {
    // --- 1. 정보 (필드) ---
    // private 키워드로 외부 접근을 막는다.
    private String name;
    private String favoriteFood;

    // --- 2. 생성자 ---
    public Dog(String name, String favoriteFood) {
        this.name = name;
        this.favoriteFood = favoriteFood;
    }

    // --- 3. Getter/Setter (데이터 접근을 위한 통로) ---
    // (1) 이름을 위한 Getter
    public String getName() {
        return this.name;
    }
    
    // (2) 이름을 위한 Setter
    public void setName(String newName) {
        if (newName != null && !newName.contains("바보")) {
            System.out.println("이름이 '" + this.name + "'에서 '" + newName + "'(으)로 변경되었습니다.");
            this.name = newName;
        } else {
            System.out.println("[경고] '" + newName + "'은(는) 사용할 수 없는 이름입니다!");
        }
    }
    
    // (3) 좋아하는 음식을 위한 Getter
    public String getFavoriteFood() {
        return this.favoriteFood;
    }

    // (4) 좋아하는 음식을 위한 Setter
    public void setFavoriteFood(String newFood) {
        this.favoriteFood = newFood;
    }

    // --- 4. 일반 메서드 ---
    public void introduce() {
        System.out.println("멍멍! 내 이름은 " + this.name + "이고, " + this.favoriteFood + "을(를) 제일 좋아해!");
    }
}

public class Exam3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 예시: 만들어진 객체의 이름이 아무렇게나 바뀌지 않도록 기존 설계안 강화
		// --- 1. 정보 (필드) ---
		// private 키워드로 외부 접근을 막는다.
		// 이 클래스 내부에서만 접근 가능!

		// --- 2. 생성자 ---
		// 생성자는 그대로 둔다.

		// --- 3. Getter/Setter (데이터 접근을 위한 통로) ---
		// (1) 이름을 위한 Getter: 잠긴 이름을 외부에 알려주는 역할
       
		// (2) 이름을 위한 Setter: 새로운 이름이 올바른지 검사하고, 안전하게 변경하는 역할
       
		// (3) 좋아하는 음식을 위한 Getter
       
		// (4) 좋아하는 음식을 위한 Setter (여기선 간단하게만 만듦)

		// --- 4. 일반 메서드 ---
		// 기존 메서드들은 그대로 사용
      
		// main 
      
        // --- 누렁이 객체 생성 ---
        
        // --- Getter로 정보 확인하기 ---
        // nureongi.name; // 이제 이 코드는 private 접근 오류로 에러 발생!
        // 반드시 getName() 메서드를 통해 접근해야 한다.
        
        // --- Setter로 정보 안전하게 변경하기 ---
        // nureongi.name = "바보"; // 이 코드도 당연히 에러 발생!
        // 반드시 setName() 메서드를 통해 변경해야 한다.
        // 1. 올바른 이름으로 변경 시도
        
        // 2. 유효하지 않은 이름으로 변경 시도
		
		
        Dog nureongi = new Dog("누렁이", "꿀 와플");
        System.out.println("--- 초기 상태 ---");
        nureongi.introduce();

        // --- Getter로 정보 확인하기 ---
        System.out.println("\nGetter로 확인한 이름: " + nureongi.getName());

        // --- Setter로 정보 안전하게 변경하기 ---
        System.out.println("\n--- 이름 변경 시도 ---");
        // 1. 올바른 이름으로 변경 시도
        nureongi.setName("씩씩한 누렁이");
        nureongi.introduce();
        
        System.out.println("\n--- 잘못된 이름으로 변경 시도 ---");
        // 2. 유효하지 않은 이름으로 변경 시도
        nureongi.setName("바보");
        nureongi.introduce(); // 이름이 바뀌지 않은 것을 확인!
        
        
	}

}
