/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Cube extends Polygon {

    final int SQUARES = 6;
    Square[] squares = new Square[SQUARES];

    int[][] fb;
    double[][] points;
    Transformations t;

    int x;
    int y;

    public Cube(int x, int y, int size) {
        this.x = x;
        this.y = y;

        //set points
        this.points = new double[][] {
                //4x15 size
                //[rowID][colID]
                //pt1-8, 6 surface normal point of each side, 1 centroid point
                                                                    //8T 9F 10Bo 11Ba 12R 13L 14c
                {-size, size, size, -size, -size, size, size, -size, 0, 0, 0, 0, 0, 0, 0}, //x 0
                {-size, -size, size, size, -size, -size, size, size, 0, 0, 0, 0, 0, 0, 0}, //y 1
                {size, size, size, size, -size, -size, -size, -size, 0, 0, 0, 0, 0, 0, 0}, //z 2
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}  //w 3
        };
        this.calculateSN(this.points);
        this.calculateCenter();

        //allows for this particular cube to be transformed on its own set of points
        this.t = new Transformations(this.points);

        //set individual frame buffers
        this.fb = new int[x][y];
        for (int i = 0; i < this.SQUARES; i++) {
            this.squares[i] = new Square(x, y);
        }
    }

    /*
    this.points = new double[][] {
                //4x15 size
                //[rowID][colID]
                //pt1-8, 9-13: surface normals, 14: centroid point
                {-1, 1, 1, -1, -1, 1, 1, -1, 0, 0, 0, 0, 0, 0, 0}, //x 0
                {-1, -1, 1, 1, -1, -1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, //y 1
                {1, 1, 1, 1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0}, //z 2
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}  //w 3
    };
    */

    //initial calculations during construction
    //center
    public void calculateCenter() {
        //(point 0 to point 6) / 2
        //c=         pt0: x, y, z+ pt6: x, y, z / 2
        this.points[0][14] = (this.points[0][0] + this.points[0][6]) / 2.0;
        this.points[1][14] = (this.points[1][0] + this.points[1][6]) / 2.0;
        this.points[2][14] = (this.points[2][0] + this.points[2][6]) / 2.0;
    }
    //surface normals
    public void calculateSN(double[][] points) {
        //top: 3: 2, 0
        this.setSurfaceNormals(points, 3, 0, 2, 8);
        //front: 7: 6, 3
        this.setSurfaceNormals(points, 7, 3, 6, 9);
        //bottom: 4, 5, 7
        this.setSurfaceNormals(points, 4, 7, 5, 10);
        //back: 0: 1, 4
        this.setSurfaceNormals(points, 0, 4, 1, 11);
        //right: 6: 5, 2
        this.setSurfaceNormals(points, 6, 2, 5, 12);
        //left: 4: 7, 0
        this.setSurfaceNormals(points, 4, 0, 7, 13);
    }
    public void setSurfaceNormals(double[][] points, int o, int a, int b, int i) {
        //cross product of two vector lengths on each face
        //vector sub always returns a length 3 array of x, y, z based on ints specified
        //cross returns a length 3 array of distance vector
        double[] cross = SurfaceNormal.cross(vectorSub(points, a, o), vectorSub(points, b, o));
        double cm = SurfaceNormal.mag(cross);

        //x(c)/mag(c), y(c)/mag(c), z(c)/mag(c)
        points[0][i] = cross[0] / cm;
        points[1][i] = cross[1] / cm;
        points[2][i] = cross[2] / cm;
    }
    public double[] vectorSub(double[][] points, int k, int o) {
        //convert column into row
        double[] pt = {points[0][k], points[1][k], points[2][k]};
        double[] og = {points[0][o], points[1][o], points[2][o]};

        return new double[] {pt[0] - og[0], pt[1] - og[1], pt[2] - og[2]};
    }

    public void render(int[][] fb) {
        //these may be definable later
        int outColor = 255;
        //the custom fill colors
        //top, front, bottom, back, right, left
        int[] surfaceColor = {255, 220, 180, 140, 100, 60};

        this.clearFB();

        //surface normals need to be recalculated every time so that accurate numbers can be tested
        this.calculateSN(this.t.points);

        //testing: vector multiplication?

        //unit circle:
        //sin(0) = 0
        //sin(90) = 1
        //sin(180) = 0
        //sin(180) = 0
        //cos(0) = 1
        //cos(1) = 1

        //top: 0, 1, 2, 3
        //x, y, z
        //0, 0, 1 (only one shown: aka the viewer vector)
        if (this.t.points[2][8] > 0 && this.t.points[2][8] < 1.00000000001) {
            //System.out.println("render top");
            squares[0].render(this.fb, this.t.points, 0, 1, 2, 3, surfaceColor[0], outColor);
        }
        //front: 3, 2, 6, 7
        //0, 1, 0
        if (this.t.points[2][9] > 0 && this.t.points[2][9] < 1.00000000001) {
            //System.out.println("render front");
            squares[1].render(this.fb, this.t.points, 3, 2, 6, 7, surfaceColor[1], outColor);
        }
        //bottom: 7, 6, 5, 4
        //0, 0, -1
        if (this.t.points[2][10] > 0 && this.t.points[2][10] < 1.00000000001) {
            //System.out.println("render bottom");
            squares[2].render(this.fb, this.t.points, 7, 6, 5, 4, surfaceColor[2], outColor);
        }
        //back: 4, 5, 1, 0
        //0, -1, 0
        if (this.t.points[2][11] > 0 && this.t.points[2][11] < 1.00000000001) {
            //System.out.println("render back");
            squares[3].render(this.fb, this.t.points, 4, 5, 1, 0, surfaceColor[3], outColor);
        }
        //right: 2, 1, 5, 6
        //1, 0, 0
        if (this.t.points[2][12] > 0 && this.t.points[2][12] < 1.00000000001) {
            //System.out.println("render right");
            squares[4].render(this.fb, this.t.points, 2, 1, 5, 6, surfaceColor[4], outColor);
        }
        //left: 0, 3, 7, 4
        //-1, 0, 0
        if (this.t.points[2][13] > 0 && this.t.points[2][13] < 1.00000000001) {
            //System.out.println("render left");
            squares[5].render(this.fb, this.t.points, 0, 3, 7, 4, surfaceColor[5], outColor);
        }

        //this.points = this.t.points;
        //update the scenegraph's framebuffer
        updateFB(this.fb, fb);
    }

    public void clearFB() {
        this.fb = new int[x][y];
    }
}
