package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line {
	public Point3D v0, v1;
	
	public Line ()
	{
		v0 = new Point3D();
		v1 = new Point3D();
	}

	public Line(Point3D v0, Point3D v1) {
		super();
		this.v0 = v0;
		this.v1 = v1;
	}
	
	public String toString ()
	{
		return getV0().toString() + ":" + getV1().toString();
	}
	
	public void draw (Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		g2d.drawLine((int)v0.getX(), (int)v0.getY(), (int)v1.getX(), (int)v1.getY());
	}

	public Point3D getV0() {
		return v0;
	}

	public void setV0(Point3D v0) {
		this.v0 = v0;
	}

	public Point3D getV1() {
		return v1;
	}

	public void setV1(Point3D v1) {
		this.v1 = v1;
	}

}
