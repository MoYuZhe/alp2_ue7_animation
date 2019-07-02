// @author: Yichi Chen

package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;



public class MiniStein extends Stein {

    public MiniStein(Point center, double radiusX, double radiusY, Color color) {
        super();
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.center = center;
        this.color = color;
    }

    public MiniStein(double x, double y, double radiusX, double radiusY, Color color) {
        super();
        this.center.x = x;
        this.center.y = y;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.center = center;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        fillStein(g, center.x-radiusX, center.y-radiusY);
    }

} // end of class MiniStein

