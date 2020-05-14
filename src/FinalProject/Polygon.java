/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

import javafx.scene.paint.Color;

public abstract class Polygon extends Lines {

    //1 -> 2
    public void updateFB(FrameBuffer fb1, FrameBuffer fb2) {
        if (fb1.fb.length == fb2.fb.length && fb1.fb[0].length == fb2.fb[0].length) {
            for (int i = 0; i < fb2.fb.length; i++) {
                for (int j = 0; j < fb2.fb[0].length; j++) {
                    //ignore the empty space in the individual fb
                    //check to make sure the color is in fact NOT black in the r, g, and b values
                    fb2.writePixel(i, j, fb1.readPixel(i, j));
                }
            }
        }
    }

    //1 -> 2
    public void updateFB(FrameBuffer fb1, FrameBuffer fb2, Color b) {
        if (fb1.fb.length == fb2.fb.length && fb1.fb[0].length == fb2.fb[0].length) {
            for (int i = 0; i < fb2.fb.length; i++) {
                for (int j = 0; j < fb2.fb[0].length; j++) {
                    //ignore the empty space in the individual fb
                    //check to make sure the color is in fact NOT black in the r, g, and b values
                    //fb2.writePixel(i, j, fb1.readPixel(i, j));
                    if (!fb1.compareColor(i, j, b)) {
                        fb2.writePixel(i, j, fb1.readPixel(i, j));
                    } else {
                        fb2.writePixel(i, j, b);
                    }
                }
            }
        }
    }

    //1 -> 2
    public void replaceFB(FrameBuffer fb1, FrameBuffer fb2, Color b) {
        if (fb1.fb.length == fb2.fb.length && fb1.fb[0].length == fb2.fb[0].length) {
            for (int i = 0; i < fb2.fb.length; i++) {
                for (int j = 0; j < fb2.fb[0].length; j++) {
                    //ignore the empty space in the individual fb
                    //check to make sure the color is in fact NOT black in the r, g, and b values
                    //fb2.writePixel(i, j, fb1.readPixel(i, j));
                    if (!fb1.compareColor(i, j, b)) {
                        fb2.writePixel(i, j, fb1.readPixel(i, j));
                    } else {
                        fb2.writePixel(i, j, Color.rgb(0, 0, 0, 1.0));
                    }
                }
            }
        }
    }

    public void fill(FrameBuffer fb, Color black, Color color) {
        for (int x = 0; x < fb.fb.length; x++) {

            //read the fb from left to right
            int i = 0;
            while (i < fb.fb[0].length && fb.compareColor(x, i, black)) {
                i++;
            }
            if (i == fb.fb[0].length) {
                continue;
            }
            int x1 = i;

            //read the fb from right to left
            i = fb.fb[0].length - 1;
            while (i > 0 && fb.compareColor(x, i, black)) {
                i--;
            }
            int x2 = i;

            for (int j = x2; j >= x1; j--) {
                fb.writePixel(x, j, color);
            }
        }
    }
}
