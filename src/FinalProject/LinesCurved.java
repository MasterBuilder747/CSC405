package FinalProject;

import javafx.scene.paint.Color;

public class LinesCurved {

    //note that this only works for an exact circle (with equal radius), not a ellipsoid
    public static void drawCircle(Point c, int radius, int[][] fb, Color color)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //Found here, converted from C++:
        //SOURCE: https://iq.opengenus.org/bresenhams-circle-drawing-algorithm/
        /*
        Works Cited

        Chaudhari, Piyush  Rajendra. “Bresenham's Circle Drawing Algorithm.” OpenGenus IQ: , Learn
            Computer Science, 6 Mar. 2019, iq.opengenus.org/bresenhams-circle-drawing-algorithm/.

         */

        //get vars
        int x = 0;
        int y = radius;
        int xc = (int)c.x;
        int yc = (int)c.y;

        //decision parameter
        int dp = 3 - (2 * radius);

        //write initial points to compare them
        writeAll(fb, xc, yc, x, y, color);

        //change values according to the algorithm,
        //then write the new values into the fb during certain circumstances
        while (y >= x) {
            x++;
            if (dp > 0) {
                y--;
                dp = dp + 4 * (x - y) + 10;
            } else {
                dp = dp + 4 * x + 6;
            }
            writeAll(fb, xc, yc, x, y, color); //displaying all the Eight Pixels of (x,y)
            //delay(30); //used to show the drawing process in animation
        }
    }

    private static void writeAll(int[][] fb, int xc, int yc, int x, int y, Color color)
            throws ArrayIndexOutOfBoundsException {
        //store each point drawing in all 8 parts in the fb
        writePixel(fb, xc+x, yc+y, color);
        writePixel(fb, xc-x, yc+y, color);
        writePixel(fb, xc+x, yc-y, color);
        writePixel(fb, xc-x, yc-y, color);
        writePixel(fb, xc+y, yc+x, color);
        writePixel(fb, xc-y, yc+x, color);
        writePixel(fb, xc+y, yc-x, color);
        writePixel(fb, xc-y, yc-x, color);
    }

    private static void writePixel(int[][] fb, int x, int y, Color color)
            throws ArrayIndexOutOfBoundsException {
        try {
            //fb[x][y] = colorConvert.RGBtoInt(color);
        } catch (ArrayIndexOutOfBoundsException e) {
            //ignore due to clipping
        }
    }
}
