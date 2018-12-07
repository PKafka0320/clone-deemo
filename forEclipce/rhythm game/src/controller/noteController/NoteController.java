package noteController;

import java.util.ArrayList;
import java.util.Queue;

import etc.GameInputFormat;
import eventProcesser.KeyListener;
import javafx.scene.Scene;
import note.NoteFormat;
import note.NoteReader;

public class NoteController {
	private CollisionChecker cc = new CollisionChecker();
	private NotePositionCalc npCalc = new NotePositionCalc();
	private NoteReader nReader = new NoteReader();
	private KeyListener kListener;
	
	Queue<NoteFormat> notes;
	ArrayList<NoteFormat> notesOnScreen = new ArrayList<NoteFormat>();
	
	public NoteController(Scene theScene) {
		this.kListener = new KeyListener(theScene);
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
		for(GameInputFormat currentInput : inputList) {
			for(NoteFormat drownNote : this.notesOnScreen) {
				if( currentInput.getLine() == drownNote.getLine()) {
					String collisionType = this.cc.checkIsCollision(drownNote, currentInput);
					
					if( collisionType != "pass") {
						System.out.println(collisionType);
						this.notesOnScreen.remove( drownNote );
						this.kListener.remove( currentInput );
						break;
					}
				}
			}
		}
	}
	
	public boolean update(double currentTime) {
//		System.out.println(this.notes);
//		System.out.println(this.notesOnScreen);
		
		if( this.notes.isEmpty() && this.notesOnScreen.isEmpty() ) {
			return false;
		}
		
		else {
			NoteFormat currentNote = notes.peek();
			//System.out.println(currentNote);
			if( this.npCalc.isOnScreen(currentTime, currentNote) ) {
				this.addDrawableNote(currentNote);
			}
			
			ArrayList<GameInputFormat> inputList = this.kListener.getInputList();
			this.checkCollision(inputList);
			
			return true;
		}
	}
	
	
}