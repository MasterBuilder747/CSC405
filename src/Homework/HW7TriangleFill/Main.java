/*
Homework 7
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-26-20
*/

package Homework.HW7TriangleFill;

import Homework.HW3BresenhamSave.LineBase;

import java.io.IOException;
import java.util.Scanner;

public class Main {

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

		//create and process the framebuffer
		int w = 1024;
		int[][] im = new int[w][w];
		t.render(im);

		//save the render
		String fileName = "TriangleFill";
		try {
			LineBase.ImageWrite(im, fileName + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
