/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Transformations extends Matrix {

    double[][] points;

    public Transformations(double[][] points) {
        this.points = points;
    }

    //STATIC TRANSFORMATION MATRICES
    //translation
    public double[][] bldTrans(double x, double y, double z) {
        return new double[][] {
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        };
    }
    //scaling
    public double[][] bldScale(double x, double y, double z) {
        return new double[][] {
                {x, 0, 0, 0},
                {0, y, 0, 0},
                {0, 0, z, 0},
                {0, 0, 0, 1}
        };
    }
    //rotX
    public double[][] bldX(double angle) {
        return new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
                {0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }
    //rotY
    public double[][] bldY(double angle) {
        return new double[][]{
                {Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
                {0, 1, 0, 0},
                {-Math.sin(Math.toRadians(angle)), 0, Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }
    //rotZ
    public double[][] bldZ(double angle) {
        return new double[][]{
                {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0, 0},
                {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

    public double[] getCenter() {
        return new double[] {points[0][14], points[1][14], points[2][14]};
    }
    public double[] getCenterNeg() {
        return new double[] {-points[0][14], -points[1][14], -points[2][14]};
    }

    // TRANSFORMATIONS
    public void translation(double x, double y, double z) {
        points = matMult((bldTrans(x, y, z)), points);
    }

    public void toOrigin() {
        double[] center = getCenterNeg();
        points = matMult(bldTrans(center[0], center[1], center[2]), points);
    }
    //take an old stored center
    public void toOldCenter(double[] a) {
        points = matMult((bldTrans(a[0], a[1], a[2])), points);
    }

    //requires other multiplications
    public void scaling(double x, double y, double z) {
        double[] oldCenter = getCenter();
        toOrigin();
        points = matMult(bldScale(x, y, z), points);
        toOldCenter(oldCenter);
    }
    public void scaling(double x, double y, double z, double X, double Y, double Z) {
        translation(-X, -Y, -Z);
        points = matMult(bldScale(x, y, z), points);
        translation(X, Y, Z);
    }

    //rotations
    // X
    public void rotateX(double angle) {
        double[] oldCenter = getCenter();
        toOrigin();
        points = matMult(bldX(angle), points);
        toOldCenter(oldCenter);
    }
    public void rotateX(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        points = matMult(bldX(angle), points);
        translation(x, y, z);
    }

    // Y
    public void rotateY(double angle) {
        double[] oldCenter = getCenter();
        toOrigin();
        points = matMult(bldY(angle), points);
        toOldCenter(oldCenter);
    }
    public void rotateY(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        points = matMult(bldY(angle), points);
        translation(x, y, z);
    }

    // Z
    public void rotateZ(double angle) {
        double[] oldCenter = getCenter();
        toOrigin();
        points = matMult(bldZ(angle), points);
        toOldCenter(oldCenter);
    }
    public void rotateZ(double angle, double x, double y, double z) {
        translation(-x, -y, -z);
        points = matMult(bldZ(angle), points);
        translation(x, y, z);
    }

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
        points = matMulti(new double[][][] {Ti, Rix, Riy, Rz, Ry, Rx, T, points});
    }

}
