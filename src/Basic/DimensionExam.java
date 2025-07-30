package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DimensionExam {

	public static void main(String[] args) {
		// 2차원 배열
		// -> 배열의 배열
		// -> 2차원 배열은 행과 열로 구성된 표 형태의 데이터 구조
		
		// 선언 방법
		// int[][] test = new int[3][4]
		
		int[][] test = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12}
		};
		
		// 2차원 배열에 직접적인 인덱싱 방법
		// 행과 열을 순서대로 작성
		
//		System.out.println(test[0][0]);
		
		//2차원 배열을 순회하는 방법
		for(int i=0; i<test.length; i++) {
			for(int j=0; j<test[i].length; j++) {
				System.out.printf("%3d", test[i][j]);
			}
			System.out.println();
		}
		
		//향상된 for문 이용
		for(int[] row : test) {
			for(int col : row) {
				System.out.printf("%3d", col);
			}
			System.out.println("");
		}
		
		// 2차원 배열과 실생활
		
		String[][] apart = {
				{"누렁이", "탱고", "와플곰"},
				{"토심이", "바쁘개", "급하냥"}
		};
		
		String[] newFloor = {"1층창고", "2층창고", "3층창고"};
		
		for(int floor=0; floor<apart.length; floor++) {
			for(int room=0; room<apart[floor].length; room++) {
				System.out.println((floor+1)+"층 "+(room+1)+"호 "+apart[floor][room]+"님, 아파트 반상회를 준비하셔야 합니다.");
			}
		}
		
		// 위의 코드 줄이기 -> (2차원 배열 한 번에 보는 법)
		System.out.println(Arrays.deepToString(apart));
		
		// 2차원 배열 확장하기 
		String [][] newApt = new String[apart.length+1][];
		
		for(int i=0; i<apart.length; i++) {
			newApt[i] = apart[i];
		}
		
		System.out.println(Arrays.toString(newApt[1]));
		
		// 새로운 층 추가
		newApt[newApt.length-1] = newFloor;
		System.out.println(Arrays.toString(newApt));
		
//		newApt[] = newFloor;
		
		// 배열에 값 추가
		
		int[] origin = {1,2,3,4,5};
		int newVal = 6;
		
//		int[] exp = new int[origin.length+1];
//		
		// 얕은 복사, 깊은 복사
//		// arraycopy(원본배열, 복사시작할 인덱스, 복사한 내용 받을배열, 
//		//           받을 배열에서 데이터 저장 시작 인덱스, 복사할 요소의 개수)
//		System.arraycopy(origin, 0, exp, 0, origin.length);
//		exp[origin.length] = newVal;
//		
		// Arrays.copyOF()
		
		int[] exp2 = Arrays.copyOf(origin, newVal);
		exp2[exp2.length-1] = 55;
		
		// 반복문 없이 배열의 모든 요소를 한번에 보는 법
		System.out.println("원본 : "+Arrays.toString(origin));
		System.out.println("원본 : "+Arrays.toString(exp2));
		
		
		
		int[] scores = {85,92,78,95,67,88,73,91};
		int len = scores.length;
		
		// 성적을 전부 80점 이상으로 바꾸기
		
//		for(int i=0; i<len; i++) {
//			if(scores[i]<80) {
//				scores[i] =80;
//			}
//		}
//		
//		System.out.println(Arrays.toString(scores));
		
		int j=0;
		int k=0;
		int[] odd = new int[scores.length+1];
		int[] even = new int[scores.length+1];
		for(int i=0; i<len; i++) {
			
			if(scores[i]%2==0) {
				even[j]=scores[i];
				j++;
			}
			else {
				odd[k]=scores[i];
				k++;
			}
		}
		System.out.println("짝수: "+Arrays.toString(even)+"\r홀수: "+Arrays.toString(odd));
	}
		
		

	

}

