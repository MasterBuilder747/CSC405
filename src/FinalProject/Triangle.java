/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

public class Triangle extends Polygon {

    final int SIDES = 3;
    Point[] points = new Point[SIDES];

    int[][] fb;

    public Triangle(int x, int y) {
        this.fb = new int[x][y];
    }

    public void add(int i, Point p) {
        if (i > -1 && i < this.SIDES) {
            this.points[i] = p;
        }
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] fb, int fillColor, int outColor) {
        renderWire(outColor);
        //fill(this.fb, 0, fillColor);
        renderWire(outColor);

        //updateFB(this.fb, fb);
    }

    public void renderWire(int outColor) {
        drawLine(points[0], points[1], this.fb, outColor);
        drawLine(points[1], points[2], this.fb, outColor);
        drawLine(points[2], points[0], this.fb, outColor);
    }
}


