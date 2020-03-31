/*
Homework 5
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-5-20
*/

package Homework.HW5Transformations;

import Homework.HW1LineDrawing.LineBase;

public class Lines extends LineBase {

    @Override
    public void twoPointForm(int x0, int y0, int x1, int y1, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //two point form
        //y = (((y1 - y0)/(x1 - x0)) * (t - x0)) + y0;
        //x = (((x1 - x0)/(y1 - y0)) * (t - y0)) + x0;
        double x = 0;
        double y = 0;

        //x0 to x1
        for (int i = x0; i < x1; i++) {
            x = (((y1 - y0)/((x1 - x0) * 1.0)) * (i - x0)) + y0;
            framebuffer[(int)x][i] = 255;
        }
        //y0 to y1
        for (int j = y0; j < y1; j++) {
            y = (((x1 - x0)/((y1 - y0) * 1.0)) * (j - y0)) + x0;
            framebuffer[j][(int)y] = 255;
        }
    }

    @Override
    public void parametricForm(int x0, int y0, int x1, int y1, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //parametric form
        /*
        𝑥 = 𝑥0 + (𝑥1 − 𝑥0)𝑡
        𝑦 = 𝑦0 + (𝑦1 − 𝑦0)𝑡
        0.0 ≤ 𝑡 ≤ 1.0
        */
        double x = 0;
        double y = 0;

        //pixelDensity var
        double pd = 1000;

        //i is the t value
        for (int i = 0; i < pd; i++) {
            x = x1 - ((x1 - x0) * (i / pd));
            y = y1 - ((y1 - y0) * (i / pd));
            framebuffer[(int)x][(int)y] = 255;
        }
    }

    //@Override
    public static void bresenhamForm(int x1, int y1, int x2, int y2, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //SOURCE: https://github.com/SagarGaniga/computer-graphics/blob/master/Bresenham's%20Line/Bresenhams.cpp
        /*
        Works Cited:

        Ganiga, Sagar. “Computer-Graphics.” GitHub, 4 Nov. 2017,
            github.com/SagarGaniga/computer-graphics/blob/master/Bresenham's%20Line/Bresenhams.cpp.

         */

        //inputs are the range and the numbers that go through the array of the framebuffer

        double dx = x2 - x1; //change in x axis
        double dy = y2 - y1; //change in y axis

        // Find Signs
        int sx = (dx >= 0) ? 1 : (-1); //if the change in x is equal to or greater than 0, sx will be 1 otherwise -1
        int sy = (dy >= 0) ? 1 : (-1); //if the change in y is equal to or greater than 0, yx will be 1 otherwise -1

        // Get Initial Points
        double x = x1;
        double y = y1;

        // Flag to check if swapping happens
        int isSwaped = 0;

        // Swap if needed
        if (Math.abs(dy) > Math.abs(dx)) { //if the magnitude of the change in y is greater than x, then:
            // swap dx and dy
            double tdx = dx; //so that dx will be larger than dy
            dx = dy;
            dy = tdx;

            isSwaped = 1; //swap variable incremented for control
        }

        // Decision parameter
        double p = 2 * Math.abs(dy) - Math.abs(dx); //p is the difference in ranges,
        //this is used for determining what direction the line will be rendered
        //Print Initial Point
        //putpixels(x,y);


            // Loop for dx times
        for (int i = 0; i < Math.abs(dx); i++) {
            // Depending on decision parameter
            if (p < 0) { //p is negative, dx is greater than dy, point is rendered above the line
                if (isSwaped == 0) {
                    x = x + sx; //plot the point at (x+1,y)
                    try {
                        framebuffer[(int) x][(int) y] = 255; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                } else {
                    y = y + sy; //plot the point at (x,y+1)
                    try {
                        framebuffer[(int) x][(int) y] = 255; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                }
                p = p + 2 * Math.abs(dy);
            } else { //p is positive, dy is greater than dx, and the point rendered is below the line
                x = x + sx; //plot the point at (x+1,y+1)
                y = y + sy;
                try {
                framebuffer[(int) x][(int) y] = 255; //plot the image
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore exception due to clipping
                }
                p = p + 2 * Math.abs(dy) - 2 * Math.abs(dx);
            }
        }
    }

    public static void mainTwoPoint(int[][] framebuffer) {
        LineBase lb = new Lines();
        for (int x = 0; x < framebuffer[0].length; x += 13) {
            lb.twoPointForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
        }
        for (int y = 0; y < framebuffer.length; y += 13) {
            lb.twoPointForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
        }
    }

    public static void mainParametric(int[][] framebuffer) {
        LineBase lb = new Lines();
        for (int x = 0; x < framebuffer[0].length; x += 13) {
            lb.parametricForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
        }
        for (int y = 0; y < framebuffer.length; y += 13) {
            lb.parametricForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
        }
    }

    public static void mainBresenham(int[][] framebuffer) {
        LineBase lb = new Lines();
        for (int x = 0; x < framebuffer[0].length; x += 13) {
            bresenhamForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
        }
        for (int y = 0; y < framebuffer.length; y += 13) {
            bresenhamForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
        }
    }

    public static void main (String[] args) {
        LineBase lb = new Lines();
        {
            int[][] framebuffer = new int[256][256];
            for (int x = 0; x < framebuffer[0].length; x += 13) {
                lb.parametricForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
            }
            for (int y = 0; y < framebuffer.length; y += 13) {
                lb.parametricForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
            }
        }
    }
}
