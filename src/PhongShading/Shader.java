package PhongShading;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import PhongShading.Utilities.Point3D;
import PhongShading.Utilities.ScanConvert;

public class Shader {

	public Point3D light;
	
	int[][] shadebuffer;
	BufferedImage renderSurface;

	int background = 0;
	
	int radius = 150;
	
	public void reset()
	{
		for (int i = 0; i < shadebuffer.length; ++i) {
			for (int j = 0; j < shadebuffer[i].length; ++j) {
				shadebuffer[i][j] = 0;
			}
			
		}
	}
	
	public void fillSphere ()
	{
		if (shadebuffer != null) {
			
			int centerx, centery;
			
			centerx = shadebuffer[0].length / 2;
			centery = shadebuffer.length / 2;
			
			// -- outline the circle
			ScanConvert.BresenhamCircleGray(radius, centerx, centery, shadebuffer, 255);

			// -- fill the circle
			for (int i = 0; i < shadebuffer.length; ++i) {
				int left = 0, right = shadebuffer[i].length - 1;
				
				// -- find the outline
				while (left < shadebuffer[i].length && shadebuffer[i][left] == 0) ++left;
				while (right > 0 && shadebuffer[i][right] == 0) --right;
				if (left >= right) continue;
				
				// -- shade the sphere
				for (int j = left; j <= right; ++j) {					
					shadebuffer[i][j] = 128;
				}
			}
			
			// -- render to BufferedImage for display
	    	for (int i = 0; i < renderSurface.getHeight(); ++i) {
	    	    for (int j = 0; j < renderSurface.getWidth(); ++j) {
	    	    	int val = shadebuffer[i][j];
	    			int pixel =	(val << 16) | (val << 8) | (val);
	    			renderSurface.setRGB(j, i, pixel);
	    		}
	    	}
		}
	}
	
	public void shadeSphere ()
	{
		if (shadebuffer != null) {
			
			int centerx, centery;
			
			centerx = shadebuffer[0].length / 2;
			centery = shadebuffer.length / 2;
			
			// -- outline the circle
			ScanConvert.BresenhamCircleGray(radius, centerx, centery, shadebuffer, 255);

			// -- make the light source vector a unit vector
//			double lx = light.getX() - centerx;
//			double ly = light.getY() - centery;
//			double lz = light.getZ();
//			double lightmag = Math.sqrt(lx * lx + ly * ly + lz * lz);
			double lightmag = Math.sqrt(light.getX() * light.getX() + light.getY() * light.getY() + light.getZ() * light.getZ());

			light.setX(light.getX() / lightmag);
			light.setY(light.getY() / lightmag);
			light.setZ(light.getZ() / lightmag);
			
			// -- fill the circle
			for (int i = 0; i < shadebuffer.length; ++i) {
				int left = 0, right = shadebuffer[i].length - 1;
				
				// -- find the outline
				while (left < shadebuffer[i].length && shadebuffer[i][left] == 0) ++left;
				while (right > 0 && shadebuffer[i][right] == 0) --right;
				if (left >= right) continue;
				
				// -- shade the sphere
				for (int j = left; j <= right; ++j) {
					
					// -- translate to 0, 0, 0
					double x = j - centerx;
					double y = i - centery;
					double z;
					double r2 = radius * radius;
					double x2 = x * x; // (x - centerx)^2
					double y2 = y * y; // (y - centery)^2
					
					// -- calculate z making sure floating point inaccuracies don't
					//    make the value go negative
//					z = (r2 > x2) ? Math.sqrt(r2 - x2) : 0;
					z = Math.sqrt(r2 - (x2 + y2));
										
					// -- normal at point x, y, z of the sphere
					//    could do this with cross product but no need
					//    since a ray from the center to the surface
					//    is normal at the surface
					double nx = x;
					double ny = y;
					double nz = z;
					
					// -- make the normal a unit vector
					double nmag = Math.sqrt(nx * nx + ny * ny + nz * nz);
					nx /= nmag;
					ny /= nmag;
					nz /= nmag;
					
					// -- angle between the surface normal and the light source vector
					double dot = (light.getX() * nx) + (light.getY() * ny) + (light.getZ() * nz);
					double angle = Math.acos(dot);// * 180.0 / Math.PI;
					
					// -- scale the angle between surface normal and light vector to [0..255]
					//    and assign as pixel intensity
					int pixel = (int)(angle * 255.0 / Math.PI);//180.0);
					shadebuffer[i][j] = pixel;
				}
			}
			
			// -- render to BufferedImage for display
	    	for (int i = 0; i < renderSurface.getHeight(); ++i) {
	    	    for (int j = 0; j < renderSurface.getWidth(); ++j) {
	    	    	int val = shadebuffer[i][j];
	    			int pixel =	(val << 16) | (val << 8) | (val);
	    			renderSurface.setRGB(j, i, pixel);
	    		}
	    	}			
		}
	}
	
