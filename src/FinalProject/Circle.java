package FinalProject;

public class Circle extends Polygon {
    int[][] fb;
    int x;
    int y;
    int radius;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
        this.fb = new int[x][y];
    }

    public void render(int[][] fb, int cx, int cy, int radius, int fillColor, int outColor) {
        this.clearFB();
        //write the render to this individual surface's framebuffer
        LinesCurved.drawCircle(new Point(cx, cy), radius, fb, outColor);
        //fill(this.fb, 0, fillColor);
        //update the cube's framebuffer with the filled square
        updateFB(this.fb, fb);
    }

    public void clearFB() {
        this.fb = new int[this.x][this.y];
    }
}
