/*
Homework 3
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-11-20
*/

//SOURCE: https://github.com/SagarGaniga/computer-graphics/blob/master/Bresenham's%20Line/Bresenhams.cpp
/*
Works Cited:

Ganiga, Sagar. “Computer-Graphics.” GitHub, 4 Nov. 2017,
    github.com/SagarGaniga/computer-graphics/blob/master/Bresenham's%20Line/Bresenhams.cpp.

 */



//converted from C++ to Java code:
//this is a standalone class, not used

package Homework.HW3;

public class Bresenham {

    public static void bresLine(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        // Find Signs
        int sx = (dx >= 0) ? 1 : (-1);
        int sy = (dy >= 0) ? 1 : (-1);

        // Get Initial Points
        double x = x1;
        double y = y1;

        // Flag to check if swapping happens
        int isSwaped = 0;

        // Swap if needed
        if (Math.abs(dy) > Math.abs(dx)) {
            // swap dx and dy
            double tdx = dx;
            dx = dy;
            dy = tdx;

            isSwaped = 1;
        }

        // Decision parameter
        double p = 2 * (Math.abs(dy)) - Math.abs(dx);

        //Print Initial Point
        //putpixels(x,y);

        // Loop for dx times
        for (int i = 0; i <= Math.abs(dx); i++) {
            // Depending on decision parameter
            if (p < 0) {
                if (isSwaped == 0) {
                    x = x + sx;
                    //putpixels(x,y);
                } else {
                    y = y + sy;
                    //putpixels(x,y);
                }
                p = p + 2 * Math.abs(dy);
            } else {
                x = x + sx;
                y = y + sy;
                //putpixels(x,y);
                p = p + 2 * Math.abs(dy) - 2 * Math.abs(dx);
            }
        }
    }
/*
    public static void main(String[] args) {
        //int gd = DETECT, gm;

        // Initialize graphics function
        //initgraph (&gd, &gm, "");

        double h = (double) getwindowheight();
        double w = (double) getwindowwidth();

        // Axis
        bresLine(-w / 2, 0, w / 2, 0);
        bresLine(0, h / 2, 0, -h / 2);

        //Input values

        int[][] p = {{50, 50, 50, 60, 60, 60, 60, 50, 60, 200, 200, 200, 200, 60, 60, 120, 120, 60},
                {50, 200, 200, 200, 200, 50, 50, 50, 200, 200, 200, 120, 120, 120, 120, 160, 160, 200}};

        for (int i = 0; i < 18; i = i + 2) {
            bresLine(p[0][i], p[1][i], p[0][i + 1], p[1][i + 1]);
        }
    }
*/
}