package FinalProject;

import javafx.scene.paint.Color;

//import PhongShading.Utilities.ScanConvert;

public class Circle extends Polygon {
    frameBuffer fb;
    int w;
    int h;
    //this is the center point location
    int x;
    int y;
    int radius;

    public Circle(int w, int h, int x, int y, int radius) {
        //size of the internal fb
        this.w = w;
        this.h = h;
        //center position
        this.x = x;
        this.y = y;
        this.radius = radius;
        //framebuffer is still empty
        this.fb = new frameBuffer(w, h);
    }

    //shading
    public void render(frameBuffer fb, double lx, double ly, double lz, Color c) {
        //make internal fb the background color
        this.fb.setFill(Color.color(0.0, 0.0, 0.0, 1.0));
        //write the render to this individual surface's framebuffer
        LinesCurved.drawCircle(new Point(this.x, this.y), this.radius, this.fb, c);
        //fill(this.fb, Color.rgb(0, 0, 0, 1.0), fillColor);
        shade(lx, ly, lz, c);
        //update the cube's framebuffer with the filled square
        updateFB(this.fb, fb);
    }

    //fill, no shading
    public void render(frameBuffer fb, Color c) {
        //make internal fb the background color
        this.fb.setFill(Color.color(0.0, 0.0, 0.0, 1.0));
        //this.fb.print();

        //write the render to this individual surface's framebuffer
        LinesCurved.drawCircle(new Point(this.x, this.y), this.radius, this.fb, c);
        //this.fb.print();

        //fill the color in
        fill(this.fb, Color.rgb(0, 0, 0, 1.0), c);

        //update the external framebuffer with the filled sphere
        updateFB(this.fb, fb);
        //fb.print();
    }

    public void resize(int radius) {
        this.radius = radius;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shade(double lx, double ly, double lz, Color c) {
        Color b = Color.rgb(0, 0, 0, 1.0);
        if (this.fb != null) {
            int centerX = this.fb.fb[0].length / 2;
            int centerY = this.fb.fb.length / 2;

            // -- outline the circle
            //ScanConvert.BresenhamCircleGray(radius, centerX, centerY, fb, 255);
            //circle will already have an outline before shading

            // -- make the light source vector a unit vector
            double lightMag = Math.sqrt(lx * lx + ly * ly + lz * lz);
            double magX = lx / lightMag;
            double magY = ly / lightMag;
            double magZ = lz / lightMag;

            // -- fill the circle
            for (int i = 0; i < this.fb.fb.length; ++i) {
                int left = 0, right = this.fb.fb[i].length - 1;

                // -- find the outline
                while (left < this.fb.fb[i].length && this.fb.compareColor(i, left, b)) {
                    ++left;
                }
                while (right > 0 && this.fb.compareColor(i, right, b)) {
                    --right;
                }
                if (left >= right) continue;

                // -- shade the sphere
                for (int j = left; j <= right; ++j) {
                    // -- translate to 0, 0, 0
                    double x = j - centerX;
                    double y = i - centerY;
                    double z;
                    double r2 = this.radius * this.radius;
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
                    double nMag = Math.sqrt(nx * nx + ny * ny + nz * nz);
                    nx /= nMag;
                    ny /= nMag;
                    nz /= nMag;

                    // -- angle between the surface normal and the light source vector
                    double dot = (magX * nx) + (magY * ny) + (magZ * nz); //use light object if this isn't working
                    double angle = Math.acos(dot);// * 180.0 / Math.PI;

                    // -- scale the angle between surface normal and light vector to [0..255]
                    //    and assign as pixel intensity
                    int pixel = (int)(angle * 255.0 / Math.PI);//180.0);
                    this.fb.writePixel(i, j, Color.rgb(pixel, pixel, pixel, 1.0));
                }
            }
        }
    }
}
