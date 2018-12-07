package tester;

import java.util.ArrayList;

import etc.GameInputFormat;
import eventProcesser.KeyListener;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class KeyBoardHandleTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage theStage) 
    {
        theStage.setTitle( "Keyboard Example" );
 
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
 
        Canvas canvas = new Canvas( 512 - 64, 256 );
        root.getChildren().add( canvas );
 
        KeyListener kbh = new KeyListener(theScene);
        double startTime = System.nanoTime()/1000000000.0;;
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	ArrayList<GameInputFormat> currentInputList = kbh.getInputList();
            	for(GameInputFormat currentInput : currentInputList) {
            		System.out.print(",("+currentInput.getLine()+", "+(currentInput.getTiming()-startTime)+")");
            	}
            	System.out.println("");
//            	ArrayList<String> currentInputList = kbh.getInputCheckTable();
//            	for(String currentInput : currentInputList) {
//            		System.out.print(", "+currentInput);            		
//            	}
//            	System.out.println("");
            }
        }.start();
 
        theStage.show();
    }
}
