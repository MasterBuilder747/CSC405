/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Triangle extends Polygon {

    final int SIDES = 3;
    Point[] points = new Point[SIDES];

    int[][] fb;

    public Triangle(int x, int y) {
        this.fb = new int[x][y];
    }

    public void add(int i, Point p) {
        if (i > -1 && i < SIDES) {
            this.points[i] = p;
        }
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] fb, int fillColor, int outColor) {
        renderWire(outColor);
        fill(this.fb, 0, fillColor);
        renderWire(outColor);

        updateFB(this.fb, fb);
    }

    public void renderWire(int outColor) {
        drawLine((int)points[0].y, (int)points[0].x, (int)points[1].y, (int)points[1].x, this.fb, outColor);
        drawLine((int)points[1].y, (int)points[1].x, (int)points[2].y, (int)points[2].x, this.fb, outColor);
        drawLine((int)points[2].y, (int)points[2].x, (int)points[0].y, (int)points[0].x, this.fb, outColor);
    }
}


