package gamedrawer;

import etc.ScoreFormat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreDrawer {
	private GraphicsContext gc;
	
	private Font scoreFont = Font.loadFont("file:./asset/font/rocknroll_typo_college.TTF", 40);
	private Font comboFont = Font.loadFont("file:./asset/font/JUNGJ___.TTF", 60);
	private Font typeFont = Font.loadFont("file:./asset/font/rocknroll_typo_bevel.TTF", 25);
	
	public ScoreDrawer(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void drawScore(ScoreFormat score) {
		gc.setFill(Color.WHITE);
		gc.setFont(scoreFont);
		gc.fillText(score.getScore()+"", 700, 40);
	}
	
	public void drawCombo(ScoreFormat score, double currentTime) {
		gc.setFill(Color.WHITE);
		gc.setFont(comboFont);
		gc.fillText(score.getCombo()+"", 400, 20);	
		
		gc.setFill(Color.WHITE);
		gc.setFont(typeFont);
		gc.fillText(score.getScore()+"", 400, 20);
	}
	
	public void comboEffect(ScoreFormat score, double currentTime) {
		// TODO make effect.java and link here
	}
}