package Quiz1;

import java.util.List;
import java.util.stream.Collectors;

class Email {
	String sender;
	boolean isSpam;
	public Email(String s, boolean i) {sender=s; isSpam=i;}
	public String getSender() {return sender;}
	public boolean isSpam() {return isSpam;}
}
public class quiz1_0819 {

	public static void main(String[] args) {
		List<Email> inbox = List.of(
	            new Email("Mr. 빅", false), // 의뢰
	            new Email("[광고] 대출상담", true), // 스팸
	            new Email("정보상 '스파이더'", false), // 의뢰
	            new Email("아라사카 신입채용", true), // 스팸
	            new Email("탱고", false) // 의뢰
	        );
		
		List<Email> urgentContacts = inbox.stream()
		.filter(i->i.isSpam == false)
		.collect(Collectors.toList());
		
		System.out.println("--- 긴급 연락처 리스트 ---");
		urgentContacts.forEach(i->
		System.out.println(" -"+i.getSender()));
	}

}
