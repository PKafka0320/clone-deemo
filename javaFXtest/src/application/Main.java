package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.event.EventHandler;

public class Main extends Application {
	@Override
	public void start(Stage main) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		Scene scene01 = new Scene(root);
		
		main.setScene(scene01);
		main.show();
		
//		scene01.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent e) {
//				if(e.getCode() == KeyCode.LEFT) {
//					System.out.println("Select Songs");
//				}
//			}
//		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}