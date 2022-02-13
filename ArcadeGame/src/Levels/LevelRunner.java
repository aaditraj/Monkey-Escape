package Levels;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class LevelRunner {
	static PApplet drawing;
	public static void main(String args[]) {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		drawing = new Level1();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		
		window.setSize(1500, 1500);
		if (drawing instanceof Level3) {
			window.setSize(1050, 1500);
		}
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
}
