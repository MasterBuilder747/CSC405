/*
Homework 5
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-5-20
*/


package Homework.HW5;

public class SurfaceNormal {

    //1 = x
    //2 = y
    //3 = z
    // a x b = (a2b3 - a3b1, a1b3 - a1b3, a1b2 - a2b1)
    //cross product assumes both vectors originate at (0, 0, 0)

    //cross two vectors on every specific plane
    public static double[] cross(double[] a, double[] b) {
        double[] c = new double[3];

        c[0] = a[1] * b[2] - a[2] * b[1];
        c[1] = a[2] * b[0] - a[0] * b[2];
        c[2] = a[0] * b[1] - a[1] * b[0];

        return c;
    }

    //sqrt(x, y, z)
    public static double mag (double[] a) {
        return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2) + Math.pow(a[2], 2));
    }

    public static double[][] matMult (double a[][], double b[][]) throws IllegalArgumentException {
        //a[0] indicates to test the length of just the columns of array a
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("incompatible arrays");
        }

        //vice versa
        double c[][] = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) { //for every row in a
            for(int j = 0; j < b[0].length; j++) { //for every column in b
                double dotprod = 0;
                for(int k = 0; k < a[0].length; k++) { //dot product
                    dotprod += a[i][k] * b[k][j];
                }
                c[i][j] = dotprod;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        double t = 0;
        double [][] rotateY = {
                {Math.cos(t), 0, Math.sin(t), 0},
                {0, 1, 0, 0},
                {-Math.sin(t), 0, Math.cos(t), 0},
                {0, 0, 0, 1}
        };

        //last point is the center
        double [][] square = {
                {-1, 1, 1, -1, 0}, //top right:  (1, -1, 1)
                {1, 1, -1, -1, 0}, //top left:  (-1, -1, 1)
                {1, 1, 1, 1, 1}, //bottom right: (1, 1, 1)
                {1, 1, 1, 1, 1} //bottom left: (-1, 1, 1)
        };

        double[][] trans = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        square = matMult(trans, square);
        square = matMult(rotateY, square);

        double newmatrix[][] = matMult(rotateY, trans);
        newmatrix = matMult(trans, newmatrix);
/*
        double[] a = {square[0][1] - square[0][0], square[1][1] - square[1][0], square[2][1] - square[2][0]};
        double[] b = {square[0][3] - square[0][0], square[1][3] - square[1][0], square[2][3] - square[2][0]};
        double[] c = cross(b, a);
        double magC = mag(c);
        System.out.println(c[0] + ", " + c[1] + ", " + c[2] +  ": " + mag(c));

        double[] unitC = {c[0] / magC , c[1] / magC, c[2] / magC};
        System.out.println(unitC[0] + ", " + unitC[1] + ", " + unitC[2] +  ": " + mag(unitC));

        unitC[0] =+ square[0][4];
        unitC[1] =+ square[1][4];
        unitC[2] =+ square[2][4];
        System.out.println(unitC[0] + ", " + unitC[1] + ", " + unitC[2] +  ": " + mag(unitC));


 */
    }
}
