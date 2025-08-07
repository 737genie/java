package OOP0807;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListExam1 {
	
//	잔망루피의 요구: "배열에 숫자가 [1, 1, 3, 3, 0, 1, 1] 이렇게 들어있는데, "
//	똑같은 숫자가 연속으로 나오는 건 너무 지루해! [1, 3, 0, 1] 
//	이렇게 깔끔하게 하나씩만 남겨줘! 순서는 그대로 유지해야 하고!"
	
	public int[] solution(int[] arr) {
		return;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayListExam1 arr1 = new ArrayListExam1();
		int[] arr = {1,1,3,3,0,1,1};
		System.out.println("결과: "+Arrays.toString(arr1.solution(arr)));
		
		
//		int len = arr.length;
//		List<Integer> arr2 = new ArrayList<>();
//		arr2.add(arr[0]);
//		for(int i=0; i<len; i++) {
//			for(int j=1; j<len; j++) {
//				if(arr[i]==arr2[j]) {}
//				else {
//					int a = arr[i];
//					arr2.add(a);
//				}
//				
//			}
//		}
		
//		public int[] solution(int[] arr) {
//		    List<Integer> answerList = new ArrayList<>();
//		    
//		    // 첫 번째 원소는 비교 대상이 없으므로 무조건 추가
//		    answerList.add(arr[0]);
//		    
//		    // 두 번째 원소부터 순회
//		    for (int i = 1; i < arr.length; i++) {
//		        // 리스트의 마지막 원소와 현재 원소가 다를 때만 추가
//		        if (answerList.get(answerList.size() - 1) != arr[i]) {
//		            answerList.add(arr[i]);
//		        }
//		    }
//		    
//		    // List를 int[] 배열로 전통적인 방식으로 변환
//		    int[] result = new int[answerList.size()];
//		    for (int i = 0; i < answerList.size(); i++) {
//		        result[i] = answerList.get(i);
//		    }
//		    
//		    return result;
		    
		    // 풀이2 : only 성능만 고려.
//		    int[] temp = new int[arr.length];
//		    int index = 0;
//		    
//		    // 첫 번째 원소는 무조건 추가
//		    temp[index++] = arr[0];
//		    
//		    // 두 번째 원소부터 순회
//		    for (int i = 1; i < arr.length; i++) {
//		        if (temp[index - 1] != arr[i]) {
//		            temp[index++] = arr[i];
//		        }
//		    }
//		    
//		    // Arrays.copyOf()로 실제 크기만큼 복사
//		    return Arrays.copyOf(temp, index);	 
			
			// 풀이 3
			// 딴거 모르고 가장 쉽게
		    List<Integer> answerList = new ArrayList<>();
		    
		    answerList.add(arr[0]);
		    
		    for (int i = 1; i < arr.length; i++) {
		        if (answerList.get(answerList.size() - 1) != arr[i]) {
		            answerList.add(arr[i]);
		        }
		    }
		    
		    // 가장 직관적인 변환
		    int[] result = new int[answerList.size()];
		    for (int i = 0; i < result.length; i++) {
		        result[i] = answerList.get(i);
		    }
		    
		    return result;
			
			
		}
		
		

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			ArrayListExam1 arr1 = new ArrayListExam1();
			int[] arr = {1, 1, 3, 3, 0, 1, 1};
			System.out.println("결과 : " + Arrays.toString(arr1.solution(arr)));
			
			
		}	
		
		
	}

}
