import java.util.ArrayList;

public class Leaderboard {
	private ArrayList<Double> data;
	private ArrayList<String> usernames;
	private DrawingSurface surface;
//	BufferedReader reader;
	
	public Leaderboard(DrawingSurface surface) {
		//USE FILEREADER, FILEWRITER, BUFFEREDWRITER TO GET DATA FROM FILE AND ALSO EDIT FILE 
		this.surface = surface;
		data = new ArrayList<Double>();
		usernames = new ArrayList<String>();
		readData();
		readUsernames();
	
		
	}
	
	public void draw()
	{
		surface.push(); 
		surface.background(surface.color(173,216,230));
		surface.fill(surface.color(144,238,144));
		surface.noStroke();
		surface.rect(0,0,surface.width * 0.2f,surface.height);
		surface.rect(surface.width-surface.width*0.2f,0,surface.width * 0.2f,surface.height);
		
		surface.textSize(100);
		
		surface.fill(surface.color(0,0,0));
		surface.text("Leaderboard", surface.width/3, surface.height * 0.1f);
		
		surface.fill(surface.color(246,190,0));
		for(int i = 0; i<data.size(); i++)
			surface.text("" + data.get(i), surface.width/3, surface.height * 0.25f + i*10);
		
		for(int k = 0; k<usernames.size(); k++)
			surface.text("" + usernames.get(k), surface.width/2, surface.height * 0.25f + k*10);
		
		System.out.println(data);
		surface.pop(); 
	}
	
	public void readList()
	{
		
	}
	
	public void readData() {
		ArrayReader reader = new ArrayReader("data/HighScores.txt");
		reader.fillDataArray(this);
	
	}
	
	public void readUsernames() {
		ArrayReader reader = new ArrayReader("data/Username.txt");
		reader.fillStringArray(this);
	
	}
	
	public void add(double val)
	{
		data.add(val);
	}
	
	public void add(String name)
	{
		usernames.add(name);
	}
}





