package OOP0804;

class WorkProcessor {
	String taskName;
	int urgency;
	boolean isTurboMode;
	
	public WorkProcessor(String taskName, int urgency) {
		this.taskName=taskName;
		this.urgency=urgency;
		this.isTurboMode=false;
	}
	
	
	public void processWork() {
		System.out.println("바빠바빠! 기본 작업 처리한다개!");
	}
	public void processWork(String taskName) {
		System.out.println(taskName+"을 처리한다개!");
	}
	public void processWork(String taskName, int urgency) {
		switch(urgency) {
		case 1:
			System.out.println("긴급도가 "+urgency+"인 "+taskName+"을 처리한다개!");
		case 2:
			System.out.println("긴급도가 "+urgency+"인 "+taskName+"을 처리한다개!");
		case 3:
			System.out.println("긴급도가 "+urgency+"인 "+taskName+"을 처리한다개!");
		case 4: 
			System.out.println("긴급도가 "+urgency+"이니 "+taskName+"을 불꽃같이 처리한다개!");
		case 5: 
			System.out.println("긴급도가 "+urgency+"이니 "+taskName+"을 불꽃같이 처리한다개!");
		default :
			System.out.println("내가 처리할 수 없는 일이라개...");
		}
	}
	public void processWork(String taskName, int urgency, boolean isTurboMode) {
		System.out.println("긴급도가 "+urgency+"인 "+taskName+"을 불꽃같이 처리한다개!");
	}
}

public class quiz1 {
	public static void main(String[] args) {
	WorkProcessor task1 = new WorkProcessor("task1", 5);
	task1.processWork("task1");
	task1.processWork("task1", 5);
	task1.processWork("task1", 2, true);
	task1.processWork("task1");
}
}
