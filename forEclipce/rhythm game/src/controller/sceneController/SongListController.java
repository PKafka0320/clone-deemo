package sceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import format.ScoreFormat;
import gameController.GameLoopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import reader.ScoreReader;

public class SongListController implements Initializable {

	SceneChanger sceneChanger = new SceneChanger();
	GameLoopController glController;
	ScoreReader sReader = new ScoreReader();
	
	String songNameValue = "SuspensefulThirdDay";
//	String songNameValue = "test3";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		ScoreFormat scoreValues = sReader.readScore(songNameValue);
		
		this.songName.setFont(Font.loadFont("file:./asset/font/FANTIQUE.TTF", 30));
		this.songName.setText(songNameValue);
		this.score.setFont(Font.loadFont("file:./asset/font/rocknroll_typo_regular.TTF", 25));
		this.score.setText(scoreValues.getScore()+"");
		this.scoreString.setFont(Font.loadFont("file:./asset/font/rocknroll_typo_regular.TTF", 30));

		
		SongList.setOpacity(0);
		sceneChanger.fadeIn(SongList);
	}
	
    @FXML
    private AnchorPane SongList;

    @FXML
    private ImageView Song;

    @FXML
    private Label scoreString;

    @FXML
    private Label score;

    @FXML
    private Label songName;

	@FXML
	void SelectSong(MouseEvent event) {
		System.out.println(songNameValue+" Song Selected");
		glController = new GameLoopController(songNameValue);
//		glController = new GameLoopController("test3");
		sceneChanger.fadeOut(SongList, SongList.getScene(), glController);
	}

	@FXML
	private void BackToMain(MouseEvent m) throws IOException {
		System.out.println("Back to Main");
		sceneChanger.fadeOut(SongList, SongList.getScene(), "main");
	}
}