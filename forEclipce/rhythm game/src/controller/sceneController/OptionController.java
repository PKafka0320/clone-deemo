package sceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OptionController implements Initializable {
	
	SceneChanger sceneChanger = new SceneChanger();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	Option.setOpacity(0.5);
    	sceneChanger.fadeIn(Option);
    	VolumeSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				VolumeValue.textProperty().setValue(
                        String.valueOf((int) VolumeSlide.getValue()));
				
			}
        });
    	SyncSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				SyncValue.textProperty().setValue(
                        String.valueOf((int) SyncSlide.getValue()));
				
			}
        });
    	SpeedSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				SpeedValue.textProperty().setValue(
                        String.valueOf((int) SpeedSlide.getValue()));
				
			}
        });
    }

    @FXML
    private AnchorPane Option;

    @FXML
    private Slider VolumeSlide;
    
    @FXML
    private Label VolumeValue;
    
    @FXML
    private Slider SyncSlide;
    
    @FXML
    private Label SyncValue;
    
    @FXML
    private Slider SpeedSlide;
    
    @FXML
    private Label SpeedValue;
    
    @FXML
    private ImageView BacktoMain;

    @FXML
	private void BackToMain(MouseEvent m) throws IOException {
		System.out.println("Back to Main");
		sceneChanger.fadeOut(Option, Option.getScene(), "main");
	}
}