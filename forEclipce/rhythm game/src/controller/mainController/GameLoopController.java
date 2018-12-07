package mainController;

import gamedrawer.GameDrawer;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import noteController.NoteController;

public class GameLoopController {
	private Group gameComponents = new Group();
	private Scene gameScene = new Scene( gameComponents, 800, 480);
	
	private AnimationTimer gameLoop;
	
	private GameDrawer gameDrawer = new GameDrawer();
	
	private NoteController noteController = new NoteController(gameScene);
	
	public GameLoopController() {
		
		gameComponents.getChildren().add( gameDrawer.getCanvas() );
		
		this.gameLoop = new AnimationTimer()
		{
			double startNanoTime;
			double currentTime;
			
			public void start() {
				this.startNanoTime = System.nanoTime();
				noteController.setNotes("music1");
				
				super.start();
			}
			
			public void handle(long currentNanoTime)
			{
				this.currentTime = (currentNanoTime - this.startNanoTime) / 1000000000.0;
				
				if( !noteController.update(this.currentTime) ) {
					super.stop();
				}
				
				gameDrawer.draw(this.currentTime, noteController.getNoteOnScreen());
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
