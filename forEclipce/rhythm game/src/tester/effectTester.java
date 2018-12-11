package tester;

import effectDrawer.ComboEffect;
import etc.CollisionTypeFormat;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class effectTester extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
        final double W = 800;
        final double H = 800;

        final Canvas canvas = new Canvas(W, H);
        stage.setScene(new Scene(new Group(canvas)));
        
        ComboEffect cEffect = new ComboEffect(canvas, 30, new CollisionTypeFormat(3, 2) );
        
        cEffect.start();
        
        stage.show();
		
	}
	

}
