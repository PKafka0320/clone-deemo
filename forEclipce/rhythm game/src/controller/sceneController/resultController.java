package sceneController;

import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import format.ScoreFormat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import reader.ScoreReader;
import reader.SongValueReader;

public class resultController implements Initializable {
	
	SceneChanger sceneChanger = new SceneChanger();
	
	ScoreReader scoreReader = new ScoreReader();
	SongValueReader songValueReader = new SongValueReader();
	
	String musicName;
	int noteAmount;
	
	double scoreValue;
	int maxComboValue;
	int perfectHitValue;

    @FXML
    private AnchorPane result;

    @FXML
    private Group perfectAmount;

    @FXML
    private Label currentPerfect;

    @FXML
    private Label maxPerfect;
    
    @FXML
    private Label perfectHit;

    @FXML
    private Group comboAmount;

    @FXML
    private Label currentCombo;

    @FXML
    private Label maxCombo;
    
    @FXML
    private Label combo;

    @FXML
    private Label songName;

    @FXML
    private Label score;

    @FXML
    void BackToMain(MouseEvent event) {
		System.out.println("Back to Main");
    	sceneChanger.fadeOut(result, result.getScene(), "main");

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] songValues = songValueReader.readSongValue();
		this.musicName = songValues[0];
		
		this.songName.setFont(Font.loadFont("file:./asset/font/FANTIQUE.TTF", 40));
		this.songName.setText(musicName);
		
		this.noteAmount = Integer.parseInt(songValues[1]);
		
		ScoreFormat score = scoreReader.readScore(musicName);
		this.scoreValue = score.getScore();
		this.maxComboValue = score.getMaxCombo();
		this.perfectHitValue = score.getPerfectHit();

		this.maxPerfect.setFont(Font.loadFont("file:./asset/font/rocc.TTF", 50));
		this.maxPerfect.setText(this.noteAmount+"");
		this.maxCombo.setFont(Font.loadFont("file:./asset/font/rocc.TTF", 50));
		this.maxCombo.setText(this.noteAmount+"");
		this.currentPerfect.setFont(Font.loadFont("file:./asset/font/rocc.TTF", 50));
		this.currentPerfect.setText(this.perfectHitValue+"");
		this.currentCombo.setFont(Font.loadFont("file:./asset/font/rocc.TTF", 50));
		this.currentCombo.setText(this.maxComboValue+"");
		this.score.setFont(Font.loadFont("file:./asset/font/rocknroll_typo_regular.TTF", 50));
		this.score.setText(this.scoreValue+"");
		
		this.perfectHit.setFont(Font.loadFont("file:./asset/font/GLECB.TTF", 40));
		this.combo.setFont(Font.loadFont("file:./asset/font/GLECB.TTF", 40));
		
		result.setOpacity(0);
		sceneChanger.fadeIn(result);
	}
}
