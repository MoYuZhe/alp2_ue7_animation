// @author: Yichi Chen

package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Stein implements Shape, Animation {
	double radiusX, radiusY;
        double velocity = 0.0;
        double velocityIncrement = 0.3;           // entspricht der Beschuleunigung wegen Gravitation
	Point center;
	Color color;
	ShapesWorld world;
        boolean notToGround = true;
	
	public Stein(){
                super();
                this.radiusX = 30;
                this.radiusY = 20;               
                this.center = new Point(0, -150);
                this.color = Color.cyan;
	}

        public Stein(Point center) {
                super();
                this.radiusX = 30;
                this.radiusY = 20;
                this.center = center;
                this.color = Color.cyan;
        }

        public Stein(Point center, double radiusX, double radiusY, Color color) {
                super();
                this.radiusX = radiusX;
                this.radiusY = radiusY;
                this.center = center;
                this.color = color;
        }

        public Stein(double x, double y, double radiusX, double radiusY, Color color) {
                super();
                this.center.x = x;
                this.center.y = y;
                this.radiusX = radiusX;
                this.radiusY = radiusY;
                this.color = color;
        }

	public void draw(Graphics g) {
                if (notToGround) 
                {
                    g.setColor(color);
                    fillStein(g, center.x-radiusX, center.y-radiusY);           
                }
                else 
                {
                    MiniStein st_mid, st_left, st_right;
                    double bottom = world.getMax_Y();
                    double centerY = bottom - radiusY/4;

                    st_mid = new MiniStein(center.x, centerY, radiusX/2, radiusY/2, color);
                    st_left = new MiniStein(center.x-50, centerY, radiusX/2, radiusY/2, color);
                    st_right = new MiniStein(center.x+75, centerY, radiusX/2, radiusY/2, color);

                    st_mid.draw(g);
                    st_left.draw(g);
                    st_right.draw(g);
                }
	}

        public void fillStein(Graphics g, double x, double y) {
                int[] x_coords = {(int)x, (int)(x+2*radiusX), (int)(x+2*radiusX), 
                                (int)(x+radiusX), (int)(x+0.33*radiusX)};
                int[] y_coords = {(int)(y+2*radiusY), (int)(y+2*radiusY), 
                                (int)(y+0.5*radiusY), (int)y, (int)(y+0.5*radiusY)};
                Polygon p = new Polygon(x_coords, y_coords, 5);
                g.fillPolygon(p);
        }

	public boolean contains(double x, double y) {
		return false;
	}

	public double getRadius() {
		return (radiusX+radiusY)/2;
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
            double downSide = center.y + radiusY;
            double max_Y = world.getMax_Y();
            if (notToGround) 
            {
                if (downSide >= max_Y)
                {
                    notToGround = false;
                }
                else 
                {
                    center.y += velocity;
                    velocity += velocityIncrement;
//                    downSide = center.y
                }
            }
            else
            {

            }
	}
	
}// end of class Stein

