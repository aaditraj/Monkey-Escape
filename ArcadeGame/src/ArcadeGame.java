
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Levels.Level3;
import Levels.LevelSwitch;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import java.awt.BorderLayout;

public class ArcadeGame {

	public static void main(String args[]) {
		LevelSwitch drawing = new LevelSwitch();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		
		window.setSize(1000, 1000);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
	

	
}
