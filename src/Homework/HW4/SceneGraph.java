/*
Homework 4
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-21-20
*/

package Homework.HW4;

import static Homework.HW4.MatrixMultiplication.matMult;

public class SceneGraph {

    //starting template
    static double[][] scene = {
    //   pt0   pt1  pt2    pt3
        {-100,  100, 100,   -100}, //x
        {-100, -100, 100,    100}, //y
        {0,       0,   0,      0}, //z
        {1,       1,   1,      1}  //w
    };

    //change the coordinates of the scene
    private static void setScene(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                scene[i][j] = a[i][j];
            }
        }
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

    //lowercase: the movement
    //uppercase: the point
    //angle in degrees
    //outputs a 4x1 array
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
    // X, Y, Z are just how much it is moving
    public void translation(double x, double y, double z) {
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
    }

    public static double[][] scalePt(double X, double Y, double Z, double x, double y, double z) {
        double[][] scale = {
            {x, 0, 0, 0},
            {0, y, 0, 0},
            {0, 0, z, 0},
            {0, 0, 0, 1}
        };
        double[][] point = {
            {X},
            {Y},
            {Z},
            {1}
        };
        return matMult(scale, point);
    }
    public void scaling(double x, double y, double z) {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = scalePt(scene[0][i], scene[1][i], scene[2][i], x, y, z);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
    }

    public static double[][] rotateXPt(double X, double Y, double Z, double angle) {
        double[][] rotateX = {
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
        return matMult(rotateX, point);
    }
    public void rotateX(double angle) {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateXPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
    }

    public static double[][] rotateYPt(double X, double Y, double Z, double angle) {
        double[][] rotateY = {
            {Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
            {0, 1, 0, 0},
            {-Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0},
            {0, 0, 0, 1}
        };
        double[][] point = {
            {X},
            {Y},
            {Z},
            {1}
        };
        return matMult(rotateY, point);
    }
    public void rotateY(double angle) {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateYPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
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
    public void rotateZ(double angle) {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateZPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
    }

    //render the lines at those coordinates
    public void render(int[][] framebuffer) {
        Lines.bresenhamForm((int)0, (int)0, (int)0, (int)0, framebuffer);
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][0], (int)scene[1][0], framebuffer);
    }

}
