/*
Homework 5
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-5-20
*/

package Homework.HW5;

import java.util.Arrays;

import static Homework.HW4.MatrixMultiplication.matMult;

public class SceneGraph {

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] framebuffer) { //add grey for cube
        //Lines.bresenhamForm((int)0, (int)0, (int)0, (int)0, framebuffer);

        //render 24 lines, 6 squares in total here
        //from looking top down to it:

        //face: points in clockwise rotation from looking at the surface facing outward:
        //top (default): 0, 1, 2, 3
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][0], (int)scene[1][0], framebuffer);

        //front: 3, 2, 6, 7
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][6], (int)scene[1][6], framebuffer);
        Lines.bresenhamForm((int)scene[0][6], (int)scene[1][6], (int)scene[0][7], (int)scene[1][7], framebuffer);
        Lines.bresenhamForm((int)scene[0][7], (int)scene[1][7], (int)scene[0][3], (int)scene[1][3], framebuffer);

        //bottom: 7, 6, 5, 4
        Lines.bresenhamForm((int)scene[0][7], (int)scene[1][7], (int)scene[0][6], (int)scene[1][6], framebuffer);
        Lines.bresenhamForm((int)scene[0][6], (int)scene[1][6], (int)scene[0][5], (int)scene[1][5], framebuffer);
        Lines.bresenhamForm((int)scene[0][5], (int)scene[1][5], (int)scene[0][4], (int)scene[1][4], framebuffer);
        Lines.bresenhamForm((int)scene[0][4], (int)scene[1][4], (int)scene[0][7], (int)scene[1][7], framebuffer);

        //back: 4, 5, 1, 0
        Lines.bresenhamForm((int)scene[0][4], (int)scene[1][4], (int)scene[0][5], (int)scene[1][5], framebuffer);
        Lines.bresenhamForm((int)scene[0][5], (int)scene[1][5], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][0], (int)scene[1][0], framebuffer);
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][4], (int)scene[1][4], framebuffer);

        //right: 2, 1, 5, 6
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][5], (int)scene[1][5], framebuffer);
        Lines.bresenhamForm((int)scene[0][5], (int)scene[1][5], (int)scene[0][6], (int)scene[1][6], framebuffer);
        Lines.bresenhamForm((int)scene[0][6], (int)scene[1][6], (int)scene[0][2], (int)scene[1][2], framebuffer);

        //left: 0, 3, 7, 4
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][7], (int)scene[1][7], framebuffer);
        Lines.bresenhamForm((int)scene[0][7], (int)scene[1][7], (int)scene[0][4], (int)scene[1][4], framebuffer);
        Lines.bresenhamForm((int)scene[0][4], (int)scene[1][4], (int)scene[0][0], (int)scene[1][0], framebuffer);
    }

    //the starting coordinates of each point, and other info
    //starting template, defaulted to origin
    static double[][] scene = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt: 0     1    2     3     4     5     6     7     8T   9F   10Bo  11Ba  12R   13L    14c
            {-100,  100, 100, -100, -100,  100,  100, -100,  0,   0,   0,    0,    100, -100,   0}, //x 0
            {-100, -100, 100,  100, -100, -100,  100,  100,  0,   100, 0,   -100,  0,    0,     0}, //y 1
            { 100,  100, 100,  100, -100, -100, -100, -100,  100, 0,  -100,  0,    0,    0,     0}, //z 2
            {1, 1, 1, 1, 1, 1, 1, 1} //w 3
    };

    //the position at the origin
    static double[][] square = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt: 0     1    2     3     4     5     6     7     8T   9F   10Bo  11Ba  12R   13L    14c
            {-100,  100, 100, -100, -100,  100,  100, -100,  0,   0,   0,    0,    100, -100,   0}, //x 0
            {-100, -100, 100,  100, -100, -100,  100,  100,  0,   100, 0,   -100,  0,    0,     0}, //y 1
            { 100,  100, 100,  100, -100, -100, -100, -100,  100, 0,  -100,  0,    0,    0,     0}, //z 2
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} //w 3
    };


    //use this later
    //print it out for now
    //sample surfaces that are default, relative to the sides
    //unused, for reference and stored in main scene
    static double[][] surfaceNormals = {
        //T,   F,   Bo,  Ba,   R,    L
         {0,   0,   0,   0,    100, -100}, //x
         {0,   100, 0,  -100,  0,    0}, //y
         {100, 0,  -100, 0,    0,    0} //z
    };
    public void updateSN() {
        //points 8-13
        //T,   F,   Bo,  Ba,   R,    L
        //
        for (int i = 0; i < 3; i++) {
            for (int j = 8; j < 14; j++) {
                surfaceNormals[i][j-8] = scene[i][j];
            }
        }
    }
    public void printSN() {
        updateSN();
        System.out.println("Surface normals: ");
        printMat(surfaceNormals);
    }

    //used for cube center
    //diagonal points / 2 for each axis for 2d
    public static double sum(double[] a) {
        double sum = 0;
        for (double v : a) {
            sum += v;
        }
        return sum;
    }
    //only works for a square, use sum / 8 for a cube
    public static double[] center = new double[3];
    public static void updateC() {
        //(point 0 to point 6) / 2
        //c=         pt0: x, y, z+ pt6: x, y, z / 2
        center[0] = (scene[0][0] + scene[0][6]) / 2.0;
        center[1] = (scene[1][0] + scene[1][6]) / 2.0;
        center[2] = (scene[2][0] + scene[2][6]) / 2.0;
    }

    //testing
    public static void printMat(double[][]a) {
        for (double[] doubles : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%7.2f ", doubles[j]); // 7.2f = 7 spaces, 2 digits to the right of the decimal, floating point representation
            }
            System.out.println();
        }
        System.out.println();
    }

    //testing
    public static void printCenter() {
        System.out.println("Center point: ");
        System.out.println(Arrays.toString(center));
    }
    //change the coordinates of the scene
    private static void setScene(double[][] a) {
        //row
        for (int i = 0; i < a.length; i++) {
            //column: only read 0-7
            for (int j = 0; j < 8; j++) {
                scene[i][j] = a[i][j];
            }
        }
    }
    public void resetShape() {
        setScene(square);
        printCenter();
        printSN();
    }

    //transformations
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
        updateC();
        printCenter();
        printSN();
    }

    public void toOrigin() {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = originPt(scene[0][i], scene[1][i], scene[2][i], center);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        updateC();
        //printCenter();
        //System.out.println(Arrays.toString(scene[0]));
        //System.out.println(Arrays.toString(scene[1]));
        //System.out.println(Arrays.toString(scene[2]));
    }

    //origin translation on center
    public static double[][] originPt(double X, double Y, double Z, double[] a) {
        double[][] trans = {
                {1, 0, 0, -a[0]},
                {0, 1, 0, -a[1]},
                {0, 0, 1, -a[2]},
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
    public static double[][] oldPt(double X, double Y, double Z, double[] a) {
        double[][] trans = {
            {1, 0, 0, a[0]},
            {0, 1, 0, a[1]},
            {0, 0, 1, a[2]},
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
    public void toOldCenter(double[] b) {
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = oldPt(scene[0][i], scene[1][i], scene[2][i], b);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        updateC();
        //printCenter();
        //System.out.println(Arrays.toString(scene[0]));
        //System.out.println(Arrays.toString(scene[1]));
        //System.out.println(Arrays.toString(scene[2]));
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
        double[] oldCenter = {center[0], center[1], center[2]};
        toOrigin();
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = scalePt(scene[0][i], scene[1][i], scene[2][i], x, y, z);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        toOldCenter(oldCenter);
        printCenter();
        printSN();
    }
    //lowercase: the amount being scaled
    //uppercase: the fixed point at which it is being scaled relative to
    public void scaling(double x, double y, double z, double X, double Y, double Z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(-X, -Y, -Z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = scalePt(scene[0][i], scene[1][i], scene[2][i], x, y, z);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(X, Y, Z);
    }


    //rotations
    // X
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
        //store the old center information (so it can go back)
        double[] oldCenter = {center[0], center[1], center[2]};
        //System.out.println(Arrays.toString(oldCenter));
        //move the the origin
        toOrigin();
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            //for every point:
            double[][] a = rotateXPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0]; //x
            scene[1][i] = a[1][0]; //y
            scene[2][i] = a[2][0]; //z
        }
        toOldCenter(oldCenter);
        printSN();
    }
    //fixed point:
    //x, y, z is the defined fixed point, needs 2 parameters (3 for cube)
    public void rotateX(double angle, double x, double y, double z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(-x, -y, -z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateXPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(x, y, z);
        printCenter();
        printSN();
    }

    // Y
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
        //store the old center information (so it can go back if needed)
        double[] oldCenter = {center[0], center[1], center[2]};
        //move the the origin
        toOrigin();
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateYPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        toOldCenter(oldCenter);
        printSN();
    }
    public void rotateY(double angle, double x, double y, double z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(-x, -y, -z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateYPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(x, y, z);
        printCenter();
        printSN();
    }

    // Z
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
        //store the old center information (so it can go back if needed)
        double[] oldCenter = {center[0], center[1], center[2]};
        //move the the origin
        toOrigin();
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateZPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        toOldCenter(oldCenter);
        printSN();
    }
    public void rotateZ(double angle, double x, double y, double z) {
        //move the the defined fixed point
        //translate by -fixed point
        translation(-x, -y, -z);
        //rotate it at the origin
        for (int i = 0; i < scene[0].length; i++) {
            double[][] a = rotateZPt(scene[0][i], scene[1][i], scene[2][i], angle);
            scene[0][i] = a[0][0];
            scene[1][i] = a[1][0];
            scene[2][i] = a[2][0];
        }
        translation(x, y, z);
        printCenter();
        printSN();
    }
}
