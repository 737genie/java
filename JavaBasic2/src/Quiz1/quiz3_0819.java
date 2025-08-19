package Quiz1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Equipment{
	String name;
	int durability;
	public Equipment(String n, int d) {name=n; durability=d;}
	public String getName() {return name;}
	public int getDurability() {return durability;}
	
}

public class quiz3_0819 {
	public static void main(String[] args) {
		List<Equipment> gymEquipments = new ArrayList<>();
		
		gymEquipments.add(new Equipment("분노의 샌드백", 8)); // 위험
		gymEquipments.add(new Equipment("평온의 런닝머신", 95));
		gymEquipments.add(new Equipment("인내의 벤치프레스", 35));
		gymEquipments.add(new Equipment("해탈의 레그프레스", 25)); // 안전 기준 미달
		
		gymEquipments.stream()
		.filter(d->d.durability<=10)
		.collect(Collectors.toList())
		.forEach(d->{
			if(d != null) {
				System.out.println("카피바라: 즉시 교체해야 할 위험 기구가 있습니까? -> "+true);
				System.out.println("카피바라: 모든 기구의 내구도가 30 이상으로 안전합니까? -> "+false);
				gymEquipments.remove(d);
				System.out.println("카피바라: ...점검이 끝났습니다. 모두 평온한 하루 보내시길...");
			} else {
				System.out.println("카피바라: 즉시 교체해야 할 위험 기구가 있습니까? -> "+false);
				System.out.println("카피바라: 모든 기구의 내구도가 30 이상으로 안전합니까? -> "+true);
				System.out.println("카피바라: ...점검이 끝났습니다. 모두 평온한 하루 보내시길...");
			}
			});
		
		
//		List<Equipment> safeEquip = gymEquipments.stream()
//				.filter(d->d.getDurability()>=30)
//				.collect(Collectors.toList());
		
		
	}
}
