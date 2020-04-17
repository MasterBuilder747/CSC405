/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lines {

    public static void drawLine(int x1, int y1, int x2, int y2, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        int color = 255; //default

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
                        framebuffer[(int) x][(int) y] = color; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                } else {
                    y = y + sy; //plot the point at (x,y+1)
                    try {
                        framebuffer[(int) x][(int) y] = color; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                }
                p = p + 2 * Math.abs(dy);
            } else { //p is positive, dy is greater than dx, and the point rendered is below the line
                x = x + sx; //plot the point at (x+1,y+1)
                y = y + sy;
                try {
                    framebuffer[(int) x][(int) y] = color; //plot the image
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore exception due to clipping
                }
                p = p + 2 * Math.abs(dy) - 2 * Math.abs(dx);
            }
        }
    }

    public static void drawLine(int x1, int y1, int x2, int y2, int[][] framebuffer, int color)
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
                        framebuffer[(int) x][(int) y] = color; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                } else {
                    y = y + sy; //plot the point at (x,y+1)
                    try {
                        framebuffer[(int) x][(int) y] = color; //plot the image
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //ignore exception due to clipping
                    }
                }
                p = p + 2 * Math.abs(dy);
            } else { //p is positive, dy is greater than dx, and the point rendered is below the line
                x = x + sx; //plot the point at (x+1,y+1)
                y = y + sy;
                try {
                framebuffer[(int) x][(int) y] = color; //plot the image
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignore exception due to clipping
                }
                p = p + 2 * Math.abs(dy) - 2 * Math.abs(dx);
            }
        }
    }

    public static void ImageWrite(int[][] img, String filename) throws IOException
    {
        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            // -- prepare output image
            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int pixel =	(img[i][j] << 16) | (img[i][j] << 8) | (img[i][j]);
                    // int pixel =	((int)(Math.random() * 255) << 16) | (img[i][j] << 8) | (img[i][j]);
                    bi.setRGB(j, i, pixel);
                }
            }

            // -- write output image
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        }
        catch (IOException e) {
            throw e;
        }
    }
}
