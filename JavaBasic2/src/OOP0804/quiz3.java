package OOP0804;

class DataValidator {
	String data;
	int number;
	String email;
	boolean checkFormat;
	int[] numbers;
	
	public DataValidator() {
		this.data = "none";
		this.number = 000;
		this.checkFormat = false;
	}
	
	void validate(String data) {
		System.out.println("음... 이 "+data+" 문자열은 괜찮구나~");
	}
	void validate(int number) {
		if (number>=0) {
			checkFormat = true;
			System.out.println("음... 이 숫자는 0 이상이구나~   결과: "+checkFormat);
		}
		else {
			System.out.println("잘못된 숫자로구나... 결과: "+checkFormat);
		}
	}
	void validate(String email, boolean checkFormat) {
		if(checkFormat==true) {
			if(email.contains("@")==false) {
				checkFormat = false;
				System.out.println("이메일 형식에 맞지 않는구나... 결과: "+checkFormat);
			}
			else {
				System.out.println("음... 이메일이 {"+email+"}이구나... 결과: "+checkFormat);
			}			
		}
		else {
			System.out.println("검증 허가가 나지 않았구나...  결과: "+checkFormat);
		}
	}
	
	void validate(int[] numbers) {
		System.out.println("배열을 천천히 살펴보구나~  "+numbers);
	}
}

public class quiz3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataValidator data = new DataValidator();
		data.validate(-33);
		data.validate(100);
	
		data.validate("kk@gmail.com", true);
		data.validate("kk@gmail.com", false);
		data.validate("kkgmail.com", true);
		
		int[] numbers = {1,2,3};
		data.validate(numbers);
	}

}
