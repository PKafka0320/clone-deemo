package sceneController;

import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class resultController implements Initializable {
	
	SceneChanger sceneChanger = new SceneChanger();

    @FXML
    private AnchorPane result;

    @FXML
    private Group perfectAmount;

    @FXML
    private Label currentPerfect;

    @FXML
    private Label maxPerfect;

    @FXML
    private Group comboAmount;

    @FXML
    private Label currentCombo;

    @FXML
    private Label maxCombo;

    @FXML
    private Label songName;

    @FXML
    private Label score;

    @FXML
    void BackToMain(KeyEvent event) {
		System.out.println("Back to Main");
    	sceneChanger.fadeOut(result, result.getScene(), "main");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		result.setOpacity(0);
		sceneChanger.fadeIn(result);
	}

}
