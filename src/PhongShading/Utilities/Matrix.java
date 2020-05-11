package PhongShading.Utilities;

public class Matrix {
	protected double M[][];
	
	public Matrix (int rows, int cols)
	{
		M = new double[rows][cols];
	}
	
	public Matrix(double[][] _m)
	{
		M = _m;
	}

	public static Matrix multiply(Matrix A, Matrix B)
	{
		Matrix result = null;
		
		double[][] a = A.M;
		double[][] b = B.M;
		double[][] r;
		
		if (a[0].length == b.length) {
			r = new double[a.length][b[0].length];
			for (int i = 0; i < a.length; ++i) {
				for (int j = 0; j < b[0].length; ++j) {
					r[i][j] = 0.0;
					for (int k = 0; k < b.length; ++k) {
						r[i][j] += a[i][k] * b[k][j];
					}
				}
			}
			result = new Matrix(r);
			
		}
		
		
		return result;
	}
	
	public String toString ()
	{
		String s = "";
		
		for (int i = 0; i < M.length; ++i) {
			for (int j = 0; j < M[0].length; ++j) {
				s += M[i][j] + "\t";
			}
			s += "\n";
		}
		return s;
	}
	
	public static void main (String args[])
	{
		double a[][] = {{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};
		double b[][] = {{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};
		
		Matrix A = new Matrix(a);
		Matrix B = new Matrix(b);
		
		System.out.println(Matrix.multiply(A,  B));
	}
}
