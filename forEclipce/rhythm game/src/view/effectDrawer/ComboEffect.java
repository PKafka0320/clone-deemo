package effectDrawer;

import etc.CollisionTypeFormat;
import javafx.animation.AnimationTimer;

public class ComboEffect extends AnimationTimer{
	public static double effectDuration = 1.5;
	
	public boolean isStop = false;
	public int line;
	
	public double startTime;
	public double currentTime;
	
	public CollisionTypeFormat collisionType;
	
	public ComboEffect(int line, CollisionTypeFormat collisionType) {
		this.line = line;
		this.collisionType = collisionType;
	}
	
	@Override
	public void start() {
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
		
		// TODO make effect for when note checked and combo added
		
		
		if(currentTime > effectDuration) {
			this.stop();
		}
	}
}
