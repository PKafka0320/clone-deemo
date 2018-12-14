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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OptionController implements Initializable {
	
	private FadeTransition ft = new FadeTransition(Duration.millis(1500));
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	Option.setOpacity(0.5);
    	FadeIn();
    }

    @FXML
    private AnchorPane Option;

    @FXML
    private Slider VolumeSlide;

    @FXML
	private void BackToMain(MouseEvent m) throws IOException {
		System.out.println("Back to Main");
		toMainFadeOut();
	}
    
    void FadeIn() {
		ft.setNode(Option);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
    
    void toMainFadeOut() {
    	ft.setNode(Option);
		ft.setFromValue(1);
		ft.setToValue(0);
		
		ft.setOnFinished((ActionEvent event) -> {
			try {
				LoadMain();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		ft.play();
	}
    
    private void LoadMain() throws IOException {
		try {
			Parent MainParent;
			MainParent = (AnchorPane) FXMLLoader.load(getClass().getResource("main.fxml"));
			
			Scene MainScene = new Scene(MainParent);
			
			Stage MainStage = (Stage) Option.getScene().getWindow();
			MainStage.setScene(MainScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}