package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {
	
	private FadeTransition ft = new FadeTransition(Duration.millis(1500));
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	main.setOpacity(0);
    	FadeIn();
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
		toSongListFadeOut();
	}

	@FXML
	void OptionPressed(MouseEvent m) throws IOException {
		System.out.println("Option Selected");
		toOptionFadeOut();
	}
	
	void FadeIn() {
		ft.setNode(main);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	void toSongListFadeOut() {
		ft.setNode(main);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		ft.setOnFinished((ActionEvent event) -> {
			try {
				LoadSongList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		ft.play();
	}
	
	void toOptionFadeOut() {
		ft.setNode(main);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		ft.setOnFinished((ActionEvent event) -> {
			try {
				LoadOption();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		ft.play();
	}
	
	private void LoadSongList() throws IOException {
		try {
			Parent SongListParent;
			SongListParent = (AnchorPane) FXMLLoader.load(getClass().getResource("SongList.fxml"));
			
			Scene SongListScene = new Scene(SongListParent);
			
			Stage SongListStage = (Stage) main.getScene().getWindow();
			SongListStage.setScene(SongListScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void LoadOption() throws IOException {
		try {
			Parent OptionParent;
			OptionParent = (AnchorPane) FXMLLoader.load(getClass().getResource("Option.fxml"));
			
			Scene OptionScene = new Scene(OptionParent);
			
			Stage OptionStage = (Stage) main.getScene().getWindow();
			OptionStage.setScene(OptionScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}