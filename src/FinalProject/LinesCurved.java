package FinalProject;

import javafx.scene.paint.Color;

public class LinesCurved {

    //note that this only works for an exact circle (with equal radius), not a ellipsoid
    public static void drawCircle(Point c, int radius, FrameBuffer fb, Color color)
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

    private static void writeAll(FrameBuffer fb, int xc, int yc, int x, int y, Color color)
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

    private static void writePixel(FrameBuffer fb, int x, int y, Color color)
            throws ArrayIndexOutOfBoundsException {
        try {
            //fb[x][y] = colorConvert.RGBtoInt(color);
            fb.writePixel(x, y, color);
        } catch (ArrayIndexOutOfBoundsException e) {
            //ignore due to clipping
        }
    }

    public static void drawRays(Point c, int radius, FrameBuffer fb, Color color)
            throws NullPointerException, ArrayIndexOutOfBoundsException {
        //similar code, but it draws lines outward
        //this is a failed attempt at trying to draw rays... :/
        //apparently its requires a lot of trig and other possible math

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
            writeRays(fb, xc, yc, x, y, radius, color); //displaying all the Eight Pixels of (x,y)
            //delay(30); //used to show the drawing process in animation
        }
    }

    private static void writeRays(FrameBuffer fb, int xc, int yc, int x, int y, int radius, Color color)
            throws ArrayIndexOutOfBoundsException {
        //store each point drawing in all 8 parts in the fb
        writeRay(fb, xc+x, yc+y, xc, yc, true, true, radius, color);
        writeRay(fb, xc-x, yc+y, xc, yc, false, true, radius, color);
        writeRay(fb, xc+x, yc-y, xc, yc, true, false, radius, color);
        writeRay(fb, xc-x, yc-y, xc, yc, false, false, radius, color);
        writeRay(fb, xc+y, yc+x, xc, yc, true, true, radius, color);
        writeRay(fb, xc-y, yc+x, xc, yc, false, true, radius, color);
        writeRay(fb, xc+y, yc-x, xc, yc, true, false, radius, color);
        writeRay(fb, xc-y, yc-x, xc, yc, false, false, radius, color);
    }

    private static void writeRay(FrameBuffer fb, int x1, int y1, int xc, int yc, boolean xSign, boolean ySign, int radius, Color color)
            throws ArrayIndexOutOfBoundsException {
        try {
            int dark = 0;
            Color c = Color.rgb((int)(Math.abs((color.getRed() * 255) - dark)), (int)(Math.abs((color.getGreen() * 255) - dark)), (int)(Math.abs((color.getBlue() * 255) - dark)), color.getOpacity());
            //we need to determine the point that is perpendicular (normal) to the pixel being drawn
            //so we need the angle that the pixel is at
            int x2, y2;
            int base = 10; //base length
            int rand = 10; //random value from 1 to this value to be added to base value
            int length = base + (int)(Math.random() * rand);
            int hypo = radius + length; //radius + length
            double scale = hypo / (radius * 1.0); //how much larger the triangle is increasing in size

            x2 = (int)(x1 * scale);
            y2 = (int)(y1 * scale);
            Lines.drawLine(x1, y1, x2, y2, fb, c);

            //fb.writePixel(x+1, y+1, c);
        } catch (ArrayIndexOutOfBoundsException e) {
            //ignore due to clipping
        }
    }
}
