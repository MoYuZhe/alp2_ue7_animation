package u7;


/**
 * Basiert auf die Vorlage, Around, von Frau Esponda.
 * Anweisung in ../: javac u7/*.java && java u7.ShapesWorld_Main GoAndBack
 * @author Myz, Barbaros
 * @version 1.01
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;


public class GoAndBack implements Shape, Animation {

    double radius;
    Point center;
    Random rand;

    Color color;
    ShapesWorld welt;
    double velocity = 10;
    int nachLinks = 1;                   // für GoAndBack


    public GoAndBack() {
        this.radius = 25;
        this.color = Color.GREEN; 
        this.center = new Point();
        this.rand = new Random();
    }


    public Color getColor() { 
        return color; 
    }


    public void moveTo(double x, double y) {
        center.x = (int) x;
        center.y = (int) y;
    }


    public void setShapesWorld( ShapesWorld theWorld )
    { 
        this.welt = theWorld;
    }   


    public void draw(Graphics g) {
        g.setColor(color);
        fillTriangle(g, center.x - radius, center.y - radius, radius * 2, radius * 2);
    }


    public void fillTriangle(Graphics g, double x, double y, double w, double h) {
        int[] x_coords = { (int) (x+w/2), (int) (x), (int) (x+w) };
        int[] y_coords = { (int) (y), (int) (y+h), (int) (y+h) };           
        Polygon p = new Polygon(x_coords, y_coords, 3);
        g.fillPolygon(p);
    }


    public Point getCenter() {
        return center;
    }


    public void userClicked(double atX, double atY) { 
    }


    public void userTyped(char key){
        System.out.println("key");
    }


    // implement the Animation-Interface
    public void play() {
        // Das Objekt bewegt sich innerhalb des Frames hin und her.
        double leftSide, rightSide, min_X, max_X;
        leftSide = center.x - radius;
        rightSide = center.x + radius;
        min_X = welt.getMin_X();
        max_X = welt.getMax_X();
        if ( leftSide > min_X && rightSide < max_X )               // *Fall 1: innerhalb des Frames
        {
            if ( nachLinks == 1 )
                {
                    center.x -= velocity;               // sich nach links bewegen
                }
            else
                {
                    center.x += velocity;               // sich nach recht bewegen
                }
        }
        else if ( leftSide <= min_X )                        // *Fall 2: links vom Frame
        {
            nachLinks = 0;                              // Wechsel die Bewegrichtung nach rechts
            center.x += velocity;
        }
        else if ( rightSide >= max_X )                        // *Fall 3: recht vom Frame
        {
            nachLinks = 1;                              // Wechsel die Bewegrichtung nach links
            center.x -= velocity;
        }
    }


    public boolean contains(double x, double y) {
            if (x<(center.x-radius) || x>center.x+radius || y<(center.y-radius) || y>(center.y+radius))
                return false;
            else
                    return true;
    }


    public double getRadius() {
            return radius;
    }


} // end of class GoAndBack

