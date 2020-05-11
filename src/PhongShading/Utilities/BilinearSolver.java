package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class BilinearSolver {
	double xXform[], yXform[]; // -- screen image to texture image mapping
								// matrices

	void TransformPoint(int _x, int _y, Point _prime) // int &_xprime, int &_yprime)
	{
		double xPrime = xXform[0] * _x + xXform[1] * _y + xXform[2] * _x * _y
				+ xXform[3];
		double yPrime = yXform[0] * _x + yXform[1] * _y + yXform[2] * _x * _y
				+ yXform[3];

		// _xprime = (int)xPrime;
		// _yprime = (int)yPrime;
		_prime.x = (int) xPrime;
		_prime.y = (int) yPrime;
	}

	void ComputeTransform(int _from[][], int _to[][]) // _from[4][2] _to[4][2]
	{
		// -- allocate memory for matrix of equation coefficients
		double equations[][] = new double[4][4];

		// c1*x + c2*y + c3*x*y + c4
		// c5*x + c6*y + c7*x*y + c8
		// -- set up left hand side of equations
		equations[0][0] = _from[0][0];
		equations[0][1] = _from[0][1];
		equations[0][2] = _from[0][0] * _from[0][1];
		equations[0][3] = 1;

		equations[1][0] = _from[1][0];
		equations[1][1] = _from[1][1];
		equations[1][2] = _from[1][0] * _from[1][1];
		equations[1][3] = 1;

		equations[2][0] = _from[2][0];
		equations[2][1] = _from[2][1];
		equations[2][2] = _from[2][0] * _from[2][1];
		equations[2][3] = 1;

		equations[3][0] = _from[3][0];
		equations[3][1] = _from[3][1];
		equations[3][2] = _from[3][0] * _from[3][1];
		equations[3][3] = 1;

		// -- solve equations for x
		// -- set up right hand side of equations
		double xPrime[] = new double[4];
		xPrime[0] = _to[0][0];
		xPrime[1] = _to[1][0];
		xPrime[2] = _to[2][0];
		xPrime[3] = _to[3][0];

		xXform = LUDSolver(equations, xPrime, 4);

		// -- solve equations for y;
		// -- set up right hand side of equations
		double yPrime[] = new double[4];
		yPrime[0] = _to[0][1];
		yPrime[1] = _to[1][1];
		yPrime[2] = _to[2][1];
		yPrime[3] = _to[3][1];

		yXform = LUDSolver(equations, yPrime, 4);

	}

	void LUDecomp(double a[][], int n, int indx[], double d[]) // d[1]
	{
		int i, imax = 0, j, k;
		double big, dum, sum, temp;
		double vv[];

		vv = new double[n];
		d[0] = 1.0;
		for (i = 0; i < n; i++) {
			big = 0.0;
			for (j = 0; j < n; j++) {
				if ((temp = Math.abs(a[i][j])) > big) {
					big = temp;
				}
			}
			if (big == 0.0) {
				System.out.println("Singular matrix in routine LUDecomp");
				return;
			}
			vv[i] = 1.0 / big;
		}
		for (j = 0; j < n; j++) {
			for (i = 0; i < j; i++) {
				sum = a[i][j];
				for (k = 0; k < i; k++) {
					sum -= a[i][k] * a[k][j];
				}
				a[i][j] = sum;
			}
			big = 0.0;
			for (i = j; i < n; i++) {
				sum = a[i][j];
				for (k = 0; k < j; k++)
					sum -= a[i][k] * a[k][j];
				a[i][j] = sum;
				if ((dum = vv[i] * Math.abs(sum)) >= big) {
					big = dum;
					imax = i;
				}
			}
			if (j != imax) {
				for (k = 0; k < n; k++) {
					dum = a[imax][k];
					a[imax][k] = a[j][k];
					a[j][k] = dum;
				}
				d[0] = -(d[0]);
				vv[imax] = vv[j];
			}
			indx[j] = imax;
			if (a[j][j] == 0.0) {
				a[j][j] = 1.0E-20;
			}
			if (j != n - 1) {
				dum = 1.0 / (a[j][j]);
				for (i = j + 1; i < n; i++) {
					a[i][j] *= dum;
				}
			}
		}
	}

	void LUBackSub(double a[][], int n, int indx[], double b[]) {
		int i, ii = -1, ip, j;
		double sum = 0;

		for (i = 0; i < n; i++) {
			ip = indx[i];
			sum = b[ip];
			b[ip] = b[i];
			if (ii != -1) {
				for (j = ii; j <= i - 1; j++) {
					sum -= a[i][j] * b[j];
				}
			} else if (Math.abs(sum) > 0.0001) {
				ii = i;
			}
			b[i] = sum;
		}
		for (i = n - 1; i >= 0; i--) {
			sum = b[i];
			for (j = i + 1; j < n; j++) {
				sum -= a[i][j] * b[j];
			}
			b[i] = sum / a[i][i];
		}
	}

	double[] LUDSolver(double _lhs[][], double _rhs[], int _size) 
	{
		int i;
		double p[] = new double[1];
		int indx[];
		double solution[];

		// -- assumes that _lhs is a square matrix and that _rhs
		// has as many rows as _lhs has columns

		// -- create copy of equations matrix so as
		// to not overwrite the original
		double temp[][] = new double[_size][_size];
		for (i = 0; i < _size; ++i) {
			for (int j = 0; j < _size; ++j) {
				temp[i][j] = _lhs[i][j];
			}
		}

		// -- allocate the solution vector (starts out
		// holding the right hand side of the equations
		// then is overwritten with the solution)
		solution = new double[_size];
		for (i = 0; i < _size; ++i) {
			solution[i] = _rhs[i];
		}

		indx = new int[_size];

		// Solve equations
		LUDecomp(temp, _size, indx, p);
		LUBackSub(temp, _size, indx, solution);

		return solution;
	}
	
	public static void map(int inputimg[][][], Graphics2D g2d, int src[][], int dst[][])
	{		
		BilinearSolver bis = new BilinearSolver();
				

		bis.ComputeTransform(src, dst);
		
		for (int i = 0; i < src.length; ++i) {
			Point p = new Point(0, 0);
			bis.TransformPoint(src[i][0], src[i][1], p);
		}
		
		int mask[][] = new int[inputimg[0].length + 500][inputimg[0][0].length + 500];
		for (int j = 0; j < mask.length; ++j) {
			for (int k = 0; k < mask[j].length; ++k) {
				mask[j][k] = 0;
			}
		}

		int img[][][] = new int[3][inputimg[0].length + 500][inputimg[0][0].length + 500];
		for (int i = 0; i < img.length; ++i) {
			for (int j = 0; j < img[i].length; ++j) {
				for (int k = 0; k < img[i][j].length; ++k) {
					img[i][j][k] = 0;
				}
			}
		}
		
		ScanConvert.BresenhamLine(src[0][0], src[0][1], src[1][0], src[1][1], mask, 255);
		ScanConvert.BresenhamLine(src[1][0], src[1][1], src[2][0], src[2][1], mask, 255);
		ScanConvert.BresenhamLine(src[2][0], src[2][1], src[3][0], src[3][1], mask, 255);
		ScanConvert.BresenhamLine(src[3][0], src[3][1], src[0][0], src[0][1], mask, 255);


		for (int i = 0; i < mask.length; ++i) {
			int jleft = 0;
			while (jleft < mask[i].length && mask[i][jleft] == 0) {
				++jleft;
			}
			int jright = mask[i].length - 1;
			while (jright >= 0 && mask[i][jright] == 0) {
				--jright;
			}
			for (int j = jleft; j <= jright; ++j) {
				Point p = new Point();
				bis.TransformPoint(j, i, p);
				//System.out.println("(" + j + ", " + i + ")" + "->" +"(" + p.x + ", " + p.y + ")");
				if (p.x > 0 && p.x < inputimg[0][0].length - 1 && p.y >= 0 && p.y < inputimg[0].length - 1) {
					g2d.setColor(new Color(inputimg[0][p.y][p.x], inputimg[1][p.y][p.x], inputimg[2][p.y][p.x]));
					g2d.fillRect(j, i, 1, 1);
					img[0][i][j] = inputimg[0][p.y][p.x];
					img[1][i][j] = inputimg[1][p.y][p.x];
					img[2][i][j] = inputimg[2][p.y][p.x];
				}
			}
		}
		
		ImageReadWrite.ImageWriteC(img,  "src.png");
	}
	
	
	public static void main (String[] args)
	{
		
		int inputimg[][][] = ImageReadWrite.ImageReadC("rose.png");
		
		BilinearSolver bis = new BilinearSolver();
		int src[][] = new int[4][2];
		int dst[][] = new int[4][2];
		

		// -- destination shape. Called "src" because it's a reverse mapping
		//   column                                    row
		src[0][0] = 0;                           src[0][1] = inputimg[0].length / 2;
		src[1][0] = inputimg[0][0].length - 1;      src[1][1] = 0;
		src[2][0] = inputimg[0][0].length - 1;      src[2][1] = inputimg[0].length - 1;
		src[3][0] = 0;                           src[3][1] = 3 * inputimg[0].length / 2;

		dst[0][0] = 0;                      dst[0][1] = 0;
		dst[1][0] = inputimg[0][0].length - 1; dst[1][1] = 0;
		dst[2][0] = inputimg[0][0].length - 1; dst[2][1] = inputimg[0].length - 1;
		dst[3][0] = 0;                      dst[3][1] = inputimg[0].length - 1;
		
//		BilinearSolver.map(inputimg, g2d, src, dst);
	

		bis.ComputeTransform(src, dst);
		
		for (int i = 0; i < src.length; ++i) {
			Point p = new Point(0, 0);
			bis.TransformPoint(src[i][0], src[i][1], p);
		}
		
		int mask[][][] = new int[3][inputimg[0].length + 500][inputimg[0][0].length + 500];
		for (int i = 0; i < mask.length; ++i) {
			for (int j = 0; j < mask[i].length; ++j) {
				for (int k = 0; k < mask[i][j].length; ++k) {
					mask[i][j][k] = 0;
				}
			}
		}

		int img[][][] = new int[3][inputimg[0].length + 500][inputimg[0][0].length + 500];
		for (int i = 0; i < img.length; ++i) {
			for (int j = 0; j < img[i].length; ++j) {
				for (int k = 0; k < img[i][j].length; ++k) {
					img[i][j][k] = 0;
				}
			}
		}
		
		ScanConvert.BresenhamLineC(src[0][0], src[0][1], src[1][0], src[1][1], mask, 255, 255, 0);
		ScanConvert.BresenhamLineC(src[1][0], src[1][1], src[2][0], src[2][1], mask, 255, 255, 0);
		ScanConvert.BresenhamLineC(src[2][0], src[2][1], src[3][0], src[3][1], mask, 255, 255, 0);
		ScanConvert.BresenhamLineC(src[3][0], src[3][1], src[0][0], src[0][1], mask, 255, 255, 0);


		for (int i = 0; i < mask[0].length; ++i) {
			int jleft = 0;
			while (jleft < mask[0][i].length && mask[0][i][jleft] == 0) {
				++jleft;
			}
			int jright = mask[0][i].length - 1;
			while (jright >= 0 && mask[0][i][jright] == 0) {
				--jright;
			}
			for (int j = jleft; j <= jright; ++j) {
				Point p = new Point();
				bis.TransformPoint(j, i, p);
				//System.out.println("(" + j + ", " + i + ")" + "->" +"(" + p.x + ", " + p.y + ")");
				if (p.x > 0 && p.x < inputimg[0][0].length - 1 && p.y >= 0 && p.y < inputimg[0].length - 1) {
					img[0][i][j] = inputimg[0][p.y][p.x];
					img[1][i][j] = inputimg[1][p.y][p.x];
					img[2][i][j] = inputimg[2][p.y][p.x];
				}
			}
		}
		
		ImageReadWrite.ImageWriteC(img,  "src.png");
		 		
		
	}

}
