package gamedrawer;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import note.NoteFormat;
import noteController.NotePositionCalc;

public class NoteDrawer {
	public ArrayList<NoteFormat> notesOnScreen = new ArrayList<NoteFormat>();
	
	private GraphicsContext gc;
	private NotePositionCalc npCalc = new NotePositionCalc();
	
	private Image noteImage = new Image( "file:./asset/image/note.png" );
	
	public NoteDrawer(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void draw(ArrayList<NoteFormat> notesOnScreen, double currentTime) {
		for(int i=0; i<notesOnScreen.size(); i++) {
			NoteFormat drownNote = notesOnScreen.get(notesOnScreen.size() - i - 1);
			double[] notePosition = npCalc.getNotePosition(drownNote, currentTime);
			gc.drawImage(noteImage, notePosition[0], notePosition[1], notePosition[2], notePosition[3]);
		}
	}
}
