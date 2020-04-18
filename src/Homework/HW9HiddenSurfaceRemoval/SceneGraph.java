/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

public class SceneGraph {

    //this scenegraph's size is the framebuffer size
    int w;
    int h;

    //a collection of objects on the scene
    //for now, there is only one cube,
    //but later on an ArrayList will be used for multiple cubes
    Cube c;

    public SceneGraph(int x, int y) {
        this.w = x;
        this.h = y;
    }

    public void addCube(int size) {
        //this would later add to the ArrayList
        this.c = new Cube(this.w, this.h);
        c.setSize(size);
    }

    //render the lines at those coordinates
    //this renders each square
    public void renderCube(int[][] fb) { //add int index for which cube later
        this.c.render(fb);
    }
}
