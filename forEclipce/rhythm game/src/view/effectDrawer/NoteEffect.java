package effectDrawer;


import format.CollisionTypeFormat;
import format.NoteFormat;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import noteController.NotePositionCalc;

public class NoteEffect extends AnimationTimer{
	public static double effectDuration = 0.7;
	
	NotePositionCalc npCalc = new NotePositionCalc();
	private GraphicsContext gc;
	public boolean isStop = true;
	
	private double startTime;
	private double currentTime;
	
	public CollisionTypeFormat collisionType;
	public int line;
	public Color color;
	
	private double[] xPoints = new double[3];
	private double[] yPoints = new double[3];
	
	public NoteEffect(GraphicsContext gc, int line, CollisionTypeFormat collisionType) {
		this.gc = gc;
		this.collisionType = collisionType;
		this.line = line;
		
		xPoints[0] = npCalc.getLocationX(0, new NoteFormat(line, 0));
		xPoints[2] = xPoints[0] + npCalc.getInitSizeX();
		yPoints[0] = npCalc.getLocationY(0, new NoteFormat(line, 0));
		yPoints[2] = yPoints[0];
		if(collisionType.getCollisionType() == "perfect") {
			color = Color.rgb(255, 215, 0, 0.3);
		}
		else if(collisionType.getCollisionType() == "great"){
			color = Color.rgb(121, 233, 130, 0.3);
		}
		else if(collisionType.getCollisionType() == "bad") {
			color = Color.rgb(83, 167, 206, 0.3);
		}
//		npCalc.getSizeX(npCalc.droptime, new NoteFormat((int) (line+( npCalc.getInitSizeX())/2 ), npCalc.droptime));
		
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
		double time = getImgLength( currentTime/effectDuration );
		
		NoteFormat n = new NoteFormat(line, effectDuration);
		xPoints[1] = npCalc.getLocationX(-time/2, n) + npCalc.getSizeX(-time/2, n)/2;
		yPoints[1] = npCalc.getLocationY(-time/2, n) + npCalc.getSizeY(-time/2, n)/2;
		
		gc.setFill(color);
		gc.setEffect(new GaussianBlur(50));
		gc.fillPolygon(xPoints, yPoints, 3);
		gc.setEffect(null);
		
		if( currentTime > effectDuration ) {
			this.stop();
		}
	}
	//0<time<1
	public double getImgLength(double time) {
		double x;
		if( time < effectDuration/8 ) {
			x = Math.sin( (time*8/2)*Math.PI );
		}
		else {
			x = Math.sin((((time*8)+6)/14)*Math.PI);
		}
		return x;
	}
}
