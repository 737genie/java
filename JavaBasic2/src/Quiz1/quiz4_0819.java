package Quiz1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DanceMove {
    String name;
    String genre;
    int swagScore;

    public DanceMove(String name, String genre, int swagScore) {
        this.name = name;
        this.genre = genre;
        this.swagScore = swagScore;
    }

    public int getSwagScore() { return swagScore; }

    @Override
    public String toString() {
        return String.format("[%s] %s (스웩: %d)", genre, name, swagScore);
    }
}

public class quiz4_0819 {

	public static void main(String[] args) {
		 Map<String, List<DanceMove>> moveLibrary = Map.of(
		            "HIPHOP", List.of(
		                new DanceMove("윈드밀", "HIPHOP", 95),
		                new DanceMove("탑락", "HIPHOP", 80),
		                new DanceMove("식스 스텝", "HIPHOP", 92)
		            ),
		            "POPPIN", List.of(
		                new DanceMove("웨이브", "POPPIN", 93),
		                new DanceMove("터팅", "POPPIN", 88),
		                new DanceMove("로봇 댄스", "POPPIN", 85)
		            ),
		            "LOCKING", List.of(
		                new DanceMove("락킹", "LOCKING", 89)
		            )
		        );
		
		List<DanceMove> selected = moveLibrary.entrySet().stream()
		.filter(m->!m.getKey().equals("LOCKING"))
		.flatMap(m->m.getValue().stream())
		.sorted(Comparator.comparing(DanceMove::getSwagScore))
		.limit(2)
		.collect(Collectors.toList());
		
		selected.stream()
		.forEach(i->
		System.out.println(i.genre));
	}

}
