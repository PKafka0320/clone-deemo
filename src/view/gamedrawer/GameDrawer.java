package gamedrawer;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import note.NoteFormat;

public class GameDrawer {
	private Canvas gameScreen = new Canvas( 800, 480 );
	private GraphicsContext gc = gameScreen.getGraphicsContext2D();
	
	private DrawNote noteDrawer = new DrawNote(gc);;
	private Image background = new Image("file:./asset/image/background3.png");
	private Image cover = new Image("file:./asset/image/cover2.png");
	
	public GameDrawer() {
	}
	
	public Canvas getCanvas() {
		return this.gameScreen;
	}
	
	public void draw(double currentTime, ArrayList<NoteFormat> notesOnScreen) {
		gc.drawImage(background, -0.5, -0.5, 801, 481);
		noteDrawer.draw(notesOnScreen, currentTime);
		gc.drawImage(cover, -0.5, -0.5, 801, 481);
	}
}