package Homework.HW9HiddenSurfaceRemoval;

public abstract class Polygon extends Lines {

    //1 -> 2
    public void updateFB(int[][] fb1, int[][] fb2) {
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
