import processing.core.PApplet;

public class Person {
	
	//Fields
		private int x, y, width, height;
		
		
		// Constructors
		public Person() {
			x = 50;
			y = 50;
			width = 40;
			height = 160;
		}
		
		// Methods
		
		public void draw(PApplet surface) {	
			surface.ellipse(x + width/2, y + height/8, width , height/4); // head
			surface.line(x + width/2, y + height/4, x + width/2, y + height*3/4); // body
			surface.line(x + width/2, y + height*3/4, x + width/4, y + height); // leg
			surface.line(x + width/2, y + height*3/4, x + (width * 3 / 4), y + height); // leg
			
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
				if ((width >= 10) && (height >= 10)) {
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
