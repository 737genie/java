package Collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Set : 리스트랑 거의 유사 다만 데이터의 중복을 허용하지 않음.
		
		// List와 비교했을때 특징
		// 1. 저장 순서가 없음 (저장순서가 유지되지 않음)
		// 2. 객체의 중복저장 불가.(null값 1개만 저장 가능)
		
		// HashSet : 순서 없는 주머니
		//   -> 순서에 상관없이 매우 빠른 속도로 데이터를 추가, 삭제, 검색할때 쓰는 자료형
		//   -> 이벤트 참여자 중복 체크나, 역할관리쪽에 사용하면 편함.
		// LinkedHashSet : 순서가 있는 set
		//  -> hashSet의 빠른속도 + 순서 기억
		//  -> 최근 본 상품 목록이나 캐시데이터 관리시 사용.
		// TreeSet : 자동정렬 주머니
		//  -> 트리구조에 저장하여 항상 정렬된 상태를 유지하는 자료형
		//  -> 데이터의 중복을 제거하면서 항상 정렬된 상태가 필요할때 사용
		//     (로또번호, 랭킹 시스템)
		
		// set의 문제점
		// -> 인덱스를 이용해서 가져오는 방법이 없음.
		
		// Set 설명 예시 : 탱고의 파티초대.
		//  -> 탱고가 친구들을 파티에 초대하려는데 너무 신이나서 같은친구를 여러번 넣은 시나리오
		//     중복된 친구들은 걸러줘야함.
		Set<String> invi = new HashSet<>();
		System.out.println("탱고 : 파티에 와줘!");
		
		invi.add("와플곰");
		invi.add("누렁이");
		invi.add("토심이");
		invi.add("바쁘개");
		invi.add("급하냥");
		invi.add("누렁이");
		invi.add("누렁이");
		invi.add("누렁이");
		invi.add("누렁이");
		invi.add("급하냥");
		
		System.out.println("탱고의 파티 초대 목록" +invi);
		System.out.println("필요한 초대장 수 : " + invi.size());
		
		// 파티 명단을 가나다 순으로 정렬하기.
		Set<String> sorted = new TreeSet<>(invi);
		
		System.out.println("명단 정렬 : " + sorted);
		
		String[] phone_book = {"119", "97674223", "1195524421"};
	}
	
	 public boolean solution(String[] phone_book) {
		return false;

	 }
	
	

}