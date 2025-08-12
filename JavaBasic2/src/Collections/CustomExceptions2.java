package Collections;

class SnackOverException extends Exception {
	public SnackOverException(String msg) {
		super(msg);
	}
}

class Feeder {
	private int snackCount=0;
	private final int MAX_SNACKS=3;
	
	public void giveSnack(String snackName) throws SnackOverException {
		snackCount++;
		if(snackCount>MAX_SNACKS) {
			throw new SnackOverException("하루 적정 간식 양을 초과하였습니다.");
		}
		else {
			System.out.println("누렁이에게 "+snackName+" 을(를) 줍니다 ... \r오늘 먹은 개수 : "+snackCount);
		}
	}
}

public class CustomExceptions2 {

	
	// 누렁이의 간식 과부화
	// 누렁이가 살이 너무 쪄서 간식을 하루 3개까지만 먹기로 결심함 
	// 하지만 3개를 먹어도 더 달라고 짖음
	// 누렁이에게 간식을 주는 것은 간식 개수 초과라는 위험을 내포하고 있음
	// (예측 가능한 위험 / checked Exception)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Feeder feeder = new Feeder();
		
		System.out.println("누렁아 간식 먹자");
		
		try {
			feeder.giveSnack("뼈다구");
			feeder.giveSnack("육포");
			feeder.giveSnack("치즈버거");
			feeder.giveSnack("고구마");
		}catch(SnackOverException e) {
			System.out.println(e.getMessage());
		}
	}

}
