package PhongShading.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import PhongShading.Utilities.Point3D;

public class Triangulate {

	private static Vector<Point3D> vertices = new Vector<Point3D>();
	
	static double area(Point3D A, Point3D B, Point3D C)
	{
		return (A.getX() - C.getX()) * (B.getY() - C.getY()) - (A.getY() - C.getY()) * (B.getX() - C.getX());
	}

	static boolean inside(Point3D A, Point3D B, Point3D C, Point3D P)
	{
		// -- inequalities are swapped (<= instead of >=) from book implementation because
		//    positive Y is down
		return (
				Triangulate.area(A, B, P) <= 0 &&
				Triangulate.area(B, C, P) <= 0 &&
				Triangulate.area(C, A, P) <= 0
				);
	}
	
	static int triangulate(Vector<Point3D> vertices)
	{
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
				// -- inequality is swapped (<= instead of >=) from book implementation because
				//    positive Y is down
				if (Triangulate.area(A, B, C) <= 0) {
					j = next[iC];
					while (j != iA && !Triangulate.inside(A, B, C, vertices.get(j))) {
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
	
	public static void main (String[] args)
	{
		try {

			String filename = "ccwpolygon.txt";
			BufferedReader buf = new BufferedReader(new FileReader(filename));
			String line;
			line = buf.readLine(); // -- header
			String fields[] = line.split(",");
			while ((line = buf.readLine()) != null) {
				//TODO remove System.out.println(line);
				fields = line.split(",");
				Double x = new Double(fields[0]);
				Double y = new Double(fields[1]);
				Double z = new Double(fields[2]);
				Float r = new Float(fields[3]);
				Float g = new Float(fields[4]);
				Float b = new Float(fields[5]);
				
				
				Point3D p3d = new Point3D(x.doubleValue(), y.doubleValue(), z.doubleValue());
				vertices.add(p3d);
			}

			
			for (int i = 0; i < vertices.size(); ++i) {
				Point3D p = (Point3D)vertices.get(i);
				System.out.println(i + ": " + p);
			}
			
			Triangulate.triangulate(vertices);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("can't open file");
		}
		
	}
}
