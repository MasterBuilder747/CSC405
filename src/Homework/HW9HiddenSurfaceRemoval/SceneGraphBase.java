/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public abstract class SceneGraphBase {

    protected double[][] vertices;

    public void setScene(double[][] newscene) {
        vertices = newscene;
    }

    public double getScene () {
        return 0.0;
    }
}
