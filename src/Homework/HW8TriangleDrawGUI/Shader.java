/*
Homework 8
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-14-20
*/

package Homework.HW8TriangleDrawGUI;

public class Shader {

    int height;
    int width;

    //frame buffer
    int[][] fb;

    public Shader(int height, int width) {
        fb = new int[height][width];
        this.height = height;
        this.width = width;
    }

}
