package fxTest;

import java.util.ArrayList;
import java.util.Queue;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import notes.NoteFormat;

public class DrawNote {
	
	public AnimationTimer drawGameScreen;
	
	public DrawNote(GraphicsContext gc, KeyboardHandler kbh, Queue<NoteFormat> notes) {
		
		drawGameScreen = new AnimationTimer()
		{
			private long startNanoTime;
			private ArrayList<NoteFormat> notesOnScreen;
			
			Image note;
			Image background;
			Image cover;
			
			public void start() {
				
				note = new Image( "file:./asset/image/note.png" );
				background = new Image("file:./asset/image/background3.png");
				cover = new Image("file:./asset/image/cover2.png");
				
				this.startNanoTime = System.nanoTime();
				this.notesOnScreen = new ArrayList<NoteFormat>();
				
				super.start();
			}
			
			public void handle(long currentNanoTime)
			{
				ArrayList<Integer> pressedLine = kbh.getPressedLine();
				double t = (currentNanoTime - startNanoTime) / 1000000000.0 - 6; 
				
				//��Ʈ�� ��ũ�� ���� �ִ��� �˻��Ͽ� �ִٸ� �׷��� ��Ͽ� �߰���
				if( ( notes.peek() != null ) && notes.peek().isOnScreen(t) ) {
					notesOnScreen.add( notes.poll() );
				}
				
				//��Ʈ���� �˻��Ͽ� ��ũ�� �ۿ� �ִٸ� �׷��� ��Ͽ��� ������
				if( !notesOnScreen.isEmpty()) {
					for(int i=0; i<notesOnScreen.size(); i++) {
						NoteFormat currentNote = notesOnScreen.get(i);
						double noteTiming = currentNote.getTiming();
						int noteLine = currentNote.getLine();
						
						for(int j=0; j<pressedLine.size(); j++) {
							if(!notesOnScreen.isEmpty() && pressedLine.get(j) == noteLine) {
								if( t+0.08 < noteTiming && noteTiming <= t+0.2) {
									System.out.println("bad "+ noteLine);
									notesOnScreen.remove(i);
									//kbh.removeInput(pressedLine.get(j));
								}
								else if( t+0.03 < noteTiming && noteTiming <= t+0.08) {
									System.out.println("great "+ noteLine);
									notesOnScreen.remove(i);
									//kbh.removeInput(pressedLine.get(j));
								}
								else if( t-0.03 < noteTiming && noteTiming <= t+0.03) {
									System.out.println("perfect "+ noteLine);
									notesOnScreen.remove(i);
									//kbh.removeInput(pressedLine.get(j));
								}
								else if( t-0.06 < noteTiming && noteTiming <= t-0.03) {
									System.out.println("great "+ noteLine);
									notesOnScreen.remove(i);
									//kbh.removeInput(pressedLine.get(j));
								}
								else if(noteTiming <= t-0.06) {
									System.out.println("bad "+ noteLine);
									notesOnScreen.remove(i);
									//kbh.removeInput(pressedLine.get(j));
								}
							}
						}
						
						if(noteTiming < t-0.1) {
							notesOnScreen.remove(i);
						}
					}
				}
				
				//��Ʈ�� �� ������� �ִϸ��̼� ������ ������
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
				gc.drawImage( cover, -0.5, -0.5, 801, 481 );
			}
		};
		
	}
}
