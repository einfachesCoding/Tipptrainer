package main;

public class WPM_Calculator {
	
	public static double getKeysPerMinute(double sekonds, double textlenght) {
		return textlenght / (sekonds/60);
	}
	
	public static double getWordsPerMinute(double keysPerMinute) {
		return keysPerMinute / 5;
	}
	
	public static double getAccuracy(double misstakes, double textlenght) {
		if(misstakes == 0) {
			return 100;
		}
		return (1-(misstakes/textlenght))*100;
	}
}
