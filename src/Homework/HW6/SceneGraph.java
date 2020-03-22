/*
Homework 6
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-19-20
*/

package Homework.HW6;

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
    static double x = 0.5;
    static double[][] scene = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt: 0     1    2     3     4     5     6     7     8T   9F   10Bo  11Ba  12R   13L    14c
            {-100,  100, 100, -100, -100,  100,  100, -100,  0,   0,   0,    0,    0,    0,     0}, //x 0
            {-100, -100, 100,  100, -100, -100,  100,  100,  0,   0,   0,    0,    0,    0,     0}, //y 1
            { 100,  100, 100,  100, -100, -100, -100, -100,  0,   0,   0,    0,    0,    0,     0}, //z 2
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}                                           //w 3
    };

    //the position at the origin
    static double[][] square = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt: 0     1    2     3     4     5     6     7     8T   9F   10Bo  11Ba  12R   13L    14c
            {-100,  100, 100, -100, -100,  100,  100, -100,  0,   0,   0,    0,    0,    0,     0}, //x 0
            {-100, -100, 100,  100, -100, -100,  100,  100,  0,   0,   0,    0,    0,    0,     0}, //y 1
            { 100,  100, 100,  100, -100, -100, -100, -100,  0,   0,   0,    0,    0,    0,     0}, //z 2
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}                                           //w 3
    };

    //the position at the origin
    static double[][] defaultSquare = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
            //pt: 0     1    2     3     4     5     6     7     8T   9F   10Bo  11Ba  12R   13L    14c
            {-100,  100, 100, -100, -100,  100,  100, -100,  0,   0,   0,    0,    0,    0,     0}, //x 0
            {-100, -100, 100,  100, -100, -100,  100,  100,  0,   0,   0,    0,    0,    0,     0}, //y 1
            { 100,  100, 100,  100, -100, -100, -100, -100,  0,   0,   0,    0,    0,    0,     0}, //z 2
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}                                           //w 3
    };

    //use this later
    //print it out for now
    //sample surfaces that are default, relative to the sides
    //unused, for reference and stored in main scene
    static double[][] surfaceNormals = new double[3][6];
    static double[][] surfaceNormalsDefault = {
            //T,   F,   Bo,  Ba,   R,    L
            {0,   0,   0,   0,    1,   -1}, //x
            {0,   1,   0,  -1,    0,    0}, //y
            {1,   0,  -1,   0,    0,    0}  //z
    };

    public void printSN() {
        //calculateSN();
        //not needed, as this updates through affine transformations
        System.out.println("Surface normals: ");
        printMat(surfaceNormals);
    }
    public void resetSN() {
        //calculate the surface normals at this point
        //calculation done HERE:
        calculateSN();
        //store it into a separete array in case
        //then put those values into the scene matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 8; j < 14; j++) {
                //vector - center
                scene[i][j] = surfaceNormals[i][j-8];
            }
        }
        //for now on, these numbers will be updated based on affine transformations
        System.out.println("Surface normals: ");
        printMat(surfaceNormals);
    }
    public void calculateSN() {
        //the surface normals are updated based on the current center point
        //so you can store these current calculated values in a separate array
        //this is surfaceNormals

        //points 8-13
        //T,   F,   Bo,  Ba,   R,    L

        //clockwise of each direction on each face

        //side: relative origin point: point1, point2
        //top: 3: 2, 0
        setSurfaceNormals(3, 0, 2, 0);
        //front: 7: 6, 3
        setSurfaceNormals(7, 3, 6, 1);
        //bottom: 4, 5, 7
        setSurfaceNormals(4, 7, 5, 2);
        //back: 0: 1, 4
        setSurfaceNormals(0, 4, 1, 3);
        //right: 6: 5, 2
        setSurfaceNormals(6, 2, 5, 4);
        //left: 4: 7, 0
        setSurfaceNormals(4, 0, 7, 5);
    }
    public static void setSurfaceNormals(int o, int a, int b, int i) {
        //cross product of two vector lengths on each face
        //vector sub always returns a length 3 array of x, y, z based on ints specified
        //cross returns a length 3 array of distance vector
        double[] cross = SurfaceNormal.cross(vectorSub(a, o), vectorSub(b, o));
        //System.out.println("cross prod: " + Arrays.toString(cross));
        //System.out.println();
        double cm = SurfaceNormal.mag(cross);

        //x(c)/mag(c), y(c)/mag(c), z(c)/mag(c)
        surfaceNormals[0][i] = cross[0] / cm;
        surfaceNormals[1][i] = cross[1] / cm;
        surfaceNormals[2][i] = cross[2] / cm;
    }
    //point k - point o
    //where o is the relative origin and k is the point of interest
    public static double[] vectorSub(int k, int o) {
        //convert column into row
        double[] pt = {scene[0][k], scene[1][k], scene[2][k]};
        //aString(pt);
        double[] og = {scene[0][o], scene[1][o], scene[2][o]};
        //aString(og);
        //return new double[] {scene[0][k], scene[1][k], scene[2][k]};

        //then subtract
        double[] a = {pt[0] - og[0], pt[1] - og[1], pt[2] - og[2]};
        //System.out.println("vector: " + Arrays.toString(a));
        return a;
    }
    public static void aString(double[] a) {
        System.out.println(Arrays.toString(a));
    }


