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

    public Cube(int x, int y) {
        //set points
        this.points = new double[][] {
                //4x15 size
                //[rowID][colID]
                //pt1-8, 9-13: surface normals, 14: centroid point
                {-1, 1, 1, -1, -1, 1, 1, -1, 0, 0, 0, 0, 0, 0, 0}, //x 0
                {-1, -1, 1, 1, -1, -1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, //y 1
                {1, 1, 1, 1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0}, //z 2
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}  //w 3
        };
        this.calculateSN();
        this.calculateCenter();

        //set individual frame buffers
        this.fb = new int[x][y];
        for (int i = 0; i < this.SQUARES; i++) {
            this.squares[i] = new Square(x, y);
        }

        //allows for this particular cube to be transformed on its own set of points
        this.t = new Transformations(this.points);
    }

    public void setSize(int size) {
        this.points = new double[][] {
                //4x15 size
                //[rowID][colID]
                //pt1-8, 6 surface normal point of each side, 1 centroid point
                {-size, size, size, -size, -size, size, size, -size, 0, 0, 0, 0, 0, 0, 0}, //x 0
                {-size, -size, size, size, -size, -size, size, size, 0, 0, 0, 0, 0, 0, 0}, //y 1
                {size, size, size, size, -size, -size, -size, -size, 0, 0, 0, 0, 0, 0, 0}, //z 2
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}  //w 3
        };
    }

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
    public void calculateSN() {
        //top: 3: 2, 0
        setSurfaceNormals(3, 0, 2, 8);
        //front: 7: 6, 3
        setSurfaceNormals(7, 3, 6, 9);
        //bottom: 4, 5, 7
        setSurfaceNormals(4, 7, 5, 10);
        //back: 0: 1, 4
        setSurfaceNormals(0, 4, 1, 11);
        //right: 6: 5, 2
        setSurfaceNormals(6, 2, 5, 12);
        //left: 4: 7, 0
        setSurfaceNormals(4, 0, 7, 13);
    }
    public void setSurfaceNormals(int o, int a, int b, int i) {
        //cross product of two vector lengths on each face
        //vector sub always returns a length 3 array of x, y, z based on ints specified
        //cross returns a length 3 array of distance vector
        double[] cross = SurfaceNormal.cross(vectorSub(a, o), vectorSub(b, o));
        double cm = SurfaceNormal.mag(cross);

        //x(c)/mag(c), y(c)/mag(c), z(c)/mag(c)
        this.points[0][i] = cross[0] / cm;
        this.points[1][i] = cross[1] / cm;
        this.points[2][i] = cross[2] / cm;
    }
    public double[] vectorSub(int k, int o) {
        //convert column into row
        double[] pt = {points[0][k], points[1][k], points[2][k]};
        double[] og = {points[0][o], points[1][o], points[2][o]};

        return new double[] {pt[0] - og[0], pt[1] - og[1], pt[2] - og[2]};
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] fb, int fillColor, int outColor) {
        int outlineColor = 255;
        int[] surfaceColor = {255, 192, 255, 192, 128, 128};

        //renderWire(outColor);
        //fill(this.fb, 0, fillColor);
        //renderWire(outColor);

        updateFB(this.fb, fb);
    }

    public void renderWire(int[][] fb, int outColor) {
        //top (default): 0, 1, 2, 3

        //front: 3, 2, 6, 7

        //bottom: 7, 6, 5, 4

        //back: 4, 5, 1, 0

        //right: 2, 1, 5, 6

        //left: 0, 3, 7, 4

        updateFB(this.fb, fb);
    }
}
