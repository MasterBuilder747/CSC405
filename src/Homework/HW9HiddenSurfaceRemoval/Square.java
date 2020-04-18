/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Square extends Polygon {

    //internal framebuffer
    int[][] fb;

    public Square(int x, int y) {
        this.fb = new int[x][y];
    }

    public void render(int[][] fb, double[][] points, int a, int b, int c, int d, int fillColor, int outColor) {
        //write the render to this individual surface's framebuffer
        renderWire(points, a, b, c, d, outColor);
        fill(this.fb, 0, fillColor);
        //update the cube's framebuffer with the filled square
        updateFB(this.fb, fb);
    }

    public void renderWire(double[][] points, int a, int b, int c, int d, int outColor) {
        drawLine((int)points[0][a], (int)points[1][a], (int)points[0][b], (int)points[1][b], this.fb, outColor);
        drawLine((int)points[0][b], (int)points[1][b], (int)points[0][c], (int)points[1][c], this.fb, outColor);
        drawLine((int)points[0][c], (int)points[1][c], (int)points[0][d], (int)points[1][d], this.fb, outColor);
        drawLine((int)points[0][d], (int)points[1][d], (int)points[0][a], (int)points[1][a], this.fb, outColor);
    }
}


