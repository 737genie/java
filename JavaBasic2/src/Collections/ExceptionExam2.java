package Collections;

// 바쁘개의 배달 소요시간 계산 프로그램
// 예외 처리는 잘 했으나 예외 발생한 순간 프로그램은 멈춤
// throw로만 끝내지 않고 던진 예외를 받아 최종적으로 main에서 처리
class DeliveryScheduler {
    public void setDeliveryTime(int hours) {
        if (hours < 0) {
            // 허용되지 않는 인자 값이 들어왔을 때 예외를 발생시킨다.
        	// 프로그램 중단 시점
            throw new IllegalArgumentException("배달 소요 시간은 음수가 될 수 없습니다. 입력값: " + hours);
        }
        System.out.println(hours + "시간 후 배달되도록 설정되었습니다.");
    }
}

// 와플곰의 와플 기계
// 상황: 와플곰이 최신 와플 기계를 도입함
// 		요즘 너무 바빠서 급하냥이 하루 도움을 주고자 왔음
// 		와플기계는 반드시 예열을 먼저 한 후에 굽기를 해야하는데
// 		급하냥이 성격이 너무 급해서 바로 굽기 버튼을 누른 상황

class WaffleMachine{
	
	// this 인스턴스 지정해주는 것
	private int count=0;
	private boolean isPreheated = false;
	public void preheat() {
		this.isPreheated = true;
		System.out.println("예열 완료!");
	}
	
	public void bake() {
//		if(!isPreheated) {
//			count++;
//			System.out.println("예열이 진행되지 않아 와플 굽기에 실패했습니다 ㅠㅠ ..  굽기 시도: "+count+"회");
//		}else {
//			count++;
//			System.out.println("와플을 굽고 있습니다 ...  굽기 시도: "+count+"회");
//		}
		if(!isPreheated) {
			throw new IllegalStateException("예열이 진행되지 않았습니다.");
		}
		System.out.println("와플 굽는 중!");
	}
	
	
}


public class ExceptionExam2 {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//        DeliveryScheduler scheduler = new DeliveryScheduler();
//        
//        try {
//        	scheduler.setDeliveryTime(3); // 정상적인 경우
//        	
//        	// 예외 처리 없이 호출하면 여기서 프로그램이 비정상 종료된다!
//        	scheduler.setDeliveryTime(-5); 
//        } catch(IllegalArgumentException e) {
//        	System.out.println("바쁘개: 시간을 잘못 입력했습니다.");
//        	e.printStackTrace();
//        }
//
//        System.out.println("이 메시지는 반드시 출력됩니다."); // 프로그램이 멈췄기 때문
		
		
		// 와플곰의 와플기계 예시
		WaffleMachine machine = new WaffleMachine();
		
		try {
			machine.bake();
			machine.preheat();
			machine.bake();
		} catch(IllegalStateException ise) {
			System.out.println("와플곰: 예열을 먼저 해야 해!");
			System.out.println("다시 시작해보자.");
		} catch(Exception e) {
			
		}
		
		System.out.println("");
		
		
		
	}

}