	public void shadeCylinder ()
	{
		if (shadebuffer != null) {
			
			int centerx, centery;
			
			centerx = shadebuffer[0].length / 2;
			centery = shadebuffer.length / 2;
			
			// -- make the light source vector a unit vector
			double lightmag = Math.sqrt(light.getX() * light.getX() + light.getY() * light.getY() + light.getZ() * light.getZ());

			light.setX(light.getX() / lightmag);
			light.setY(light.getY() / lightmag);
			light.setZ(light.getZ() / lightmag);

			// -- set cylinder size in the center of the canvas
			double height = shadebuffer.length - 50;
			double r = height / 3;
			
			// -- loop through frame buffer
			for (int i = 0; i < shadebuffer.length; ++i) {
				for (int j = 0; j < shadebuffer[i].length; ++j) {
					// -- translate the sphere to the origin
					double x = (j - centerx);
					double y = (i - centery);
					double z = -1;
					
					// -- check to see if (x, y) is within the cylinder
					if (Math.abs(y) < height/2 && Math.abs(x) < r) {
						// -- if it is, calculate z...
						z = Math.sqrt((r*r) - x*x);
						// -- turn it into a unit vector,
						//    for a cylinder, the surface normal at (x, y, z) is (x, y, z)...
						double mag = Math.sqrt(x*x + y*y + z*z);
						double nx = x / mag;
						double ny = y / mag;
						double nz = z / mag;
						
						// -- calculate the angle between the light source and the surface normal...
						double dot = nx * light.getX() + ny * light.getY() + nz * light.getZ();
						double angle = Math.acos(dot / (lightmag)); // -- note that (nx, ny, nz) is a unit vector from above
						
						// -- scale the angle to [0..255] and write to frame buffer
						int pixel = (int)(angle * 255.0 / Math.PI);
						shadebuffer[i][j] = pixel;
					}
					
				}
			}
			
			// -- render to BufferedImage for display
	    	for (int i = 0; i < renderSurface.getHeight(); ++i) {
	    	    for (int j = 0; j < renderSurface.getWidth(); ++j) {
	    	    	int val = shadebuffer[i][j];
	    			int pixel =	(val << 16) | (val << 8) | (val);
	    			renderSurface.setRGB(j, i, pixel);
	    		}
	    	}			
		}
	}


	public static void main (String[] args) {
		Shader.cylindershader();
	}
	public static void simpleshader ()
	{
		// -- create a frame buffer
		int framebuffer[][] = new int[512][512];
		
		// -- set sphere of radius 200 in the center of the frame buffer
		int centerx = framebuffer[0].length / 2;
		int centery = framebuffer.length / 2;
		double r = 200;

		// -- set the light source location
		double lsx = 1;
		double lsy = 1;
		double lsz = -1;
		double magl = Math.sqrt(lsx*lsx + lsy*lsy + lsz*lsz);
	
		// -- loop through frame buffer
		for (int i = 0; i < framebuffer.length; ++i) {
			for (int j = 0; j < framebuffer[i].length; ++j) {
				// -- translate the sphere to the origin
				double x = (j - centerx);
				double y = (i - centery);
				double z = -1;
				
				// -- check to see if (x, y) is within the circle
				if (Math.sqrt(x*x + y*y) < (r*r)) {
					// -- if it is, calculate z...
					z = Math.sqrt((r*r) - (x*x + y*y));
					// -- turn it into a unit vector,
					//    for a sphere, this is the surface normal at (x, y, z)...
					double mag = Math.sqrt(x*x + y*y + z*z);
					double nx = x / mag;
					double ny = y / mag;
					double nz = z / mag;
					
					// -- calculate the angle between the light source and the surface normal...
					double dot = nx*lsx + ny*lsy + nz*lsz;
					double angle = Math.acos(dot / (magl)); // -- note that (nx, ny, nz) is a unit vector from above
					
					// -- scale the angle to [0..255] and write to frame buffer
					int pixel = (int)(angle * 255.0 / Math.PI);
					framebuffer[i][j] = pixel;
				}
				
			}
		}
		
		// -- render to frame buffer to a BufferedImage for file I/O
		BufferedImage bi = new BufferedImage(framebuffer[0].length, 
				framebuffer.length, BufferedImage.TYPE_INT_RGB);
    	for (int i = 0; i < bi.getHeight(); ++i) {
    	    for (int j = 0; j < bi.getWidth(); ++j) {
    	    	int val = framebuffer[i][j];
    			int pixel =	(val << 16) | (val << 8) | (val);
    			bi.setRGB(j, i, pixel);
    		}
    	}
    	// -- write the BufferedImage to a PNG file
		File outfile = new File("C:/sphere.png");
		try {
			ImageIO.write(bi, "PNG", outfile);
		} catch (IOException e) {
			System.out.println("cannot open sphere.png");
		}

	}
	
