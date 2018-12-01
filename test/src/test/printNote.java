package test;

import java.util.Arrays;
import notes.Note;

public class printNote {
	public static void main(String[] args) {
		
		Note music1 = new Note("music1");
		
		int index = 0;
		int run = 1;
		long currentTime;
		long startTime = System.currentTimeMillis();
		
		while(run == 1) {
			currentTime = System.currentTimeMillis();
			if( currentTime - startTime > music1.notes.get(index).gettiming() ) {
				System.out.print( Arrays.toString( music1.notes.get(index).getline() ) );
				System.out.println(" / "+music1.notes.get(index).gettiming() );
				index += 1;
			}
			if(index ==	 music1.notes.size() ) {
				break;
			}
		}
		
		System.out.println("done");
	}
}