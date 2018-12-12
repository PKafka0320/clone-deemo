package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage main) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		Scene scene01 = new Scene(root);
		
		main.setScene(scene01);
		main.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
