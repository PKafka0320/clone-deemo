package effectDrawer;

import etc.CollisionTypeFormat;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComboEffect extends AnimationTimer{
	public static double effectDuration = 3;
	
	private GraphicsContext gc;
	public boolean isStop = true;
	public int combo;

	private Font comboFont = Font.loadFont("file:./asset/font/JUNGJ___.TTF", 120);
	private Font typeFont = Font.loadFont("file:./asset/font/rocknroll_typo_bevel.TTF", 25);
	
	private double startTime;
	private double currentTime;
	
	public CollisionTypeFormat collisionType;
	
	public ComboEffect(GraphicsContext gc, int combo, CollisionTypeFormat collisionType) {
		this.combo = combo;
		this.collisionType = collisionType;
		this.gc = gc;
	}
	
	@Override
	public void start() {
		this.isStop = false;
		this.startTime = System.nanoTime();
		super.start();
	}
	
	@Override
	public void stop() {
		this.isStop = true;
		super.stop();
	}
	
	@Override
	public void handle(long now) {
		currentTime = ( now - startTime )/1000000000.0;
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, 800, 800);
		// TODO make effect for when note checked and combo added
		gc.setFill(Color.rgb(0, 0, 0, 0));
		gc.fillRect(650, 110, 140, 160);
		gc.setFill(Color.rgb(255, 255, 255, getVisibility(currentTime) ) );
        gc.setFont(comboFont);
        gc.fillText(combo+"", 700, 230);
        gc.setFont(typeFont);
        gc.fillText(collisionType.toString(), 700, 240);
        gc.setFill(Color.BLACK);
		
		if(currentTime > effectDuration) {
			this.stop();
		}
	}
	
	private double getVisibility(double currentTime) {
		final double a = 3;
		//use Gaussian function to calculate transparency effect
		double x = ( currentTime - effectDuration/2 )/( effectDuration/2 )+effectDuration/5;
		return Math.pow( Math.E, -Math.pow(a*x, 2) );
	}
}
