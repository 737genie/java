package OOP0806; // 바쁘개의 팀 프로젝트 관리

class TeamMember {
	String name;
	public TeamMember(String name) {this.name=name;}
	public int calculatePerformance() {
		return 100;
	}
}

class Developer extends TeamMember {
	private int fixedBugs;
	public Developer(String name, int fixedBugs) {super(name); this.fixedBugs=fixedBugs;}
	@Override
	public int calculatePerformance() {
		int result = 100 + fixedBugs*10; 
		return result;
	}
}

class Designer extends TeamMember {
	private int creativityScore;
	public Designer(String name, int creativityScore) {super(name); this.creativityScore=creativityScore;}
	@Override
	public int calculatePerformance() {
		int result = 100 + creativityScore;
		return result;
	}
}


public class quiz5 {

	public static void main(String[] args) {
		Developer d1 = new Developer("누렁이", 100);
		Designer d2 = new Designer("토심이", 50);
		
		System.out.println(d1.name+"의 성과: "+d1.calculatePerformance());
		System.out.println(d2.name+"의 성과: "+d2.calculatePerformance());

		System.out.println("팀의 총 성과: "+(d1.calculatePerformance()+d2.calculatePerformance()));

	}

}
