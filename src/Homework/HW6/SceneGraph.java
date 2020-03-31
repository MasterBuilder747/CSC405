/*
Homework 6
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-19-20
*/

package Homework.HW6;

import java.util.Arrays;

public class SceneGraph extends SceneGraphBase {

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] framebuffer) { //add grey for cube
        //render 24 lines, 6 squares in total here
        //from looking top down to it:

        //face: points in clockwise rotation from looking at the surface facing outward:
        //top (default): 0, 1, 2, 3
        Lines.bresenhamForm((int) scene[0][0], (int) scene[1][0], (int) scene[0][1], (int) scene[1][1], framebuffer);
        Lines.bresenhamForm((int) scene[0][1], (int) scene[1][1], (int) scene[0][2], (int) scene[1][2], framebuffer);
        Lines.bresenhamForm((int) scene[0][2], (int) scene[1][2], (int) scene[0][3], (int) scene[1][3], framebuffer);
        Lines.bresenhamForm((int) scene[0][3], (int) scene[1][3], (int) scene[0][0], (int) scene[1][0], framebuffer);

        //front: 3, 2, 6, 7
        Lines.bresenhamForm((int) scene[0][3], (int) scene[1][3], (int) scene[0][2], (int) scene[1][2], framebuffer);
        Lines.bresenhamForm((int) scene[0][2], (int) scene[1][2], (int) scene[0][6], (int) scene[1][6], framebuffer);
        Lines.bresenhamForm((int) scene[0][6], (int) scene[1][6], (int) scene[0][7], (int) scene[1][7], framebuffer);
        Lines.bresenhamForm((int) scene[0][7], (int) scene[1][7], (int) scene[0][3], (int) scene[1][3], framebuffer);

        //bottom: 7, 6, 5, 4
        Lines.bresenhamForm((int) scene[0][7], (int) scene[1][7], (int) scene[0][6], (int) scene[1][6], framebuffer);
        Lines.bresenhamForm((int) scene[0][6], (int) scene[1][6], (int) scene[0][5], (int) scene[1][5], framebuffer);
        Lines.bresenhamForm((int) scene[0][5], (int) scene[1][5], (int) scene[0][4], (int) scene[1][4], framebuffer);
        Lines.bresenhamForm((int) scene[0][4], (int) scene[1][4], (int) scene[0][7], (int) scene[1][7], framebuffer);

        //back: 4, 5, 1, 0
        Lines.bresenhamForm((int) scene[0][4], (int) scene[1][4], (int) scene[0][5], (int) scene[1][5], framebuffer);
        Lines.bresenhamForm((int) scene[0][5], (int) scene[1][5], (int) scene[0][1], (int) scene[1][1], framebuffer);
        Lines.bresenhamForm((int) scene[0][1], (int) scene[1][1], (int) scene[0][0], (int) scene[1][0], framebuffer);
        Lines.bresenhamForm((int) scene[0][0], (int) scene[1][0], (int) scene[0][4], (int) scene[1][4], framebuffer);

        //right: 2, 1, 5, 6
        Lines.bresenhamForm((int) scene[0][2], (int) scene[1][2], (int) scene[0][1], (int) scene[1][1], framebuffer);
        Lines.bresenhamForm((int) scene[0][1], (int) scene[1][1], (int) scene[0][5], (int) scene[1][5], framebuffer);
        Lines.bresenhamForm((int) scene[0][5], (int) scene[1][5], (int) scene[0][6], (int) scene[1][6], framebuffer);
        Lines.bresenhamForm((int) scene[0][6], (int) scene[1][6], (int) scene[0][2], (int) scene[1][2], framebuffer);

        //left: 0, 3, 7, 4
        Lines.bresenhamForm((int) scene[0][0], (int) scene[1][0], (int) scene[0][3], (int) scene[1][3], framebuffer);
        Lines.bresenhamForm((int) scene[0][3], (int) scene[1][3], (int) scene[0][7], (int) scene[1][7], framebuffer);
        Lines.bresenhamForm((int) scene[0][7], (int) scene[1][7], (int) scene[0][4], (int) scene[1][4], framebuffer);
        Lines.bresenhamForm((int) scene[0][4], (int) scene[1][4], (int) scene[0][0], (int) scene[1][0], framebuffer);
    }

    //the starting coordinates of each point, and other info
    double[][] sceneReset = {
            //4x15 size
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt:0      1       2       3       4       5       6       7       8T  9F  10Bo    11Ba    12R     13L 14c
            {-100,  100,    100,    -100,   -100,   100,    100,    -100,   0,  0,  0,      0,      0,      0,  0}, //x 0
            {-100,  -100,   100,    100,    -100,   -100,   100,    100,    0,  0,  0,      0,      0,      0,  0}, //y 1
            {100,   100,    100,    100,    -100,   -100,   -100,   -100,   0,  0,  0,      0,      0,      0,  0}, //z 2
            {1,     1,      1,      1,      1,      1,      1,      1,      1,  1,  1,      1,      1,      1,  1}  //w 3                                        //w 3
    };

    double[][] scene = new double[sceneReset.length][sceneReset[0].length];

    public void resetScene() {
        for (int i = 0; i < scene.length; i++) {
            for (int j = 0; j < scene[0].length; j++) {
                scene[i][j] = sceneReset[i][j];
            }
        }
    }

    //use this later
    //print it out for now
    //sample surfaces that are default, relative to the sides
    //unused, for reference and stored in main scene
    /*
    //this is for reference only!
    double[][] surfaceNormalsDefault = {
            //T,   F,   Bo,  Ba,   R,    L
            {0, 0, 0, 0, 1, -1}, //x
            {0, 1, 0, -1, 0, 0}, //y
            {1, 0, -1, 0, 0, 0}  //z
    };
    */
    double[][] surfaceNormals = new double[3][6];
    public void resetSN() {
        //calculate the surface normals at this point
        //calculation done HERE:
        calculateSN();
        //store it into a separete array in case
        //then put those values into the scene matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 8; j < 14; j++) {
                //vector - center
                scene[i][j] = surfaceNormals[i][j - 8];
            }
        }
        printSN();
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
    public void printSN() {
        //calculateSN();
        //not needed, as this updates through affine transformations
        System.out.println("Surface normals: ");
        printMat(surfaceNormals);
    }

    public void setSurfaceNormals(int o, int a, int b, int i) {
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
    public double[] vectorSub(int k, int o) {
        //convert column into row
        double[] pt = {scene[0][k], scene[1][k], scene[2][k]};
        //aString(pt);
        double[] og = {scene[0][o], scene[1][o], scene[2][o]};
        //aString(og);
        //return new double[] {scene[0][k], scene[1][k], scene[2][k]};

        //then subtract
        //System.out.println("vector: " + Arrays.toString(a));
        return new double[] {pt[0] - og[0], pt[1] - og[1], pt[2] - og[2]};
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

    //STATIC TRANSFORMATION MATRICES
    //translation
    public static double[][] bldTrans(double x, double y, double z) {
        return new double[][] {
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        };
    }
    //scaling
    public static double[][] bldScale(double x, double y, double z) {
        return new double[][] {
                {x, 0, 0, 0},
                {0, y, 0, 0},
                {0, 0, z, 0},
                {0, 0, 0, 1}
        };
    }
    //rotX
    public static double[][] bldX(double angle) {
        return new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
                {0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }
    //rotY
    public static double[][] bldY(double angle) {
        return new double[][]{
                {Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
                {0, 1, 0, 0},
                {-Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }
    //rotZ
    public static double[][] bldZ(double angle) {
        return new double[][]{
                {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0},
                {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
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


    //only works for a square, use sum / 8 for a cube
    public double[] center = new double[3];
    //this recalculated the center of this particular object
    public void updateC() {
        //(point 0 to point 6) / 2
        //c=         pt0: x, y, z+ pt6: x, y, z / 2
        center[0] = (scene[0][0] + scene[0][6]) / 2.0;
        center[1] = (scene[1][0] + scene[1][6]) / 2.0;
        center[2] = (scene[2][0] + scene[2][6]) / 2.0;
    }
    //testing
    public void printCenter() {
        System.out.println("Center point: ");
        System.out.println(Arrays.toString(center));
    }



    // TRANSFORMATIONS
    public void translation(double x, double y, double z) {
        scene = matMult(bldTrans(x, y, z), scene);
        updateC();
    }

    public void toOrigin() {
        scene = matMult(bldTrans(-center[0], -center[1], -center[2]), scene);
        updateC();
    }
    public void toOldCenter(double[] b) {
        scene = matMult(bldTrans(b[0], b[1], b[2]), scene);
        updateC();
    }

    public void scaling(double x, double y, double z) {
        double[] oldCenter = {center[0], center[1], center[2]};
        toOrigin();
        scene = matMult(bldScale(x, y, z), scene);
        toOldCenter(oldCenter);
        //updateC();
    }
    public void scaling(double x, double y, double z, double X, double Y, double Z) {
        translation(-X, -Y, -Z);
        scene = matMult(bldScale(x, y, z), scene);
        translation(X, Y, Z);
        //updateC();
    }

    //rotations
    // X
    public void rotateX(double angle) {
        double[] oldCenter = {center[0], center[1], center[2]};
        toOrigin();
        scene = matMult(bldX(angle), scene);
        toOldCenter(oldCenter);
    }
    public void rotateX(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        scene = matMult(bldX(angle), scene);
        translation(x, y, z);
    }

    // Y
    public void rotateY(double angle) {
        double[] oldCenter = {center[0], center[1], center[2]};
        toOrigin();
        scene = matMult(bldY(angle), scene);
        toOldCenter(oldCenter);
    }
    public void rotateY(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        scene = matMult(bldY(angle), scene);
        translation(x, y, z);
    }

    // Z
    public void rotateZ(double angle) {
        double[] oldCenter = {center[0], center[1], center[2]};
        toOrigin();
        scene = matMult(bldZ(angle), scene);
        toOldCenter(oldCenter);
    }
    public void rotateZ(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        scene = matMult(bldZ(angle), scene);
        translation(x, y, z);
    }

    //ARBITRARY
    double[][] M; // -- the transformation matrix

    // -- p is the fixed point x, y, z
    //    angle is rotation angle in degrees
    //    axis is the axis of rotation x, y, z
    //    builds the transformation matrix M
    public void arbitrary(double[] p, double angle, double[] axis) throws IllegalArgumentException
    {
        // -- convert the axis to a unit vector
        double mag = Math.sqrt(Math.pow(axis[0],  2) + Math.pow(axis[1],  2) + Math.pow(axis[2],  2));
        //System.out.println(mag);
        double[] alpha = {axis[0] / mag, axis[1] / mag, axis[2] / mag};
        //System.out.println(Arrays.toString(alpha));

        if (mag < 0.000000001) {
            throw new IllegalArgumentException("invalid axis of rotation");
        }

        double d = Math.sqrt(Math.pow(alpha[1],  2) + Math.pow(alpha[2],  2));
        //System.out.println(d);
        if (Math.abs(d) < 0.00000001) {
            // -- just rotate about the x axis

            // ADD CALL TO rotateX(P, angle) HERE
            rotateX(angle, p[0], p[1], p[2]);
            //m = xrotate((int)p[0],(int)p[1],(int)p[2],angle,scene);
            return;
        }


        // -- translate fixed point to origin
        double[][] T = bldTrans(-p[0], -p[1], -p[2]);
        // -- translate fixed point to original location
        double[][] Ti = bldTrans(p[0], p[1], p[2]);
        // -- rotate about the z axis by theta
        double[][] Rz = bldZ(angle);

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
        M = matMult(Rx, T);
        M = matMult(Ry, M);
        M = matMult(Rz, M);
        M = matMult(Riy, M);
        M = matMult(Rix, M);
        M = matMult(Ti, M);
        scene = matMult(M, scene);
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


