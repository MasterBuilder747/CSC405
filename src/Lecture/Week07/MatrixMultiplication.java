package Lecture.Week07;

public class MatrixMultiplication {

    //static double m[][] =

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
/*
    //p is the fixed point x, y, z
    //angle is the rotation angle in degrees
    //axis is the axis of rotation x, y, z
    //scene is the object (scene) to be rotated, rows are x, y, z, y, columns are the vertices
    public static double[][] rotateArbitrary(double[] p, double angle, double[] axis) {
        double theta = Math.toRadians(angle);
        double mag = Math.sqrt(Math.pow(axis[0], 2) + Math.pow(axis[1], 2) + Math.pow(axis[2], 2));
        double alpha[] = {axis[0] / mag, axis[1] / mag, axis[2] / mag};

        double[][] t = {
                {1, 0, 0, -p[0]},
                {0, 1, 0, -p[1]},
                {0, 0, 1, -p[2]},
                {0, 0, 0, 1}
        };

        double[][] ti = {
                {1, 0, 0, p[0]},
                {}
        }

        double[][] Rix = {
                {1, 0, 0, 0},
                {0, alpha[2] / d, alpha[1] / 2}
        }

        double m[][] = matMult(Rx, t);
        m = matMult(Ry, m);
        m = matMult(Rz, m);
        m = matMult(Riy, m);
        m = matMult(Rix, m);
        m = matMult(ti, m);


    }

    public static double[][] applyMatrix() {

    }
*/
    public static void printMat(double[][]a) {
        for (double[] doubles : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%7.2f ", doubles[j]); // 7.2f = 7 spaces, 2 digits to the right of the decimal, floating point representation
            }
            System.out.println();
        }
        System.out.println();
    }

    //example arrays
    double[][] a = {{1, 0, 0, 10},
                    {0, 1, 0, 10},
                    {0, 0, 0, 1},
                    {0, 0, 0, 1}};
    double[][] scene = {{0, 10}, {0, 10}, {0, 0}, {1, 1}};



    //public static void main(String[] args) {
        /*
        try {
            double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            printmat(a);
            System.out.println();
            double[][] b = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
            printmat(b);
            System.out.println();
            double[][] c = matmult(a, b);
            printmat(c);
            System.out.println();
            double[][] d = {{1, 2, 3}}; //rows only
            printmat(d);
            System.out.println();
            double[][] e = {{1}, {2}, {3}}; //columns only
            printmat(e);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        */
    //}

}
