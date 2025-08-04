package Quiz1;

public class quiz1_0730 {

	public static void main(String[] args) {
		// 와플곰의 꿀 등급 평균 내기
		
		int[] honey = {8, 9, 7, 10, 6};
		int len = honey.length;
		int total = 0;
		for(int i=0; i<len; i++) {
			total += honey[i];
		}

		System.out.println("꿀 점수의 평균: "+total/len);
		
		// 탱고의 금지된 스텝 찾기
		
		int[] step = {1, 2, 4, 3, 6};
		int len2 = step.length;
		for(int i=0; i<len2; i++) {
			if(step[i]==5) {
				System.out.println("탱고의 안무에 금지된 스텝 5번이 포함 되어 있습니다.");
			}
		}
		
		int[] work = {3, 5, 1, 8, 2};
		String[] done = {"true", "false", "true", "false", "false"};
		int len3 = work.length;

		int max = work[0];

		for(int i=0; i<len3; i++) {
			if(done[i].equals("false")) {
				if(work[i]>max) {
					max = work[i];
				}
			}
		}
		System.out.println("미완료 된 것 중 가장 높은 업무 중요도: "+max);
	}
}
