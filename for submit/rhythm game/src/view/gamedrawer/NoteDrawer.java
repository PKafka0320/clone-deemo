package gamedrawer;

import java.util.ArrayList;

import calculater.NotePositionCalc;
import format.NoteFormat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import reader.SettingReader;

public class NoteDrawer {
	public ArrayList<NoteFormat> notesOnScreen = new ArrayList<NoteFormat>();
	
	private GraphicsContext gc;
	private NotePositionCalc npCalc;
	private SettingReader settingReader = new SettingReader();
	DropShadow shadowEffect = new DropShadow();
	
	private Image noteImage = new Image( "file:./asset/image/note2.png" );
	
	public NoteDrawer(GraphicsContext gc, NotePositionCalc npCalc) {
		this.npCalc = npCalc;
		this.gc = gc;
		shadowEffect.setColor(javafx.scene.paint.Color.rgb(255, 255, 255, 1));
		shadowEffect.setSpread(0.3);
		shadowEffect.setBlurType(BlurType.GAUSSIAN);
		double[] settingValues = settingReader.readSetting();
		this.npCalc.setDroptime(2 + (3 - settingValues[2])/2);
	}
	
	public void draw(ArrayList<NoteFormat> notesOnScreen, double currentTime) {
		for(int i=0; i<notesOnScreen.size(); i++) {
			NoteFormat drownNote = notesOnScreen.get(notesOnScreen.size() - i - 1);
			double[] notePosition = npCalc.getNotePosition(drownNote, currentTime);
			shadowEffect.setRadius(notePosition[3]*2);
			gc.setEffect(shadowEffect);
			gc.drawImage(noteImage, notePosition[0], notePosition[1], notePosition[2], notePosition[3]);
			gc.setEffect( null );
		}
	}
}
