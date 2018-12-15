package sceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import gameController.GameLoopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SongListController implements Initializable {

	SceneChanger sceneChanger = new SceneChanger();
	GameLoopController glController;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println(url);
		System.out.println(rb);
		SongList.setOpacity(0);
		sceneChanger.fadeIn(SongList);
	}

	@FXML
	private AnchorPane SongList;

	@FXML
	private ImageView Song;

	@FXML
	void SelectSong(MouseEvent event) {
		System.out.println("Song Selected");
		glController = new GameLoopController("SuspensefulThirdDay", "SuspensefulThirdDay");
		sceneChanger.fadeOut(SongList, SongList.getScene(), glController);
	}

	@FXML
	private void BackToMain(MouseEvent m) throws IOException {
		System.out.println("Back to Main");
		sceneChanger.fadeOut(SongList, SongList.getScene(), "main");
	}
}