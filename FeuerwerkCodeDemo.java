
/* ---------------------------  Feuerwerk-Class ------------------------ */

package u7;

/**
 * @author Yichi Chen
 * @version 1.01
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

public class Feuerwerk implements Shape, Animation {

    double radius;
    Point center;
    Random rand;

    Color color;
    ShapesWorld theWorld;
    double velocity = 4;
    boolean start = false;

    int liveSteps = 200;
    int runningSteps = 0;


    public Feuerwerk() {
           this.radius = 25;
           this.color = Color.CYAN; 
           this.center = new Point(0, 0);
           this.rand = new Random();
    }

    public Color getColor()
    { return color; }

    public void moveTo(double x, double y){
                center.x = (int) x;
                center.y = (int) y;
    }

    public void setShapesWorld( ShapesWorld theWorld )
    { 
           this.theWorld = theWorld;
    }   

    public void draw(Graphics g) {
    }

    public Point getCenter() {
           return center;
    }

    public void userClicked(double atX, double atY){ 
        System.out.println("Feuer!");
        center.x = atX;
        center.y = atY;
        start = true;
    }

    public void userTyped(char key){
           System.out.println("key");
           color = Color.black;
    }

    // implement the Animation-Interface
    public void play()
    {
        if (start)
        {
            System.out.println("Feuer!");
            MyFeuerwerk fw = new MyFeuerwerk(center);
            theWorld.addShape(fw);
            start = false;
        }
    }

    public boolean contains(double x, double y) {
        return true;
    }
    
    public double getRadius() {
            return radius;
    }

} // end of class Feuerwerk









/* ------------------------------ MyFeuerwerk-Class --------------------------------*/

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












/* -------------------------------- FeuerwerkStueck-Class ----------------------- */

//@author: Yichi Chen

package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class FeuerwerkStueck extends Stein {
    private double velocityX;
    private double velocityY;
    private Color color;
    private Point point;
    
    public double velocityYIncrement = 0.15;
    public int liveSteps;
    private int runningSteps = 0;
    ShapesWorld theWorld;

   public FeuerwerkStueck(Point center, double radiusX, double radiusY, 
                    Color color, double velocityX, double velocityY, int liveSteps) {
        super();
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.liveSteps = liveSteps;
    }

    public FeuerwerkStueck(double x, double y, double radiusX, double radiusY, 
                    Color color, double velocityX, double velocityY, int liveSteps) {
        super();
        this.center.x = x;
        this.center.y = y;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.liveSteps = liveSteps;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        fillStein(g, center.x-radiusX, center.y-radiusY);
    }

    @Override
    public void play() {
        if (runningSteps <= liveSteps)
        {
            center.x += velocityX;
            center.y += velocityY;
            velocityY += velocityYIncrement;
            runningSteps += 1;
        }
        else
        {
        }
    }

}// end of class FeuerwerkStueck











