package fxTest;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import notes.Note;
import notes.NoteFormat;

class NoteObject {
	int line;
	double timing;
	
	double locationX;
	double locationY;
	
	
	public NoteObject(int line, long timing) {
		this.line = line;
		this.locationX = line*100.0;
		this.locationY = 0.0;
		this.timing = timing/1000.0;
	}
	
	public double getLocationX(double currentTime) {
		if(timing > currentTime + 5.0) {
			return -1.0;
		}
		else {
			return this.locationX;
		}
	}
}

public class DropNote extends Application {

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
		
		Note music1 = new Note("music1");
		int index = 0;
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				//System.out.println(currentNanoTime);
				double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
				System.out.println( ( (currentNanoTime - startNanoTime) / 1000000000.0) );
				
				music1.notes.get(index).gettiming()
				
				gc.drawImage( background, 0, 0 );
				gc.drawImage( note, 0, 0 );
			}
		}.start();
		
		theStage.show();
	}
}