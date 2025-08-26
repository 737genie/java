package Quiz1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum DanceGenre { HIPHOP, BALLET, JAZZ, POPPIN }

class Student {
    String name; DanceGenre genre;
    public Student(String n, DanceGenre g) { name=n; genre=g; }
    public String getName() { return name; }
    public DanceGenre getGenre() { return genre; }
}



public class quiz1_0825 {
	public static void main(String[] args) {
		Map<DanceGenre, List<String>> classRoster = new HashMap<>();
		List<Student> applicants = List.of(
			    new Student("탱고", DanceGenre.HIPHOP),
			    new Student("토심이", DanceGenre.BALLET),
			    new Student("잔망루피", DanceGenre.POPPIN),
			    new Student("오구", DanceGenre.JAZZ),
			    new Student("급하냥", DanceGenre.HIPHOP) // 힙합 수강생 추가
			);
		
		for(Student a : applicants) {
			if (!classRoster.containsKey(a.getGenre())) {
                // 키가 없다면, 새로운 리스트를 만들어서 추가
                classRoster.put(a.getGenre(), new ArrayList<>());
            }
            
            // 해당 장르의 리스트를 가져와서 학생 이름 추가
            classRoster.get(a.getGenre()).add(a.getName());
		}
		
		for (Map.Entry<DanceGenre, List<String>> entry : classRoster.entrySet()) {
            System.out.println("【" + entry.getKey() + "반】: " + entry.getValue());
        }
		
	}
}
