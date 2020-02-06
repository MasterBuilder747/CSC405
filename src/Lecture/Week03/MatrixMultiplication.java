package Lecture.Week03;

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
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%7.2f ", a[i][j]); // 7.2f = 7 spaces, 2 digits to the right of the decimal, floating point representation
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
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

        try {
            double[][] a = {{1, 0, 0, 10}, {0, 1, 0, 10}, {0, 0, 0, 1}, {0, 0, 0, 1}};
            printmat(a);

            double[][] scene = {{0, 10}, {0, 10}, {0, 0}, {1, 1}};
            printmat(scene); //movement

            printmat(matmult(a, scene));

            double[][] x = {{1, 0, 0, 0},
                            {0, Math.cos(Math.toRadians(90)), -Math.sin(Math.toRadians(90)), 0},
                            {0, Math.sin(Math.toRadians(90)), Math.cos(Math.toRadians(90)), 0},
                            {0, 0, 0, 1}};
            printmat(matmult(x, scene));

            double[][] y = {{Math.cos(Math.toRadians(90)), 0, Math.sin(Math.toRadians(90)), 0},
                            {0, 1, 0, 0},
                            {-Math.sin(Math.toRadians(90)), 0, Math.cos(Math.toRadians(90)), 0},
                            {0, 0, 0, 1}};
            printmat(matmult(y, scene));

            double[][] z = {{Math.cos(Math.toRadians(90)), -Math.sin(Math.toRadians(90)), 0, 0},
                            {Math.sin(Math.toRadians(90)), Math.cos(Math.toRadians(90)), 0, 0},
                            {0, 0, 1, 0},
                            {0, 0, 0, 1}};
            printmat(matmult(z, scene));

        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

}
