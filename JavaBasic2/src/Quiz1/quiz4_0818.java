package Quiz1;

import java.util.List;
import java.util.Map;

class Artifact {
	String name;
	int powerLevel;
	public Artifact(String n, int p, List<String> enchantments) {name=n; powerLevel=p;}
}

public class quiz4_0818 {

	public static void main(String[] args) {
		
	List<Map<String, List<Artifact>>> chaoticRiftData = List.of(
            Map.of(
                "고대 서랍장", List.of(
                    new Artifact("얼음의 수정검", 900, List.of("ICE", "SWORD")),
                    new Artifact("이프리트의 심장", 1500, List.of("FIRE", "SUMMON")) // 강력한 화염 유물
                )
            ),
            Map.of(
                "먼지 쌓인 보물상자", List.of(
                    new Artifact("가이아의 방패", 1200, List.of("EARTH", "DEFENSE"))
                ),
                "잊혀진 제단", List.of(
                    new Artifact("불꽃의 채찍", 850, List.of("FIRE", "WHIP")),
                    new Artifact("뇌신의 망치", 1400, List.of("LIGHTNING", "HAMMER"))
                )
            )
        );
	
	
			
		
	
	}

}
