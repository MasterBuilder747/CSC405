/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

//this is a point on the 2D framebuffer, hence why it is 2D
//this does not represent the point for a 3D object,
//it is the resulting point in the framebuffer
public class Point {
    double x;
    double y;

    private Point() {

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
