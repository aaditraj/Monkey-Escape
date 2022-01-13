import processing.core.PApplet;

public class House {
	
	//Fields
	private int x, y, width, height;	
//	private Line leftRoof, rightRoof;
	


	// Constructors
	public House() {
		x = 250;
		y = 175;
		width = 200;
		height = 200;
//		leftRoof = new Line(x, y - (height * 5 / 8),  x - (width/2), y - (height * 2/5));
//		rightRoof = new Line(x, y - (height * 5 / 8), x + (width/2), y - (height * 2/5));
	}
	
	
	// Methods
	public void draw(PApplet surface) {
		Line leftRoof = new Line(x, y - (height * 5 / 8),  x - (width/2), y - (height * 2/5));
		Line rightRoof = new Line(x, y - (height * 5 / 8), x + (width/2), y - (height * 2/5));
		leftRoof.draw(surface); // Left part of roof
		rightRoof.draw(surface); // Right part of roof
		surface.line(x - (width/2), y - (height * 2/5), x + (width/2), y - (height * 2/5)); // Base of roof
		surface.triangle(x, y - (height * 5 / 8), x - (width/2), y - (height * 2/5), x + (width/2), y - (height * 2/5)); // Fill in the roof
		
		surface.rect(x - (width/2), y - (height * 2/5), width, height * 31 / 40); // House 
		surface.rect(x- (width/8), y, width/4, height * 3 / 8); // Door
		surface.rect(x - (width * 2 / 5), y - height/4, width/5, height/5); // Window
		surface.rect(x + width/5, y - height/4, width/5, height/5); // Window
	}
	
	public void move(int mouseX, int mouseY) {
		
		this.x = mouseX;
		this.y = mouseY;
		
	}
	
	public void scale(boolean scaleUp) {
		if (scaleUp) {
			width += 10;
			height += 10;
			System.out.println("width: " + width + ", height:" + height);
		} else {
			if ((width >= 10) && (height >= 10)) { // Decrease only if width and height won't go below 0 
				width -= 10;
				height -= 10;
			}
			System.out.println("width: " + width + ", height:" + height);
		}
		
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}
	
	
	public Line getLeftRoof() {
		return new Line(x, y - (height * 5 / 8),  x - (width/2), y - (height * 2/5));
	}


	public Line getRightRoof() {
		return new Line(x, y - (height * 5 / 8), x + (width/2), y - (height * 2/5));
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	

}
