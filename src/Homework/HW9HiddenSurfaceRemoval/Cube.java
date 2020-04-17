/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class Cube extends Polygon{

    final int POLYS = 6;
    Square[] polys = new Square[POLYS];

    int[][] fb;

    public Cube(int x, int y) {
        this.fb = new int[x][y];
    }

    public void add(int i, Square s) {
        if (i > -1 && i < POLYS) {
            this.polys[i] = s;
        }
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] fb, int fillColor, int outColor) {
        renderWire(outColor);
        //fill(this.fb, 0, fillColor);
        renderWire(outColor);

        updateFB(this.fb, fb);
    }

    public void renderWire(int outColor) {

    }
}
