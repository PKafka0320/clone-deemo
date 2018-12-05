package fxTest;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;

public class animation extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage theStage) 
	{
		theStage.setTitle( "Timeline Example" );
        theStage.setHeight(519); // 480 + 39
        theStage.setWidth(816); // 800 + 16
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );
		
		Canvas canvas = new Canvas( 800, 480 );
		root.getChildren().add( canvas );
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Image note = new Image( "file:./asset/image/note.png" );
		//Image background = new Image("file:./asset/image/background.png");
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				//System.out.println(currentNanoTime);
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				System.out.println( ( (currentNanoTime - startNanoTime) / 1000000000.0) );
				double x = 400 + 128 * Math.cos(t);
				double y = 240 + 128 * Math.sin(t);
				
				// background image clears canvas
				// gc.drawImage( background, 0, 0 );
				gc.drawImage( note, 400-50, 240-50 , 100, 100 );
				gc.drawImage( note, x-50, y-50 , 100, 100 );
			}
		}.start();
		
		theStage.show();
	}
}