/*
Homework 4
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-21-20
*/

package Homework.HW4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class LineBase {
    public abstract void twoPointForm(int x0, int y0, int x1, int y1, int[][] framebuffer) throws NullPointerException, ArrayIndexOutOfBoundsException;

    public abstract void parametricForm(int x0, int y0, int x1, int y1, int[][] framebuffer) throws NullPointerException, ArrayIndexOutOfBoundsException;

    public abstract void bresenhamForm(int x0, int y0, int x1, int y1, int[][] framebuffer) throws NullPointerException, ArrayIndexOutOfBoundsException;

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