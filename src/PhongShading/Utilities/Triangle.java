package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import PhongShading.Utilities.Triangulate;

public class Triangle {
	private Point3D v0, v1, v2;
	public Circle circumscribe;
	public Circle inscribe;
	public boolean isSelected = false;
	
	public Triangle ()
	{
		v0 = new Point3D();
		v1 = new Point3D();
		v2 = new Point3D();
	}

	public Triangle(Point3D v0, Point3D v1, Point3D v2) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		circumscribedCircle();
		inscribedCircle();
		
	}
	
	public String toString ()
	{
		return getV0().toString() + ":" + getV1().toString() + ":" + getV2().toString();
	}
	
	public void draw (Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		g2d.drawLine((int)v0.getX(), (int)v0.getY(), (int)v1.getX(), (int)v1.getY());
		g2d.drawLine((int)v1.getX(), (int)v1.getY(), (int)v2.getX(), (int)v2.getY());
		g2d.drawLine((int)v2.getX(), (int)v2.getY(), (int)v0.getX(), (int)v0.getY());		
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

	public Point3D getV2() {
		return v2;
	}

	public void setV2(Point3D v2) {
		this.v2 = v2;
	}
	
	public double area()
	{
		double i = v1.getX() * v2.getY() - v2.getX() * v1.getY();
		double j = v2.getX() * v0.getY() - v0.getX() * v2.getY();
		double k = v0.getX() * v1.getY() - v1.getX() * v0.getY();
				
		return 0.5 * Math.sqrt(i*i + j * j + k * k);
	}

	public double pseudoArea()
	{
		double i = v1.getX() * v2.getY() - v2.getX() * v1.getY();
		double j = v2.getX() * v0.getY() - v0.getX() * v2.getY();
		double k = v0.getX() * v1.getY() - v1.getX() * v0.getY();
				
		return (i + j + k);
	}

	private double pseudoArea(Point3D v0, Point3D v1, Point3D v2)
	{
		double i = v1.getX() * v2.getY() - v2.getX() * v1.getY();
		double j = v2.getX() * v0.getY() - v0.getX() * v2.getY();
		double k = v0.getX() * v1.getY() - v1.getX() * v0.getY();
				
		return i + j + k;
	}
	
	public boolean pointIn (Point3D p)
	{
		double a0 = pseudoArea(v0, v1, p);
		double a1 = pseudoArea(v1, v2, p);
		double a2 = pseudoArea(v2, v0, p);
		
		// -- inequalities are swapped (<= instead of >=) from book implementation because
		//    positive Y is down
		//return (a0 <= 0 && a1 <= 0 && a2 <= 0);		
		return (a0 >= 0 && a1 >= 0 && a2 >= 0);		
	}
	
	public boolean isNormal ()
	{
		return (pseudoArea(v0, v1, v2) > 0);		
	}
	
	private boolean isNormal (Point3D v0, Point3D v1, Point3D v2)
	{
		return (pseudoArea(v0, v1, v2) > 0);		
	}

	public void makeNormal()
	{
		Point3D v0prime, v1prime, v2prime;
		
		v0prime = v0;
		if (isNormal(v0prime, v1, v2)) {
			v1prime = v1;
			v2prime = v2;
		}
		else {
			v1prime = v2;
			v2prime = v1;
		}
		v1 = v1prime;
		v2 = v2prime;
		
	}
	
	public static int triangulate(Vector<Point3D> vertices)
	{
		for (int i = 0; i < vertices.size(); ++i) {
			Point3D p = (Point3D)vertices.get(i);
			System.out.println(i + ": " + p);
		}
		int n = vertices.size();
		int j = n - 1;
		int iA = 0;
		int iB;
		int iC;
		
		int[] next = new int[n];
		for (int i = 0; i < n; ++i) {
			next[j] = i;
			j = i;
		}
		for (int k = 0; k < n - 2; ++k) {
			Point3D A, B, C;
			boolean triaFound = false;
			int count = 0;
			while (!triaFound && ++count < n) {
				iB = next[iA];
				iC = next[iB];
				A = (Point3D)vertices.get(iA);
				B = (Point3D)vertices.get(iB);
				C = (Point3D)vertices.get(iC);
				Triangle T = new Triangle(A, B, C);
				if (T.area() >= 0) {
					j = next[iC];
					while (j != iA && !T.pointIn(vertices.get(j))) {
						j = next[j];
					}
					if (j == iA) {
						System.out.print("found a triangle");
						System.out.println(A + " " + B + " " + C);
						next[iA] = iC;
						triaFound = true;
					}
				}
				iA = next[iA];
			}
			if (count == n) {
				System.out.println("not a simple polygon");
				return -1;
			}
		}
		
		return 0;
	}
	
	public static Vector<Triangle> triangulate1(Vector<Point3D> vertices)
	{
		for (int i = 0; i < vertices.size(); ++i) {
			Point3D p = (Point3D)vertices.get(i);
			System.out.println(i + ": " + p);
		}
		
		Vector<Triangle> triangulation = new Vector<Triangle>();
		
		int n = vertices.size();
		int j = n - 1;
		int iA = 0;
		int iB;
		int iC;
		
		int[] next = new int[n];
		for (int i = 0; i < n; ++i) {
			next[j] = i;
			j = i;
		}
		for (int k = 0; k < n - 2; ++k) {
			Point3D A, B, C;
			boolean triaFound = false;
			int count = 0;
			while (!triaFound && ++count < n) {
				iB = next[iA];
				iC = next[iB];
				A = (Point3D)vertices.get(iA);
				B = (Point3D)vertices.get(iB);
				C = (Point3D)vertices.get(iC);
				Triangle T = new Triangle(A, B, C);
				double aaa = T.pseudoArea();
				if (T.pseudoArea() >= 0) {
					j = next[iC];
					while (j != iA && !T.pointIn(vertices.get(j))) {
						j = next[j];
					}
					if (j == iA) {
						System.out.print("found a triangle");
						System.out.println(A + " " + B + " " + C);
						triangulation.add(new Triangle(A, B, C));
						next[iA] = iC;
						triaFound = true;
					}
				}
				iA = next[iA];
			}
			if (count == n) {
				System.out.println("not a simple polygon");
				return null;
			}
		}
		
		return triangulation;
	}
	
	public boolean circumscribedCircle()
	{
		boolean status = false;

		double k;
		double h;
		double numer, denom;

		double a, b, c, d, e, f;
		a = v0.getX(); b = v0.getY();
		c = v1.getX(); d = v1.getY();
		e = v2.getX(); f = v2.getY();
		
		// -- these horrible looking equations are the closed-form solution to solving these three equations for h, k, and r
		//        2           2     2
		// (a - h)  +  (b - k)  = r
		//        2           2     2
		// (c - h)  +  (d - k)  = r
		//        2           2     2
		// (e - h)  +  (f - k)  = r
		// (h, k) will be the center of the circle, r will be the radius
		//
		numer = -(b * e * e) - (b * f * f) - (d * a * a) - (d * b * b) + (d * e * e) + (d * f * f) + (b * c * c) + (b * d * d) + (f * a * a) + (f * b * b) - (f * c * c) - (f * d * d);
		denom = 2 * (-(b * e) - (a * d) + (d * e) + (b * c) + (a * f) - (f * c));
		h = numer / denom;
		
		k = ((a * a) - (c * c) - (2 * a * h) + (2 * c * h) + (b * b) - (d * d)) / (2 * (b - d));

		double r = Math.sqrt((a - h) * (a - h) + (b - k) * (b - k));

		circumscribe = new Circle(h, k, r);
		
		
		{ // -- alternative method
			double Ax = v0.getX(), Ay = v0.getY();
			double Bx = v1.getX(), By = v1.getY();
			double Cx = v2.getX(), Cy = v2.getY();
			
			double aa = Math.hypot(Bx - Cx, By - Cy);
			double bb = Math.hypot(Ax - Cx, Ay - Cy);
			double cc = Math.hypot(Ax - Bx, Ay - By);
			
			//D=2(A_{x}(B_{y}-C_{y})+B_{x}(C_{y}-A_{y})+C_{x}(A_{y}-B_{y})).
			double D =  2.0 * (Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By));
			//U_{x}=((A_{x}^{2}+A_{y}^{2})(B_{y}-C_{y})+(B_{x}^{2}+B_{y}^{2})(C_{y}-A_{y})+(C_{x}^{2}+C_{y}^{2})(A_{y}-B_{y}))/D,
			double Ux = ((Ax * Ax + Ay * Ay) * (By - Cy) + (Bx * Bx + By * By) * (Cy - Ay) + (Cx * Cx + Cy * Cy) * (Ay - By)) / D;
			//U_{y}=((A_{x}^{2}+A_{y}^{2})(C_{x}-B_{x})+(B_{x}^{2}+B_{y}^{2})(A_{x}-C_{x})+(C_{x}^{2}+C_{y}^{2})(B_{x}-A_{x}))/D
			double Uy = ((Ax * Ax + Ay * Ay) * (Cx - Bx) + (Bx * Bx + By * By) * (Ax - Cx) + (Cx * Cx + Cy * Cy) * (Bx - Ax)) / D;
			
			double radius = (2.0 * aa * bb * cc) / Math.sqrt((aa + bb + cc) * (-aa + bb + cc) * (aa - bb + cc) * (aa + bb - cc)) / 2;
			circumscribe = new Circle(Ux, Uy, radius);
			
		}
		
		return status;
	}
	
	public boolean inscribedCircle()
	{
		boolean status = false;
		
		// -- vector from v0 to v1
		Point3D v01 = new Point3D(v1.getX() - v0.getX(), v1.getY() - v0.getY(), 0.0);
		// -- vector from v0 to v2
		Point3D v02 = new Point3D(v2.getX() - v0.getX(), v2.getY() - v0.getY(), 0.0);
		
		double angle01 = Math.atan2(-v01.getY(), v01.getX());
		double angle02 = Math.atan2(-v02.getY(), v02.getX());
		
		double m0 = (angle01 + angle02) / 2.0;

		// -- vector from v2 to v1
		Point3D v21 = new Point3D(v1.getX() - v2.getX(), v1.getY() - v2.getY(), 0.0);
		// -- vector from v2 to v0
		Point3D v20 = new Point3D(v0.getX() - v2.getX(), v0.getY() - v2.getY(), 0.0);
		
		angle01 = Math.atan2(-v21.getY(), v21.getX());
		angle02 = Math.atan2(-v20.getY(), v20.getX());
		
		double m1 = (angle01 + angle02) / 2.0;		

		// -- vector from v1 to v2
		Point3D v12 = new Point3D(v2.getX() - v1.getX(), v2.getY() - v1.getY(), 0.0);
		// -- vector from v1 to v0
		Point3D v10 = new Point3D(v0.getX() - v1.getX(), v0.getY() - v1.getY(), 0.0);
	
		angle01 = Math.atan2(-v12.getY(), v12.getX());
		angle02 = Math.atan2(-v10.getY(), v10.getX());
		
		double m2 = (angle01 + angle02) / 2.0;
		
		
		double x0 = v0.getX();
		double y0 = v0.getY();
		double x1 = (x0 + 500 * Math.cos(m0));
		double y1 = (y0 - 500 * Math.sin(m0));

		double x2 = v1.getX();
		double y2 = v1.getY();
		double x3 = (x2 + 500 * Math.cos(m2));
		double y3 = (y2 - 500 * Math.sin(m2));

		double M0 = (y1 - y0) / (x1 - x0);
		double M1 = (y3 - y2) / (x3 - x2);
		
		double X = (M1 * x2 - M0 * x0 - y2 + y0) / (M1 - M0);
		double Y = M0 * (X - x0) + y0;
		
		M0 = (y2 - y0) / (x2 - x0);
		
		inscribe = new Circle(X, Y, Math.abs(M0 * X - Y + (y0 - M0 * x0)) / Math.sqrt(M0 * M0 + 1));

		{ // -- alternative method
			x0 = v0.getX();
			y0 = v0.getY();
			x1 = v1.getX();
			y1 = v1.getY();
			x2 = v2.getX();
			y2 = v2.getY();

			double a = Math.hypot(x2-x1, y2-y1);
			double b = Math.hypot(x2-x0, y2-y0);
			double c = Math.hypot(x1-x0, y1-y0);
			
			X = (a * x0 + b * x1 + c * x2) / (a + b + c);
			Y = (a * y0 + b * y1 + c * y2) / (a + b + c);
			double radius = 0.5 * Math.sqrt(((b + c - a) * (c + a - b) * (a + b - c)) / (a + b + c));

			inscribe = new Circle(X, Y, radius);
		}
		
		return status;
	}
	
	
}
