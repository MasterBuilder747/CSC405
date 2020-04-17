/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class SceneGraph extends Transformations {

    //a collection of objects on the scene

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] framebuffer) { //add grey for cube
        //render 24 lines, 6 squares in total here
        //from looking top down to it:

        //top, front, bottom, back, right, left

        //face: points in clockwise rotation from looking at the surface facing outward:
        //top (default): 0, 1, 2, 3
        /*
        Lines.drawLine((int) points[0][0], (int) points[1][0], (int) points[0][1], (int) points[1][1], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][1], (int) points[1][1], (int) points[0][2], (int) points[1][2], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][2], (int) points[1][2], (int) points[0][3], (int) points[1][3], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][3], (int) points[1][3], (int) points[0][0], (int) points[1][0], framebuffer, outlineColor);

        //front: 3, 2, 6, 7
        Lines.drawLine((int) points[0][3], (int) points[1][3], (int) points[0][2], (int) points[1][2], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][2], (int) points[1][2], (int) points[0][6], (int) points[1][6], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][6], (int) points[1][6], (int) points[0][7], (int) points[1][7], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][7], (int) points[1][7], (int) points[0][3], (int) points[1][3], framebuffer, outlineColor);

        //bottom: 7, 6, 5, 4
        Lines.drawLine((int) points[0][7], (int) points[1][7], (int) points[0][6], (int) points[1][6], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][6], (int) points[1][6], (int) points[0][5], (int) points[1][5], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][5], (int) points[1][5], (int) points[0][4], (int) points[1][4], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][4], (int) points[1][4], (int) points[0][7], (int) points[1][7], framebuffer, outlineColor);

        //back: 4, 5, 1, 0
        Lines.drawLine((int) points[0][4], (int) points[1][4], (int) points[0][5], (int) points[1][5], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][5], (int) points[1][5], (int) points[0][1], (int) points[1][1], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][1], (int) points[1][1], (int) points[0][0], (int) points[1][0], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][0], (int) points[1][0], (int) points[0][4], (int) points[1][4], framebuffer, outlineColor);

        //right: 2, 1, 5, 6
        Lines.drawLine((int) points[0][2], (int) points[1][2], (int) points[0][1], (int) points[1][1], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][1], (int) points[1][1], (int) points[0][5], (int) points[1][5], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][5], (int) points[1][5], (int) points[0][6], (int) points[1][6], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][6], (int) points[1][6], (int) points[0][2], (int) points[1][2], framebuffer, outlineColor);

        //left: 0, 3, 7, 4
        Lines.drawLine((int) points[0][0], (int) points[1][0], (int) points[0][3], (int) points[1][3], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][3], (int) points[1][3], (int) points[0][7], (int) points[1][7], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][7], (int) points[1][7], (int) points[0][4], (int) points[1][4], framebuffer, outlineColor);
        Lines.drawLine((int) points[0][4], (int) points[1][4], (int) points[0][0], (int) points[1][0], framebuffer, outlineColor);
         */
    }

    //the starting coordinates of each point, and other info
    //0-7: points
    //8-13: surface normals
    //14: center
    //cube
    double[][] resetPoints = {
            //4x15 size
            //[rowID][colID]
            //pt1-8, 6 surface normal point of each side, 1 centroid point
        //pt:0      1       2       3       4       5       6       7       8T  9F  10Bo    11Ba    12R     13L 14c
            {-100,  100,    100,    -100,   -100,   100,    100,    -100,   0,  0,  0,      0,      0,      0,  0}, //x 0
            {-100,  -100,   100,    100,    -100,   -100,   100,    100,    0,  0,  0,      0,      0,      0,  0}, //y 1
            {100,   100,    100,    100,    -100,   -100,   -100,   -100,   0,  0,  0,      0,      0,      0,  0}, //z 2
            {1,     1,      1,      1,      1,      1,      1,      1,      1,  1,  1,      1,      1,      1,  1}  //w 3                                        //w 3
    };
    //double[][] points = new double[resetPoints.length][resetPoints[0].length];

    //"Constructor" to the parent
    public void resetScene() {
        points = resetPoints;
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
        calculateSN(); //only dones when reset, will get modified otherwise
        //store it into a separete array in case
        //then put those values into the scene matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 8; j < 14; j++) {
                //vector - center
                points[i][j] = surfaceNormals[i][j - 8];
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
        //printMat(surfaceNormals);
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
        double[] pt = {points[0][k], points[1][k], points[2][k]};
        //aString(pt);
        double[] og = {points[0][o], points[1][o], points[2][o]};
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
}
