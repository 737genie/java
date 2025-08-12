package Collections;

public class ThrowsExam {
	// throw - 예외 처리 키워드
	// Throwable이라는 최상위 클래스
	// Error, Exception(예외)
	
	// 주의: 호출한 곳에서는 반드시 예외처리 코드가 필요함
	// 		-> Exception의 경우 개발자들이 예측하지 못한 예외 이외에는 가급적 안 쓰도록
	
	// 밑과 같이 throws를 붙이면 해당 메서드는 무조건 예외 처리 항목을 요구함
	public static void main(String[] args) throws Exception {}
	// throw 키워드와 throws 키워드는 전혀 다르다
	// throw : 폭탄이었으면 터뜨리는 것
	// 		   -> throw가 있으면 예외를 무조건 발생시키는 것(직접 던지기)
	// throws : 이 클래스 혹은 메서드 내부에 폭탄이 있을지도 모른다, 라고 경고하는 것
	// 		   -> 해당 예외가 발생할 수도 있음을 알림
	
	// 왜 써야할까?
	// 1. throws는 SRP를 지킬 때 유용함
	// 책임을 분리함으로써 유연하고 유지보수 하기 쉬운 코드를 작성하게 해줌
	
	// ex. 파일이 없는 경우 FileNotFoundException을 띄워주면 됨
	// -> 경고창을 띄우기, 대체, 로그(메세지) 남기기 중 하나를
	// -> 사용자 인터페이스를 담당하는 메서드가 설정하는 것
	
	
}

