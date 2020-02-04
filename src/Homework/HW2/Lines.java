package Homework.HW2;

import Homework.HW1LineDrawing.LineBase;

public class Lines extends LineBase {

    @Override
    public void twoPointForm(int x0, int y0, int x1, int y1, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //two point form
        //y = (((y1 - y0)/(x1 - x0)) * (t - x0)) + y0;
        //x = (((x1 - x0)/(y1 - y0)) * (t - y0)) + x0;
        double x = 0;
        double y = 0;

        //x0 to x1
        for (int i = x0; i < x1; i++) {
            x = (((y1 - y0)/((x1 - x0) * 1.0)) * (i - x0)) + y0;
            framebuffer[(int)x][i] = 255;
        }
        //y0 to y1
        for (int j = y0; j < y1; j++) {
            y = (((x1 - x0)/((y1 - y0) * 1.0)) * (j - y0)) + x0;
            framebuffer[j][(int)y] = 255;
        }
    }

    @Override
    public void parametricForm(int x0, int y0, int x1, int y1, int[][] framebuffer)
            throws NullPointerException, ArrayIndexOutOfBoundsException {

        //parametric form
        /*
        𝑥 = 𝑥0 + (𝑥1 − 𝑥0)𝑡
        𝑦 = 𝑦0 + (𝑦1 − 𝑦0)𝑡
        0.0 ≤ 𝑡 ≤ 1.0
        */
        double x = 0;
        double y = 0;

        //pixelDensity var
        double pd = 1000;

        //i is the t value
        for (int i = 0; i < pd; i++) {
            x = x1 - ((x1 - x0) * (i / pd));
            y = y1 - ((y1 - y0) * (i / pd));
            framebuffer[(int)x][(int)y] = 255;
        }

    }

    public static void mainTwoPoint(int[][] framebuffer) {
        LineBase lb = new Lines();
        for (int x = 0; x < framebuffer[0].length; x += 13) {
            lb.twoPointForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
        }
        for (int y = 0; y < framebuffer.length; y += 13) {
            lb.twoPointForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
        }
    }

    public static void mainParametric(int[][] framebuffer) {
        LineBase lb = new Lines();
        for (int x = 0; x < framebuffer[0].length; x += 13) {
            lb.parametricForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
        }
        for (int y = 0; y < framebuffer.length; y += 13) {
            lb.parametricForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
        }
    }

    public static void main (String[] args) {
        LineBase lb = new Lines();
        {
            int[][] framebuffer = new int[256][256];
            for (int x = 0; x < framebuffer[0].length; x += 13) {
                lb.parametricForm(x, 0, framebuffer[0].length - x - 1, framebuffer.length - 1, framebuffer);
            }
            for (int y = 0; y < framebuffer.length; y += 13) {
                lb.parametricForm(0, y, framebuffer[0].length - 1, framebuffer.length - y - 1, framebuffer);
            }
        }
    }
}


