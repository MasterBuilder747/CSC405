package Lecture.Week04;

public class SceneGraph {

    private double[][] scene = {
            {-100,  100, 100,   -100},
            {-100, -100, 100,    100},
            {0,       0,   0,      0},
            {1,       1,   1,      1}
    };

    public void render(int[][] framebuffer) {
        Lines.bresenhamForm((int)0, (int)0, (int)0, (int)0, framebuffer);
        Lines.bresenhamForm((int)scene[0][0], (int)scene[1][0], (int)scene[0][1], (int)scene[1][1], framebuffer);
        Lines.bresenhamForm((int)scene[0][1], (int)scene[1][1], (int)scene[0][2], (int)scene[1][2], framebuffer);
        Lines.bresenhamForm((int)scene[0][2], (int)scene[1][2], (int)scene[0][3], (int)scene[1][3], framebuffer);
        Lines.bresenhamForm((int)scene[0][3], (int)scene[1][3], (int)scene[0][0], (int)scene[1][0], framebuffer);
    }

}
