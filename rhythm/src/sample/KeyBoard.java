package test;

import javax.swing.*;
import java.awt.event.*;

class KeyBoard {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(800,480);
		f.setLayout(null);
		
		class key implements KeyListener{

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == 's') System.out.println("s");
				if(e.getKeyChar() == 'd') System.out.println("d");
				if(e.getKeyChar() == 'f') System.out.println("f");
				if(e.getKeyChar() == 'j') System.out.println("j");
				if(e.getKeyChar() == 'k') System.out.println("k");
				if(e.getKeyChar() == 'l') System.out.println("l");
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		}
		
		f.setVisible(true);
		f.addKeyListener(new key());
	}
}