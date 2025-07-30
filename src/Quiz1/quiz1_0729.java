package Quiz1;

public class quiz1_0729 {

	public static void main(String[] args) {
		// 카피바라의 인내심 명상
		
		int First = 100;
		int Concentrate = 0;
		int minute = 0;
		
		while(minute<30) {
				minute++;
				System.out.println("카피바라는 "+minute+"분째 명상 중 ... ");
				System.out.println("인내심: "+First+", 명상 집중도: "+Concentrate);
				if(minute%5==0) {
					System.out.println("토심이가 나타나 인내심을 25 감소시켰습니다. \r");
					First = First - 25; 
					if(First < 50 && First >0) {
						System.out.println("인내심이 50 미만입니다. 카피바라의 명상 집중도가 10 감소합니다. \r");
						Concentrate = Concentrate - 10;
					}
					else if(First<=0) {
						System.out.println(">>> 인내심이 0이 되었습니다. 명상을 종료합니다. <<< \r");
						break;
					}
				} else {
					System.out.println("\r !명상 집중도가 1 올랐습니다. \r");
					Concentrate = Concentrate + 1;
				}
				if(minute==30) {
					System.out.println(">>> 명상이 성공적으로 종료되었습니다. <<<");
				}
		}

	}

}
