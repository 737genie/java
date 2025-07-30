package Basic;

public class ForExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 구구단 6단 출력
		for (int i=1; i<10; i++) {
			System.out.println("6 * "+i+" = "+6*i);
		}
		
		System.out.println("===============================");
		// 구구단 2단에서 9단 출력
		for (int i=2; i<10; i++) {
			for (int j=1; j<10; j++) {
				System.out.println(i+" * "+j+" = "+(j*i));
			}
		}
		
		System.out.println("===============================");
		System.out.println("와플곰: 벌집 만들기를 시작해볼까!");
		for(int i=1; i<4; i++) {
			System.out.println("--- "+i+"층 작업 시작! ---");
			for(int j=1; j<5; j++) {
				System.out.println("> "+i+"층의 "+j+"번 방을 만들었습니다.");
				
			}
			System.out.println("--- "+i+"층 작업 완료! --- \r");
		}
	}

}
