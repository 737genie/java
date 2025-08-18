package Quiz1;

import java.util.List;

class Mixtape {
	String title;
	int noiseLevel;
	public Mixtape(String t, int n) {title=t; noiseLevel=n;}
	public int getNoiseLevel() {return noiseLevel;}
}
public class quiz3_0818 {

	public static void main(String[] args) {
		List<Mixtape> spilledMixtapes = List.of(
				new Mixtape("붐뱁 비트 VOL.1", 95),
				new Mixtape("Lo-Fi Chillhop", 20),
				new Mixtape("누렁이가 고오급 사료 먹는 소리", 88),
				new Mixtape("드릴 비트 샘플", 100),
				new Mixtape("새소리 ASMR", 5),
				new Mixtape("개뼉다구끼리 부딪히는 소리", 120),
				new Mixtape("물방울 소리", 2)
				);
		
		int totalNoise = spilledMixtapes.stream()
		.filter(m->m.noiseLevel>=90)
		.mapToInt(Mixtape::getNoiseLevel)
		.sum();
		
		long peacefulTrackCount = spilledMixtapes.stream()
				.filter(m->m.noiseLevel<=30)
				.count();
		
		System.out.println("카피바라: 음... 정화해야 할 소음 에너지는 총 "+totalNoise+" 데시벨이군요.");
		System.out.println("카피바라: 그리고... 연못에 남겨진 마음의 평화는 "+peacefulTrackCount+"개 입니다.");
		
		
	}

}
