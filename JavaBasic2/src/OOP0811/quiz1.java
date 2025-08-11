package OOP0811;

interface Reviewable {String review();}

class Restaurant implements Reviewable {
	String name;
	public Restaurant(String name) {
		this.name=name;
	}
	public String review() {
		if(name.equals("한식당")) {
			return "급하냥: (김치를 맛보며) ...흠, 85점. 숙성도가 완벽하군. 통과!";
		}
		else if(name.equals("양식당")) {
			return "급하냥: (스테이크를 썰며) ...좋아, 미디엄 레어. 90점. 다음!";
		}
		else if(name.equals("분식집")) {
			return "급하냥: (떡볶이를 찍으며) ...밀떡이군! 합격! 100점!";
		}
		else {
			return "급하냥: 그런 식당은 없어.";
		}
	}
}

public class quiz1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Restaurant[] restaurants = {
				new Restaurant("한식당"),
				new Restaurant("양식당"),
				new Restaurant("분식집")
				};
		
		System.out.println("### 급하냥의 초스피드 맛집 심사 ###");
		for(Restaurant r : restaurants) {
			System.out.println(r.review());
		}
	}

}
