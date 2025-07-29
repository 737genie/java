package Basic;

public class WhileForExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 나는 점심을 매우 먹고싶다 화면에 10개 출력
		
		
		
		// for (초기식; 조건식; 증감식;) {}
		
		for (int i=1; i<11; i++) {
			System.out.println(i + "나는 점심을 매우 먹고싶다.");
		};
		
		
		int k = 0;
		while (k<5) {
			k++;
			System.out.println("누렁이에게 간식을 줍니다 : " + k + "회");
		}
		
		int total = 0;
		for (int i=1; i<11; i++) {
			total += i;
			System.out.println(total);
		}
		System.out.println("============================");
		int j = 0;
		int total2 = 0;
		while (j<10) {
			j++;
			total2 += j;
			System.out.println(total2);
		}
		
		//디버깅 모드(실행: f11 or Run > Debug)
		//브레이크포인트(중단점): 코드가 실행되다가 일시적으로 멈추는 지점
		//브레이크포인트 설정: 라인 더블클릭, 마우스 우클릭으로 설정, Ctrl+Shift+B
		
		int tot = 0;
		for (int i=1; i<11; i++) {
			if (i%2==0) {
				tot += i;				
			}
			System.out.println(tot);
		}
		
		// 페르소나
		
		// 퀴즈: 사용자에게 3번의 기회를 줘서 로그인 성공이나 실패를 알리는 프로그램.
		// 아이디: gunchim, 비번: 9724
		
		int p = 0;
		while(p>=3) {
			System.out.println("로그인 시도 "+p+"회");
			System.out.println("=======================");
			System.out.println("");
		}
		
	}

}
