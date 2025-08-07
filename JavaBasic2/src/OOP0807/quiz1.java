package OOP0807;
// 바쁘개의 모듈형 로봇 제작

import java.util.Scanner;

interface Module{void activate();}

class PowerModule implements Module {
	int maxspeed;
	public PowerModule(int maxspeed) {this.maxspeed=maxspeed;}
	public void activate() {System.out.println("  - 원자력 엔진 가동! 최대 속도: "+this.maxspeed);}
}

class ToolModule implements Module {
	public void activate() {System.out.println("  - 도구 모듈 활성화");}
	public void work() {};
	}

class SensorModule implements Module {
	public void activate() {System.out.println("  - 라이다 센서 활성화.");}
	public void analyze() {System.out.println("  >>> 센서 분석: 3D 지형을 스캔합니다");}
}

class DrillTool extends ToolModule {
	public void activate() {System.out.println("  - 드릴 시스템 활성화.");}
	public void work() {System.out.println("  >>> 드릴 작업: 땅을 팝니다.");}
}

class WelderTool extends ToolModule {
	public void activate() {System.out.println("  - 용접 시스템 활성화.");}
	public void work() {System.out.println("  >>> 용접 작업: 금속을 용접합니다.");}
}

class Robot {
	private String name;
	public Robot(String name) {this.name=name;}
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("로봇의 목적을 입력해 주세요.\r1.탐사 2.건설");
		String work = sc.next();
		
		SensorModule s1 = new SensorModule();
		
		Module[] modules = {
				new PowerModule(50),
				new ToolModule(),
				new SensorModule(),
				new DrillTool()
		};
		
		Module[] modules1 = {
				new PowerModule(20),
				new ToolModule(),
				new SensorModule(),
				new DrillTool(),
				new WelderTool()
		};
		
		ToolModule[] tool = {
				new DrillTool(),
				new WelderTool()
		};
		
		DrillTool drill = new DrillTool();
		WelderTool welder = new WelderTool();
		
		if(work.equals("탐사")) {
			System.out.println("===== 로봇 [탐사로봇 "+this.name+"] 임무 시작 =====");
			System.out.println("1. 모든 모듈 활성화: ");
			for (Module m : modules) {
				m.activate();
			}
			System.out.println("2. 모든 센서 작동: ");
			s1.analyze();
			
			System.out.println("3. 모든 도구 작동: ");
			tool[0].work();
		}
		else if(work.equals("건설")) {
			System.out.println("===== 로봇 [건설로봇 "+this.name+"] 임무 시작 =====");
			System.out.println("1. 모든 모듈 활성화: ");
			for (Module m : modules) {
				m.activate();
			}
			System.out.println("2. 모든 센서 작동: ");
			
			System.out.println("3. 모든 도구 작동: ");
			tool[0].work();
			tool[1].work();
		}
		else {System.out.println("입력이 잘못 되었습니다.");}
		
		System.out.println("===== 임무 완료! =====");
	}
}

public class quiz1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot rob1 = new Robot("누렁이");
		Robot rob2 = new Robot("탱고");
		
		rob1.run();
		
		rob2.run();
	}

}
