package Lecture.Week07;

import Homework.HW5.Lines;

import java.util.Arrays;

import static Homework.HW4.MatrixMultiplication.matMult;

public class SceneGraph {

    /*

    about an arbitrary axis and privoded a fixed point (Px, Py, Pz) = P

    theta = 0 = angle

    0x != 0
    0y!= 0

    doing matrix multiplications, all are 4x4:
    z cannot be undone, it is the chosen axis to be rotating the object on the axis,
    it is what is changing the rotation

    all 4x4s:
    T = translate
    Rx = rotate x
    Ry = rotate y
    Rz = rotate z

    //IN THIS ORDER!!!!! IT MATTERS HERE
    T(P) * Rx(-0x) * Ry(-0y) * Rz(0) * Ry(0y) * Rx(0x) * T(-P) , multiply all by: |SG|
    this all collapses to a

    specified as a vector in 3D with tail (start) at (0, 0, 0)
    must be a unit vector

    A = alpha
    d = sqrt(Ay^2 + Ay^2)


    unit vector calculation:
    x, y, z = x/(sqrt(x^2+y^2+z^2)) + y/(sqrt(x^2+y^2+z^2)) + z/(sqrt(x^2+y^2+z^2))

     */

    private static double alphaX;
    private static double alphaY;
    private static double alphaZ;
    private static double d;
    static double[][] rotateXA = {
            {1, 0, 0, 0},
            {0, alphaZ/d, -alphaY/d, 0},
            {0, alphaY/d, alphaZ/d, 0},
            {0, 0, 0, 1}
    };
    static double[][] rotateYA = {
            {d, 0, alphaX, 0},
            {0, 1, 0, 0},
            {-alphaX, 0, d, 0},
            {0, 0, 0, 1}
    };


    /*
    cos(0x) = alphaX/d
    -sin(0x) = -alphaY/d
    sin(0x) = alphaX/d

    sin(-0) = -sin(0)
    cos(-0) = cos(0)

     */


    //use this later
    //print it out for now
    //sample surfaces that are default, relative to the sides
    static double[][] surfaceNormals = {
        {0, 0, 0, 0, 1, -1}, //x
        {0, 0, 1, -1, 0, 0}, //y
        {1, -1, 0, 0, 0, 0} //z
    };
    public void printSurfaceNormals() {
        printMat(surfaceNormals);
    }

    //
    //starting template, defaulted to origin
    static double[][] scene = {
        //15x4 size
        //pt1-8, 6 surface normal point of each side, 1 centroid point
        {-100,  100, 100,   -100}, //x
        {-100, -100, 100,    100}, //y
        {0,       0,   0,      0}, //z
        {1,       1,   1,      1}  //w
    };
    //  (-100, -100, 0), top left
    //  (100, -100, 0), top right
    //  (100, 100, 0), bottom right
    //  (-100, 100, 0) bottom left

    //only works for a square, use sum / 8 for a cube
    public static double[] center = new double[3];
    public static void updateC() {
        center[0] = (scene[0][0] + scene[0][2]) / 2.0;
        center[1] = (scene[1][0] + scene[1][2]) / 2.0;
        center[2] = 0;
    }

    //the position at the origin
    static double[][] square = {
        //   pt0   pt1  pt2    pt3
        {-100,  100, 100,   -100}, //x
        {-100, -100, 100,    100}, //y
        {0,       0,   0,      0}, //z
        {1,       1,   1,      1}  //w
    };
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
        System.out.println(Arrays.toString(center));
    }
    //change the coordinates of the scene
    private static void setScene(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                scene[i][j] = a[i][j];
            }
        }
    }
    public void resetShape() {
        setScene(square);
    }

    //used for cube
    //distance / 2 for each axis
    public static double sum(double[] a) {
        double sum = 0;
        for (double v : a) {
            sum += v;
        }
        return sum;
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
    }


    //render the lines at those coordinates
    public void render(int[][] framebuffer) { //add grey for cube
        Lines.bresenhamForm((int)0, (int)0, (int)0, (int)0, framebuffer);
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][0], (int)scene[1][0], framebuffer);
    }
}
