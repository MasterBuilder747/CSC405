/*
Homework 7
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-26-20
*/

package Homework.HW7TriangleFill;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter three points, press enter for each x and y value: ");
		Scanner kb = new Scanner(System.in);
		double x1 = kb.nextDouble();
		double y1 = kb.nextDouble();
		double x2 = kb.nextDouble();
		double y2 = kb.nextDouble();
		double x3 = kb.nextDouble();
		double y3 = kb.nextDouble();

		Triangle t = new Triangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
	}

}
