package gamedrawer;

import etc.ScoreFormat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreDrawer {
	private GraphicsContext gc;
	
	private Font scoreFont = new Font(30).loadFont("file:./asset/font/rocknroll_typo_regular.TTF", 30);
	private Font comboFont = new Font(60).loadFont("file:./asset/font/JUNGJ___.TTF", 60);
	private Font typeFont = new Font(25).loadFont("file:./asset/font/FANTIQUE.TTF", 25);
	
	public ScoreDrawer(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void draw(ScoreFormat score) {
		gc.setFill(Color.WHITE);
		gc.fillText(score.getScore()+"", 400, 20);
		
		gc.fillText(score.getCombo()+"", 400, 20);
		gc.fillText(score.getScore()+"", 400, 20);
	}
}
