package physics;

import javax.swing.*;

public class DrawWindow {

	public static void main(String[] args) {
		//0 ~ 2030, 0 ~ 1050
		//0 ~ 1830, 0 ~ 850
		final int BOX_SIZE_X = 200;
		final int BOX_SIZE_Y = 200;
		final int MAX_X = 1930 - BOX_SIZE_X;
		final int MAX_Y = 1050 - BOX_SIZE_Y;

		JFrame baseFrame = new JFrame();
		baseFrame.setSize(BOX_SIZE_X, BOX_SIZE_Y);
		baseFrame.setLayout(null);
		baseFrame.setVisible(true);
		
		long currentT;
		long lastT = 0;
		
		baseFrame.setLocation(20, 20);
		
		while(true){
			currentT = System.currentTimeMillis();
			if(currentT - lastT > 1000/60) {
				lastT = currentT;
				
				x = baseFrame.getX();
				y = baseFrame.getY();
				
				
				
				baseFrame.setLocation(x, y);
			}
	}

}
