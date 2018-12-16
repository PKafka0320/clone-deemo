package application;
	
//import java.io.File;
//import java.net.URL;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage main) throws IOException {
		main.setResizable(false);
//		File file = new File(System.getProperty("user.dir") + "/asset/fxml/" + "main" + ".fxml");
//		String filePath = file.toURI().toString();
//		Parent root = FXMLLoader.load(new URL(filePath));

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("asset/fxml/" + "main" + ".fxml"));
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