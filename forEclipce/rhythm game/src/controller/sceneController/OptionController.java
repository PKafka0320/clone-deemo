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
import reader.SettingReader;

public class OptionController implements Initializable {

	private SceneChanger sceneChanger = new SceneChanger();

	private SettingReader settingReader = new SettingReader();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Option.setOpacity(0.5);
		sceneChanger.fadeIn(Option);
		VolumeSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				VolumeValue.textProperty().setValue(String.valueOf((int) VolumeSlide.getValue()));

			}
		});
		SyncSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				SyncValue.textProperty().setValue(String.valueOf((int) SyncSlide.getValue()));

			}
		});
		SpeedSlide.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				SpeedValue.textProperty().setValue(String.valueOf((int) SpeedSlide.getValue()));

			}
		});

		double[] settingValues = settingReader.readSetting();
		VolumeSlide.setValue(settingValues[0]);
		SyncSlide.setValue(settingValues[1]);
		SpeedSlide.setValue(settingValues[2]);
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

	@FXML
	private void optionSet(MouseEvent m) throws IOException {
		double[] settingValues = new double[3];
		settingValues[0] = VolumeSlide.getValue();
		settingValues[1] = SyncSlide.getValue();
		settingValues[2] = SpeedSlide.getValue();
		settingReader.writeSetting(settingValues);
	}
}