/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

import javafx.scene.paint.Color;

public class Square extends Polygon {

    //internal framebuffer
    FrameBuffer fb;
    int x;
    int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.fb = new FrameBuffer(x, y);
    }

    public void render(FrameBuffer fb, double[][] points, int a, int b, int c, int d, Color cl) {
        this.clearFB();
        //updateFB(fb, this.fb);
        //write the render to this individual surface's framebuffer
        renderWire(points, a, b, c, d, cl);
        fill(this.fb, Color.rgb(255, 0, 0, 1.0), cl);
        //update the cube's framebuffer with the filled square
        updateFB(this.fb, fb);
        //remove the black color and use the square
        replaceFB(this.fb, fb, Color.rgb(255, 0, 0, 1.0));
    }

    public void renderWire(double[][] points, int a, int b, int c, int d, Color cl) {
        drawLine((int)points[0][a], (int)points[1][a], (int)points[0][b], (int)points[1][b], this.fb, cl);
        drawLine((int)points[0][b], (int)points[1][b], (int)points[0][c], (int)points[1][c], this.fb, cl);
        drawLine((int)points[0][c], (int)points[1][c], (int)points[0][d], (int)points[1][d], this.fb, cl);
        drawLine((int)points[0][d], (int)points[1][d], (int)points[0][a], (int)points[1][a], this.fb, cl);
    }

    public void clearFB() {
        this.fb.setFill(Color.rgb(255, 0, 0, 1.0));
    }
}


