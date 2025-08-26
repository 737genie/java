package Quiz1;

public class quiz2_0825 {

	public static void main(String[] args) {
		
		StringBuilder originWord = new StringBuilder();
		String corruptedText = "Nurung@!is$the#best!dog";
		
		
		for(int i=0; i<corruptedText.length(); i++) {
			char c = corruptedText.charAt(i);
			if(!Character.isAlphabetic(c) | Character.isWhitespace(c)) {
				
			} else {
				originWord.append(c);
			}
		}
		
		String correct = originWord.toString();
		
		System.out.println(correct);
	}

}
