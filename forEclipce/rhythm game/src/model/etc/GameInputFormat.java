package etc;

import java.util.ArrayList;
import java.util.Arrays;

public class GameInputFormat {
	ArrayList<String> availableKeyList = new ArrayList<String>(
		Arrays.asList("", "S", "D", "F", "J", "K", "L")
	);
	
	String inputValue;
	
	int inputLine;
	double inputTime;
	
	public GameInputFormat(String inputString) {
		this.inputValue = inputString;
		this.inputTime = 0;
		
		this.inputLine = availableKeyList.indexOf(this.inputValue);
	}
	
	public GameInputFormat(String inputString, double currentTime) {
		this.inputValue = inputString;
		this.inputTime = currentTime;
		
		this.inputLine = availableKeyList.indexOf(this.inputValue);
	}

	public int getLine() {
		return this.inputLine;
	}
	
	public double getTiming() {
		return this.inputTime;
	}
	
	public String toString() {
		return "("+this.inputLine+", "+this.inputTime+")";
	}
}
