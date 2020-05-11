package PhongShading.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class ScanConvert 
{
	private static int brushmask[][] = 
	{ {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 1, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
	  {0, 0, 0, 0, 0, 0, 0, 0, 0}}; 
	public static int imageNo = 0;
	
	public static void SetBrush (int _r) 
	{
		if (_r < 1) _r = 1;
		if (_r > 5) _r = 5;
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				brushmask[i][j] = 0;
			}
		}
		
		for (int i = 5 - _r; i < 5 + _r - 1; ++i) {
			for (int j = 5 - _r; j < 5 + _r - 1; ++j) {
				brushmask[i][j] = 1;
			}
		}
	}

	public static void BresenhamLine(int Ax, int Ay, int Bx, int By, int[][] _image, int _g)
    {
        // -- Initialize the components of the algorithm that are not affected by the
        //    slope or direction of the line
        int dX = Math.abs(Bx-Ax);    // store the change in X and Y of the line endpoints
        int dY = Math.abs(By-Ay);

        int CurrentX = Ax;              // store the starting point (just point A)
        int CurrentY = Ay;

        // DETERMINE "DIRECTIONS" TO INCREMENT X AND Y (REGARDLESS OF DECISION)
        int Xincr, Yincr;
        if (Ax > Bx) { Xincr=-1; } else { Xincr=1; }    // which direction in X?
        if (Ay > By) { Yincr=-1; } else { Yincr=1; }    // which direction in Y?

        // DETERMINE INDEPENDENT VARIABLE (ONE THAT ALWAYS INCREMENTS BY 1 (OR -1) )
        // AND INITIATE APPROPRIATE LINE DRAWING ROUTINE (BASED ON FIRST OCTANT
        // ALWAYS). THE X AND Y'S MAY BE FLIPPED IF Y IS THE INDEPENDENT VARIABLE.

        if (dX >= dY) {   // if X is the independent variable
		    int dPr = dY << 1;    // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dX << 1);  // amount to increment decision if up is chosen
		    int P = dPr - dX;  // decision variable start value
		    
		    for (; dX>=0; dX--) {  // process each point in the line one at a time (just use dX)
				putpixel(CurrentX, CurrentY, _image, _g);    // plot the pixel
				if (P > 0) {                              // is the pixel going right AND up?
				    CurrentX+=Xincr;                                        // increment independent variable
				    CurrentY+=Yincr;                                        // increment dependent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going right?
				    CurrentX+=Xincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
        else {                    // if Y is the independent variable
		    int dPr = dX<<1; // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dY << 1);    // amount to increment decision if up is chosen
		    int P = dPr - dY;       // decision variable start value
		    for (; dY>=0; dY--) { // process each point in the line one at a time (just use dY)
				putpixel(CurrentX, CurrentY, _image, _g);    // plot the pixel
				if (P > 0)  { // is the pixel going up AND right?
				    CurrentX+=Xincr;                                        // increment dependent variable
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going up?
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
    }


	
	public static void BresenhamLineC(int Ax, int Ay, int Bx, int By, int[][][] _image, int _r, int _g, int _b)
    {
        // -- Initialize the components of the algorithm that are not affected by the
        //    slope or direction of the line
        int dX = Math.abs(Bx-Ax);    // store the change in X and Y of the line endpoints
        int dY = Math.abs(By-Ay);

        int CurrentX = Ax;              // store the starting point (just point A)
        int CurrentY = Ay;

        // DETERMINE "DIRECTIONS" TO INCREMENT X AND Y (REGARDLESS OF DECISION)
        int Xincr, Yincr;
        if (Ax > Bx) { Xincr=-1; } else { Xincr=1; }    // which direction in X?
        if (Ay > By) { Yincr=-1; } else { Yincr=1; }    // which direction in Y?

        // DETERMINE INDEPENDENT VARIABLE (ONE THAT ALWAYS INCREMENTS BY 1 (OR -1) )
        // AND INITIATE APPROPRIATE LINE DRAWING ROUTINE (BASED ON FIRST OCTANT
        // ALWAYS). THE X AND Y'S MAY BE FLIPPED IF Y IS THE INDEPENDENT VARIABLE.

        if (dX >= dY) {   // if X is the independent variable
		    int dPr = dY << 1;    // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dX << 1);  // amount to increment decision if up is chosen
		    int P = dPr - dX;  // decision variable start value
		    
		    for (; dX>=0; dX--) {  // process each point in the line one at a time (just use dX)
				putpixelC(CurrentX, CurrentY, _image, _r, _g, _b);    // plot the pixel
				if (P > 0) {                              // is the pixel going right AND up?
				    CurrentX+=Xincr;                                        // increment independent variable
				    CurrentY+=Yincr;                                        // increment dependent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going right?
				    CurrentX+=Xincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
        else {                    // if Y is the independent variable
		    int dPr = dX<<1; // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dY << 1);    // amount to increment decision if up is chosen
		    int P = dPr - dY;       // decision variable start value
		    for (; dY>=0; dY--) { // process each point in the line one at a time (just use dY)
				putpixelC(CurrentX, CurrentY, _image, _r, _g, _b);    // plot the pixel
				if (P > 0)  { // is the pixel going up AND right?
				    CurrentX+=Xincr;                                        // increment dependent variable
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going up?
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
    }

	
    private static void putpixel(int _x, int _y, int[][] _image, int _g)
    {
        int h = _image.length;
        int w = _image[0].length;
   	
        try {
    	  for (int i = -4; i <= 4; ++i) {
	    		for (int j = -4; j <= 4; ++j) {
	    			if (brushmask[i + 4][j + 4] == 1) {
	    				_image[_y + i][_x + j] = _g;
	    			}
	    		}
	    	}
        } catch (ArrayIndexOutOfBoundsException aioobe)
       {
       }
        
    }
	
    private static void putpixelC(int _x, int _y, int[][][] _image, int _r, int _g, int _b)
    {
        int h = _image[0].length;
        int w = _image[0][0].length;
   	
        try {
    	  for (int i = -4; i <= 4; ++i) {
	    		for (int j = -4; j <= 4; ++j) {
	    			if (brushmask[i + 4][j + 4] == 1) {
	    				_image[0][_y + i][_x + j] = _r;
	    				_image[1][_y + i][_x + j] = _g;
	    				_image[2][_y + i][_x + j] = _b;    				
	    			}
	    		}
	    	}
        } catch (ArrayIndexOutOfBoundsException aioobe)
       {
       }
        
        
		/* -- this is for animating the line drawing process
		try {
			String s = String.format("frame%03d.BMP", imageNo++);
			BMP.WriteInt(s, bimage, bimage, bimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		-- */
        /*
	    try {
	        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	        for (int i = 0; i < h; ++i) {
	        	for (int j = 0; j < w; ++j) {
	        		int pixel = (_image[0][i][j] << 16) | (_image[1][i][j] << 8) | (_image[2][i][j]);
	        		bi.setRGB(j, i, pixel);
	        	}
	        }
			String s = String.format("c:/ani/frame%03d.PNG", imageNo++);
	        File outputfile = new File(s);
	        ImageIO.write(bi, "png", outputfile);
	    } catch (IOException e) {
	        System.out.println("image write error");
	    }
	    */
 }
    
    
    // -- Bresenham circle, this version is for drawing in _image with color (_r, _g, _b)
	public static int BresenhamCircle(int radius, int _x, int _y, int[][][] _image, int _r, int _g, int _b)
	{
		int numPoints = 0;

		int x, y;
		int d;

		x = 0;
		y = radius;
		d = 1 - radius;
	
		CirclePoints(x, y, _image, _x, _y, _r, _g, _b);
		numPoints += 8;
		while (y > x) {
			if (d < 0) {
				d = d + (2 * x) + 3;
				++x;
			}
			else {
				d = d + (2 * (x - y)) + 5;
				++x;
				--y;
			}
			CirclePoints(x, y, _image, _x, _y, _r, _g, _b);
			numPoints += 8;
		}

		return numPoints;
	}

	private static void CirclePoints (int _x, int _y, int[][][] _image, int _ox, int _oy, int _r, int _g, int _b) 
	{
		putpixelC( _x + _ox,  _y + _oy, _image, _r, _g, _b);
		putpixelC( _y + _ox,  _x + _oy, _image, _r, _g, _b);
		putpixelC( _y + _ox, -_x + _oy, _image, _r, _g, _b);
		putpixelC( _x + _ox, -_y + _oy, _image, _r, _g, _b);
		putpixelC(-_x + _ox, -_y + _oy, _image, _r, _g, _b);
		putpixelC(-_y + _ox, -_x + _oy, _image, _r, _g, _b);
		putpixelC(-_y + _ox,  _x + _oy, _image, _r, _g, _b);
		putpixelC(-_x + _ox,  _y + _oy, _image, _r, _g, _b);
		
	
	}
	

    // -- Bresenham circle, this version is for drawing in _image with color (_r, _g, _b)
	public static int BresenhamCircleGray(int radius, int _x, int _y, int[][] _image, int _g)
	{
		int numPoints = 0;

		int x, y;
		int d;

		x = 0;
		y = radius;
		d = 1 - radius;
	
		CirclePointsG(x, y, _image, _x, _y, _g);
		numPoints += 8;
		while (y > x) {
			if (d < 0) {
				d = d + (2 * x) + 3;
				++x;
			}
			else {
				d = d + (2 * (x - y)) + 5;
				++x;
				--y;
			}
			CirclePointsG(x, y, _image, _x, _y, _g);
			numPoints += 8;
		}

		return numPoints;
	}

	private static void CirclePointsG (int _x, int _y, int[][] _image, int _ox, int _oy, int _g) 
	{
		putpixel( _x + _ox,  _y + _oy, _image, _g);
		putpixel( _y + _ox,  _x + _oy, _image, _g);
		putpixel( _y + _ox, -_x + _oy, _image, _g);
		putpixel( _x + _ox, -_y + _oy, _image, _g);
		putpixel(-_x + _ox, -_y + _oy, _image, _g);
		putpixel(-_y + _ox, -_x + _oy, _image, _g);
		putpixel(-_y + _ox,  _x + _oy, _image, _g);
		putpixel(-_x + _ox,  _y + _oy, _image, _g);
		
	
	}
	


/* -- circle code (and more)
//ScanConvertDoc.cpp : implementation of the CScanConvertDoc class
//



//unsigned char **points;
//int height, width;
//bool reset = true;
#define MAX(x,y) (fabs(x) > fabs(y) ? fabs(x) : fabs(y))

int CScanConvertDoc::CalculateEllipsePoints()
{
	// -- file I/O preparations
	if (reset) {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				points[i][j] = 0;
			}
		}
	}

	numPoints = 0;

	int a = 25; // -- 2a is major axis length
	int b = 10;  // -- 2b is minor axis length

	int x, y;
	double d1, d2;

	x = 0;
	y = b;
	d1 = (b * b) - (a * a * b) + ((a * a) / 4.0);
	EllipsePoints(x, y);
	numPoints += 4;

	while ((a * a * (y - 0.5)) > (b * b * (x + 1))) {
		if (d1 < 0) {
			d1 = d1 + (b * b * (2 * x + 3));
			++x;
		}
		else {
			d1 = d1 + (b * b * (2 * x + 3)) + (a * a * (-2 * y + 2));
			++x;
			--y;
		}
		EllipsePoints(x, y);
		numPoints += 4;
	}

	d2 = (b * b * (x + 0.5) * (x + 0.5)) + (a * a * (y - 1) * (y - 1)) - (a * a * b * b);
	while (y > 0) {
		if (d2 < 0) {
			d2 = d2 + (b * b * (2 * x + 2)) + (a * a * (-2 * y + 3));
			++x;
			--y;
		}
		else {
			d2 = d2 + (a * a * (-2 * y + 3));
			--y;
		}
		EllipsePoints(x, y);
		numPoints += 4;
	}

	return numPoints;
}


void CScanConvertDoc::EllipsePoints (int _x, int _y)
{
	SetPixel( _x,  _y, 0);
	SetPixel(-_x,  _y, 0);
	SetPixel( _x, -_y, 0);
	SetPixel(-_x, -_y, 0);
}


int CScanConvertDoc::CalculateLinePoints()
{

	numPoints = 0;

	int dx, dy, incrE, incrNE, d, x, y;
	int xinc, yinc;

	// -- delta x and delta y (slope components)
	dx = P1x - P0x;
	dy = P1y - P0y;

	// -- normalize based on quadrants 
	if (dx < 0) {
		dx = -dx;
		xinc = -1;
	}
	else {
		xinc = 1;
	}

	if (dy < 0) {
		dy = -dy;
		yinc = -1;
	}
	else {
		yinc = 1;
	}

	// -- file I/O preparations
	if (reset) {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				points[i][j] = 0;
			}
		}
	}

	// -- set initial point
	x = P0x;
	y = P0y;
	SetPixel(x, y, numPoints++);

	// -- slope less than 1 will traverse x coordinate
	if (dy <= dx) {

		d = (2 * dy) - dx;
		incrE = 2 * dy;
		incrNE = 2 * (dy - dx);
		
		// -- always traverse from point 0 to point 1
		//    two sets of code to handle while condition
		if (P0x < P1x) {
			while (x < P1x) {
				if (d <= 0) {
					d = d + incrE;
					x += xinc;
				}
				else {
					d = d + incrNE;
					x += xinc;
					y += yinc;
				}
				SetPixel(x, y, numPoints++);
			}
		}
		else {
			while (x > P1x) {
				if (d <= 0) {
					d = d + incrE;
					x += xinc;
				}
				else {
					d = d + incrNE;
					x += xinc;
					y += yinc;
				}
				SetPixel(x, y, numPoints++);
			}
		}
	}
	// -- slope greater than 1 will traverse y coordinate
	else {

		d = (2 * dx) - dy;
		incrE = 2 * dx;
		incrNE = 2 * (dx - dy);

		// -- always traverse from point 0 to point 1
		//    two sets of code to handle while condition
		if (P0y < P1y) {
			while (y < P1y) {
				if (d <= 0) {
					d = d + incrE;
					y += yinc;
				}
				else {
					d = d + incrNE;
					x += xinc;
					y += yinc;
				}
				SetPixel(x, y, numPoints++);
			}
		}
		else {
			while (y > P1y) {
				if (d <= 0) {
					d = d + incrE;
					y += yinc;
				}
				else {
					d = d + incrNE;
					x += xinc;
					y += yinc;
				}
				SetPixel(x, y, numPoints++);
			}
		}
	}
	return numPoints;
}

void CScanConvertDoc::SetPixel(int _x, int _y, int _loc)
{
	endPoints[_loc][0] = _x;
	endPoints[_loc][1] = _y;

	{
		char filename[128];
		static int count = 0;
		sprintf(filename, "c:\\ani\\line%03d.bmp", count++);
		points[_y + (height >> 1)][_x + (width >> 1)] = 255;
		WriteBMP(filename, points, points, points, height, width);

	}

}


-- */


	public static void BresenhamLine(int Ax, int Ay, int Bx, int By, Graphics2D g2d)
    {
        // -- Initialize the components of the algorithm that are not affected by the
        //    slope or direction of the line
        int dX = Math.abs(Bx-Ax);    // store the change in X and Y of the line endpoints
        int dY = Math.abs(By-Ay);

        int CurrentX = Ax;              // store the starting point (just point A)
        int CurrentY = Ay;

        // DETERMINE "DIRECTIONS" TO INCREMENT X AND Y (REGARDLESS OF DECISION)
        int Xincr, Yincr;
        if (Ax > Bx) { Xincr=-1; } else { Xincr=1; }    // which direction in X?
        if (Ay > By) { Yincr=-1; } else { Yincr=1; }    // which direction in Y?

        // DETERMINE INDEPENDENT VARIABLE (ONE THAT ALWAYS INCREMENTS BY 1 (OR -1) )
        // AND INITIATE APPROPRIATE LINE DRAWING ROUTINE (BASED ON FIRST OCTANT
        // ALWAYS). THE X AND Y'S MAY BE FLIPPED IF Y IS THE INDEPENDENT VARIABLE.

        if (dX >= dY) {   // if X is the independent variable
		    int dPr = dY << 1;    // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dX << 1);  // amount to increment decision if up is chosen
		    int P = dPr - dX;  // decision variable start value
		    
		    for (; dX>=0; dX--) {  // process each point in the line one at a time (just use dX)
				g2d.fillOval(CurrentX - 1, CurrentY - 1, 3, 3);
				if (P > 0) {                              // is the pixel going right AND up?
				    CurrentX+=Xincr;                                        // increment independent variable
				    CurrentY+=Yincr;                                        // increment dependent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going right?
				    CurrentX+=Xincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
        else {                    // if Y is the independent variable
		    int dPr = dX<<1; // amount to increment decision if right is chosen (always)
		    int dPru = dPr - (dY << 1);    // amount to increment decision if up is chosen
		    int P = dPr - dY;       // decision variable start value
		    for (; dY>=0; dY--) { // process each point in the line one at a time (just use dY)
				g2d.fillOval(CurrentX - 1, CurrentY - 1, 3, 3);
				if (P > 0)  { // is the pixel going up AND right?
				    CurrentX+=Xincr;                                        // increment dependent variable
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPru;                                                        // increment decision (for up)
				}
				else {                                                                    // is the pixel just going up?
				    CurrentY+=Yincr;                                        // increment independent variable
				    P+=dPr;                                                         // increment decision (for right)
				}
		    }
        }
    }




}

