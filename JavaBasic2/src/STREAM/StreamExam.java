package STREAM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class Review {
	String author;
	int rating;
	public Review(String author, int rating) {
		this.author=author;
		this.rating=rating;
	}
	public String getAuthor() {
		return author;
	}
	public int getRating() {
		return rating;
	}
}

public class StreamExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Stream : 데이터의 흐름
		
		
		// 예시 
		// 와플곰의 가게 리뷰 정리하기
		// 4점 이상만 뽑고 가나다 순 정렬
		
		List<Review> reviews = Arrays.asList(
				new Review("누렁이", 5), new Review("망곰", 2),
				new Review("탱고",4), new Review("급하냥",4));
//	
//        // 1. 별점 4점 이상인 리뷰를 담을 임시 리스트 생성
//        List<Review> filteredReviews = new ArrayList<>();
//        for (Review review : reviews) {
//            if (review.score >= 4) {
//                filteredReviews.add(review);
//            }
//        }
//
//        // 2. 작성자 아이디만 뽑아서 담을 또 다른 임시 리스트 생성
//        List<String> authorIds = new ArrayList<>();
//        for (Review review : filteredReviews) {
//            authorIds.add(review.name);
//        }
//
//        // 3. 아이디를 정렬
//        Collections.sort(authorIds);
//
//        System.out.println("우수 리뷰어 명단: " + authorIds);
//        System.out.println("바쁘개: '휴... 임시 변수만 몇 개야! 로직이 한눈에 안 들어오잖아!'");
        
        // 스트림은 이 코드를 더 줄일 수 있음(효율적으로 작성 가능)
        // 주로 사용되는 곳: 미디어 관리(업로드, 다운로드)
        
        // 컬렉션에 담긴 데이터들을 하나씩 올려놓고 여러 단계의 작업을 거쳐 최종 
		
		
		
		// 스트림 예시
        List<String> excellentAuthors = reviews.stream() // 1. 컨베이어 벨트에 올리고
                .filter(r -> r.getRating() >= 4)             // 2. 별점 4점 이상만 남기고 (불량품 검수)
                .map(Review::getAuthor)                      // 3. 리뷰 객체를 작성자 이름(String)으로 바꾸고 (가공)
                .sorted()                                    // 4. 가나다순으로 정렬하고
                .collect(Collectors.toList());               // 5. 최종 결과를 새로운 List로 포장!

        System.out.println("우수 리뷰어 명단: " + excellentAuthors);
        System.out.println("잔망루피: '흥, 이게 바로 지적인 코딩이지. '뭘' 할 건지가 명확하잖아?'");
        
        // 스트림 API를 통해 기존의 코드들을 획기적으로 줄일 수 있음
        // -> 기존의 for, if문은 가내수공업
        // -> 스트림 API는 자동화 공장 생산라인
        // 다만 주의할 점이 있음
        
        
        // 주의
        // 1. 스트림 API의 장점은 가독성 측면이지 기존 코드보다 성능상 우월하다는 아님
        //	  - 스트림 API는 병렬처리에 강함
        // 2. 1.8버전부터 사용 가능
        // 3. 가독성 측면에선 유리하지만 남용하면 오히려 해침
        
        // 결론: 상황에 맞춰서 쓰자!
        
//        for (Student s : students) {        //← 어떻게 반복할지 명시
//            if (s.getScore() >= 80) {      //← 어떻게 조건 검사할지 명시
//                result.add(s.getName());    //← 어떻게 결과에 추가할지 명시
//            }
//        }
        
//        students.stream()
//        	.filter(s -> s.getScore() >= 80) // 필터링(조건문) 영역
//        	.map(Student::getName) // 무엇을 바꿀지(변환할지)
//        	.collect(toList()); // 어떻게(무엇으로) 수집할지 / 데이터를 어떻게 저장할지
        
        
        // 메서드 참조
        // -> 람다식을 한 번 더 줄인 경우
        // -> 람다식에서 굳이 선언이 불필요한 부분을 생략하는 개념
        
        
        // 메서드 참조 종류
        // 1. 정적 메서드 참조
//        Map<String, Integer> str;
        
        // str = (s) -> Integer.parseInt(s);
        // -> str = Integer::parseInt

        // 2. 인스턴스 메서드 참조
//        ArrayList<Number> list = new ArrayList<>();
        // Test<Collection<Number>> addEl;
        
//        addEl = (arr) -> list.addAll(arr);
//        addEl = list::addAll;
        
        // 3. 파라미터 메서드 참조
        // 4. 생성자 참조
        
        // 스트림 API 활용 예시
        // 1. 스트림 생성하기
        
