package InnerClass;

@FunctionalInterface // 함수형 인터페이스를 명시적으로 선언하기 위해 사용하는 어노테이션
					 // 람다 표현식을 사용하기 위해 주로 씀
					 // 이 어노테이션이 붙은 인터페이스는 추상메서드를 단 1개만 쓸 수 있음
interface ClickListener {
    void onClick();
}

// === 2. 버튼 클래스 정의 ===
class Button {
    private String name;
    private ClickListener clickListener;
    public Button(String name) { this.name = name; }
    public void setClickListener(ClickListener listener) { this.clickListener = listener; }
    public void click() {
        System.out.println("'" + name + "' 버튼이 클릭되었습니다.");
        this.clickListener.onClick();
    }
}

public class NoNameClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Button order = new Button("주문");
		
		// order 객체에 클릭이벤트를 처리하기 위한
		// 리스너 설정(인터페이스 로딩)
		order.setClickListener(new ClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("주방에 주문을 전달합니다.");
			}
			
		});
//		order.click();
		// onClick 메서드 쓰려고 기존 방식보다 불편하게 코드를 작성하고 있음
		
		
		order.setClickListener(() -> System.out.println("주방에 주문을 전달합니다."));
		order.click();
		
		// 위와 같이 람다를 쓰면 간결하게 표현 가능
		
		// 람다로 익명 내부 클래스의 활용을 바꾸는 과정
		// 1. setClickListener의 파라미터로 요구받는게 ClickListener니까
		// 객체 생성해야하니 이름 생략하고 () -> 로 간단하게 쓰자
		// 2. @FunctionInterface 어노테이션 붙어있으면 메서드 1개인건 확실하니
		// 굳이 메서드명, 리턴타입 안 써도 되니까 화살표로 축약하고 내용은 {} 안에 채워넣기
		
		// pros & cons
		// pros
		// 1. 간결함 : 핵심 의도만 명확하게 보여주는 특징이 있음
		//          - 스트림 API와 최상의 결합
		// 2. 함수형 프로그래밍이 가능해짐
		// 3. 병렬 처리에 유용함 -> 성능 향상에 도움을 줌
		
		// cons
		// 1. 가독성 떨어짐
		// 2. 일부 예외처리 활용 불가
		
	}

}
