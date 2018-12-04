package fxTest;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class KeyBoardHandle extends Application {

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
 
        KeyboardHandler kbh = new KeyboardHandler(theScene);
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                System.out.println(kbh.getPressedLine());
            }
        }.start();
 
        theStage.show();
    }
}
