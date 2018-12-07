package eventProcesser;

import java.util.ArrayList;	

import etc.GameInputFormat;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import noteController.CollisionChecker;

public class KeyListener {
	Scene theScene;
	
	GameInputFormat input;
	GameInputFormat inputChecker;
	ArrayList<GameInputFormat> inputList = new ArrayList<GameInputFormat>();
	ArrayList<String> inputCheckTable = new ArrayList<String>();
	CollisionChecker cc = new CollisionChecker();
	
	public KeyListener( Scene theScene ) {
		this.theScene = theScene;
		
		theScene.setOnKeyPressed(
			new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					double currentTime = System.nanoTime()/1000000000.0;
					input = new GameInputFormat(code, currentTime);
					inputChecker = new GameInputFormat(code);
					String line = ""+inputChecker.getLine();
					if ( !inputCheckTable.contains(line) ) {
						inputCheckTable.add( line );
						inputList.add(input);
					}
				}
			});
		
		theScene.setOnKeyReleased(
		    new EventHandler<KeyEvent>() {
		    	public void handle(KeyEvent e) {
		    		String code = e.getCode().toString();
		    		inputChecker = new GameInputFormat(code);
					String line = ""+inputChecker.getLine();
		    		inputCheckTable.remove( line );
		    	}
		    });
	}
	
	public ArrayList<GameInputFormat> getInputList() {
		return this.inputList;
	}
	
	public ArrayList<String> getInputCheckTable() {
		return this.inputCheckTable;
	}
	
	public boolean remove(GameInputFormat currentInput) {
		return this.inputList.remove(currentInput);
	}
}