//        List<String> list = Arrays.asList("a","b","c"); // 대분류 데이터
//        Stream<String> stream1 = list.stream() // 대분류에서 뽑아오고 싶은 것들 조건문 써서 저장하기
//        							.filter(list->list.equals("a"))
//        							.collect(Collectors.toList());
        
        // 스트림 연산 분류
        // 중간연산 : 스트림 위에서 데이터를 원하는 형태로 조정하는 과정
        // -> 여러개 연결할 수 있음 (체이닝)
        // -> 최종연산이 호출되기 전까지는 실행되지 않는 것이 특징
        // -1. 필터링 filter
        //     filter(람다변수 -> 리스트.contains(""))
        List<String> names = List.of("잔망루피", "와플곰", "와플곰", "누렁이");
        List<String> result = new ArrayList<>();
        names.stream()
        .filter(a -> a.contains("곰"))
        .forEach(System.out::println);
        
        
        // -2. 중복제거 distinct
        System.out.println("==========================");
        names.stream()
        	.distinct()
        	.forEach(i -> result.add(i));
        System.out.println(result);
        
        // -3. 변환 Mapping
        //  ex. 참가자들의 이름 글자 수만큼 각각 출력해주세요
        System.out.println("==========================");
        names.stream()
        .map(name -> name.length())
        .forEach(System.out::println);
        
        	// map(method(T, R)) : 흩어진 데이터 모음을 하나로 합칠 때 유용
        	System.out.println("==========================");
	        List<List<String>> teams = List.of(
	        	    List.of("누렁이", "탱고"),
	        	    List.of("와플곰", "망곰")
        	);
	        teams.stream()
	        .flatMap(name -> name.stream()) // 각각의 리스트를 스트림으로 풀어버리고 하나로 합침
	        .sorted()
	        .forEach(System.out::println);
	        
	        
	        List<List<String>> teams1 = List.of(
	        	    List.of("누렁이", "탱고", "잔망루피", "보노보노", "바쁘개")
        	);
	        System.out.println("==========================");
	        teams1.stream()
	        .sorted() // 각각의 리스트를 스트림으로 풀어버리고 하나로 합침
	        .forEach(System.out::println);
        	// flatMap : 여러개의 리스트나 객체들을 하나로 합칠 때 사용
        
        
        // 최종연산 : 스트림의 끝에서 최종 결과물을 만들어내는 과정
        // -> 최종연산들이 실행되어야지 중간연산들이 실행되는 구조
        							
        							
        							
            
        // =====================================================================================
         // 입력스트림, 출력스트림
            
            // 1. 문자 기반 - 문자주고받기
            // 	  -> 최상위 클래스 갖고 있음
            // 		- 입력 스트림: Reader
            //		- 출력 스트림: Writer
            // 2. 바이트 기반 - 모든 종류의 데이터 주고받기 (미디어, 멀티미디어 ...)
            // 	  	- 입력 스트림: InputStream
            //	  	- 출력 스트림: OutputStream
            
         // 스트림 I/O : 데이터가 흐르는 파이프 
         // 메모리 한정적
         // -> 프로젝트나 일이 진행 될수록 메모리 요구사항이 커질 수 있음. 반도체 공정은 한계.
         // 따라서 개발자가 자체적으로 메모리를 줄여가는 쪽으로 작업을 해야 함.
         // !전략적으로 접근
           
         
            
        // 메모리 영역
        // 1. heap 영역 (참조형 데이터 저장) -> 모든 객체와 배열이 사는 곳
        // - JVM이 관리하는 프로그램에서 데이터를 저장하기 위해 실행 시 동적으로 할당하여 사용하는 영역
        //   - 참조타입 변수(인스턴스), 배열
        // - 특징 : 호출이 끝나더라도 메모리가 삭제되지 않고 유지
            
        // 2. 정적 영역(Method) -> 클래스가 사는 곳
        // - 멤버변수(필드), 클래스변수(
        // - 어디서나 접근 가능(접근 제어자)
        // - 프로그램 시작부터 종료까지 무조건 남아있음.
        // - 남용하지말것 -- static 데이터를 무분별하게 많이 사용하면 메모리 부족함

        // 3. stack 영역 -> 임대주택, 메서드 호출의 기록 보관소
        // - 메서드 내 변수 값이 저장되는 공간(임시로 머무는 공간)
        // - 메서드가 호출될 때 스택 영역에 스택 프레임이라는 것이 생기고 그 안의 메서드를 호출
        // - 메서드가 호출될 때 메모리에 할당되고 메서드 종료되면 메모리에서 사라짐
            
            
            
            /* JVM Runtime Data Area 구조 */

//            ┌───────────────────────────────────────────────────────────────┐
//            │                        JVM Memory Space                       │
//            ├───────────────────────────────────────────────────────────────┤
//            │  스레드별 독립 영역         		│         스레드 공유 영역      		  │
//            ├─────────────────────────────┼─────────────────────────────────┤
//            │  ┌─────────────────────┐    │  ┌─────────────────────────────┐│
//            │  │   JVM Stack         │    │  │     Method Area             ││
//            │  │  - Stack Frame      │    │  │   - Class 정보               ││
//            │  │  - Local Variables  │    │  │   - Runtime Constant Pool   ││
//            │  │  - Operand Stack    │    │  │   - Static 변수              ││
//            │  └─────────────────────┘    │  └─────────────────────────────┘│
//            │                             │                                 │
//            │  ┌─────────────────────┐    │  ┌─────────────────────────────┐│
//            │  │   PC Register       │    │  │         Heap                ││
//            │  │  - 현재 실행 명령어  │    │  │  ┌─────────────────────────┐││
//            │  │    주소 저장        │    │  │  │    Young Generation     │││
//            │  └─────────────────────┘    │  │  │  - Eden Space           │││
//            │                             │  │  │  - Survivor S0, S1      │││
//            │  ┌─────────────────────┐    │  │  └─────────────────────────┘││
//            │  │ Native Method Stack │    │  │                              ││
//            │  │  - JNI 호출용       │    │  │  ┌─────────────────────────┐││
//            │  └─────────────────────┘    │  │  │   Old Generation        │││
//            │                             │  │  │  - Tenured Space        │││
//            │                             │  │  └─────────────────────────┘││
//            │                             │  └─────────────────────────────┘│
//            └─────────────────────────────┴─────────────────────────────────┘
        
                    
	}
	


}
