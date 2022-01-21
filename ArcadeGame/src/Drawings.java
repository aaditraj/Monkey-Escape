
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import java.awt.BorderLayout;

public class Drawings {

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(screenSize.width, screenSize.height);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
	

	
}
