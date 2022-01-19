
public class Leaderboard {
	private DrawingSurface surface;
//	BufferedReader reader;
	
	public Leaderboard(DrawingSurface surface) {
		//USE FILEREADER, FILEWRITER, BUFFEREDWRITER TO GET DATA FROM FILE AND ALSO EDIT FILE 
		this.surface = surface;
	}
	
	public void draw()
	{
		surface.push(); 
		surface.background(surface.color(173,216,230));
		surface.fill(surface.color(144,238,144));
		surface.rect(0,0,50,surface.height);
		surface.rect(surface.width-50,0,50,surface.height);
		
		
		surface.pop(); 
	}
	
	public void nextList()
	{
		
	}
}