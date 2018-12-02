package sample;

import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    public static void main(String[] args)  {

        JFXPanel panel = new JFXPanel();

        JFrame f = new JFrame("Main Window");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setSize(200,200);
        f.setVisible(true);

        String s = System.getProperty("user.dir");
        Media m = new Media("file:" + s + "/asset/music/SuspensefulThirdDay.mp3"); // file:" + s + "/music/SuspensefulThirdDay.mp3
        MediaPlayer p = new MediaPlayer(m);
        while(true){
            p.play();
        }
    }
}
