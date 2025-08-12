package Collections;

class PackageDamagedException extends Exception {
	public PackageDamagedException(String msg) {
		super(msg);
	}
}


class FragilePackage{
	public void deliver() throws PackageDamagedException{
		System.out.println("급하냥이 전속력으로 달려갑니다.");
		throw new PackageDamagedException("와장창 택배 물품 파손.");
	}
}

public class ExceptionExam3 {
	// 급하냥의 택배 배송
	// 택배 나르다가 자빠져서 물품을 깨먹는다면?'
	
	public static void main(String[] args) {
		
    FragilePackage fragileItem = new FragilePackage();

    System.out.println("급하냥: 배달 시작!");

    try {
        System.out.println("   [시도] 조심스럽게 배달을 시도합니다...");
        fragileItem.deliver();
        System.out.println("   [성공] 배달에 성공했습니다!");
    }
    // 이제 더 구체적인 예외를 잡을 수 있다.
    catch (PackageDamagedException e) {
        System.out.println("   [대처] 배송 실패! '파손' 문제가 발생했습니다!");
        System.out.println("   문제 내용: " + e.getMessage());
        System.out.println("   파손 전용 보험 처리팀에 바로 연락합니다...");
    }
    // 다른 종류의 예외도 처리할 수 있다.
    catch (Exception e) {
        System.out.println("   [대처] 예상치 못한 다른 문제가 발생했습니다: " + e.getMessage());
    }
    finally {
        System.out.println("   [마무리] 어떤 상황이든 배달 트럭을 깨끗이 정리합니다.");
    }

    System.out.println("급하냥: 비록 한 건은 실패했지만, 다음 배달은 계속되어야 해!");
	
	
	}
}
