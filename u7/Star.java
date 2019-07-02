package u7;

import java.awt.Color;
import java.awt.Graphics;

public class Star implements Shape, Animation {
	double radius;
	Point center;
	Color color;
	ShapesWorld world;
	
	public Star(){
                super();
                this.radius = 50;               
                this.center = new Point(0,0);
                this.color = Color.black;
	}

        public Star(Point center) {
                super();
                this.radius = 50;
                this.center = center;
                this.color = Color.black;
        }

        public Star(Point center, double radius, Color color) {
                super();
                this.radius = radius;
                this.center = center;
                this.color = color;
        }

	public void draw(Graphics g) {
                int x, y, r, d;
                x = (int) center.x;
                y = (int) center.y;
                r = (int) radius;
                d = (int) (this.radius / 5.0);
                
		g.setColor(color);
                g.drawLine(x-r, y, x-d, y+d);
                g.drawLine(x-r, y, x-d, y-d);
                g.drawLine(x+r, y, x+d, y+d);
                g.drawLine(x+r, y, x+d, y-d);
                g.drawLine(x, y-r, x-d, y-d);
                g.drawLine(x, y-r, x+d, y-d);
                g.drawLine(x, y+r, x-d, y+d);
                g.drawLine(x, y+r, x+d, y+d); 
	}

	public boolean contains(double x, double y) {
		return false;
	}

	public double getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public Point getCenter() {
		return center;
	}

	public void setShapesWorld(ShapesWorld theWorld) {
         this.world = theWorld;
	}

	public void userClicked(double at_X, double at_Y) {        
	}

	public void userTyped(char key) {
	}

	public void moveTo(double x, double y) {
		center.x = x;
		center.y = y;
	}

	public void play() {
	}
	
}// end of class Footprint

