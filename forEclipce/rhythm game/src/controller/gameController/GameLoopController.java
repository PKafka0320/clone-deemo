package gameController;

import java.io.File;

import calculater.ScoreCalc;
import eventProcesser.KeyListener;
import eventProcesser.SceneChanger;
import gamedrawer.GameDrawer;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import reader.SettingReader;

public class GameLoopController {
	SceneChanger sceneChanger = new SceneChanger();
	private Group gameComponents = new Group();
	private Scene gameScene = new Scene(gameComponents, 800, 480);

	private AnimationTimer gameLoop;
	private KeyListener kListener = new KeyListener(this);
	private GameDrawer gameDrawer = new GameDrawer();
	private ScoreController scoreController = new ScoreController(gameDrawer.scoreDrawer);
	private NoteController noteController = new NoteController(kListener, scoreController, gameDrawer.getGc());
	static MediaPlayer musicPlayer;
	private SettingReader settingReader = new SettingReader();

	public double startNanoTime;
	public double delayedTime = 6.0;

	public GameLoopController(String musicName, String noteName) {
		gameComponents.getChildren().add(gameDrawer.getCanvas());

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

			public void start() {
				System.out.println("Song Started");
				gameDrawer.getCanvas().setOpacity(0);
				sceneChanger.fadeIn(gameDrawer.getCanvas());
				startNanoTime = System.nanoTime();
				
				super.start();
			}

			// ���� �̺�Ʈ�� ó���ϰ� ȭ�鿡 �׷��ִ� ����
			public void handle(long currentNanoTime) {
				this.currentTime = ((currentNanoTime - startNanoTime) / 1000000000.0) - delayedTime;
				if (currentTime > -1 && !(musicPlayer.getStatus() == Status.PLAYING)) {
					musicPlayer.play();
				}

				if (!noteController.update(this.currentTime)) {
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
