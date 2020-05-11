package PhongShading.Utilities;

public class Transformation {

	// -- Standard matrix multiplication routine
	public static double [][] matmult(double A[][], double B[][]) {
		int rowsA = A.length;
		int colsA = A[0].length;
		int rowsB = B.length;
		int colsB = B[0].length;
		
		if (colsA != rowsB) {
			return null;
		}
		
		double C[][] = new double [rowsA][colsB];
		
		for (int i = 0; i < rowsA; ++i) {
			for (int j = 0; j < colsB; ++j) {
				for (int k = 0; k < rowsB; ++k) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		return C;
	}
	
	
	public static Point3D rotateX (Point3D p, double theta, Point3D fix)
	{
		// -- degrees to radius
		theta *= (Math.PI / 180.0);

		// -- translation to origin
		double trans0[][] = {{1, 0, 0, -fix.getX()}, {0, 1, 0, -fix.getY()}, {0, 0, 1, -fix.getZ()}, {0, 0, 0, 1}};
		// -- rotate about X axis
		double rot[][] = {{1, 0, 0, 0}, {0, Math.cos(theta), -Math.sin(theta), 0}, {0, Math.sin(theta), Math.cos(theta), 0}, {0, 0, 0, 1}};
		// -- translate back to point
		double trans1[][] = {{1, 0, 0, fix.getX()}, {0, 1, 0, fix.getY()}, {0, 0, 1, fix.getZ()}, {0, 0, 0, 1}};

		// -- build transformation matrix    trans1 X rot X trans0
		double trans[][] = Transformation.matmult(rot, trans0);
		trans = Transformation.matmult(trans1, trans);

		double hpoint[][] = {{p.getX()}, {p.getY()}, {p.getZ()}, {1}};
		double ans[][] = Transformation.matmult(trans, hpoint);
		
		return (new Point3D(ans[0][0], ans[1][0], ans[2][0]));
		
	}
	
	public static Point3D rotateY (Point3D p, double theta, Point3D fix)
	{
		// -- degrees to radius
		theta *= (Math.PI / 180.0);

		// -- translation to origin
		double trans0[][] = {{1, 0, 0, -fix.getX()}, {0, 1, 0, -fix.getY()}, {0, 0, 1, -fix.getZ()}, {0, 0, 0, 1}};
		// -- rotate about Y axis
		double rot[][] = {{Math.cos(theta), 0, Math.sin(theta), 0}, {0, 1, 0, 0}, {-Math.sin(theta), 0, Math.cos(theta), 0}, {0, 0, 0, 1}};
		// -- translate back to point
		double trans1[][] = {{1, 0, 0, fix.getX()}, {0, 1, 0, fix.getY()}, {0, 0, 1, fix.getZ()}, {0, 0, 0, 1}};

		// -- build transformation matrix    trans1 X rot X trans0
		double trans[][] = Transformation.matmult(rot, trans0);
		trans = Transformation.matmult(trans1, trans);

		double hpoint[][] = {{p.getX()}, {p.getY()}, {p.getZ()}, {1}};
		double ans[][] = Transformation.matmult(trans, hpoint);
		
		return (new Point3D(ans[0][0], ans[1][0], ans[2][0]));

	}
	
	public static Point3D rotateZ (Point3D p, double theta, Point3D fix)
	{
		// -- degrees to radius
		theta *= (Math.PI / 180.0);

		// -- translation to origin
		double trans0[][] = {{1, 0, 0, -fix.getX()}, {0, 1, 0, -fix.getY()}, {0, 0, 1, -fix.getZ()}, {0, 0, 0, 1}};
		// -- rotate about Z axis
		double rot[][] = {{Math.cos(theta), -Math.sin(theta), 0, 0}, {Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		// -- translate back to point
		double trans1[][] = {{1, 0, 0, fix.getX()}, {0, 1, 0, fix.getY()}, {0, 0, 1, fix.getZ()}, {0, 0, 0, 1}};

		// -- build transformation matrix    trans1 X rot X trans0
		double trans[][] = Transformation.matmult(rot, trans0);
		trans = Transformation.matmult(trans1, trans);

		double hpoint[][] = {{p.getX()}, {p.getY()}, {p.getZ()}, {1}};
		double ans[][] = Transformation.matmult(trans, hpoint);
		
		return (new Point3D(ans[0][0], ans[1][0], ans[2][0]));

	}
	
	public static Point3D scale (Point3D p, Point3D factor, Point3D fix)
	{

		// -- translation to origin
		double trans0[][] = {{1, 0, 0, -fix.getX()}, {0, 1, 0, -fix.getY()}, {0, 0, 1, -fix.getZ()}, {0, 0, 0, 1}};
		// -- scale
		double scale[][] = {{factor.getX(), 0, 0, 0}, {0, factor.getY(), 0, 0}, {0, 0, factor.getZ(), 0}, {0, 0, 0, 1}};
		// -- translate back to point
		double trans1[][] = {{1, 0, 0, fix.getX()}, {0, 1, 0, fix.getY()}, {0, 0, 1, fix.getZ()}, {0, 0, 0, 1}};

		// -- build transformation matrix    trans1 X rot X trans0
		double trans[][] = Transformation.matmult(scale, trans0);
		trans = Transformation.matmult(trans1, trans);

		double hpoint[][] = {{p.getX()}, {p.getY()}, {p.getZ()}, {1}};
		double ans[][] = Transformation.matmult(trans, hpoint);
		
		return (new Point3D(ans[0][0], ans[1][0], ans[2][0]));

	}
	
	public static Point3D translate (Point3D p, Point3D factor)
	{

		// -- translation matrix
		double translate[][] = {{1, 0, 0, factor.getX()}, {0, 1, 0, factor.getY()}, {0, 0, 1, factor.getZ()}, {0, 0, 0, 1}};

		double hpoint[][] = {{p.getX()}, {p.getY()}, {p.getZ()}, {1}};
		double ans[][] = Transformation.matmult(translate, hpoint);
		
		return (new Point3D(ans[0][0], ans[1][0], ans[2][0]));
		
	}


}
