//package OOP0806;
//
////요리 경연 대회에 참여한 와플곰
//// 경연대회가 진행되다 보니 와플곰 인기가 많아짐
//// 재고가 부족해 와플곰은 재고까지 관리하게 됨
//
//interface Cookable {public void cook();}
//
//interface Performer {public void manage();}
//
//class Animals { }
//
//class FastCat extends Animals implements Performer {
//	@Override
//	public void manage() {System.out.println("급하냥이 재료가 충분한지 확인하고 부족하면 주문합니다.");}
//}
//
//class WaffleBear extends Animals implements Cookable {
//	// 아래는 SRP 위반. 요리만 하는게 아니고 재고관리까지 하기 때문
////	@Override
////	public void manage() {System.out.println("와플곰이 재료가 충분한지 확인하고 부족하면 주문합니다.");}
////	@Override
////	public void cook() {System.out.println("요리중");}
//	
//	private FastCat fastCat = new FastCat();
//	@Override
//	public void cook() {System.out.println("요리중"); fastCat.manage();}
//}
//public class SRPExam {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		WaffleBear gom = new WaffleBear();
//		gom.cook();
//		
//
//	}
//
//}
