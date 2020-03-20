/*
Homework 6
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-19-20
*/

package Homework.HW6;

public class MatrixMultiplication {

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
}
