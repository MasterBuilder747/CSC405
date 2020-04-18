/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Square extends Polygon {

    final int SIDES = 4;
    Point[] points = new Point[SIDES];

    int[][] fb;

    public Square(int x, int y) {
        this.fb = new int[x][y];
    }

    public void add(int i, Point p) {
        if (i > -1 && i < this.SIDES) {
            this.points[i] = p;
        }
    }

    //render the lines at those coordinates
    //this renders each square
    public void renderFill(int[][] fb, int fillColor, int outColor) {
        fill(this.fb, 0, fillColor);

        updateFB(this.fb, fb);
    }

    public void renderWire(int[][] fb, int a, int b, int c, int d, int outColor) {
        drawLine(points[a], points[b], this.fb, outColor);
        drawLine(points[b], points[c], this.fb, outColor);
        drawLine(points[c], points[d], this.fb, outColor);
        drawLine(points[d], points[a], this.fb, outColor);
        updateFB(this.fb, fb);
    }
}


