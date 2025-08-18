package Quiz1;

import java.util.List;

class Snack {
	String name;
	int calories;
	public Snack(String n, int c) {name=n; calories=c;}
	public int getCalories() {return calories;} 
}
public class quiz1_0818 {

	public static void main(String[] args) {
		List<Snack> spiledSnack = List.of(
				new Snack("초코바", 750),
				new Snack("빠다코코낫", 500),
				new Snack("오예스", 600),
				new Snack("빼빼로", 90),
				new Snack("프로틴쉐이크", 80),
				new Snack("견과류", 50)
				);
	
		int totalHighCalories = spiledSnack.stream()
		.filter(s -> s.getCalories() >= 500)
		.mapToInt(Snack::getCalories)
		.sum();
		
		
		long lowCalorieCount = spiledSnack.stream()
				.filter(s -> s.getCalories() < 100)
				.count();
		
		
		
		System.out.println("음... 자연의 균형을 깨뜨리는... 과도한 에너지를 계산해봅시다.\r - 칼로리가 높은 간식들의 총 칼로리: "+totalHighCalories);
		System.out.println("작은 기쁨들은... 그 수도 중요한 법이지요.\r - 칼로리가 낮은 간식들의 총 개수: "+lowCalorieCount);
		
	}

}
