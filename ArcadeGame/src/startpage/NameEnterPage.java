package startpage;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import processing.core.PApplet;
import processing.core.PImage;
public class NameEnterPage {
	StringBuilder currName = new StringBuilder("AAAAA");
	PImage backArrow;
	File location;
	int time = 0;
	int frequency = 10;
	boolean displayFlash = false;
	boolean finished = false;
	Leaderboard board;
	int points;
	int position = 0;
	int rank = 0;
	String positionStr = "_     ";
	public NameEnterPage(int points, Leaderboard board) {
		this.points = points;
		this.board = board;
	}
	public void draw(PApplet surface) {
		surface.push();
		backArrow = surface.loadImage("assets/backArrow.png");
		surface.image(surface.loadImage("assets/Backgrounds/forest2.jpg"), 0, 0,surface.width, surface.height);
		surface.image(backArrow, 50, 50, surface.width/18, surface.height/15);
		surface.textSize(60);
		int line = 0;
		rank = getPosition();
		if(rank == 1) {
			if(displayFlash) {
				surface.text("New High Score", surface.width/3, surface.height * 0.1f+(line*100));
			}
			line++;
		}
		surface.text("Rank: " + " " + rank, surface.width/3, surface.height * 0.1f+(line*100));
		line++;
		surface.text("Points: " + " " + points, surface.width/3, surface.height * 0.1f+(line*100));
		line++;
		surface.fill(surface.color(255,0,0));
		surface.text(currName.toString(), surface.width/3, surface.height * 0.1f+(line*100));
		surface.text(positionStr, surface.width/3, surface.height * 0.1f+(line*100)+50);
		surface.textSize(30);
		surface.fill(surface.color(255,255,0));
		surface.text("Copyright 2022 AAJ Corp", surface.width/3, (8)*100+surface.height * 0.15f);
		if(time%frequency == 0) {
			displayFlash = !displayFlash;
		}
		time++;
		surface.pop();
	}

	public void interpretKeystroke(char keyCode) {
		if(keyCode == PApplet.UP) {
			if(currName.charAt(position) == 65) {
				currName.setCharAt(position,'Z');
			} else {
				currName.setCharAt(position,(char)(currName.charAt(position)-1));
			}
		}
		if(keyCode == PApplet.DOWN) {
			if(currName.charAt(position) == 90) {
				currName.setCharAt(position,'A');
			} else {
				currName.setCharAt(position,(char)(currName.charAt(position)+1));
			}
		}
		
		if(keyCode == PApplet.LEFT) {
			if(position > 0) {
				position--;
				genPositionString();
			}
			
		}
		if(keyCode == PApplet.RIGHT) {
			if(position < 4) {
				position++;
				genPositionString();
			}
		}
		if(keyCode == PApplet.ENTER) {
			board.add(points, currName.toString());
			board.complete(points,currName.toString(),rank);
			finished = true;
		}
	}
	public void genPositionString() {
		String str= "";
		for(int i = 0; i < 5; i++) {
			if(i == position) {
				str+='_';
			} else {
				str += ' ';
			}
		}
		positionStr = str;
	}
	public boolean isFinished() {
		return finished;
	}
	public int getPosition() {
		Integer[] pointArr = new Integer[board.leaderboard.size()];
		board.leaderboard.keySet().toArray(pointArr);
		Arrays.sort(pointArr,Comparator.reverseOrder());
		int pos = 1;
		for(int i = 0; i < pointArr.length; i++) {
			if(pointArr[i] < points) {
				return pos;
			}
			pos += board.leaderboard.get(pointArr[i]).size();
		}
		return pos;
	}
}
