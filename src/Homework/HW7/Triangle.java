/*
Homework 7
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-26-20
*/

package Homework.HW7;

public class Triangle {

    private Triangle() {
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    double x1;
    double x2;
    double x3;
    double y1;
    double y2;
    double y3;

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] framebuffer) { //add grey for cube
        /*
        Lines.bresenhamForm((int) scene[0][0], (int) scene[1][0], (int) scene[0][1], (int) scene[1][1], framebuffer);
        Lines.bresenhamForm((int) scene[0][1], (int) scene[1][1], (int) scene[0][2], (int) scene[1][2], framebuffer);
        Lines.bresenhamForm((int) scene[0][2], (int) scene[1][2], (int) scene[0][3], (int) scene[1][3], framebuffer);
        Lines.bresenhamForm((int) scene[0][3], (int) scene[1][3], (int) scene[0][0], (int) scene[1][0], framebuffer);
        */


    }

    int[][] fb;

    public void fill() {
        for(int y = 1; y < fb.length; y++) {
            int x = 0, x2 = 0;
            for(int x0 = 0; x0 < fb[y].length; x0++) {
                if(fb[y][x0] == 255) x = x0;
            }
            for(int x1 = fb[y].length-1; x1 > -1; x1--) {
                if(fb[y][x1] == 255) x2 = x1;
            }
            if(x <= x2) {
                Lines.bresenhamForm(x, y, x2, y, fb, 255);
            }
        }
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
}


