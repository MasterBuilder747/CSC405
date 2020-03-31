/*
Homework 6
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 3-19-20
*/

package Homework.HW7;

import java.util.Scanner;

public class MainAppJavaFX {

	public static void main(String[] args) {
		System.out.println("Enter three points, press enter for each x and y value: ");
		Scanner kb = new Scanner(System.in);
		double x1 = kb.nextDouble();
		double y1 = kb.nextDouble();
		double x2 = kb.nextDouble();
		double y2 = kb.nextDouble();
		double x3 = kb.nextDouble();
		double y3 = kb.nextDouble();

		Triangle t = new Triangle(x1, y1, x2, y2, x3, y3);
		t.fill();
	}

}
