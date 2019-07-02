package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Captive implements Shape, Animation {
	
	double radius;
	int step;
	Point center;
	Random rand;
	ShapesWorld theWorld;	   
	Color color;
	
	
	public Captive() {
		super();                    
		this.rand = new Random();
		this.radius = 20;
		this.step = 15;
		this.center = new Point(0,0);
		this.color = Color.red;
	}

	@Override
	public void play() {
	    int d = rand.nextInt(4);
	    int xd=0, yd=0;
            double newLeftSide, newRightSide, newTopSide, newDownSide, 
                    min_X, max_X, min_Y, max_Y;

            min_X = theWorld.getMin_X();
            max_X = theWorld.getMax_X();
            min_Y = theWorld.getMin_Y();
            max_Y = theWorld.getMax_Y();

            // Bestimmung der Bewegrichtung
            switch (d) {
                 case 0:
                             xd = 1; yd = 1; break;
                 case 1:
                             xd = 1; yd = -1; break;
                 case 2:
                             xd = -1; yd = 1; break;
                 case 3:
                             xd = -1; yd = -1; break;
            }
           
            // Feststelle, ob sich das Objekt den Rand des Fensters berührt
            // wenn ja, geh zur umgekehrten Richtung

            double newX, newY;
            newX =  (this.center.x + rand.nextDouble()*step*xd);  
            newY =  (this.center.y + rand.nextDouble()*step*yd);

	    newLeftSide = newX - radius;
            newRightSide = newX + radius;
            newTopSide = newY + radius;
            newDownSide = newY - radius;

            if ( newLeftSide > min_X && newRightSide < max_X &&
                newTopSide < max_Y && newDownSide > min_Y )
            {
                // noch innerhalb des Fensters, daher einfach weiter wie es in der 
                // CrazyWalker-Vorlage vorgegeben ist.

                this.moveTo((int) newX, (int) newY);		
            }
            else
            {
                // schon außerhalb des Fensters, daher umgekehrte Richtung gehen
                // Dennoch brauchen wir hierbei noch mal ne Fallunterscheidung..
                int isXReversed = 1; 
                int isYReversed = 1;
                if ( newLeftSide <= min_X || newRightSide >= max_X)
                    isXReversed = -1;
                if ( newTopSide >= max_Y || newDownSide <= min_Y )
                    isYReversed = -1;
                newX =  (this.center.x + isXReversed * rand.nextDouble()*step*xd);  
                newY =  (this.center.y + isYReversed * rand.nextDouble()*step*yd);
                this.moveTo((int) newX, (int) newY);
            }
        }

	@Override
	public void draw(Graphics g) {
                Star st = new Star(center, radius, color);
                st.draw(g);
	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public Point getCenter() {
		// TODO Auto-generated method stub
		return center;
	}

	@Override
	public void setShapesWorld(ShapesWorld theWorld) {
		this.theWorld = theWorld;
	}

	@Override
	public void userClicked(double at_X, double at_Y) {
	}

	@Override
	public void userTyped(char key) {
	}

	@Override
	public void moveTo(double x, double y) {
		center.x = x;
		center.y = y;
	}

}
