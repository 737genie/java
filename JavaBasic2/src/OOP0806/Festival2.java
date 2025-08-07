package OOP0806;

////=== 1. 역할 정의 (인터페이스) ===
//interface Performer { void perform(); }
//interface Cheerable { void cheer(); }
//interface Cookable { void cook(); }
//
////=== 2. 공통 조상 및 캐릭터 클래스 정의 ===
//abstract class Animal {
// private String name;
// public Animal(String name) { this.name = name; }
// public String getName() { return name; }
//}
//
//class Dog extends Animal implements Performer, Cheerable {
// public Dog(String name) { super(name); }
// @Override public void perform() { System.out.println("공연: " + getName() + "이(가) 원반을 물어옵니다!"); }
// @Override public void cheer() { System.out.println("응원: " + getName() + "이(가) 멍! 멍! 짖습니다!"); }
//}
//
//class Cat extends Animal implements Performer {
// public Cat(String name) { super(name); }
// @Override public void perform() { System.out.println("공연: " + getName() + "이(가) 캣타워에 오릅니다."); }
//}
//
//class WaffleBear extends Animal implements Performer, Cookable {
// public WaffleBear(String name) { super(name); }
// @Override public void perform() { System.out.println("공연: " + getName() + "이(가) 꿀단지 저글링을 선보입니다."); }
// @Override public void cook() { System.out.println("요리: " + getName() + "이(가) 세상에서 가장 맛있는 와플을 굽습니다."); }
//}
//
//class MC implements Cheerable {
// private String name;
// public MC(String name) { this.name = name; }
// @Override public void cheer() { System.out.println("응원: 사회자 " + this.name + "이(가) 외칩니다. '최고!'"); }
//}
//
////=== 3. 축제 진행 클래스 리팩토링 (DIP 적용) ===
//class Festival {
// private Performer[] performers;
// private Cheerable[] cheerers;
//
// // 생성자를 통해 의존성을 주입받는다.
// public Festival(Performer[] performers, Cheerable[] cheerers) {
//     this.performers = performers;
//     this.cheerers = cheerers;
// }
//
// public void startPerformance() {
//     System.out.println("\n--- 🎶 신나는 공연 시간입니다! ---");
//     for (Performer p : performers) {
//         p.perform();
//     }
// }
//
// public void startCheering() {
//     System.out.println("\n--- 👏 뜨거운 응원 시간입니다! ---");
//     for (Cheerable c : cheerers) {
//         c.cheer();
//     }
// }
//}
//
////=== 4. 메인 클래스 (총괄 기획자 역할) ===
//public class Festival2 {
// public static void main(String[] args) {
//     System.out.println("🎊 캐릭터 마을 대축제 기획 시작! 🎊");
//
//     // 1. 오늘 축제에 참여할 모든 캐릭터 객체를 생성한다.
//     Dog nureongi = new Dog("누렁이");
//     Cat tango = new Cat("탱고");
//     WaffleBear waffleBear = new WaffleBear("와플곰");
//     MC zanmangLoopy = new MC("잔망루피");
//
//     // 2. 역할에 따라 팀을 구성한다.
//     Performer[] performerTeam = { nureongi, tango, waffleBear };
//     Cheerable[] cheererTeam = { nureongi, zanmangLoopy };
//     Cookable[] cookTeam = { waffleBear };
//     
//     // 3. 팀을 Festival 객체에 주입하여 축제를 생성한다.
//     Festival festival = new Festival(performerTeam, cheererTeam);
//     
//     // 4. 축제를 파트별로 진행시킨다.
//     festival.startPerformance();
//     festival.startCheering();
//
//     // 5. 요리 경연 파트 (새로 추가)
//     System.out.println("\n--- 🍳 맛있는 요리 경연 시간입니다! ---");
//     for (Cookable c : cookTeam) {
//         c.cook();
//     }
// }
//}