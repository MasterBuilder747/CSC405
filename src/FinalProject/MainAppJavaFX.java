/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

public class MainAppJavaFX {

	// -- There is some configuration issue with the JavaFX launcher.
	//    It has something to do with modules vs. classpath
	//    To get around it run this main method to invoke the actual
	//    JavaFX main method that we want
	//    https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174
	public static void main(String[] args) {
		new GraphicsJavaFX().launchApp(args);
	}

}
