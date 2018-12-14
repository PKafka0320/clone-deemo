package eventProcesser;

import java.util.ArrayList;

import etc.GameInputFormat;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import mainController.GameLoopController;

public class KeyListener {
	Scene theScene;

	GameInputFormat gameInput;
	GameInputFormat gameInputChecker;

	ArrayList<GameInputFormat> gameInputList = new ArrayList<GameInputFormat>();
	ArrayList<String> gameInputCheckTable = new ArrayList<String>();

	ArrayList<String> inputList = new ArrayList<String>();

	public KeyListener(GameLoopController glController) {
		// use in gameScene
		this.theScene = glController.getScene();

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (GameInputFormat.getAvailableInputList().contains(code)) {
					double currentTime = (System.nanoTime() - glController.startNanoTime) / 1000000000.0
							- glController.delayedTime;
					gameInput = new GameInputFormat(code, currentTime);
					gameInputChecker = new GameInputFormat(code);
					String line = "" + gameInputChecker.getLine();
					if (!gameInputCheckTable.contains(line)) {
//						System.out.println(line + " " + currentTime);
						gameInputCheckTable.add(line);
						gameInputList.add(gameInput);
					}
				}

				if (!inputList.contains(code)) {
					inputList.add(code);
				}
			}
		});

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (GameInputFormat.getAvailableInputList().contains(code)) {
					gameInputChecker = new GameInputFormat(code);
					String line = "" + gameInputChecker.getLine();
					gameInputCheckTable.remove(line);
				}

				inputList.remove(code);
			}
		});
	}

	public KeyListener(Scene theScene) {
		// use in other scene
		// ex) mainScene, settingScene
		this.theScene = theScene;

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				if (!inputList.contains(code)) {
					inputList.add(code);
				}
			}
		});

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				inputList.remove(code);
			}
		});
	}

	public ArrayList<GameInputFormat> getInputLineList() {
		return this.gameInputList;
	}

	public ArrayList<String> getInputCheckTable() {
		return this.gameInputCheckTable;
	}

	public ArrayList<String> getInputList() {
		return this.inputList;
	}

	public boolean remove(String currentInput) {
		return this.inputList.remove(currentInput);
	}

	public boolean removeGameInput(GameInputFormat currentGameInput) {
		return this.gameInputList.remove(currentGameInput);
	}

	public void removeGameInput(int index) {
		this.gameInputList.remove(index);
	}

}
