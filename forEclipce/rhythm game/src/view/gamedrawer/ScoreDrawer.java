package gamedrawer;

import effectDrawer.ComboEffect;
import etc.CollisionTypeFormat;
import etc.ScoreFormat;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreDrawer {
	private GraphicsContext gc;
	
	private Font scoreFont = Font.loadFont("file:./asset/font/rocknroll_typo_college.TTF", 40);
	
	private ComboEffect comboEffect;
	private boolean isFirst = true;
	
	public ScoreDrawer(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void drawScore(ScoreFormat score) {
		gc.setFill(Color.WHITE);
		gc.setFont(scoreFont);
		gc.fillText((int)Math.round(score.getScore())+"", 650, 40);
	}
	
	public void drawCombo(ScoreFormat score, double currentTime, CollisionTypeFormat collisionType) {
		if(isFirst || comboEffect.isStop) {
			isFirst = false;
			comboEffect = new ComboEffect(gc, score.getCombo(), collisionType);
			comboEffect.start();
		}
		else {
			comboEffect.stop();
			comboEffect = new ComboEffect(gc, score.getCombo(), collisionType);
			comboEffect.start();
		}
	}
}