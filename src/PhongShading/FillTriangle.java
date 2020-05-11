package PhongShading;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FillTriangle 
{

	/*
	 * This code is not optimized. Redundant calculations can be eliminated
	 * and some arithmetic operations can be reduced. It is left this way
	 * to match the lecture notes.
	 * 
	 */
	public static void BresenhamLine(int x0, int y0, int x1, int y1, Graphics2D g2d)
    {

        // -- line always starts on x0, y0 regardless of location of x1, y1
        int x = x0; 
        int y = y0;

        // -- increments of x and y are dependent on the slope of the line
        //    (location of x1, y1 relative to x0, y0
        int xinc = (x0 > x1) ? -1 : 1;
        int yinc = (y0 > y1) ? -1 : 1;
        
		// -- change in x and y used to determine the slope of the line
		//    and to set dE and dNE
        int dx = Math.abs(x1 - x0);    
        int dy = Math.abs(y1 - y0);

        // -- 0-45 degree line (first quadrant reference)
        if (dx >= dy) {   
		    int dE = dy;
		    int dNE = dy - dx;
		    int d = dy - (dx / 2); 
		    
		    // -- Loop over the x length of the line. This will handle both cases (x0 > x1 and x0 < x1)
		    for (dx = 0; dx < Math.abs(x1 - x0); ++ dx) {
				g2d.fillRect(x, y, 1, 1);    
				if (d > 0) {                             
				    x += xinc;                                        
				    y += yinc;                                        
				    d += dNE;                                         
				}
				else {                                                                    
				    x += xinc;                                        
				    d += dE;                                          
				}
		    }
        }
        // -- 45-90 degree line (first quadrant reference)
        else {  
		    int dE = dx;
		    int dNE = dx - dy;
		    int d = dx - (dy / 2);       
		    // -- Loop over the y length of the line. This will handle both cases (y0 > y1 and y0 < y1)
		    for (dy = 0; dy < Math.abs(y1 - y0); ++dy) {
				g2d.fillRect(x, y, 1, 1);    
				if (d > 0)  { 
				    x += xinc;                                        
				    y += yinc;                                        
				    d += dNE;                                         
				}
				else {                                              
				    y += yinc;                                        
				    d += dE;                                          
				}
		    }
        }
    }

	public static void BresenhamLine(int x0, int y0, int x1, int y1, int c0, int c1, int FrameBuffer[][])
    {

        // -- line always starts on x0, y0 regardless of location of x1, y1
        int x = x0; 
        int y = y0;

        // -- increments of x and y are dependent on the slope of the line
        //    (location of x1, y1 relative to x0, y0
        int xinc = (x0 > x1) ? -1 : 1;
        int yinc = (y0 > y1) ? -1 : 1;
        
		// -- change in x and y used to determine the slope of the line
		//    and to set dE and dNE
        int dx = Math.abs(x1 - x0);    
        int dy = Math.abs(y1 - y0);

        // -- 0-45 degree line (first quadrant reference)
        if (dx >= dy) {   
		    int dE = dy;
		    int dNE = dy - dx;
		    int d = dy - (dx / 2); 
		    double cstep = (double)(c1 - c0) / Math.abs(x1 - x0);
		    double c = c0;
		    // -- Loop over the x length of the line. This will handle both cases (x0 > x1 and x0 < x1)
		    for (dx = 0; dx < Math.abs(x1 - x0); ++ dx) {
				FrameBuffer[y][x] = (int)c;
				c += cstep;
				if (d > 0) {                             
				    x += xinc;                                        
				    y += yinc;                                        
				    d += dNE;                                         
				}
				else {                                                                    
				    x += xinc;                                        
				    d += dE;                                          
				}
		    }
        }
        // -- 45-90 degree line (first quadrant reference)
        else {  
		    int dE = dx;
		    int dNE = dx - dy;
		    int d = dx - (dy / 2);       
		    double cstep = (double)(c1 - c0) / Math.abs(y1 - y0);
		    double c = c0;
		    
		    // -- Loop over the y length of the line. This will handle both cases (y0 > y1 and y0 < y1)
		    for (dy = 0; dy < Math.abs(y1 - y0); ++dy) {
				FrameBuffer[y][x] = (int)c;
				c += cstep;
				if (d > 0)  { 
				    x += xinc;                                        
				    y += yinc;                                        
				    d += dNE;                                         
				}
				else {                                              
				    y += yinc;                                        
				    d += dE;                                          
				}
		    }
        }
    }
	
	public static void FillRecursive(int x, int y, int FrameBuffer[][], int background)
	{
		if (FrameBuffer[y][x] != background)
			return;
		FrameBuffer[y][x] = 255;
		if (x > 0) FillRecursive(x - 1, y, FrameBuffer, background);
		if (y > 0) FillRecursive(x, y - 1, FrameBuffer, background);
		if (x < FrameBuffer[0].length - 1) FillRecursive(x + 1, y, FrameBuffer, background);
		if (y < FrameBuffer.length - 1) FillRecursive(x, y + 1, FrameBuffer, background);
		
	}
	
	public static void FillIterative(int FrameBuffer[][], int background)
	{
		// -- assume that the frame buffer contains only 1 triangle
		
		// -- loop through all rows of the frame buffer
		for (int y = 0; y < FrameBuffer.length; ++y) {
			
			// -- for the current row find the left side of the triangle
			int j = 0;
			while (j < FrameBuffer[0].length && FrameBuffer[y][j] == background) ++j;
			if (j == FrameBuffer[0].length) continue;
			int x0 = j;
			int c0 = FrameBuffer[y][j];
			
			// -- for the current row, find the right side of the triangle
			j = FrameBuffer[0].length - 1;
			while (j > 0 && FrameBuffer[y][j] == background) --j;
//			if (j == -1) continue;
			int x1 = j;
			int c1 = FrameBuffer[y][j];
			
	    	BresenhamLine(x0, y, x1, y, c0, c1, FrameBuffer);
		}
	}
	
	public static void main (String[] args)
	{
		
		try {

			// -- create frame buffer with background of -1 (no color)
	    	int framebuffer[][] = new int[1024][1024];
	    	for (int i = 0; i < framebuffer.length; ++i) {
	    		for (int j = 0; j < framebuffer[i].length; ++j) {
	    			framebuffer[i][j] = -1;
	    		}
	    	}
	    	
	    	// -- scan convert a triangle
//	    	BresenhamLine(512, 100,  256, 350, 32, 128, framebuffer);
//	    	BresenhamLine(256,  350, 768, 350, 128, 255, framebuffer);
//	    	BresenhamLine(768, 350, 512, 100, 255, 64, framebuffer);
	    	BresenhamLine(512, 100,  256, 350, 255, 255, framebuffer);
	    	BresenhamLine(256,  350, 768, 350, 255, 255, framebuffer);
	    	BresenhamLine(768, 350, 512, 100, 255, 255, framebuffer);
	    	
	    	// -- fill/shade the triangle
//	    	FillRecursive(512, 125, framebuffer, 0);
	    	FillIterative(framebuffer, -1);
	    	
	    	// -- prepare output image
	    	BufferedImage bi = new BufferedImage(framebuffer[0].length, framebuffer.length, BufferedImage.TYPE_INT_RGB);
	    	for (int i = 0; i < bi.getHeight(); ++i) {
	    	    for (int j = 0; j < bi.getWidth(); ++j) {
	    	    	int val = (framebuffer[i][j] > 0) ? framebuffer[i][j] : 0;
	    			int pixel =	(val << 16) | (val << 8) | (val);
	    			bi.setRGB(j, i, pixel);
	    		}
	    	}

	    	// -- write output image

	    	File outputfile = new File("triangle.png");
	    	ImageIO.write(bi, "png", outputfile);

	    } catch (IOException e) {
	        System.out.println("image I/O error");
	    }
	}
	

}

