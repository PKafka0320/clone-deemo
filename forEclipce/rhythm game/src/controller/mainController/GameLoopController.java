package mainController;

import eventProcesser.KeyListener;
import gamedrawer.GameDrawer;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import noteController.NoteController;
import scoreController.ScoreCalc;
import scoreController.ScoreController;

public class GameLoopController {
	private Group gameComponents = new Group();
	private Scene gameScene = new Scene( gameComponents, 800, 480);
	
	private AnimationTimer gameLoop;
	private KeyListener kListener = new KeyListener(this);
	private GameDrawer gameDrawer = new GameDrawer();
	private ScoreController scoreController = new ScoreController(gameDrawer.scoreDrawer);
	private NoteController noteController = new NoteController(kListener, scoreController, gameDrawer.getGc());
	
	public double startNanoTime;
	public double delayedTime = 3.0;
	
	public GameLoopController() {
		gameComponents.getChildren().add( gameDrawer.getCanvas() );
		
		this.gameLoop = new AnimationTimer()
		{
			double currentTime;
			
			public void start() {
				noteController.setNotes("test1");
				scoreController.setScoreCalc(new ScoreCalc(noteController.noteAmount));
				
				startNanoTime = System.nanoTime();
//				noteController.setStartTime( (startNanoTime/1000000000.0) + delayedTime );
				
				super.start();
			}
			//게임 이벤트를 처리하고 화면에 그려주는 루프
			public void handle(long currentNanoTime)
			{
				this.currentTime = ( (currentNanoTime-startNanoTime) / 1000000000.0 ) - delayedTime;
				
				if( !noteController.update(this.currentTime) ) {
//					super.stop();
				}
				
				gameDrawer.draw(this.currentTime, noteController.getNoteOnScreen(), scoreController.getScoreFormat());
				
			}
		};
	}
	
	public Scene getScene() {
		return gameScene;
	}
	
	public void start() {
		this.gameLoop.start();
	}
}
