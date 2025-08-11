package teamP;


class Delegate{
	String name;
	String disposition;
	public Delegate(String name, String disposition) {
		this.name=name;
		this.disposition=disposition;
	}
}

class Agenda {
	String name;
	String type;
	public Agenda(String name, String type) {
		this.name=name;
		this.type=type;
	}
}

interface Policy {void decide();}

class AggressivePolicy extends Agenda implements Policy {
	public AggressivePolicy(String name, String disposition) {
		super(name, disposition);
	}
	public void decide() {
	}
}

class DefensivePolicy extends Delegate implements Policy {
	public DefensivePolicy(String name, String disposition) {
		super(name, disposition);
	}
	public void decide() {
	}
}

class NeutralPolicy extends Delegate implements Policy {
	public NeutralPolicy(String name, String disposition) {
		super(name, disposition);
	}
	public void decide() {
	}
}


public class project1_0811 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
