package eventProcesser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import gameController.GameLoopController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneChanger {
	
	private FadeTransition ft = new FadeTransition(Duration.millis(1500));
	
	public SceneChanger() {
		// TODO Auto-generated constructor stub
	}
	
	public void fadeIn(AnchorPane pane) {
		ft.setNode(pane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	public void fadeOut(AnchorPane pane, Scene currentScene, String fxmlFileName) {
		ft.setNode(pane);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		ft.setOnFinished((ActionEvent event) -> {
			try {
				load(currentScene, fxmlFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		ft.play();
	}
	
	public void fadeOut(AnchorPane pane, Scene currentScene, GameLoopController glController) {
		ft.setNode(pane);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		ft.setOnFinished((ActionEvent event) -> {
			loadGame(currentScene, glController);
		});
		
		ft.play();
	}
	
	private void loadGame(Scene currentScene, GameLoopController glController){
		Scene Scene = glController.getScene();
		
		Stage Stage = (Stage) currentScene.getWindow();
		Stage.setScene(Scene);
		glController.start();
		Stage.show();
	}

	private void load(Scene currentScene, String fileName) throws IOException {
		try {
			Parent Parent;
			File file = new File(System.getProperty("user.dir") + "/asset/fxml/" + fileName + ".fxml");
			String filePath = file.toURI().toString();
			Parent = (AnchorPane) FXMLLoader.load(new URL(filePath));
			
			Scene Scene = new Scene(Parent);
			
			Stage Stage = (Stage) currentScene.getWindow();
			Stage.setScene(Scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
