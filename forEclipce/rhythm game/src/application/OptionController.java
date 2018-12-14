package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OptionController implements Initializable {
	
	SceneChanger sceneChanger = new SceneChanger();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	Option.setOpacity(0.5);
    	sceneChanger.fadeIn(Option);
    }

    @FXML
    private AnchorPane Option;

    @FXML
    private Slider VolumeSlide;

    @FXML
	private void BackToMain(MouseEvent m) throws IOException {
		System.out.println("Back to Main");
		sceneChanger.fadeOut(Option, Option.getScene(), "main");
	}
}