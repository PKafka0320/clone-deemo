package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eventProcesser.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {
	
	SceneChanger sceneChanger = new SceneChanger();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	main.setOpacity(0);
    	sceneChanger.fadeIn(main);
    }
    
	@FXML
	private AnchorPane main;
	
	@FXML
	private ImageView Songs;

	@FXML
	private ImageView Option;

	@FXML
	private void SongsPressed(MouseEvent m) throws IOException {
		System.out.println("Songs Selected");
		sceneChanger.fadeOut(main, main.getScene(), "SongList");
	}

	@FXML
	void OptionPressed(MouseEvent m) throws IOException {
		System.out.println("Option Selected");
		sceneChanger.fadeOut(main, main.getScene(), "Option");
	}
}