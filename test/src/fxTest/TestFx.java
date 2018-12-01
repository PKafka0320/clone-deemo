package fxTest;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TestFx extends Application 
{
	public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
        theStage.setTitle("Hello, World!");
        theStage.setHeight(480);
        theStage.setWidth(800);
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
        Canvas canvas = new Canvas(400, 200);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        Image note = new Image( "file:./asset/image/note.png" );
        
        gc.drawImage( note, 180, 100 );
        
        theStage.show();
    }
}