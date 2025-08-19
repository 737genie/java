package Util;

//1. 계산 규칙 인터페이스
interface Calculable {
 double calculate(double price);
}

//2. 규칙을 구현하는 enum
enum Membership implements Calculable {
 BRONZE("브론즈") {
     // 3. BRONZE 등급만의 할인 로직
     @Override
     public double calculate(double price) {
         return price * 0.01; // 1% 할인
     }
 },
 SILVER("실버") {
     // 3. SILVER 등급만의 할인 로직
     @Override
     public double calculate(double price) {
         return price * 0.05; // 5% 할인
     }
 },
 GOLD("골드") {
     // 3. GOLD 등급만의 할인 로직
     @Override
     public double calculate(double price) {
         return price * 0.10; // 10% 할인
     }
 }; // 세미콜론 필수!

 private final String tierName;
 private Membership(String tierName) { this.tierName = tierName; }
 public String getTierName() { return tierName; }
}

public class SmartDiscountSystem {
 public static void main(String[] args) {
     Membership customerTier = Membership.SILVER;
     double orderPrice = 10000;

     // 4. if문 없이, 그냥 해당 등급의 계산 메서드를 호출!
     double discountAmount = customerTier.calculate(orderPrice);

     System.out.println("바쁘개: '계산 완료!'");
     System.out.println(" - 고객 등급: " + customerTier.getTierName());
     System.out.println(" - 주문 금액: " + orderPrice + "원");
     System.out.println(" - 할인 금액: " + discountAmount + "원");
 }
}

