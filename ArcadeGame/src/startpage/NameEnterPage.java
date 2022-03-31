package startpage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PImage;
public class NameEnterPage {
	int currPoints;
	StringBuilder currName = new StringBuilder("AAA");
	PImage backArrow;
	File location;
	int time = 0;
	int frequency = 10;
	boolean displayFlash = false;
	boolean finished = false;
	Leaderboard board;
	int points;
	int position = 0;
	public NameEnterPage(int points, Leaderboard board) {
		this.points = points;
		this.board = board;
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
		surface.text(currName.toString(), surface.width/3, surface.height * 0.1f+(line*100));
		if(time%frequency == 0) {
			displayFlash = !displayFlash;
		}
		time++;
		surface.pop();
	}

	public void interpretKeystroke(char keyCode, int position) {
		if(keyCode == PApplet.UP) {
			if(currName.charAt(position) == 65) {
				currName.setCharAt(position,'Z');
			} else {
				currName.setCharAt(position,(char)(keyCode-1));
			}
		}
		if(keyCode == PApplet.DOWN) {
			if(currName.charAt(position) == 90) {
				currName.setCharAt(position,'A');
			} else {
				currName.setCharAt(position,(char)(keyCode+1));
			}
		}
		
		if(keyCode == PApplet.LEFT) {
			if(position > 0) {
				position--;
			}
		}
		if(keyCode == PApplet.DOWN) {
			if(position < 2) {
				position++;
			}	
		}
		if(keyCode == PApplet.ENTER) {
			board.add(currPoints, currName.toString());
			finished = true;
		}
	}
	public boolean isFinished() {
		return finished;
	}
	public int getPosition() {
		Integer[] pointArr = new Integer[board.leaderboard.size()];
		board.leaderboard.keySet().toArray(pointArr);
		int pos = 1;
		for(int i = 0; i < pointArr.length; i++) {
			if(pointArr[i] > points) {
				return pos;
			}
			pos += board.leaderboard.get(pointArr[i]).size();
		}
		return pos;
	}
}
