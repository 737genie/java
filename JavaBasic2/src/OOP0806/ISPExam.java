package OOP0806;

interface Entertainer{
	void perform(); // 공연
	void cook(); // 요리
}


public class ISPExam {
	// 인터페이스 분리 원칙(Interface Segregation Principle)
	// SRP의 인터페이스버전
	// 인터페이스는 최대한 잘게 분리해라.
	// 용도가 다르면 그냥 다른 인터페이스로 써서 처리할것.
	// 결론 : 필요한 인터페이스가 있으면 필요할때만 가져가라.
}