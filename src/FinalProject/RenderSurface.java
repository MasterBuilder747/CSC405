/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class RenderSurface extends WritableImage {
	
	private frameBuffer surface;
	private final int x;
	private final int y;

	//initial image when starting
	public RenderSurface(int width, int height) {
		super(width, height);
		this.x = width;
		this.y = height;
		this.surface = new frameBuffer(width, height);
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				surface.writePixel(i, j, Color.rgb(0, 0, 0, 1.0));
				// colorConvertOld.RGBtoInt(Color.rgb(100, 0, 0, 1.0));
			}
		}
		this.insertArray();
	}

	public void clearSurface() {
		this.surface.setFill(Color.rgb(0, 0, 0, 1.0));
	}
	
	public frameBuffer getSurface() {
		return this.surface;
		//return new int[3][3];
	}
	
    public void insertArray() {
        //Creating a writable image
    	int height = this.surface.fb.length;
    	int width = this.surface.fb[0].length;

        //getting the pixel writer 
        PixelWriter writer = this.getPixelWriter();

        for(int y = 0; y < height; y++) {
        	for(int x = 0; x < width; x++) {
           		//this inserts the fb into the screen
				writer.setColor(x, y, this.surface.readPixel(x, y));
           	}
        }
    }
    
	public BufferedImage toImage() {
		BufferedImage bi = new BufferedImage(surface.fb[0].length, surface.fb.length, BufferedImage.TYPE_INT_RGB);
    	
    	// -- prepare output image
    	for (int i = 0; i < bi.getHeight(); ++i) {
    	    for (int j = 0; j < bi.getWidth(); ++j) {
    			//int pixel =	(surface[i][j] << 16) | (surface[i][j] << 8) | (surface[i][j]);
//    			int pixel =	((int)(Math.random() * 255) << 16) | ((int)(Math.random() * 255) << 8) | ((int)(Math.random() * 255));
    			Color c = surface.readPixel(x, y);
				bi.setRGB(j, i, colorConvert.RGBtoInt(c));
    		}
    	}

    	return bi;
	}
}
