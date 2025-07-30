package Basic;


public class ArrayExam {

	public static void main(String[] args) {
		// 배열:
		// 똑같은 데이터 타입의 여러 변수를 하나로 묶어 처리하는 구조
		// 특정한 데이터들을 하나의 변수로 표현
		// 사용하기 불편함
		
		// 스프링 프레임워크를 배우게 되면 컬렉션 프레임워크라는 대체 배열을 활용.
		// 대부분 데이터 처리는 컬렉션 프레임워크를 쓰지만
		// 첨부파일 전송같은 바이트코드 계열을 다룰때에는 일반 배열도 자주 등장.
		
		// 퀴즈
		// 10, 20, 30, 40 이라는 값을 저장하고
		// 합계를 구해라
		
		int[] test = {10,20,30,40};
		
		System.out.println(test[0]);
		
		
		
		// JDK = 개발 도구 모음
		// JRE = JVM + 자바에 내장되어있는 클래스 라이브러리
		// JVM = 자바 코드를 기계가 알아 들을 수 있도록 제공되는 프로그램
		
		// var 타입을 일반 배열로 선언하는 방법 확인
		// var[] test123 = {123};
		// var[] gunchim23 = new var[4];
		
		// 1. 배열에 집어넣은 데이터 확인 후 사용해보기
		String[] tosim2 = {"당근 씻기", "꽃에 물 주기", "낮잠 자기"};
		
		System.out.println(tosim2[1]);
		
		String[] nureongSnack = {"뼈다구", "고급사료", "육포", "개껌", "고구마"};
		// 변수명에 파스칼 표기법 절대 안 됨 NureongSnack << 안됨
		
		System.out.println("누렁이가 좋아하는 간식");
		
		// 리터럴로 배열 크기 만큼 조건 적용하는 것 피하기
		// 배열 크기가 달라질 수도 있으니까 그때마다 바꾸는 것은 비효율적
		for(int i=0; i<3; i++) {
			System.out.println(nureongSnack[i]);
		}
		
		System.out.println("==========================");
		// -> length
		// 반복문에 length를 바로 쓰는 것보단 변수 지정
		// JIT 컴파일러 최적화 기법
		
		int len = nureongSnack.length;
		for(int i=0; i<len; i++) {
			System.out.println(nureongSnack[i]);
		}
		
		// -> enhanced-for 활용
		// == for-each
		// 배열이나 컬렉션을 순회할 때 인덱스 관리 없이 처리해주는 문법.
		// --> 배열 처음부터 끝까지 다 돌게 해주는 문법.
		// for(요소타입 요소명 : 순회대상)
		System.out.println("==========================");
		for(String snack : nureongSnack) {
			System.out.println(snack);
		}
		
		
	}

}
