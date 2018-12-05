package fxTest;

import java.util.Queue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import notes.NoteReader;
import notes.NoteFormat;

public class DropNote extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage theStage) 
	{
		theStage.setTitle( "Timeline Example" );
		
		Group root = new Group();
		Scene theScene = new Scene( root, 800, 480 );
		theStage.setScene( theScene );
		
		Canvas canvas = new Canvas( 800, 480 );
		root.getChildren().add( canvas );
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		NoteReader music1 = new NoteReader("test1");
		Queue<NoteFormat> notes = music1.notes;
		
		KeyboardHandler kbh = new KeyboardHandler(theScene);
		DrawNote drawGamePage = new DrawNote(gc, kbh, notes);
		AnimationTimer gameLoop = drawGamePage.drawGameScreen;
		
		gameLoop.start();
		
		theStage.show();
	}
}