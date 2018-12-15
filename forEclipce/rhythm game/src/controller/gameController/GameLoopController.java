package gameController;

import java.io.File;

import calculater.NotePositionCalc;
import calculater.ScoreCalc;
import eventProcesser.KeyListener;
import eventProcesser.SceneChanger;
import gamedrawer.GameDrawer;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import reader.SettingReader;

public class GameLoopController {
	private SceneChanger sceneChanger = new SceneChanger();
	
	private AnchorPane gamePane = new AnchorPane();
	
	private Scene gameScene = new Scene(gamePane, 800, 480);

	private AnimationTimer gameLoop;
	private KeyListener kListener = new KeyListener(this);
	private NotePositionCalc npCalc = new NotePositionCalc();
	private GameDrawer gameDrawer = new GameDrawer(npCalc);
	private ScoreController scoreController = new ScoreController(gameDrawer.scoreDrawer);
	private NoteController noteController = new NoteController(kListener, scoreController, gameDrawer.getGc(), npCalc);
	private MediaPlayer musicPlayer;
	private SettingReader settingReader = new SettingReader();

	private double startNanoTime;
	private double delayedTime = 6.0;
	private double endDelayTime = 5.0;
	private double endTimerTime = 0;

	public GameLoopController(String musicName, String noteName) {
		gamePane.getChildren().add(gameDrawer.getCanvas());

		File file = new File(System.getProperty("user.dir") + "/asset/music/soundtrack/" + musicName + ".mp3");
		String filePath = file.toURI().toString();
		Media music = new Media(filePath);
		musicPlayer = new MediaPlayer(music);
		noteController.setNotes(noteName);
		scoreController.setScoreCalc(new ScoreCalc(noteController.noteAmount));
		
		double[] settingValues = settingReader.readSetting();
		musicPlayer.setVolume(settingValues[0]/100.0);

		this.gameLoop = new AnimationTimer() {
			double currentTime;

			@Override
			public void start() {
				System.out.println("Song Started");
				gamePane.setOpacity(0);
				sceneChanger.fadeIn(gamePane);
				startNanoTime = System.nanoTime();

				super.start();
			}
			
			@Override
			public void stop() {
//				sceneChanger.fadeOut(node, currentScene, fxmlFileName);
				
				super.stop();
			}

			@Override
			public void handle(long currentNanoTime) {
				this.currentTime = ((currentNanoTime - startNanoTime) / 1000000000.0) - delayedTime;
				if (currentTime > -1 && !(musicPlayer.getStatus() == Status.PLAYING)) {
					musicPlayer.play();
				}

				if (!noteController.update(this.currentTime)) {
					if(endTimerTime == 0) {
						endTimerTime = System.nanoTime();
					}
					else {
						if(endDelayTime < ((currentNanoTime-endTimerTime)/1000000000.0)) {
							this.stop();
						}
					}
				}

				gameDrawer.draw(this.currentTime, noteController.getNoteOnScreen(), scoreController.getScoreFormat());

			}
		};
	}

	public Scene getScene() {
		return gameScene;
	}

	public double getStartNanoTime() {
		return startNanoTime;
	}

	public double getDelayedTime() {
		return delayedTime;
	}

	public void start() {
		this.gameLoop.start();
	}
}
