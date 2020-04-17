/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

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

    //perform a series of matrix multiplications through a 3D array
    //this is done in an array of matrices
    //all of the matrices in the array must be the same size in rows and columns
    //note that this goes from left to right
    public static double[][] matMulti(double[][][] a) {
        double[][] result = matMult(a[0], a[1]);
        for (int i = 1; i < a.length - 1; i++) {
            result = matMult(result, a[i+1]);
        }
        return result;
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
}
