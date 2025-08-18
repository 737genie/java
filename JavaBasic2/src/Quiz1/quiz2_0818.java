package Quiz1;

import java.util.List;
import java.util.stream.Collectors;

class Snack33 {
	String name;
	public Snack33(String n) {name=n;}
	public String getName() {return name;}
}
public class quiz2_0818 {

	public static void main(String[] args) {
		List<Snack33> snackNames = List.of(
				new Snack33("뼈다귀"),
				new Snack33("사료"),
				new Snack33("고구마"),
				new Snack33("치킨")
				);
		
		List<String> changeNames = snackNames.stream()
				.map(s-> s.name.replaceAll(s.name, "맛있는 "+s.name+" 최고입누렁!"))
				.collect(Collectors.toList());
				
		System.out.println(changeNames);
	}

}
