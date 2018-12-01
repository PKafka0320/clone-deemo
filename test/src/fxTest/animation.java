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
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene( theScene );
		
		Canvas canvas = new Canvas( 512, 512 );
		root.getChildren().add( canvas );
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Image note = new Image( "file:./asset/image/note.png" );
		Image background = new Image("file:./asset/image/background.png");
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				//System.out.println(currentNanoTime);
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				System.out.println( ( (currentNanoTime - startNanoTime) / 1000000000.0) );
				double x = 232 + 128 * Math.cos(t);
				double y = 232 + 128 * Math.sin(t);
				
				// background image clears canvas
				gc.drawImage( background, 0, 0 );
				gc.drawImage( note, x, y );
			}
		}.start();
		
		theStage.show();
	}
}