package Lecture.Week07;

public class MatrixMultiplication {

    static double m[][]; //transformation matrix

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

    //p is the fixed point x, y, z
    //angle is the rotation angle in degrees
    //axis is the axis of rotation x, y, z
    //scene is the object (scene) to be rotated, rows are x, y, z, y, columns are the vertices
    public static void rotateArbritrary (double p[], double angle, double axis[]) throws IllegalArgumentException
    {
        // -- make sure the angle is in radians
        double theta = Math.toRadians(angle);
        // -- convert the axis to a unit vector
        double mag = Math.sqrt(Math.pow(axis[0],  2) + Math.pow(axis[1],  2) + Math.pow(axis[2],  2));
        double alpha[] = {axis[0] / mag, axis[1] / mag, axis[2] / mag};

        if (mag < 0.000000001) {
            throw new IllegalArgumentException("invalid axis of rotation");
        }

        double d = Math.sqrt(Math.pow(alpha[1],  2) + Math.pow(alpha[2],  2));
        if (Math.abs(d) < 0.00000001) {
            // -- just rotate about the x axis

            // ADD CALL TO rotateX(P, angle) HERE
            // ADD CALL TO rotateX(P, angle) HERE
            // ADD CALL TO rotateX(P, angle) HERE
            // ADD CALL TO rotateX(P, angle) HERE
            // ADD CALL TO rotateX(P, angle) HERE
            // ADD CALL TO rotateX(P, angle) HERE

            return;
        }


        // -- now for the matrices

        // -- translate fixed point to origin
        double[][] T = {{1, 0, 0, -p[0]},
                {0, 1, 0, -p[1]},
                {0, 0, 1, -p[2]},
                {0, 0, 0, 1}
        };
        // -- translate fixed point to original location
        double[][] Ti = {{1, 0, 0, p[0]},
                {0, 1, 0, p[1]},
                {0, 0, 1, p[2]},
                {0, 0, 0, 1}
        };
        // -- rotate about the z axis by theta
        double[][] Rz = {{Math.cos(theta), -Math.sin(theta), 0, 0},
                {Math.sin(theta), Math.cos(theta), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        // -- rotate about y axis by theta-y (to be computed)
        double[][] Ry = {
                {d, 0, -alpha[0], 0},
                {0, 1, 0, 0},
                {alpha[0], 0, d, 0},
                {0, 0, 0, 1}
        };
        // -- rotate about y axis by -theta-y (to be computed)
        double[][] Riy = {
                {d, 0, alpha[0], 0},
                {0, 1, 0, 0},
                {-alpha[0], 0, d, 0},
                {0, 0, 0, 1}
        };
        // -- rotate about x axis by theta-x (to be computed)
        double[][] Rx = {
                {1, 0, 0, 0},
                {0, alpha[2] / d, -alpha[1] / d, 0},
                {0, alpha[1] / d, alpha[2] / d, 0},
                {0, 0, 0, 1}
        };
        // -- rotate about x axis by -theta-x (to be computed)
        double[][] Rix = {
                {1, 0, 0, 0},
                {0, alpha[2] / d, alpha[1] / d, 0},
                {0, -alpha[1] / d, alpha[2] / d, 0},
                {0, 0, 0, 1}
        };

        // -- build the final matrix
        m = matMult(Rx, T);
        m = matMult(Ry, m);
        m = matMult(Rz, m);
        m = matMult(Riy, m);
        m = matMult(Rix, m);
        m = matMult(Ti, m);
    }

    public static double[][] applyMatrix(double[][] scene) {
        return matMult(m, scene);
    }

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
