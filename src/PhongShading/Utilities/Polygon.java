package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class Polygon {

	private Vector<Point3D> points;
	
	public Polygon (Vector<Point3D> _p) 
	{
		points = new Vector<Point3D>(_p);
	}
	
	public String toString ()
	{
		String ret = "";
		for (int i = 0; i < points.size() - 1; ++i) {
			ret += points.get(i).toString() + ":";
		}
		ret += points.get(points.size() - 1).toString();
		
		return ret;
	}

	public void draw (Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		for (int i = 0; i < points.size() - 1; ++i) {
			Point3D v0 = points.get(i);
			Point3D v1 = points.get(i+1);
			g2d.drawLine((int)v0.getX(), (int)v0.getY(), (int)v1.getX(), (int)v1.getY());
		}
		Point3D v0 = points.get(points.size() - 1);
		Point3D v1 = points.get(0);
		g2d.drawLine((int)v0.getX(), (int)v0.getY(), (int)v1.getX(), (int)v1.getY());
		
	}
	
	
}
