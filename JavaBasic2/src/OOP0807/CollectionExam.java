package OOP0807;

import java.util.ArrayList;
import java.util.List;

public class CollectionExam {

	public static void main(String[] args) {
		// 컬렉션 프레임워크: 자료구조를 쉽게 쓰기 위해 자바에서 제공하는 프레임워크(라이브러리 개념)
		// -> 제네릭과 한 세트
		// -> 배열 쉽게 쓰려고 나온거
		
		// 배열의 문제점
		// 1. 고정된 공간
		//    - 배열의 기존 문제점을 해결하고자 컬렉션 프레임워크의 List가 나옴
		//    - 개발자에게 더 다양한 데이터를 쉽게 처리할 수 있도록 다양한 메서드, 인터페이스 제공
		
		// 컬렉션 활용 예시
		// 1. List : 순서가 존재하는 보관함(파이썬 리스트와 거의 똑같음)
		// ArrayList와 LinkedList는 별 차이 없어보이지만 상황에 맞춰 취사선택하는 것이 중요
		
		//   1) ArrayList 
		//   - 번호가 매겨진 책장, 
		//   pros: 특정 위치의 데이터를 바로 꺼내 읽을 수 있음
		//   cons: 중간에 책을 끼워넣거나 빼려면 뒤에있는 모든 책을 한칸씩 옮겨야함
		//   언제 써야하는가? : 데이터의 조회가 많이 일어나고 추가/삭제는 거의 없을 때
		
		// 		예시: 누렁이의 간식 목록
		List<String> snacks = new ArrayList<>();
		snacks.add("육포");
		snacks.add("치즈버거");
		snacks.add("소세지");
		System.out.println("누렁이의 기본 간식 목록: "+snacks);
		
		snacks.add(0,"미역국"); // 숫자 안 적으면 맨 뒤에 추가됨
		System.out.println("누렁이가 추가로 받은 간식 포함: "+snacks);
		
		String firstSnack = snacks.remove(2);
		System.out.println("누렁이가 "+firstSnack+"을 맛있게 먹습니다.");
		System.out.println("남은 간식: "+snacks);
		
		//	 2) LinkedList
		//   - 데이터를 추가하거나 삭제할 때 씀
		//   - 각각의 데이터가 앞뒤 데이터의 위치정보를 직접 들고 있음
		//   - 조회를 안 하는 건 아닌데 특정 위치 데이터 조회하는 부분이 약함
		
		//   ex. 실시간 편집기, 음악 플레이리스트
	}

}
