/*
Homework 6
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-19-20
*/

package Homework.HW6ArbitraryRotation;

public class ArrayTest {

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
        double[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        //System.out.println(Arrays.toString(a[0][0]));
        //System.out.println(-0.0 * 1);

        double[][][] b = {
                {
                    {1, 2, 3}
                },
                {
                    {7, 8}
                }
        };
        //System.out.println(Arrays.toString(matMulti(b)));
        System.out.println(b.length);
        System.out.println(b[0].length);
        System.out.println(b[1][0].length);
    }



}