	public static void cylindershader ()
	{
		// -- create a frame buffer
		int framebuffer[][] = new int[512][512];
		
		// -- set sphere of radius 200 in the center of the frame buffer
		int centerx = framebuffer[0].length / 2;
		int centery = framebuffer.length / 2;
		double r = 75;
		double height = 350;

		// -- set the light source location
		double lsx = 5;
		double lsy = 10;
		double lsz = -10;
		double magl = Math.sqrt(lsx*lsx + lsy*lsy + lsz*lsz);
	
		// -- loop through frame buffer
		for (int i = 0; i < framebuffer.length; ++i) {
			for (int j = 0; j < framebuffer[i].length; ++j) {
				// -- translate the sphere to the origin
				double x = (j - centerx);
				double y = (i - centery);
				double z = -1;
				
				// -- check to see if (x, y) is within the cylinder
				if (Math.abs(y) < height/2 && Math.abs(x) < r) {
					// -- if it is, calculate z...
					z = Math.sqrt((r*r) - x*x);
					// -- turn it into a unit vector,
					//    for a cylinder, the surface normal at (x, y, z) is (x, y, z)...
					double mag = Math.sqrt(x*x + y*y + z*z);
					double nx = x / mag;
					double ny = y / mag;
					double nz = z / mag;
					
					// -- calculate the angle between the light source and the surface normal...
					double dot = nx*lsx + ny*lsy + nz*lsz;
					double angle = Math.acos(dot / (magl)); // -- note that (nx, ny, nz) is a unit vector from above
					
					// -- scale the angle to [0..255] and write to frame buffer
					int pixel = (int)(angle * 255.0 / Math.PI);
					framebuffer[i][j] = pixel;
				}
				
			}
		}
		
		// -- render to frame buffer to a BufferedImage for file I/O
		BufferedImage bi = new BufferedImage(framebuffer[0].length, 
				framebuffer.length, BufferedImage.TYPE_INT_RGB);
    	for (int i = 0; i < bi.getHeight(); ++i) {
    	    for (int j = 0; j < bi.getWidth(); ++j) {
    	    	int val = framebuffer[i][j];
    			int pixel =	(val << 16) | (val << 8) | (val);
    			bi.setRGB(j, i, pixel);
    		}
    	}
    	// -- write the BufferedImage to a PNG file
		File outfile = new File("cylinder.png");
		try {
			ImageIO.write(bi, "PNG", outfile);
		} catch (IOException e) {
			System.out.println("cannot open cylinder.png");
		}

	}
	
	public Point getNormal(int xx, int yy, int zz, double angle[])
	{
		int centerx, centery;
		
		centerx = shadebuffer[0].length / 2;
		centery = shadebuffer.length / 2;
		if (shadebuffer[yy][xx] != background) {
			
	
			// -- translate to 0, 0, 0
			double x = xx - centerx;
			double y = yy - centery;
			double z;
			double r2 = radius * radius;
			double x2 = x * x;
			double y2 = y * y;

			
			// -- calculate z making sure floating point inaccuracies don't
			//    make the value go negative
			z = (r2 > (x2 + y2)) ? Math.sqrt(r2 - (x2 + y2)) : 0;
			
			// -- normal at point x, y, z of the sphere
			//    is the ray from center of sphere to the point
			//    on the sphere
			double nx = x;
			double ny = y;
			double nz = z;
			
			// -- make the normal a unit vector
			double nmag = Math.sqrt(nx * nx + ny * ny + nz * nz);
			nx /= nmag;
			ny /= nmag;
			nz /= nmag;

			// -- angle between the surface normal and the light source vector
			double dot = (light.getX() * nx) + (light.getY() * ny) + (light.getZ() * nz);
			angle[0] = Math.acos(dot) * 180.0 / Math.PI;

			
			// -- scale the surface normal so that it will show on the display
			nx *= 100;
			ny *= 100;
						
			return new Point((int)(nx + xx), (int)(ny+yy));
		}		
		else {
			return null;
		}
			
		
	}
	
}
