package startpage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import processing.core.PApplet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Leaderboard implements Serializable {
	private HashMap<String,Integer> leaderboard;
//	BufferedReader reader;
	
	public Leaderboard() {
		leaderboard = new HashMap<>();
		readData();
		leaderboard.put("cadi",100);
		writeData();
	}
	
	public void draw(PApplet surface)
	{
		surface.push(); 
		surface.background(surface.color(173,216,230));
		surface.fill(surface.color(144,238,144));
		surface.noStroke();
		surface.rect(0,0,surface.width * 0.2f,surface.height);
		surface.rect(surface.width-surface.width*0.2f,0,surface.width * 0.2f,surface.height);
		
		surface.textSize(60);
		
		surface.fill(surface.color(0,0,0));
		surface.text("Leaderboard", surface.width/3, surface.height * 0.1f);
		
		surface.fill(surface.color(246,190,0));
		surface.textFont(surface.createFont("assets/ARCADE_N.TTF", 50));
		Set<String> keys = leaderboard.keySet();
		Iterator<String> iter = keys.iterator();
		for(int i = 0;i < keys.size(); i++) {
			String key = iter.next();
			surface.text("" + key + "     " + leaderboard.get(key), surface.width/3, surface.height * 0.25f + i*100);
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
}





