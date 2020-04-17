/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval.HW8TriangleDrawGUI;

public class MatrixOps {

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

    static double[][] M; // -- the transformation matrix

    // -- p is the fixed point x, y, z
    //    angle is rotation angle in degrees
    //    axis is the axis of rotation x, y, z
    //    builds the transformation matrix M
    public static double[][] buildMatrix (double[][] scene, double p[], double angle, double axis[]) throws IllegalArgumentException
    {
        // -- make sure the angle is in radians
        double theta = Math.toRadians(angle);
        // -- convert the axis to a unit vector
        double mag = Math.sqrt(Math.pow(axis[0],  2) + Math.pow(axis[1],  2) + Math.pow(axis[2],  2));
        //System.out.println(mag);
        double alpha[] = {axis[0] / mag, axis[1] / mag, axis[2] / mag};
        //System.out.println(Arrays.toString(alpha));

        if (mag < 0.000000001) {
            throw new IllegalArgumentException("invalid axis of rotation");
        }

        double d = Math.sqrt(Math.pow(alpha[1],  2) + Math.pow(alpha[2],  2));
        //System.out.println(d);
        if (Math.abs(d) < 0.00000001) {
            // -- just rotate about the x axis

            // ADD CALL TO rotateX(P, angle) HERE
            M = rotateX(scene, p[0], p[1], p[2], angle);
            //m = xrotate((int)p[0],(int)p[1],(int)p[2],angle,scene);
            return M;
        }

            // -- now for the matrices

            // -- translate fixed point to origin
            double[][] T = {
                    {1, 0, 0, -p[0]},
                    {0, 1, 0, -p[1]},
                    {0, 0, 1, -p[2]},
                    {0, 0, 0, 1}
            };
            // -- translate fixed point to original location
            double[][] Ti = {
                    {1, 0, 0, p[0]},
                    {0, 1, 0, p[1]},
                    {0, 0, 1, p[2]},
                    {0, 0, 0, 1}
            };
            // -- rotate about the z axis by theta
            double[][] Rz = {
                    {Math.cos(theta), -Math.sin(theta), 0, 0},
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
            return M;

    }

    public static double[][] rotateX(double[][] scene, double angle, double x, double y, double z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(scene, -x, -y, -z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateXPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(scene, x, y, z);
        //printCenter();
        //printSN();
        return scene;
    }

    public static void translation(double[][] scene, double x, double y, double z) {
        //place the new points into the scene
        //this goes through each point
        //the scene is an array of 4x1 points
        for (int i = 0; i < scene[0].length; i++) {
            //this is a 4x1 array representing one point
            double[][] a = transPt(scene[0][i], scene[1][i], scene[2][i], x, y, z);
            //printMat(a);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        //updateC();
        //printCenter();
        //printSN();
    }
    public static double[][] transPt(double X, double Y, double Z, double x, double y, double z) {
        double[][] trans = {
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        };
        double[][] point = {
                {X},
                {Y},
                {Z},
                {1}
        };
        //this returns a 4x1 array
        //so the other 3 points needs to be added for the square to be rendered
        return matMult(trans, point);
    }

    public static double[][] rotateXPt(double X, double Y, double Z, double angle) {
        double[][] rotateXi = {
                {1, 0, 0, 0},
                {0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
                {0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
        double[][] point = {
                {X},
                {Y},
                {Z},
                {1}
        };
        return matMult(rotateXi, point);
    }

    public static double[][] rotateZPt(double X, double Y, double Z, double angle) {
        double[][] rotateZ = {
                {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0},
                {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        double[][] point = {
                {X},
                {Y},
                {Z},
                {1}
        };
        return matMult(rotateZ, point);
    }
    public static void rotateZ(double[][] scene, double angle, double x, double y, double z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(scene, -x, -y, -z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateZPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(scene, x, y, z);
        //printCenter();
        //printSN();
    }

    public static double[][] matMult (double[][] a, double[][] b) throws IllegalArgumentException {
        //a[0] indicates to test the length of just the columns of array a
        int l1 = a[0].length; //results in a null
        int l2 = b.length;
        if (l1 != l2) {
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
            double scene[][] = {
                    {0, 0},
                    {0, 1},
                    {0, 0},
                    {1, 1}
            };
            printmatrix(scene);
            double fixedpoint[] = {0, 0, 0};
            double angle = 45;
            double axis[] = {1, 0, 0};
            buildMatrix(scene, fixedpoint, angle, axis);
            printmatrix(M);
            scene = applyMatrix(scene);
            scene = applyMatrix(scene);
            printmatrix(scene);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
