package OOP0801;

public class RabbitReport {
	
	String mood;
	int energyLevel;
	
	public RabbitReport(String mood, int energyLevel) {
		this.mood = mood;
		this.energyLevel = energyLevel;
	}
	
	public String getDailyReport() {
		if(energyLevel>=8 && mood.equals("행복")) {
			return "최고의 컨디션으로 모든 일이 잘 풀리는 날!";
		}
		else if(energyLevel<=3 && mood.equals("슬픔")) {
			return "에너지가 부족하고 우울하니 휴식이 필요한 날.";
		}
		else if(energyLevel<8 && mood.equals("행복")) {
			return "기분은 좋지만 몸이 따라주지 않는 날.";
		}
		else {
			return "평범하고 무난한 날";
		}
		
	}

}
