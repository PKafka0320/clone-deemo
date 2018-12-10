package effectDrawer;

import etc.CollisionTypeFormat;
import javafx.animation.AnimationTimer;

public class ComboEffect extends AnimationTimer{
	public boolean isStop = false;
	public int line;
	
	public CollisionTypeFormat collisionType;
	
	public ComboEffect(int line, CollisionTypeFormat collisionType) {
		this.line = line;
		this.collisionType = collisionType;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
	@Override
	public void stop() {
		this.isStop = true;
		super.stop();
	}
	
	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		
	}
}
