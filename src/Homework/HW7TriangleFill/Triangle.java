/*
Homework 7
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-26-20
*/

package Homework.HW7TriangleFill;

public class Triangle {

    Point a;
    Point b;
    Point c;

    private Triangle() {

    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] framebuffer) {
        // b -> a -> c -> b
        Lines.bresenhamForm((int)b.x, (int)b.y, (int)a.x, (int)a.y, framebuffer, 255);
        Lines.bresenhamForm((int)a.x, (int)a.y, (int)c.x, (int)c.y, framebuffer, 255);
        Lines.bresenhamForm((int)c.x, (int)c.y, (int)b.x, (int)b.y, framebuffer, 255);
    }

    public void fill(int[][] fb) {
        for(int y = 1; y < fb.length; y++) {
            int x = 0, x2 = 0;
            for(int x0 = 0; x0 < fb[y].length; x0++) {
                if(fb[y][x0] == 255) x = x0;
            }
            for(int x1 = fb[y].length-1; x1 > -1; x1--) {
                if(fb[y][x1] == 255) x2 = x1;
            }
            if(x <= x2) {
                Lines.bresenhamForm(x, y, x2, y, fb, 255);
            }
        }
    }

}


