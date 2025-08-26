package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class ThreadExam4 {

	public static void main(String[] args) throws InterruptedException {
		int dataSize = 100_000_0000;
		int numberOfThreads = 4;
        // --- 순차 처리 ---
//        long startTime = System.currentTimeMillis();
//        long sum = 0;
//        for (int i = 0; i < dataSize; i++) {
//            sum += process(i);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("순차 처리 결과: " + sum + ", 소요 시간: " + (endTime - startTime) + "ms");
	
		
	// 병렬 처리
		
		
	long startTime = System.currentTimeMillis();
	// 스레드 생성을 요약한 코드
	// ExecutorService : 비동기로 작업을 실행할 수 있는 스레드를 관리하는 클래스
	// Executors : 스레드를 생성하기 위한 유틸리티 클래스
	// newFixedThreadPool : 고정된 개수의 스레드를 가진 스레드 풀 생성
	// -> 장점 : 스레드를 반복적으로 생성하고 제거하는 리소스 자체를 줄일 수 있음
	// -> 주의점 : 프로그램이 종료될 때 shutdown 호출해서 스레드 종료(무조건 해야됨)
	ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
	// synchronized보다 더 고성능의 동기화를 구현하는 합계용 클래스
	// LongAdder : 멀티 스레드 환경이 구성되면 그 환경에서 숫자값을 더하기 위한 목적으로 설계
	// -> 특징 : 여러개의 셀을 이용하여 자원 사용 경쟁을 분산시킬 수 있음
	LongAdder parallelSum = new LongAdder();
    int chunkSize = dataSize / numberOfThreads;
    for (int i = 0; i < numberOfThreads; i++) {
        final int start = i * chunkSize; // 스레드가 처리할 데이터의 시작점
        final int end = (i == numberOfThreads - 1) ? dataSize : start + chunkSize; // 끝점
        // 병렬작업 처리 실행
        executor.submit(() -> {
        	// 각 스레드가 자신에게 할당된 데이터 범위를 반복하면서
        	// 프로세스를 호출하여 데이터 처리
            long partialSum = 0;
            // 각 스레드가 처리한 결과를 합침
            for (int j = start; j < end; j++) {
                partialSum += process(j); 
            }
            parallelSum.add(partialSum);
        });
    }

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    long endTime = System.currentTimeMillis();
    System.out.println("병렬 처리 결과: " + parallelSum.sum() + ", 소요 시간: " + (endTime - startTime) + "ms");
    System.out.println("와플곰: '역시 일은 나눠서 해야 제맛이지!'");
	
	}

    // 복잡한 계산을 흉내 내는 메서드
    private static int process(int data) {
        return data % 10;
    }
	
}
