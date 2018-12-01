package test;

import javax.swing.*;
import java.awt.event.*;

public class getLocation {
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
		
		int x = 0;
		int y = 0;
		int gravity = 3;
		int accelX = 0;
		int accelY = 0;
		int valX = 0;
		int valY = 0;
		
		
		int boundValue = 10;
		int frictionValue = 5;
		int airFriction = 30; // val*(accel)/airFriction

		baseFrame.setLocation(20, 20);
		while(true){
			currentT = System.currentTimeMillis();
			if(currentT - lastT > 1000/60) {
				lastT = currentT;
				
				x = baseFrame.getX();
				y = baseFrame.getY();
				
				if(x+valX < 0) {
					valX = Math.abs(valX);// - boundValue;
					accelX = Math.abs(accelX);
				}
				else if(x+valX >= MAX_X) {
					valX = -Math.abs(valX);// + boundValue;
					accelX = -Math.abs(accelX);
				}
				else if(y+valY < 0) {
					valY = Math.abs(valY) - boundValue;
					accelY = Math.abs(accelY);
				}
				else if(y+valY >= MAX_Y) {
					valY = -Math.abs(valY) + boundValue;
					accelY = -Math.abs(accelY);
				}
				
				valX += accelX;
				if(valX > airFriction || valX < -airFriction) valX -= accelX;
				valY += accelY + gravity;
				if(valY > airFriction || valY < -airFriction) valY -= accelY;
				
				if( ( -BOX_SIZE_X < x && x < 10 ) || ( MAX_X-10 < x && x < MAX_X+BOX_SIZE_X ) ) {
					if(valY < -frictionValue) valY += frictionValue;
					else if(valY > frictionValue) valY -= frictionValue;
					else valY = 0;
				}
				if( ( -BOX_SIZE_Y < y && y < 10 ) || ( MAX_Y-10 < y && y < MAX_Y+BOX_SIZE_Y ) ) {
					if(valX < -frictionValue) valX += frictionValue;
					else if(valX > frictionValue) valX -= frictionValue;
					else valX = 0;
				}
				
				
				if(x >= 0 && x+valX <= MAX_X) x += valX;
				if(y >= 0 && y+valY <= MAX_Y) y += valY;
				
				
				baseFrame.setLocation(x, y);
				System.out.println(x+" "+y+" "+valX+" "+valY);
			}
		}
	}
}