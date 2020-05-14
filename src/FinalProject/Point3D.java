package FinalProject;

import java.awt.*;

public class Point3D {
	private double x, y, z;
	
	public Point3D()
	{
		x = y = 0;
	}

	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D(Point3D p) {
		super();
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}

	public String toString ()
	{
		return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void draw (Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		g2d.fillOval((int)x, (int)y, 3, 3);
	}
}
