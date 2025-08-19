package Quiz1;

import java.util.List;

class Applicant {
	String name;
	int power;
	public Applicant(String n, int p) {name=n; power=p;}
	public String getName() {return name;}
	public int getPower() {return power;}
	
}
public class quiz2_0819 {

	public static void main(String[] args) {
		List<Applicant> applicants = List.of(
	            new Applicant("초보 검사 토심이", 15),
	            new Applicant("대마법사 와플곰", 99),
	            new Applicant("B-Boy 누렁이", 75),
	            new Applicant("신입 해커 급하냥", 30),
	            new Applicant("명상가 카피바라", 88)
	        );
		
		long soldier = applicants.stream()
		.filter(p->p.power>=50)
		.count();
		
		System.out.println("탱고: 어디, 쓸만한 녀석들이 얼마나 왔는지 볼까...\r\n"
				+ "\r\n"
				+ "탱고 마스터님, 보고 드립니다!\r\n"
				+ "자격을 갖춘 정예 모험가는 총 "+soldier+"명입니다!");
	}

}
