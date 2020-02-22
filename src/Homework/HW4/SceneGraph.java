/*
Homework 4
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-21-20
*/

package Homework.HW4;

public class SceneGraph {

    //starting template
    private double[][] scene = {
        {-100,  100, 100,   -100}, //x
        {-100, -100, 100,    100}, //y
        {0,       0,   0,      0}, //z
        {1,       1,   1,      1}  //w
    };

    //change the coordinates of the scene
    public void setScene (double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                scene[i][j] = a[i][j];
            }
        }
    }

    //render the lines at those coordinates
    public void render(int[][] framebuffer) {
        Lines.bresenhamForm((int)0, (int)0, (int)0, (int)0, framebuffer);
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][0], (int)scene[1][0], framebuffer);
    }

}
