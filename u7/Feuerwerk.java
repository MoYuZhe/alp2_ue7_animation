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

