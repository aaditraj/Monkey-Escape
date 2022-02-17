package startpage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Leaderboard implements Serializable {
	private HashMap<String,Integer> leaderboard;
	private HashMap<Integer, ArrayList<String>> pointSet;
	PImage backArrow;
	public String currName = "";
//	BufferedReader reader;
	
	public Leaderboard() {
		leaderboard = new HashMap<>();
		pointSet = new HashMap<>();
		readData();
		leaderboard.put("cadi",100);
		writeData();
	}
	
	public void draw(PApplet surface)
	{
		surface.push(); 
		backArrow = surface.loadImage("assets/backArrow.png");
		surface.image(surface.loadImage("assets/Backgrounds/forest2.jpg"), 0,0, surface.width,surface.height);
		surface.image(backArrow, 50, 50, surface.width/18, surface.height/15);

		surface.textSize(60);
		surface.text(currName,10,300);
		surface.fill(surface.color(255,255,255));
		surface.text("Leaderboard", surface.width/3, surface.height * 0.1f);
		
		surface.fill(surface.color(255,255,255));
		surface.textFont(surface.createFont("assets/ARCADE_N.TTF", 50));
		Set<String> keys = leaderboard.keySet();
		String[] arr = new String[keys.size()];
		keys.toArray(arr);
		for(int i = 0; i < arr.length; i++) {
			ArrayList<String> names = pointSet.get(leaderboard.get(arr[i]));
			if(names != null) {
				names.add(arr[i]);
			} else {
				names = new ArrayList<String>();
				names.add(arr[i]);
			}
			
			pointSet.put(leaderboard.get(arr[i]),names);
		}
		Integer[] pointsArr = new Integer[pointSet.size()];
		pointSet.keySet().toArray(pointsArr);
		Arrays.sort(pointsArr,Collections.reverseOrder());
		for(int i = 0; i < pointsArr.length; i++) {
			Integer key = pointsArr[i];
			ArrayList<String> names = pointSet.get(key);
			for(int j = 0; j < names.size(); j++) {
				surface.text((i+1)+". "+names.get(j) + "     " + key, surface.width/3, surface.height * 0.25f + i*100);
			}
			
		}
		
		
		
		/*for(int i = 0; i<data.size(); i++)
			surface.text("" + data.get(i), surface.width/3, surface.height * 0.25f + i*100);
		
		for(int k = 0; k<usernames.size(); k++)
			surface.text("" + usernames.get(k), surface.width/2, surface.height * 0.25f + k*10);
		*/
		//System.out.println(data);
		surface.pop(); 
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
			this.leaderboard = (HashMap)reader.readObject();
			
		} catch (Exception e) {
			System.out.println("Could not serialize leaderboard");
			e.printStackTrace();
		}
	}
	
	public void add(int val, String name)
	{
		if(!leaderboard.containsKey(name)) {
			leaderboard.put(name, val);
		} else {
			System.out.println("Please enter a unique name.");
		}
		writeData();
	}
	
	public String checkClicked(int mouseX, int mouseY, int width, int height)
	{
		
		
		if(mouseX > 50 && mouseX < 50 + width/18)
		{
			if(mouseY > 50 && mouseY < 50 + height/15) return "Main Menu"; 
		}
		
		return "Leaderboard";
		
	}
}





