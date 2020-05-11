package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;


public class Circle {

	private double x, y, radius;
	

	public Circle (double _x, double _y, double _radius)
	{
		x = _x;
		y = _y;
		radius = _radius;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public double getR() {
		return radius;
	}

	public void draw (Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		g2d.drawOval((int)(x - radius), (int)(y - radius), (int)radius * 2, (int)radius * 2);
	}
	
	public String toString ()
	{
		return "(" + x + ", " + y  + ") : " + radius;
	}

}
