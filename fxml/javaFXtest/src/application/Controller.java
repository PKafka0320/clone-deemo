package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {

	Image OptionBtn = new Image("file:asset/image/OptionButton.png");
	Image SongsBtn = new Image("file:asset/image/SongsButton.png");

	@FXML
	private ImageView Songs;

	@FXML
	private ImageView Option;

	@FXML
	private void KeyboardInput(KeyEvent event) {
		System.out.println(event);
		if (event.getCode() == KeyCode.F) {
			System.out.println("Select SongsButton");
		}
	}

	@FXML
	void SongsPressed(MouseEvent m) {
		Songs.setImage(OptionBtn);
		System.out.println("pressed");
	}

	@FXML
	void SongsReleased(MouseEvent m) {
		Songs.setImage(SongsBtn);
		System.out.println("released");
	}

}
