package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LoadImage extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start (Stage primaryStage){
        primaryStage.setTitle("Load Image");

        StackPane sp = new StackPane();
        Image image = new Image("file:./image/background.jpeg");
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(480);
        imgView.setFitWidth(800);
        sp.getChildren().add(imgView);

        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}