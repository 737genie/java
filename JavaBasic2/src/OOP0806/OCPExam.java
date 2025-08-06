package OOP0806;

public class OCPExam {
	// OCP 개방 폐쇄 원칙
	// - 소프트웨어 개체(클래스, 모듈, 함수)들은 확장에는 열려있어야하고
	//   수정에는 닫혀있어야 함(아예 XX 하지만 현실적으로 그러기는 어려우니 최소화하자)
	
	// 왜 수정하지 말라고 하는 건지?
	// - 망가진 건 수정해야하고 ㅇㅇ
	//   가급적 처음부터 잘 짜라는 소리
	
	// 요약 : 기능 추가는 쉽게 할 수 있도록 구성하고, 기존 코드는 바꾸지 말것
	
	
	// OCP 위반 사례
	// 확장성 x, 메서드 하나에서 모든 것을 수행
	// 기능 추가가 필요할 때마다 직접 코드 작성해야함
class ShapeCalculator {
	public double calculateArea(String shape, double width, double height) {
		if (shape.equals("rectangle")) {
			return width * height;
		} else if (shape.equals("circle")) {
			return Math.PI * width * width;
		}
  // 다른 도형 추가 시 if-else 구문 수정 필요
      return 0;
      }
	}

	// OCP 준수 사례

interface Shape{double calculateArea(String shape, double width, double height);}
	class Rectangle implements Shape{
	   @Override
	   public double calculateArea(String shape, double width, double height) {
	      // TODO Auto-generated method stub
	      return width * height;
	   }
	}
	class Circle implements Shape{
	   @Override
	   public double calculateArea(String shape, double width, double height) {
	      // TODO Auto-generated method stub
	      return Math.PI * width * width;
	   }
	}
	class Sadariggoal implements Shape{
	   @Override
	   public double calculateArea(String shape, double width, double height) {
	      // TODO Auto-generated method stub
	      return 0;
	   }
	}
}
