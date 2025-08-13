package InnerClass;

public class InnerClassExam {
	public static void main(String[] args) {
		// innerclass (내부클래스)
		// - 클래스 내부의 클래스
		
		// 필요한 이유
		// 1. 캡슐화 강화 - 외부에 불필요한 정보 노출은 없게 하되 
		//				내부적으로는 데이터의 유기적 연결이 가능(ex. private 멤버변수)
		// 2. 논리적인 그룹화, 가독성 향상 
		//    - 내부에서 코드를 논리적으로 묶기 위함, 설계 내용 이해가 쉬워짐
		
		// 내부클래스는 반드시 이름이 있을 필요는 없음
		
		// 내부 클래스 예시
		class WaffleMachine {
		    private String machineName = "와플곰 에디션 v3";

		    // 멤버 내부 클래스: 오직 WaffleMachine 안에서만 의미를 가짐
		    class Heater { // 재사용성은 거의 없지만 이 클래스에서만 사용되는거라면 상관없잖아?
		        public void warmUp() {
		            // 내부 클래스는 외부 클래스의 private 멤버에 직접 접근 가능!
		            System.out.println(machineName + "의 히터가 예열을 시작합니다. (온도: 180도)");
		        }
		    }

		    public void start() {
		        System.out.println("와플 기계 작동!");
		        Heater heater = new Heater(); // 내부 부품 객체 생성
		        heater.warmUp();
		        System.out.println("반죽을 부어주세요...");
		    }
		}
		
		
		// pros & cons
		// pros 
		// 1. 외부 변수 접근(캡슐화 구현에 유용)
		// 2. 즉시성 (필요한 위치에서 즉시 만들어서 쓰는 것)
		//    - 안드로이드 같은 GUI 환경에서 활용하기 매우 좋음
		// cons
		// 1. 재사용성이 매우 떨어짐 -> 유지 보수 하기 안 좋음
		// 2. 논리적 가독성은 향상되지만 코드적 가독성은 저하됨
		
		
		
	}
}
