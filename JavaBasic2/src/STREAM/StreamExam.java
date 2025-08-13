package STREAM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		
            
         // 입력스트림, 출력스트림
            
            // 1. 문자 기반 - 문자주고받기
            // 	  -> 최상위 클래스 갖고 있음
            // 		- 입력 스트림: Reader
            //		- 출력 스트림: Writer
            // 2. 바이트 기반 - 모든 종류의 데이터 주고받기 (미디어, 멀티미디어 ...)
            // 	  	- 입력 스트림: InputStream
            //	  	- 출력 스트림: OutputStream
            
         
	}
	
	

}
