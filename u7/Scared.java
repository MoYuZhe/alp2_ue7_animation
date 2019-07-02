// @author: Yichi Chen

package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Scared implements Shape, Animation {
	
	double radius;
	int step;
        double jumpStep = 25.0;
	Point center, destination;
	Random rand;
	ShapesWorld theWorld;	   
	Color color;
        final static Color colorQuiet = Color.cyan;
        final static Color colorScared = Color.orange;
        final static Color colorJump = Color.red;
        final static Color colorStein = Color.yellow;
        final static int threshold = 100;
        int goRight = 1;
        boolean onJumping = false;
        int jumpingSteps = 0;
        int jumpingThreshold = 5;
        int stDist = 30;
        boolean notAlone = false;
	
	public Scared() {
		super();                        // ???
		this.rand = new Random();
		this.radius = 20;
		this.center = new Point(0, 50);
		this.color = colorQuiet;
                this.step = 3;
	}

	@Override
	public void play() {
                double dist = 1000;
                Point centerClosest;
                double radiusClosest = 1000;
                int xd = 0, yd = 0;

                Shape closestShape = theWorld.getClosestShape(this);
                if (closestShape != null)
                {
                    centerClosest = closestShape.getCenter(); 
                    radiusClosest = closestShape.getRadius();
                    dist = getDistance(centerClosest, this.center);
                    notAlone = true;
                }

                if (notAlone)
                {
                    if (onJumping) 
                    {
                        this.color = colorJump;
                        double distToDestination = getDistance(this.center, this.destination);
                        if (dist <= radiusClosest + this.radius && jumpingSteps >= jumpingThreshold)
                        {
                            // Das Scared-Objekt platzt!
                            jumpingSteps = 0;
                            theWorld.removeShape(this);
                            Stein stNorthEast, stSouthEast, stSouthWest, stNorthWest;
                
                            stNorthEast = new MiniStein(center.x+stDist, center.y-stDist, radius/4, radius/8, colorStein);
                            stSouthEast = new MiniStein(center.x+stDist, center.y+stDist, radius/4, radius/8, colorStein);
                            stSouthWest = new MiniStein(center.x-stDist, center.y+stDist, radius/4, radius/8, colorStein);
                            stNorthWest = new MiniStein(center.x-stDist, center.y-stDist, radius/4, radius/8, colorStein);

                            theWorld.addShape(stNorthEast);
                            theWorld.addShape(stSouthEast);
                            theWorld.addShape(stSouthWest);
                            theWorld.addShape(stNorthWest);
                        }
                        else
                        {
                            // Das Scared-Objekt befindet sich auf dem Weg zum Zielpunkt
                            if (distToDestination <= jumpStep)
                            {
                                this.center = this.destination;
                                onJumping = false;
                                jumpingSteps = 0;
                            }
                            else
                            {
                                center.x += jumpStep * (destination.x - center.x) / distToDestination;
                                center.y += jumpStep * (destination.y - center.y) / distToDestination;
                                jumpingSteps += 1;
                            }
                        }
                    }
                    else
                    {
                        if (dist <= radiusClosest + this.radius)
                        {
                            // Das Manöver für Springen
                            this.destination = getRandomPosition();          
                            // Das Springen fängt nun an
                            onJumping = true;
                        }
                        else if (dist <= threshold)
                        {
                            // Das Manöver für Zittern
                            this.color = colorScared;
                            this.center.x += goRight * step;
                            goRight *= -1;
                        }
                        else
                        {
                            // Das Scared-Objekt ist also ganz ruhig
                            this.color = colorQuiet;
                        }
                    }
                } 
	}


        public Point getRandomPosition() {
                double new_X, new_Y;
                double max_X, max_Y, min_X, min_Y;
                max_X = theWorld.getMax_X();
                max_Y = theWorld.getMax_Y();
                min_X = theWorld.getMin_X();
                min_Y = theWorld.getMin_Y();

                new_X = min_X + Math.random() * (max_X - min_X);
                new_Y = min_Y + Math.random() * (max_Y - min_Y);
        
                Point newPoint = new Point(new_X, new_Y);
                return newPoint;
        }

        public double getDistance(Point c1, Point c2) {
                double dist;
                dist = Math.sqrt((c1.x - c2.x) * (c1.x - c2.x) 
                                + (c1.y - c2.y) * (c1.y - c2.y));
                return dist;
        }

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
                g.drawOval((int) center.x, (int) center.y, (int) radius, (int) radius);
		g.fillOval((int) center.x, (int) center.y, (int) radius, (int) radius);
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

}// end of class Scared

