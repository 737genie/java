package Basic;

public class WhileExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		while(true) {
			for (int j=1; j<=10; j++) {
				System.out.println("나무를 "+j+"번 찍었습니다.");				
			}
			System.out.println("나무가 쓰러지고 있습니다.");
			break;
		}
		
		int i = 0;
		while (i<50) {
			i++;
			if (i%6==0) {
				System.out.println(i+"는 6의 배수입니다.");
				continue;
			}
			System.out.println(i);
			
				
		}
		
		
		//do while 
		// 조건의 참거짓 유무와는 무관하게 반복문 내부를 한번 실행한 후 조건식을 확인하는 문법
		
		// 무 조 건 한번은 해봄
		
		
		
		
	}

}
