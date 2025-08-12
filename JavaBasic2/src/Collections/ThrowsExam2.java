package Collections;

// 우리만의 커스텀 예외: 유리가 깨지는 끔찍한 사건
class GlassBrokeException extends Exception {
	public GlassBrokeException(String message) {
		super(message);
	}
}

// 사고뭉치 망곰이의 행동 클래스
class MangomActions {
	// 이 메서드는 GlassBrokeException을 발생시킬 수 있다고 '경고'한다.
	public void moveFragileItem() throws GlassBrokeException {
		System.out.println("망곰: (덜덜) '유리컵을 옮기기 시작합니다...'");
		
		// 50% 확률로 사고 발생!
		if (Math.random() < 0.5) {
			// 'throw'로 실제로 예외(사고)를 발생시킨다.
			throw new GlassBrokeException("아뿔싸! 손이 미끄러져서 와장창!");
		}
		
		System.out.println("망곰: '휴... 겨우 옮겼다...'");
	}
}
public class ThrowsExam2 {
    public static void main(String[] args) {
        MangomActions mangom = new MangomActions();

        System.out.println("바쁘개: '망곰아, 저 유리컵 좀 옮겨!'");

        // 망곰이의 메서드에 'throws'가 붙어있으므로, 호출하는 쪽에서 반드시 try-catch로 감싸야 한다.
        try {
            mangom.moveFragileItem();
            System.out.println("바쁘개: '오, 제법인데? 무사히 옮겼군.'");
        } catch (GlassBrokeException e) {
            // 망곰이가 던진 예외를 여기서 잡아서 처리한다.
            System.out.println("\n바쁘개: '내가 그럴 줄 알았지!'");
            System.out.println("   사고 보고 접수: " + e.getMessage());
            System.out.println("   조치: '괜찮아, 보험 처리하면 돼. 일단 빗자루부터 가져와!'");
        }
        
        System.out.println("\n바쁘개: '자, 다음 업무 진행하자!'");
    }
}
