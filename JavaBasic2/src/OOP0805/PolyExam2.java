package OOP0805;

class Dogs{
	
}

class Husky extends Dogs{
	
}

class Welsicogi extends Dogs{
	
}

public class PolyExam2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Dogs d1 = new Dogs();
		Husky h1 = new Husky();
		
		// 대분류 인스턴스는 소분류로 상속시킬 수 있지만 반대는 안 됨, 마찬가지로 같은 급의 분류로도 상속 안 됨
		Dogs d2 = new Husky();	// OO
//		Husky h2 = new Dogs(); // XX
//		Husky h3 = new Welsicogi(); // XX 
		
	}

}
