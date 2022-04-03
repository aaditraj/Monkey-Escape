package startpage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class Leaderboard {
	HashMap<Integer,ArrayList<String>> leaderboard;
	PImage backArrow;
	HashSet<String> names;
	ArrayList<Integer> points;
	ArrayList<String> allNames;
	boolean completed = false;
	String currName;
	int time = 0;
	int dispNum = 0;
	int frequency = 10;
	//int startPos = 0;
	int currPos = 0;
//	BufferedReader reader;
	
	public Leaderboard() {
		leaderboard = new HashMap<>();
		names = new HashSet<>();
		points = new ArrayList<>();
		allNames = new ArrayList<>();
		readData();
		writeData();
		constructPoints();
		constructNames();
		constructAllNames();
	}
	public boolean containsName(String name) {
		return names.contains(name);
	}
	public void reset() {
		//System.out.println("inside");
		currName = "";
		currPos = 0;
		time = 0;
		dispNum = 0;
		completed = false;
		
	}
	public void complete(int points, String name, int currPos) {
		completed = true;
		currName = processName(name,points);
		//System.out.println(currPos);
		if(currPos > 7) {
			this.currPos = Integer.max(0, currPos - 3);
		}
	}
	public void draw(PApplet surface)
	{
		int length = 0;
		surface.push(); 
		backArrow = surface.loadImage("assets/backArrow.png");
		surface.image(surface.loadImage("assets/Backgrounds/forest2.jpg"), 0, 0,surface.width, surface.height);
		surface.image(backArrow, 50, 50, surface.width/18, surface.height/15);

		surface.textSize(60);
		//surface.text(currName,10,300);
		surface.fill(surface.color(255,255,255));
		surface.text("High Scores", surface.width/3, surface.height * 0.1f);
		
		surface.fill(surface.color(255,255,255));
		surface.textFont(surface.createFont("assets/ARCADE_N.TTF", 50));
		if(allNames.size() > Integer.min(dispNum,7)) {
			length = Integer.min(dispNum,7);
		} else {
			length = allNames.size();
		}
		//System.out.println(currPos + " " + length + " " + allNames.size());
		surface.text("Rank"+ "    " + "Name" +  "    " + "Points", surface.width/6, 100 + surface.height * 0.1f);
		for(int i = currPos; i < Integer.min(allNames.size(),length+currPos); i++) {
			String name = allNames.get(i);
			if(completed && name.equals(currName)) {
				surface.fill(surface.color(255,0,0));
			}	
			surface.text((i+1)+ "       " + name, surface.width/6, (i-currPos+1)*100 + surface.height * 0.2f );
			surface.fill(surface.color(255,255,255));
		}
		surface.textSize(30);
		surface.fill(surface.color(0,0,0));
		surface.text("Copyright 2020 AAJ Corp", surface.width/3, surface.height * 0.95f);
		surface.pop(); 
		if(time%frequency == 0) {
			dispNum++;
		}
		time++;
	}
	public String processName(String name, int points) {
		return name + "   " + points;
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
	public void readData() {
		try {
			FileInputStream stream = new FileInputStream("data/leaderboard.dat");
			ObjectInputStream reader = new ObjectInputStream(stream);
			Object tempObject = reader.readObject();
			if(tempObject != null) {
				leaderboard = (HashMap) tempObject;
			}
			
		} catch (Exception e) {
		}
	}
	public void add(int val, String name)
	{
		if(!names.contains(name)) {
			if(!leaderboard.containsKey(val)) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(name);
				leaderboard.put(val,list);
			} else {
				leaderboard.get(val).add(name);
			}
			constructNames();
			constructPoints();
			constructAllNames();
			System.out.println(names);
			System.out.println(points);
			System.out.println(allNames);
			System.out.println(leaderboard);
			
		} else {
			System.out.println("Please enter a unique name.");
		}
		writeData();
	}
	public void constructNames() {
		names = new HashSet<>();
		for(int i = 0; i < points.size(); i++) {
			names.addAll(leaderboard.get(points.get(i)));
		}
	}
	public void constructPoints() {
		points = new ArrayList<>();
		//System.out.println(leaderboard + " " +leaderboard.keySet());
		points.addAll(leaderboard.keySet());
		Collections.sort(points,Comparator.reverseOrder());
	}
	public void constructAllNames() {
		allNames = new ArrayList<>();
		for(int i = 0; i < points.size();i++) {
			int key = points.get(i);
			ArrayList<String> names = leaderboard.get(key);
			for(int j = 0; j < names.size(); j++) {
				allNames.add(processName(names.get(j),key));
			}
		}
	}
	public int checkClicked(int mouseX, int mouseY, int width, int height)
	{
		
		
		if(mouseX > 50 && mouseX < 50 + width/18)
		{
			if(mouseY > 50 && mouseY < 50 + height/15) {
				this.reset();
				return GameStatus.MAIN_MENU; 
			}
		}
		
		return GameStatus.LEADERBOARD;
		
	}
}





