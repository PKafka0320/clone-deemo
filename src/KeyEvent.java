package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class KeyEvent extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start (Stage primaryStage){
        primaryStage.setTitle("Load Image");

        StackPane sp = new StackPane();
        Image image = new Image("file:./asset/image/background.jpeg");
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(480);
        imgView.setFitWidth(800);
        sp.getChildren().add(imgView);

        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCharacter()){
                    case "s" : System.out.println("S"); break;
                    case "d" : System.out.println("D"); break;
                    case "f" : System.out.println("F"); break;
                    case "j" : System.out.println("J"); break;
                    case "k" : System.out.println("K"); break;
                    case "l" : System.out.println("L"); break;
                }
            }
        });
    }
}
