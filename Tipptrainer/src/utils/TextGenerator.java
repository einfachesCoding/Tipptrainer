package utils;

public class TextGenerator {
		
	public static String generateTextFromChars(char[] chars, int textlenght, int MAX_WORDLENGHT, int charType) {
		String s = "";
		int wordlenght = 1; 
		for(int i = 0; i < textlenght; i++) {
			char newChar = randomChar(chars);
			if(charType == 1) {
				newChar = Character.toUpperCase(newChar);
			}
			if(charType == 2) {
				int nr = (int)(Math.random()*2);
				if(nr == 1) {
					newChar = Character.toUpperCase(newChar);
				}
			}
			if((s.endsWith(" ") && newChar == ' ')|| (newChar == ' ' && (s.length()==0) || wordlenght <= 2 && newChar == ' ')) {//no double spaces
				i--;
				continue;
			}
			wordlenght++;
			if(wordlenght == MAX_WORDLENGHT || newChar == ' ') {
				wordlenght = 1;
				s += ' ';
				continue;
			}
			s += newChar;
		}
		return s;
	}
	
	private static char randomChar(char[] chars) {
		int nr = (int)(Math.random()*chars.length);
		return chars[nr];
	}
	
	public static String generateTextFromWords(String[] words){
		String text = "";
		for(int i = 0; i < 1000; i++) {
			int nr = (int)(Math.random()*words.length);
			text = text + words[nr] + " ";
		}
		return text;	
	}
}
