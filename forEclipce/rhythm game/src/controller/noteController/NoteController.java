package noteController;

import java.util.ArrayList;
import java.util.Queue;

import etc.GameInputFormat;
import eventProcesser.KeyListener;
import javafx.scene.Scene;
import note.NoteFormat;
import note.NoteReader;

public class NoteController {
	private CollisionChecker cc = new CollisionChecker(new double[]{0.2, 0.12, 0.08});
	private NotePositionCalc npCalc = new NotePositionCalc();
	private NoteReader nReader = new NoteReader();
	private KeyListener kListener;
	
	Queue<NoteFormat> notes;
	ArrayList<NoteFormat> notesOnScreen = new ArrayList<NoteFormat>();
	double startTime;
	
	public NoteController(Scene theScene) {
		this.kListener = new KeyListener(theScene);
	}
	
	public NoteController(Scene theScene, double startTime) {
		this.kListener = new KeyListener(theScene);
		this.startTime = startTime;
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
	
	public void checkCollision(ArrayList<GameInputFormat> inputList) {
//		for(GameInputFormat currentInput : inputList) {
//			for(NoteFormat drownNote : this.notesOnScreen) {
//				if( currentInput.getLine() == drownNote.getLine()) {
//					String collisionType = this.cc.checkIsCollision(drownNote, currentInput);
//					
//					if( collisionType != "pass") {
//						System.out.println(collisionType);
//						this.notesOnScreen.remove( drownNote );
//						this.kListener.remove( currentInput );
//						break;
//					}
//				}
//			}
//		}
		int index1 = 0;
		int index2 = 0;
		while( !(inputList.size() == index1) ) {
			GameInputFormat currentInput = inputList.get(index1);
			index2 = 0;
			while( !(notesOnScreen.size() == index2) ) {
				NoteFormat currentNote = notesOnScreen.get(index2);
				
				if( cc.checkLine(currentNote, currentInput) ) {
					String collisionType = cc.checkIsCollision(currentNote, currentInput, startTime);
					
					if(collisionType != "pass") {
						System.out.println(collisionType);
						this.notesOnScreen.remove( currentNote );
						index2--;
						this.kListener.remove( currentInput );
						index1--;
						break;
					}
				}
				
				index2++;
			}
			index1++;
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
			
			ArrayList<GameInputFormat> inputList = this.kListener.getInputList();
			this.checkCollision(inputList);
			
			return true;
		}
	}
}