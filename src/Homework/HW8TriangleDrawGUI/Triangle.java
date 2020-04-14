/*
Homework 8
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-14-20
*/

package Homework.HW8TriangleDrawGUI;

public class Triangle {

    Point a;
    Point b;
    Point c;
    int[][] fb;

    public Triangle(int x, int y) {
        this.fb = new int[x][y];
    }

    public Triangle(int x, int y, Point a, Point b, Point c) {
        this.fb = new int[x][y];
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void add(int i, Point p) {
        if (i < 0 || i > 2) {
            //nothing
        } else {
            switch (i) {
                case 0:
                    this.a = p;
                case 1:
                    this.b = p;
                case 2:
                    this.c = p;
            }
        }
    }

    //render the lines at those coordinates
    //this renders each square
    public void render(int[][] fb, int color) {
        // b -> a -> c -> b
        Lines.bresenhamForm((int)b.y, (int)b.x, (int)a.y, (int)a.x, this.fb);
        Lines.bresenhamForm((int)a.y, (int)a.x, (int)c.y, (int)c.x, this.fb);
        Lines.bresenhamForm((int)c.y, (int)c.x, (int)b.y, (int)b.x, this.fb);
        fill(this.fb, 0, color);

        replaceFB(this.fb, fb);
    }

    //1 -> 2
    public void replaceFB(int[][] fb1, int[][] fb2) {
        if (fb1.length == fb2.length && fb1[0].length == fb2[0].length) {
            for (int i = 0; i < fb2.length; i++) {
                for (int j = 0; j < fb2[0].length; j++) {
                    //ignore the empty space in the individual fb
                    if (fb1[i][j] != 0) {
                        fb2[i][j] = fb1[i][j];
                    }
                }
            }
        }
    }

    public void fill(int[][] fb, int black, int color) {
        for (int x = 0; x < fb.length; x++) {

            //read the fb from left to right
            int i = 0;
            while (i < fb[0].length && fb[x][i] == black) {
                i++;
            }
            if (i == fb[0].length) {
                continue;
            }
            int x1 = i;

            //read the fb from right to left
            i = fb[0].length - 1;
            while (i > 0 && fb[x][i] == black) {
                i--;
            }
            int x2 = i;

            for (int j = x2; j >= x1; j--) {
                fb[x][j] = color;
            }
        }
    }
}


