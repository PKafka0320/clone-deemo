package fxTest;

import java.util.Queue;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
		
		Image note = new Image( "file:./asset/image/note.png" );
		Image background = new Image("file:./asset/image/background3.png");
		Image cover1 = new Image("file:./asset/image/cover2.png");
		
		NoteReader music1 = new NoteReader("test1");
		Queue<NoteFormat> notes = music1.notes;
		
		KeyboardHandler kbh = new KeyboardHandler(theScene);
		
		AnimationTimer gameLoop = new AnimationTimer()
		{
			private long startNanoTime;
			private ArrayList<NoteFormat> notesOnScreen;
			
			public void start() {
				
				this.startNanoTime = System.nanoTime();
				this.notesOnScreen = new ArrayList<NoteFormat>();
				
				super.start();
			}
			
			public void handle(long currentNanoTime)
			{
				ArrayList<Integer> pressedLine = kbh.getPressedLine();
				double t = (currentNanoTime - startNanoTime) / 1000000000.0 - 6; 
				
				//노트가 스크린 위에 있는지 검사하여 있다면 그려줄 목록에 추가함
				if( ( notes.peek() != null ) && notes.peek().isOnScreen(t) ) {
					notesOnScreen.add( notes.poll() );
				}
				
				//노트들을 검사하여 스크린 밖에 있다면 그려줄 목록에서 제거함
				if( !notesOnScreen.isEmpty()) {
					for(int i=0; i<notesOnScreen.size(); i++) {
						NoteFormat currentNote = notesOnScreen.get(i);
						double noteTiming = currentNote.getTiming();
						int noteLine = currentNote.getLine();
						
						for(int j=0; j<pressedLine.size(); j++) {
							if(pressedLine.get(j) == noteLine) {
								if( t+0.06 < noteTiming && noteTiming <= t+0.1) {
									System.out.println("bad "+ noteLine);
									notesOnScreen.remove(i);
									kbh.removeInput(pressedLine.get(j));
								}
								if( t+0.02 < noteTiming && noteTiming <= t+0.06) {
									System.out.println("great "+ noteLine);
									notesOnScreen.remove(i);
									kbh.removeInput(pressedLine.get(j));
								}
								else if( t-0.02 < noteTiming && noteTiming <= t+0.02) {
									System.out.println("perfect "+ noteLine);
									notesOnScreen.remove(i);
									kbh.removeInput(pressedLine.get(j));
								}
								else if( t-0.04 < noteTiming && noteTiming <= t-0.02) {
									System.out.println("great "+ noteLine);
									notesOnScreen.remove(i);
									kbh.removeInput(pressedLine.get(j));
								}
								else if(noteTiming <= t-0.04) {
									System.out.println("bad "+ noteLine);
									notesOnScreen.remove(i);
									kbh.removeInput(pressedLine.get(j));
								}
							}
						}
							
						if(noteTiming < t-0.1) {
							notesOnScreen.remove(i);
						}
					}
				}
				
				//노트가 다 사라지면 애니메이션 루프를 종료함
				if(notesOnScreen.isEmpty() && notes.isEmpty()) {
					System.out.println("done");
					super.stop();
				}
				
				gc.drawImage( background, -0.5, -0.5, 801, 481 );
				for(int i=0; i<notesOnScreen.size(); i++) {
					NoteFormat drownNote = notesOnScreen.get(notesOnScreen.size() - i - 1);
					double[] notePosition = drownNote.getNotePosition(t);
					gc.drawImage(note, notePosition[0], notePosition[1], notePosition[2], notePosition[3]);
				}
				gc.drawImage( cover1, -0.5, -0.5, 801, 481 );
			}
		};
		
		
		
		gameLoop.start();
		
		theStage.show();
	}
}