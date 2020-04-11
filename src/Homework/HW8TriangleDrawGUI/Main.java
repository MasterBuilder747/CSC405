/*
Homework 8
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-14-20
*/

package Homework.HW8TriangleDrawGUI;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void fill(int[][] fb, int black, int color) {
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

	//sets all black values to a number
	//repeat this before and after each object if there are more than 1
	public static void setBuffer(int[][] fb, int black, int color) {
		for (int x = 0; x < fb.length; x++) {
			for (int y = 0; y < fb.length; y++) {
				if (fb[x][y] != color) {
					fb[x][y] = black;
				}
			}
		}
	}

	public static void main(String[] args) {
		//store the points of the triangle
		System.out.println("Enter three points, press enter for each x and y value: ");
		Scanner kb = new Scanner(System.in);

		//test values:
		/*
		(100, 512)
		(350, 256)
		(350, 768)
		*/
		double x1 = kb.nextDouble();
		double y1 = kb.nextDouble();
		double x2 = kb.nextDouble();
		double y2 = kb.nextDouble();
		double x3 = kb.nextDouble();
		double y3 = kb.nextDouble();
		Triangle t = new Triangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
		//the color(shade of gray) to be filled
		int color = 255;

		//create and process the framebuffer
		int w = 1024;
		//this will set all values to 0
		int[][] im1 = new int[w][w];

		//render wireframe
		t.render(im1, color);
		setBuffer(im1, -1, color);
		//fill it
		fill(im1, -1, color);
		setBuffer(im1, 0, color);
		//save the render
		String fileName1 = "TriangleFill";
		try {
			LineBase.ImageWrite(im1, fileName1 + ".png");
			System.out.println("image saved.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