//    public void updateSNScale() {
//        //this particular version of the function is only used when scaling,
//        //an extra step is needed when calculating the surface normals
//
//        //the surface normals are updated based on the current center point
//        //so you can store these current calculated values in a separate array
//        //this is surfaceNormals
//
//        //points 8-13
//        //T,   F,   Bo,  Ba,   R,    L
//        for (int i = 0; i < 3; i++) {
//            for (int j = 8; j < 14; j++) {
//                //cross / mag
//
//            }
//        }
//    }
//    public void printSNScale() {
//        updateSNScale();
//        System.out.println("Surface normals: ");
//        printMat(surfaceNormals);
//    }


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
    private static void setScene() {
        //row
        for (int i = 0; i < square.length; i++) {
            //column: only read 0-7
            for (int j = 0; j < 8; j++) {
                scene[i][j] = square[i][j];
            }
        }
    }
    public void resetShape() {
        resetSN();
        setScene();
        //printCenter();
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
        //printCenter();
        //printSN();
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
        //printCenter();

        //printSNScale();
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
        //printCenter();
        //printSN();
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
        //printCenter();
        //printSN();
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
        //printCenter();
        //printSN();
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
        //printCenter();
        //printSN();
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
        //printCenter();
        //printSN();
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
        //printCenter();
        //printSN();
    }


    //arbitrary rotation:
    //  0 = theta
    //  fp = fixed point vector
    //  0x, 0y, [there is no 0z, as there is already rotation on the 0z] = <arbitrary axis of rotation vector>
    //  	T(fp) * Rx(-0x) * Ry(-0y) * Rz(0) * Ry(0y) * Rx(0x) * T(-fp) * [scene]
    public void arbitrary(double[] fp, double angle, double[] ar) {

        //store the multiplication matrix in temp var
        double[][] points = new double[4][8];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                points[i][j] = scene[i][j];
            }
        }

        double[][] trans = {
                {1, 0, 0, fp[0]},
                {0, 1, 0, fp[1]},
                {0, 0, 1, fp[2]},
                {0, 0, 0, 1}
        };

        double[][] transNeg = {
                {1, 0, 0, -fp[0]},
                {0, 1, 0, -fp[1]},
                {0, 0, 1, -fp[2]},
                {0, 0, 0, 1}
        };

        double[][] rotateX = {
                {1, 0, 0, 0},
                {0, Math.cos(Math.toRadians(ar[0])), -Math.sin(Math.toRadians(ar[0])), 0},
                {0, Math.sin(Math.toRadians(ar[0])), Math.cos(Math.toRadians(ar[0])), 0},
                {0, 0, 0, 1}
        };

        double[][] rotateY = {
                {Math.cos(Math.toRadians(ar[1])), 0, Math.sin(Math.toRadians(ar[1])), 0},
                {0, 1, 0, 0},
                {-Math.sin(Math.toRadians(ar[1])), 0, Math.cos(Math.toRadians(ar[1])), 0},
                {0, 0, 0, 1}
        };

        double[][] rotateXNeg = {
                {1, 0, 0, 0},
                {0, Math.cos(Math.toRadians(-ar[0])), -Math.sin(Math.toRadians(-ar[0])), 0},
                {0, Math.sin(Math.toRadians(-ar[0])), Math.cos(Math.toRadians(-ar[0])), 0},
                {0, 0, 0, 1}
        };

        double[][] rotateYNeg = {
                {Math.cos(Math.toRadians(-ar[1])), 0, Math.sin(Math.toRadians(-ar[1])), 0},
                {0, 1, 0, 0},
                {-Math.sin(Math.toRadians(-ar[1])), 0, Math.cos(Math.toRadians(-ar[1])), 0},
                {0, 0, 0, 1}
        };

        double[][] rotateZ = {
                {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0},
                {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        //T(fp) * Rx(-0x) * Ry(-0y) * Rz(0) * Ry(0y) * Rx(0x) * T(-fp) * [scene?]
        //double[][] result = matMult(matMult(matMult(matMult(matMult(matMult(matMult(trans, rotateXNeg), rotateYNeg), rotateZ), rotateY), rotateX), transNeg), scene);
        double[][] result = matMulti(new double[][][] {transNeg, rotateX, rotateY, rotateZ, rotateYNeg, rotateXNeg, trans, points});

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                scene[i][j] = result[i][j];
            }
        }
        //printSN();
        //printMat(scene);

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


