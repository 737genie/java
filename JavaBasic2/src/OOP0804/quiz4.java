package OOP0804;

abstract class PartySecuritySystem {
	protected String systemName;
	protected boolean isActive;
	
	abstract void authenticate(String credentials);
	
	void activate() {
		System.out.println("보안 시스템 활성화");
	}
	void deactivate() {
		System.out.println("보안 시스템 비활성화");
	}
}

abstract class SecurityGuard extends PartySecuritySystem {
	
	void authenticate() {
		System.out.println("왈왈! 신분증 확인이 필요합누렁!");
	}
	
	void patrol() {
		System.out.println("경비견은 순찰중입누렁!");
	}
	
}

class PartyManager extends PartySecuritySystem {
	String music;
	
	void authenticate() {
		System.out.println("춤춰도 되나요? 파티 시작!");
	}
	
	void startParty(String music) {
		System.out.println("신나는 {"+music+"} 틀어요! 파티타임!");
	}

}

public class quiz4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
