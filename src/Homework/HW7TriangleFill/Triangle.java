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
    public void render(int[][] framebuffer, int color) {
        // b -> a -> c -> b
        Lines.bresenhamForm((int)b.x, (int)b.y, (int)a.x, (int)a.y, framebuffer, color);
        Lines.bresenhamForm((int)a.x, (int)a.y, (int)c.x, (int)c.y, framebuffer, color);
        Lines.bresenhamForm((int)c.x, (int)c.y, (int)b.x, (int)b.y, framebuffer, color);
    }
}


