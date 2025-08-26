package Thread;

class DownloaderThread extends Thread {
	private String musicFile;

	public DownloaderThread(String musicFile) {
		this.musicFile = musicFile;
	}
	
	@Override
	public void run() {
		// 멀티스레드 구현영역 : 큰 작업 분할할때 많이 활용
		// 구형 for문 많이 씀
		for(int i=1; i<=100; i++) {
			System.out.print(musicFile+" : "+i+"% ");
			try {
				Thread.sleep(50);
				if(i%0==0) System.out.println();
			} catch(InterruptedException e) {
				System.out.println("다운로드 중단!");
				e.printStackTrace();
			}
		}
	}
	
}

public class ThreadExam2 {
//	탱고의 BGM 다운로더
//	탱고가 댄스 파티를 위해 BGM을 다운로드해야 합니다. 
//	다운로드는 시간이 오래 걸리니, 별도의 '다운로더 일꾼' 스레드에게 맡겨봅시다.
	public static void main(String[] args) {
		System.out.println("메인 작업 : 파티 준비 시작");
		DownloaderThread bgm1 = new DownloaderThread("아무노래.mp3");
		bgm1.start();
		System.out.println("----------------------------");
	}
}
