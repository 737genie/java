package OOP0805;

class Carrot {
	int size;
	String color;
	public Carrot(int size, String color) {
		this.size = size;
		this.color = color;
	}
}

public class quiz2 {

	public static void main(String[] args) {
		Carrot car1 = new Carrot(10, "blue");
		System.out.println("당근의 크기는 "+car1.size+"이고, 당근의 색은 "+car1.color+"이야.");

	}

}
