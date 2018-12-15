package sceneController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	
	private Scanner fileScan;
	
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
    	
    	try {
    		File noteFile = new File(System.getProperty("user.dir") + "/asset/option/setting.txt");
    		fileScan = new Scanner(noteFile);

			while (fileScan.hasNextLine()) {
				String[] settingName = fileScan.nextLine().split("#");
				String value = settingName[1];
				char[] check = settingName[0].toCharArray();
				
				if(check[0] == 'V') {
					VolumeSlide.setValue(Double.valueOf(value));
				}
				if(check[0] == 'Y') {
					SyncSlide.setValue(Double.valueOf(value));
				}
				if(check[0] == 'S') {
					SpeedSlide.setValue(Double.valueOf(value));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
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
    private void optionSet(MouseEvent m)throws IOException {
    	File file = new File(System.getProperty("user.dir") + "/asset/option/setting.txt");
    	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    	
    	if(file.isFile() && file.canWrite()) {
    		String vValue = String.valueOf((int) VolumeSlide.getValue());
    		String yValue = String.valueOf((int) SyncSlide.getValue());
    		String sValue = String.valueOf((int) SpeedSlide.getValue());
    		
    		bw.write("V#" + vValue);
    		bw.newLine();
    		bw.write("Y#" + yValue);
    		bw.newLine();
    		bw.write("S#" + sValue);
    		bw.close();
    	}
    }
}