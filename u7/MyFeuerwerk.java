//@author: Yichi Chen

package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MyFeuerwerk implements Shape, Animation {
	double radius;
	Point center;
	Color color;
	ShapesWorld theWorld;
        double r = 2.0;
        double vel = 5.0;
        int num = 60;

	public MyFeuerwerk(){
                super();
                this.radius = 6;                
                this.center = new Point(0,0);
                this.color = Color.red;
	}
	
	public MyFeuerwerk(Point center){
                super();
                this.radius = 6;                
                this.center = center;
                this.color = Color.red;
	}

	public void draw(Graphics g) {
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
                this.theWorld = theWorld;
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
                theWorld.removeShape(this);

                Stein[] stList = new Stein[num];
                Point tempCenter;
                Color tempColor;
                double tempVel;
                double radian = 0.0;
    
                tempColor = getRandomColor();
                tempVel = getRandomVel();

                for (int i = 0; i < num; i++)
                {
                    radian += (2.0 * Math.PI) / num;
                    tempCenter = new Point(center.x+1.0*Math.cos(radian), center.y+1.0*Math.sin(radian));
                    stList[i] = new FeuerwerkStueck(tempCenter, r, r, tempColor,
                                                tempVel*Math.cos(radian), 
                                                tempVel*Math.sin(radian), 
                                                100000);
                    theWorld.addShape(stList[i]);
                }
	}


        public double getRandomVel() {
            return (vel + 2*Math.random()*vel)/3.0;
        }


        public Color getRandomColor() {
            Random rand = new Random();
            Color res = null;
            int n = rand.nextInt(5);
            switch (n)
            {
                case 0:
                        res = Color.red; break;
                case 1:
                        res = Color.green; break;            
                case 2:
                        res = Color.yellow; break;
                case 3:
                        res = Color.cyan; break;
                case 4: 
                        res = Color.white; break;
            }
            return res;
        }
	
}// end of class MyFeuerwerk

