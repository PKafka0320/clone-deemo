package test;

import javax.swing.*;
import java.awt.event.*;

public class DrawScreen {
	public static void main(String[] args) {
		
		
		JFrame baseFrame = new JFrame();
		baseFrame.setSize(800, 480);
		baseFrame.setLayout(null);
		
		baseFrame.setVisible(true);
		//0 ~ 1130, 0 ~ 570
		
		int x = 0;
		int y = 0;
		
		double degree = Math.random()*90 + 270;
		System.out.println(degree);
		int dx = (int)(Math.cos((degree)*(Math.PI)/180)*10);
		int dy = -(int)(Math.sin((degree)*(Math.PI)/180)*10);
		System.out.println(dx+" "+dy);

		baseFrame.setLocation(1, 1);
		while(true){
			//System.out.println(x+", "+y+", "+dx+", "+dy+", "+degree);
			
			//System.out.println(baseFrame.getLocation());
			
			if(x < 0) {
				degree = Math.random()*180 + 270;
			}
			else if(x >= 1130) {
				degree = Math.random()*180 + 90;
			}
			else if(y < 0) {
				degree = Math.random()*180;
			}
			else if(y >= 570) {
				degree = Math.random()*180 + 180;
			}
			
			dx = (int)(Math.cos((degree)*(Math.PI)/180)*10);
			dy = (int)(Math.sin((degree)*(Math.PI)/180)*10);
			x = x+dx;
			y = y+dy;
			
			baseFrame.setLocation(x, y);
		}
	}
}
