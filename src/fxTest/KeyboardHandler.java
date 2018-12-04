package fxTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler {
	
	Map<String, Integer> convertInput = new HashMap<String, Integer>();
	
	ArrayList<String> input = new ArrayList<String>();
	Scene theScene;
	ArrayList<String> inputList = new ArrayList<String>();
	
	public KeyboardHandler(Scene theScene) {
		this.theScene = theScene;
		
		inputList.add("S");
		inputList.add("D");
		inputList.add("F");
		inputList.add("J");
		inputList.add("K");
		inputList.add("L");
		
		for(int i=0; i<6; i++) {
			convertInput.put(inputList.get(i), i+1);
		}
	 
	    theScene.setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
	            public void handle(KeyEvent e)
	            {
	                String code = e.getCode().toString();
	
	                // only add once... prevent duplicates
	                if ( !input.contains(code) )
	                    input.add( code );
	            }
	        });
	
	    theScene.setOnKeyReleased(
	        new EventHandler<KeyEvent>()
	        {
	            public void handle(KeyEvent e)
	            {
	                String code = e.getCode().toString();
	                input.remove( code );
	            }
	        });
	}
	
	public void removeInput( int line ) {
		input.remove(inputList.get(line));
	}
	
	public ArrayList<String> getInput(){
		return input;
	}
	
	public ArrayList<Integer> getPressedLine() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		
		for(int i=0; i<input.size(); i++) {
			String currentKey = input.get(i);
			if( inputList.contains( currentKey ) ) {
				ans.add( convertInput.get(currentKey) );
			}
		}
		
		return ans;
	}
}
