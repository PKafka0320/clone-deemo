package noteController;

import java.util.ArrayList;
import java.util.Queue;

import effectDrawer.NoteEffect;
import etc.CollisionTypeFormat;
import etc.GameInputFormat;
import eventProcesser.KeyListener;
import javafx.scene.canvas.GraphicsContext;
import note.NoteFormat;
import note.NoteReader;
import scoreController.ScoreController;

public class NoteController {
	private ScoreController scoreController;
	private CollisionChecker cc = new CollisionChecker(new double[]{0.12, 0.08, 0.03});
	private NotePositionCalc npCalc = new NotePositionCalc();
	private NoteReader nReader = new NoteReader();
	private KeyListener kListener;
	private GraphicsContext gc;
	
	Queue<NoteFormat> notes;
	public int noteAmount;
	ArrayList<NoteFormat> notesOnScreen = new ArrayList<NoteFormat>();
	double startTime;
	
	public NoteController(KeyListener kListener, ScoreController scoreController, GraphicsContext gc) {
		this.kListener = kListener;
		this.scoreController = scoreController;
		this.gc = gc;
	}
	
	public void setStartTime(double startTime) {
		this.startTime = startTime;
		
	}
	
	public boolean setNotes(String musicName) {
		this.notes = this.nReader.readNote(musicName);
		if(this.notes == null) {
			return false;
		}
		else {
			this.noteAmount = notes.size();
			return true;
		}
	}
	
	public Queue<NoteFormat> getNotes(){
		return this.notes;
	}
	
	public ArrayList<NoteFormat> getNoteOnScreen() {
		return this.notesOnScreen;
	}
	
	private void addDrawableNote(NoteFormat currentNote) {
		notesOnScreen.add(currentNote);
	}
	
	public void checkCollision(ArrayList<GameInputFormat> GameInputList, double currentTime) {
		int index1 = 0, index2 = 0;
		while( !(GameInputList.size() == index1) ) {
			GameInputFormat currentInput = GameInputList.get(index1);
			index2 = 0;
			while( !(notesOnScreen.size() == index2) ) {
				NoteFormat currentNote = notesOnScreen.get(index2);
				
				if( cc.checkLine(currentNote, currentInput) ) {
					CollisionTypeFormat collisionType = cc.checkCollision(currentNote, currentInput);
					
					if( collisionType.getCollisionType() != CollisionTypeFormat.getPassType() ) {
						scoreController.update(collisionType, currentTime);
						System.out.println(collisionType);
						System.out.println( scoreController.getScoreFormat() );
						new NoteEffect(gc, currentNote.getLine(), collisionType).start();
						
						this.notesOnScreen.remove( currentNote );
						index2--;
						this.kListener.removeGameInput( currentInput );
						index1--;
						break;
					}
				}
				
				index2++;
			}
			index1++;
		}
	}
	
	public void deleteOutNotes(double currentTime) {
		int i = 0;
		while( !(notesOnScreen.size() == i) ) {
			NoteFormat currentNote = notesOnScreen.get(i);
			CollisionTypeFormat collisionType = cc.checkIsOut(currentNote, currentTime, npCalc.getDroptime());
			if( collisionType.getCollisionType() == CollisionTypeFormat.getMissType() )  {
				notesOnScreen.remove(i);
				scoreController.update(collisionType, currentTime);
				System.out.println(collisionType);
				System.out.println( scoreController.getScoreFormat() );
				i--;
			}
			i++;
		}
	}
	
//	//not used
//	public void deleteOutInput(double currentTime) {
//		ArrayList<GameInputFormat> GameInputList = this.kListener.getInputLineList();
//		int i = 0;
//		while( !(GameInputList.size() == i) ) {
//			GameInputFormat currentInput = GameInputList.get(i);
//			NoteFormat currentNote = new NoteFormat( currentInput );
//			CollisionTypeFormat collisionType = cc.checkIsOut(currentNote, currentTime, npCalc.droptime);
//			if( collisionType.getCollisionType() == CollisionTypeFormat.getMissType() )  {
//				this.kListener.removeGameInput(currentInput);
//				i--;
//			}
//			i++;
//		}
//	}
	
	public void clearInput() {
		ArrayList<GameInputFormat> GameInputList = this.kListener.getInputLineList();
		while( !(GameInputList.size() == 0) ) {
			this.kListener.removeGameInput(0);
		}
	}
	
	public boolean update(double currentTime) {
//		System.out.println(this.notes);
//		System.out.println(this.notesOnScreen);
		
		if( this.notes.isEmpty() && this.notesOnScreen.isEmpty() ) {
			return false;
		}
		
		else {
//			System.out.println(notes.peek());
			if( !(this.notes.isEmpty()) && this.npCalc.isOnScreen(currentTime, notes.peek()) ) {
				this.addDrawableNote(notes.poll());
			}
			
			this.deleteOutNotes(currentTime);
			
			ArrayList<GameInputFormat> GameInputList = this.kListener.getInputLineList();
			this.checkCollision(GameInputList, currentTime);
			
//			this.deleteOutInput(currentTime);
			this.clearInput();
			
			return true;
		}
	}
}