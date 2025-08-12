package Collections;

class RainbowHoneyShortageException extends Exception {
    // 생성자: 예외 발생 시 전달할 메시지를 받는다.
    public RainbowHoneyShortageException(String message) {
        super(message); // 부모인 Exception 클래스에 메시지를 전달
    }
}

// === 2. 커스텀 예외를 사용하는 주방 클래스 ===
class WaffleKitchen {
    private int rainbowHoneyStock = 0; // 일부러 재고를 0으로 설정!

    // 이 메서드는 RainbowHoneyShortageException을 발생시킬 수 있다고 '경고'한다.
    public void makeSpecialWaffle() throws RainbowHoneyShortageException {
        System.out.println("와플곰: '자, 스페셜 와플을 만들어볼까...'");

        if (rainbowHoneyStock <= 0) {
            // 'throw'로 우리만의 커스텀 예외를 직접 발생시킨다!
            throw new RainbowHoneyShortageException("비상! 비상! 비밀 재료인 무지개 꿀이 다 떨어졌습니다!");
        }

        System.out.println("와플곰: '달콤한 무지개 꿀을 듬뿍~'");
        // ... 요리 과정 ...
    }
}
public class CustomException {
	
	public static void main(String[] args) {
		WaffleKitchen kitchen = new WaffleKitchen();
		
		System.out.println("손님: '여기 스페셜 와플 하나요!'");
		
		try {
			// 주방에 요리를 요청한다.
			kitchen.makeSpecialWaffle();
			System.out.println("손님: '와, 정말 맛있겠다!'");
		} catch (RainbowHoneyShortageException e) {
			// 주방에서 던진 '무지개 꿀 부족' 예외를 여기서 잡아서 처리한다.
			System.out.println("\n키오스크 안내: '죄송합니다, 손님!'");
			System.out.println("   " + e.getMessage());
			System.out.println("   '대신 일반 꿀 와플은 어떠세요?'");
		}
		
	}
}

// 기본 예외처리는 구체적인 문제 상황 표현이 굉장히 어려움
// 그러니 개발자들이 구체적인 문제 상황을 정의하고 그에 맞는 비상벨을 만드는 것이 커스텀 예외
// - 중요도 대비 그렇게 어렵진 않음
// - 비즈니스에서는 더 구체적인 규칙 자체를 정하는 것이 기본
// - !오류의 체계적인 관리때문에 쓰는 것이니 철저히 할 것



