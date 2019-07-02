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

