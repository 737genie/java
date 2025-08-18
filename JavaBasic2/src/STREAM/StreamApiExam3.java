package STREAM;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Package {
    String itemName;
    int weight;
    public Package(String n, int w) { itemName = n; weight = w; }
    public int getWeight() { return weight; }
}

public class StreamApiExam3 {
// 급하냥의 긴급 배송 점검
// 오늘 배송할 물품 목록이 있는데 총 몇개인지 그리고 무게가 20키로를 초과하면 비상벨을 울려야해!

// 추가된 요구 사항
// 배송 물품들의 총 개수, 평균 무게, 그리고 가장 무거운 것과 가장 가벼운 것들도 보고
    public static void main(String[] args) {
       
           List<Package> packages = List.of(
                   new Package("꿀단지", 5),
                   new Package("와플 기계", 25), // 초과 중량!
                   new Package("참치캔", 1),
                   new Package("대왕뼈다구", 19),
                   new Package("털갈이 전용 스타일러", 40)
               );
       long totalCnt = packages.stream().count();
       
       
//       boolean hasOverWeight = packages.stream()
//             .anyMatch(p -> p.getWeight() > 20);
//       
//       if(hasOverWeight) {
//          System.out.println("급하냥 : 경고! 초과 중량화물 발견! 추가 요금 발생!");
//       }else {
//          System.out.println("급하냥 : '모든 화물 무게 정상임!'");
//       }
       
       
       // 추가 요구사항 
       
       // 평균 무게 계산
       double averageWeight = 
    		   packages.stream()
    		   .mapToInt(Package::getWeight) // Package 타입의 getWeight만 Int로 가져오겠다
    		   .average()
    		   .orElse(0.0); // 만약 데이터가 없으면 0.0을 반환
       
       // 무거운 것 찾기(숫자가 가장 큰 것 찾기)
       Optional<Package> heaviestPackage = 
    		   packages.stream()
    		   .max(Comparator.comparingInt(Package::getWeight));
       			// Comparator.compaingInt : 주어진 객체의 정수값을 기준으로 비교하겠다
       heaviestPackage.ifPresent(p ->
       System.out.println("급하냥: 가장 무거운 짐은 "+p.itemName+"("+p.weight+") 입니다.")); // Optional 객체에 값이 존재할 경우에만 수행하는 메서드 
       // ifPresent -> Optinal (스트림과는 관계없는 메서드)
    		   
       // 가벼운 것 찾기(숫자가 가장 작은 것 찾기)
       Optional<Integer> heaviestPackage2 = 
    		   packages.stream()
    		   .map(Package::getWeight)
    		   .min(Integer::compareTo);
       heaviestPackage2.ifPresent(p ->
       System.out.println("급하냥: 가장 가벼운 짐의 무게는 "+"("+p+"kg) 입니다.")); // Optional 객체에 값이 존재할 경우에만 수행하는 메서드 
       
       
   } 
}
