package Lecture.Week08;

public class Shader {

    int height = 100;
    int width = 100;

    //frame buffer
    int[][] fb = new int[height][width];

    public Shader(int height, int width) {
        fb = new int[height][width];
        this.height = height;
        this.width = width;
    }

    //good method
    public void fill (int value, int i, int j) {
        //this uses a lot of memory as recursion in this case has to remember everything
        //in this case its the first point
        if (i < 0 || j < 0 || i > height - 1 || j > width - 1 || fb[i][j] == value) {
            //return;
        }else{
            fb[i][j] = value;
            fill(value, i - 1, j);
            fill(value, i + 1, j);
            fill(value, i, j + 1);
            fill(value, i, j - 1);
        }
    }

    //bad method of doing this
    public void oldFill(int value, int i, int j) {
        if (i > 0) { //hit the right
            fb[i][j] = value;
            oldFill(value, i - 1, j);
        }
        if (i < height - 1) { //not hit the left, try again
            fb[i][j] = value;
            oldFill(value, i + 1, j);
        }
        if (j > 0) { //hit the left
            fb[i][j] = value;
            oldFill(value, i, j - 1);
        } //not hit the right, try again
        if (j < width - 1) {
            fb[i][j] = value;
            oldFill(value, i, j + 1);
        }
    }

    public static void main(String[] args) {
        Shader s = new Shader(5, 10);
        s.fill(255, 2, 5);
        for (int i = 0; i < s.height; ++i) {
            for (int j = 0; j < s.width; ++j) {
                System.out.printf("%3d ", s.fb[i][j]);
            }
            System.out.println();
        }
    }
}
