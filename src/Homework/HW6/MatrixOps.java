package Homework.HW6;

public class MatrixOps {
    static double M[][]; // -- the transformation matrix
    public static double[][] matmult(double A[][], double B[][]) throws IllegalArgumentException
    {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("incompatible arrays");
        }
        double C[][] = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; ++i) { // -- for every row in A
            for (int j = 0; j < B[0].length; ++j) { // -- for every column in B
                double dotprod = 0;
                for (int k = 0; k < A[0].length; ++k) { // -- dot product
                    dotprod += A[i][k] * B[k][j];
                }
                C[i][j] = dotprod;
            }
        }
        return C;
    }

    // -- p is the fixed point x, y, z
    //    angle is rotation angle in degrees
    //    axis is the axis of rotation x, y, z
    //    builds the transformation matrix M
    public static void buildMatrix (double p[], double angle, double axis[]) throws IllegalArgumentException
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
        M = matmult(Rx, T);
        M = matmult(Ry, M);
        M = matmult(Rz, M);
        M = matmult(Riy, M);
        M = matmult(Rix, M);
        M = matmult(Ti, M);
    }

    //    scene is the object (scene) to be rotated, rows are x, y, z, w, columns are the vertices
    public static double[][] applyMatrix(double scene[][])
    {
        return matmult(M, scene);
    }

    public static void printmatrix(double A[][])
    {
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[i].length; ++j) {
                System.out.printf("%7.2f ", A[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        try {
//			double A[][] = {{1, 0, 0, 10}, {0, 1, 0, 10}, {0, 0, 1, 0}, {0, 0, 0, 1}};
//			printmatrix(A);
//
//			double scene[][] = {{0, 10}, {0, 10}, {0, 0}, {1, 1}};
//			printmatrix(scene);
//
//			printmatrix(matmult(A, scene));
//
//			double X[][] = {{1, 0, 0, 0},
//							{0, Math.cos(Math.toRadians(90)), -Math.sin(Math.toRadians(90)), 0},
//							{0, Math.sin(Math.toRadians(90)), Math.cos(Math.toRadians(90)), 0},
//							{0, 0, 0, 1}};
//			printmatrix(matmult(X, scene));
//
//			double Y[][] = {
//					{Math.cos(Math.toRadians(90)), 0, Math.sin(Math.toRadians(90)), 0},
//					{0, 1, 0, 0},
//					{-Math.sin(Math.toRadians(90)), 0, Math.cos(Math.toRadians(90)), 0},
//					{0, 0, 0, 1}};
//			printmatrix(matmult(Y, scene));
//
//			double Z[][] = {
//					{Math.cos(Math.toRadians(90)), -Math.sin(Math.toRadians(90)), 0, 0},
//					{Math.sin(Math.toRadians(90)), Math.cos(Math.toRadians(90)), 0, 0},
//					{0, 0, 1, 0},
//					{0, 0, 0, 1}};
//			printmatrix(matmult(Z, scene));
            double scene[][] = {{0, 0},
                    {0, 1},
                    {0, 0},
                    {1, 1}
            };
            printmatrix(scene);
            double fixedpoint[] = {0, 0, 0};
            double angle = 45;
            double axis[] = {0, 0, 1};
            buildMatrix(fixedpoint, angle, axis);
            scene = applyMatrix(scene);
            scene = applyMatrix(scene);
            printmatrix(scene);

        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
