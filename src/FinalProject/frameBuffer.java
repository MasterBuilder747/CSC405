/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

import javafx.scene.paint.Color;

public class frameBuffer {

    //this is a 2D representation of a 0 to 255 colored pixel
    //r, g, b, alpha(true double)
    double[][][] fb;

    public frameBuffer(int w, int h) {
        this.fb = new double[w][h][4];
    }

    //this overrides all of the pixels with this one color
    public void setFill(Color c) {
        for (int i = 0; i < this.fb.length; i++) {
            for (int j = 0; j < this.fb[0].length; j++) {
                this.writePixel(i, j, c);
            }
        }
    }

    public void writePixel(int x, int y, Color c) {
        this.fb[x][y][0] = c.getRed();
        this.fb[x][y][1] = c.getGreen();
        this.fb[x][y][2] = c.getBlue();
        this.fb[x][y][3] = c.getOpacity();
    }

    public Color readPixel(int x, int y) {
        return Color.color(this.fb[x][y][0], this.fb[x][y][1], this.fb[x][y][2], this.fb[x][y][3]);
    }

    //check if the exact color in the parm is equal to the specified pixel color in this fb
    //this excludes opacity for now
    public boolean compareColor(int x, int y, Color c) {
        return (int)this.fb[x][y][0] == (int)c.getRed() && (int)this.fb[x][y][1] == (int)c.getGreen() && (int)this.fb[x][y][2] == (int)c.getBlue() && this.fb[x][y][3] == c.getOpacity();
    }
}
