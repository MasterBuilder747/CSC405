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

    /*
	public static void fillNew(int[][] fb, int color) {
		for (int x = 0; x < fb.length; x++) {
			int y = 0;
			while (y < fb[x].length && fb[x][y] == -1) {
				y++;
			}
			if (y == fb[x].length) {
				continue;
				//fb[x][y] = color;
			}
		}
	}
	*/
	/*
	public static void fill(int[][] fb, int color) {
		//scan through each line
		for (int x = 0; x < fb.length; x++) {
			//keep checking that line until it has a color
			for (int y = 0; y < fb[0].length; y++) {
				//once it does,
				if (fb[x][y] == color) {
					//go from the left and the right and find the two pixels where it hits
					int i = 0; //left edge going ot the right
					while (fb[x][i] == -1 && i < fb[x].length) {
						i++;
					}
				}
			}
		}
	}

	//good method
    public void fillRec (int value, int i, int j) {
        //this uses a lot of memory as recursion in this case has to remember everything
        //in this case its the first point
        if (!(i < 0 || j < 0 || i > height - 1 || j > width - 1 || fb[i][j] == value)) {
            fb[i][j] = value;
            fillRec(value, i - 1, j);
            fillRec(value, i + 1, j);
            fillRec(value, i, j + 1);
            fillRec(value, i, j - 1);
        }
    }

    public void fill(int[][] fb) {
        for(int y = 1; y < fb.length; y++) {
            int x = 0, x2 = 0;
            for(int x0 = 0; x0 < fb[y].length; x0++) {
                if(fb[y][x0] == 255) {
                    x = x0;
                }
            }
            for(int x1 = fb[y].length-1; x1 > -1; x1--) {
                if(fb[y][x1] == 255) {
                    x2 = x1;
                }
            }
            if(x <= x2) {
                Lines.bresenhamForm(x, y, x2, y, fb, 255);
            }
        }
    }

	*/

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
