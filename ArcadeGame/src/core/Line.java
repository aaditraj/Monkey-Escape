package core;
import processing.core.PApplet;

public class Line {
	
	private double x1, x2, y1, y2;
	private int R, G, B; // Color of the line, default is black
	private int strokeWeight; // Width of the line, default is 1
	
//  Constructs a line from (x1, y1) to (x2, y2)
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		R = 0;
		G = 0;
		B = 0;
		strokeWeight = 1;
		//test
	}
	
	public void setColor(int R, int G, int B) {
		this.R = R;
		this.G = G;
		this.B = B;
	}
	
	public void setStrokeWeight(int weight) {
		this.strokeWeight = weight;
	}
	  
//  Sets this line’s second point (x2, y2) to a new coordinate public void
	public void setPoint1(double x1, double y1) {
		this.x1 = x1;
		this.y1 = y1;
	}
	
//  Sets this line’s second point (x2, y2) to a new coordinate public void
	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
//  Draws this line using the PApplet passed as an argument 
	public void draw(PApplet drawer) {
		drawer.push(); // Use push and pop so custom settings don't get applied elsewhere
		drawer.stroke(R, G, B);
		drawer.strokeWeight(strokeWeight);
		drawer.line((float)x1, (float)y1, (float)x2, (float)y2);
		drawer.pop();
	}
	
	public double calcDenom(Line other) { // As part of formula to get intersection coordinates
		return (x1 - x2)*(other.getY1()-other.getY2()) - (y1-y2)*(other.getX1()-other.getX2());
	}
	
//  Returns the x coordinate of the intersection point of this line and the other line (assuming they continue forever) 
	public double getIntersectionX(Line other) {
		double numerator = (x1*y2 - y1*x2)*(other.getX1() - other.getX2()) - (x1 - x2)*(other.getX1()*other.getY2() - other.getY1()*other.getX2());
		return numerator/calcDenom(other);
	}
	
//  Returns the y coordinate of the intersection point of this line and the other line (assuming they continue forever) 
	public double getIntersectionY(Line other) {
		double numerator = (x1*y2 - y1*x2)*(other.getY1() - other.getY2()) - (y1 - y2)*(other.getX1()*other.getY2() - other.getY1()*other.getX2());
		return numerator/calcDenom(other);
	}
	
//  Returns true if this line segment and the segment other intersect each other. Returns false if they do not intersect. 
	public boolean intersects(Line other) {
		Double exactXIntersect = getIntersectionX(other);
		Double exactYIntersect = getIntersectionY(other);
//		System.out.println(exactXIntersect + " " + exactYIntersect);
		if (exactXIntersect.isInfinite() || exactYIntersect.isInfinite()) { // Parallel
			return false;
		} else if (exactXIntersect.isNaN() && exactYIntersect.isNaN()) { // Collinear
			return false;
			
		}
		int xIntersect = (int) ((double) exactXIntersect); // cast to int to accommodate roundoff error
		int yIntersect = (int) ((double) exactYIntersect); // cast to int to accommodate roundoff error
		int smallerX1 = (int) Math.min(x1, x2);
		int largerX1 = (int) Math.max(x1, x2);
		double smallerX2 = Math.min(other.getX1(), other.getX2());
		double largerX2 = Math.max(other.getX1(), other.getX2());
		double smallerY1 = Math.min(y1, y2);
		double largerY1 = Math.max(y1, y2);
		double smallerY2 = Math.min(other.getY1(), other.getY2());
		double largerY2 = Math.max(other.getY1(), other.getY2());
		boolean onLine1 = xIntersect >= smallerX1 && xIntersect <= largerX1 && yIntersect >= smallerY1 && yIntersect <= largerY1;
		boolean onLine2 = xIntersect >= smallerX2 && xIntersect <= largerX2 && yIntersect >= smallerY2 && yIntersect <= largerY2;
		
		
		
		return (onLine1 && onLine2);
	}

	public boolean isCollinear(Line other) {
		Double exactXIntersect = getIntersectionX(other);
		Double exactYIntersect = getIntersectionY(other);
		if (exactXIntersect.isNaN() && exactYIntersect.isNaN()) { // Collinear 
			
			if(getY1() == getY2()) {
				if( x1 > other.getX1() && x1 < other.getX2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("1");
					return true;
				} else if (x2 > other.getX1() && x2 < other.getX2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("2");
					return true;
				} else if (x1 <= other.getX1() && x2 >= other.getX2()) {
					return true;
				}
			} else {
				if(y1 > other.getY1() && y1 < other.getY2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("3");
					return true;
				} else if (y2 > other.getY1() && y2 < other.getY2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("4");
					return true;
				} else if (y1 <= other.getY1() && y2 >= other.getY2()) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean isCollinear(Line other, int direction, int maxSpeed) {
		Double exactXIntersect = getIntersectionX(other);
		Double exactYIntersect = getIntersectionY(other);
		if (exactXIntersect.isInfinite()) { // Parallel]
			if(direction == 0) {
				if((this.getY1() - other.getY1()) > 0 &&  (this.getY1() - other.getY1()) < maxSpeed) {
					return true;
				}
			} else if (direction == 2) {
				if((other.getY1() - other.getY1()) < 0 &&  (this.getY1() - other.getY1()) > -maxSpeed) {
					return true;
				}
			}
		} else if (exactYIntersect.isInfinite()) {
			if(direction == 1) {
				if((this.getX1() - other.getX1()) > 0 &&  (this.getX1() - other.getX1()) < maxSpeed) {
					return true;
				}
			} else if (direction == 3){
				if((other.getX1() - other.getX1()) < 0 &&  (this.getX1() - other.getX1()) > -maxSpeed) {
					return true;
				}
			}
		} else if (exactXIntersect.isNaN() && exactYIntersect.isNaN()) { // Collinear
			
			if(getY1() == getY2()) {
				if( x1 > other.getX1() && x1 < other.getX2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("1");
					return true;
				} else if (x2 > other.getX1() && x2 < other.getX2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("2");
					return true;
				} else if (x1 <= other.getX1() && x2 >= other.getX2()) {
					return true;
				}
			} else {
				if(y1 > other.getY1() && y1 < other.getY2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("3");
					return true;
				} else if (y2 > other.getY1() && y2 < other.getY2()) {
//					System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " Line 1");
//					System.out.println(other.getX1() + " " + other.getY1() + " " + other.getX2() + " " + other.getY2() + " Line 2");
//					System.out.println("4");
					return true;
				} else if (y1 <= other.getY1() && y2 >= other.getY2()) {
					return true;
				}
			}
		}
		return false;
	}
	public void changeX(double amount) {
		this.x1 += amount;
		this.x2 += amount;
	}
	
	public void changeY(double amount) {
		this.y1 += amount;
		this.y2 += amount;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
	
}
