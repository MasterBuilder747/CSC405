/*
Homework 4
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-21-20
*/

package Homework.HW4;

public class MatrixMultiplication {

    public static double[][] matmult (double a[][], double b[][]) throws IllegalArgumentException {
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

    public static void printmat(double[][]a) {
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


    //lowercase: the movement
    //uppercase: the point
    public static double[][] transPt(double x, double y, double z, double X, double Y, double Z) {
        double[][] trans = {{1, 0, 0, x},
                            {0, 1, 0, y},
                            {0, 0, 1, z},
                            {0, 0, 0, 1}};

        double[][] point = {{X},
                            {Y},
                            {Z},
                            {1}};

        return matmult(trans, point);
    }

    public static double[][] scalePt(double x, double y, double z, double X, double Y, double Z) {
        double[][] scale = {{x, 0, 0, 0},
                            {0, y, 0, 0},
                            {0, 0, z, 0},
                            {0, 0, 0, 1}};

        double[][] point = {{X},
                            {Y},
                            {Z},
                            {1}};

        return matmult(scale, point);
    }

    public static double[][] rotateXPt(double X, double Y, double Z, double angle) {
        double[][] rotateX = {{1, 0, 0, 0},
                              {0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
                              {0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                              {0, 0, 0, 1}};

        double[][] point = {{X},
                            {Y},
                            {Z},
                            {1}};

        return matmult(rotateX, point);
    }

    public static double[][] rotateYPt(double X, double Y, double Z, double angle) {
        double[][] rotateY = {{Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
                              {0, 1, 0, 0},
                              {-Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0},
                              {0, 0, 0, 1}};

        double[][] point = {{X},
                            {Y},
                            {Z},
                            {1}};

        return matmult(rotateY, point);
    }

    public static double[][] rotateZPt(double X, double Y, double Z, double angle) {
        double[][] rotateZ = {{Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0},
                              {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                              {0, 0, 1, 0},
                              {0, 0, 0, 1}};

        double[][] point = {{X},
                            {Y},
                            {Z},
                            {1}};

        return matmult(rotateZ, point);
    }



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
