package startpage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PImage;
public class NameEnterPage {
	int currPoints;
	String currName;
	PImage backArrow;
	File location;
	int time = 0;
	int frequency = 2;
	boolean displayFlash = false;
	private HashMap<Integer,ArrayList<String>> leaderboard;
	int points;
	public NameEnterPage(int points) {
		readData();
		this.points = points;
	}
	public void draw(PApplet surface) {
		surface.push();
		backArrow = surface.loadImage("assets/backArrow.png");
		surface.background(0);
		surface.image(backArrow, 50, 50, surface.width/18, surface.height/15);
		surface.textSize(60);
		int line = 0;
		int position = getPosition();
		if(position == 1) {
			if(displayFlash) {
				surface.text("New High Score", surface.width/3, surface.height * 0.1f+(line*100));
			}
			line++;
		}
		if(displayFlash) {
			surface.text("Rank: " + " " + position, surface.width/3, surface.height * 0.1f+(line*100));
		}
		line++;
		surface.text("Points: " + " " + points, surface.width/3, surface.height * 0.1f+(line*100));
		line++;
		
		if(time%frequency == 0) {
			displayFlash = !displayFlash;
		}
		time++;
	}
	public void writeData() {
		try {
			FileOutputStream stream = new FileOutputStream("data/leaderboard.dat");
			ObjectOutputStream writer = new ObjectOutputStream(stream);
			writer.writeObject(leaderboard);
			writer.close();
		} catch (Exception e) {
			System.out.println("Could not serialize leaderboard");
			e.printStackTrace();
		}
	}
	public int getPosition() {
		Integer[] pointArr = new Integer[leaderboard.size()];
		leaderboard.keySet().toArray(pointArr);
		int pos = 1;
		for(int i = 0; i < pointArr.length; i++) {
			if(pointArr[i] > points) {
				return pos;
			}
			pos += leaderboard.get(pointArr[i]).size();
		}
		return pos;
	}
	public void readData() {
		try {
			FileInputStream stream = new FileInputStream("data/leaderboard.dat");
			ObjectInputStream reader = new ObjectInputStream(stream);
			this.leaderboard = (HashMap)reader.readObject();
		} catch (Exception e) {
		}
	}
}
