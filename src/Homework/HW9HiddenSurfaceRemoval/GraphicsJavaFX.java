/*
Homework 9
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 4-16-20
*/

package Homework.HW9HiddenSurfaceRemoval;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GraphicsJavaFX extends Application {

    int WIDTH = 512;
    int HEIGHT = 512;

    //triangle drawing variables
    int count = 0;
    int triIndex = 0;
    String colorText;
    ArrayList<Triangle> triangles = new ArrayList<>();

    private AnimationTimer animationTimer;
    private Scene mainScene;

    // -- Main container
    private BorderPane pane;

    // -- Graphics container
    private GraphicsCanvasInner graphicsCanvas;

    // -- Controls container
    private ControlBoxInner controlBox;

    // -- launch the application
    public void launchApp(String[] args)
    {
        launch(args);
    }

    //this is the only object being created, right now
    SceneGraph sg = new SceneGraph(WIDTH, HEIGHT);

    @Override
    public void start(Stage mainStage) {
        // -- Application title
        mainStage.setTitle("Homework 9 Joseph Audras");

        // -- create canvas for drawing
        graphicsCanvas = new GraphicsCanvasInner(WIDTH, HEIGHT);
        // -- construct the controls
        controlBox = new ControlBoxInner();
        // -- create the primary window structure
        pane = new BorderPane();
        // -- add the graphics canvas and the control box to the split pan
        pane.setLeft(controlBox);
        pane.setCenter(graphicsCanvas);
        // -- set up key listeners (to Pane)
        prepareActionHandlers(pane);
        mainScene = new Scene(pane);
        mainStage.setScene(mainScene);
        // -- create the animation timer handler
        animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //graphicsCanvas.repaint();
                System.out.println("tic");
            }
        };
        // -- paint the graphics canvas before the initial display
        graphicsCanvas.repaint();
        // -- display the application window
        mainStage.show();
        // -- set keyboard focus to the pane
        pane.requestFocus();
    }

    // -- key handlers belong to the Pane
    private void prepareActionHandlers(Pane container)
    {
        // -- key listeners belong to Pane
        container.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode().toString());
                graphicsCanvas.repaint();
            }
        });
        container.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                graphicsCanvas.repaint();
            }
        });

    }

    // -- Inner class for Graphics
    public class GraphicsCanvasInner extends Canvas  {

        private GraphicsContext graphicsContext;
        private RenderSurface renderSurface;

        public GraphicsCanvasInner(int width, int height) {
            super(width, height);
            // -- get the context for drawing on the canvas
            graphicsContext = this.getGraphicsContext2D();
            // -- set up event handlers for mouse
            prepareActionHandlers();
            renderSurface = new RenderSurface((int)width, (int)height);
        }

        // -- check the active keys and render graphics
        //update display
        public void repaint() {
            double height = this.getHeight();
            double width = this.getWidth();

            // -- clear canvas
            graphicsContext.clearRect(0, 0, width, height);

            graphicsContext.setStroke(Color.RED);
            //sc.render(renderSurface.getSurface());
            graphicsContext.drawImage(renderSurface, 0, 0, this.getWidth(), this.getHeight());
        }

        private void prepareActionHandlers() {
            // -- mouse listeners belong to the canvas
            this.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                    }
                    else if (event.getButton() == MouseButton.SECONDARY) {
                    }
                    pane.requestFocus();
                }
            });
            this.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                    }
                    else if (event.getButton() == MouseButton.SECONDARY) {
                    }
                    pane.requestFocus();
                    repaint();
                }
            });

            //triangle draw
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    //only on the last click: check the color and draw if it is valid, if not, do not draw it

                    if (event.getButton() == MouseButton.PRIMARY) {
                        //draw the triangle, exclude z axis as the screen is only in 2D
                        //System.out.println("mouse pos: (" + event.getX() + ", " + event.getY() + ")");
                        int x = (int)event.getX();
                        int y = (int)event.getY();

                        //keep track of number of clicks
                        //System.out.println(count);

                        if (count == 0) {
                            //first click creates the triangle, populating the arraylist
                            triangles.add(triIndex, new Triangle(WIDTH, HEIGHT));
                        }

                        //the current triangle will add the point to that triangle object
                        triangles.get(triIndex).add(count, new Point(x, y));

                        count++;
                        if (count == 3) {
                            //the triangle is now finished and is stored, render it
                            //graphicsCanvas.renderSurface.clearSurface();

                            //test the color textbox
                            String cText = controlBox.button10.getText();

                            if (!cText.equals("")) {
                                String[] s = cText.split(",\\s*");
                                try {
                                    if (s.length == 2) {
                                        int[] out = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
                                        if (out[0] > 255 || out[0] < 0 || out[1] > 255 || out[1] < 0) {
                                            System.out.println("Triangle colors must be between 0 and 255, inclusive.");
                                        } else {
                                            //render the triangle, do not clear the surface so that other triangles can render
                                            triangles.get(triIndex).render(graphicsCanvas.renderSurface.getSurface(), out[0], out[1]);
                                            //sg.render(graphicsCanvas.renderSurface.getSurface());
                                            graphicsCanvas.renderSurface.insertArray();
                                            graphicsCanvas.repaint();
                                            System.out.println("Triangle rendered.");
                                        }
                                    } else {
                                        System.out.println("Requires two color inputs. Inputs are fillColor, outlineColor.");
                                    }
                                } catch (Exception e) {
                                    //catch unwanted text
                                    System.out.println("Invalid input in the color textbox. Inputs are fillColor, outlineColor.");
                                }
                            } else {
                                System.out.println("Colors input required for drawing a triangle. Inputs are fillColor, outlineColor.");
                            }
                            count = 0;
                            triIndex++;
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        //nothing for now
                    }
                    pane.requestFocus();
                    repaint();
                }
            });
            this.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                    }
                    else if (event.getButton() == MouseButton.SECONDARY) {
                    }
                    pane.requestFocus();
                    repaint();
                }
            });
        }
    }

    // -- Inner class for Controls
    public class ControlBoxInner extends VBox {

        private Button buttons[];
        //total amount of elements, textboxes and buttons
        private int nButtons = 11;

        //textbox objects that store the text
        private TextField button0;
        private TextField button1;
        private TextField button2;
        private TextField button10;

        public ControlBoxInner() {
            super();

            // -- set up buttons
            prepareButtonHandlers();

            // -- add the buttons to an V (vertical) Box (container)
            for (int i = 0; i < buttons.length; ++i) {
                this.getChildren().add(buttons[i]);
                if (i == 0) {
                    button0 = new TextField();
                    button0.setMaxWidth(150);
                    this.getChildren().add(button0); //fixed point
                } else if (i == 1) {
                    button1 = new TextField();
                    button1.setMaxWidth(150);
                    this.getChildren().add(button1); //angle in degrees
                } else if (i == 2) {
                    button2 = new TextField();
                    button2.setMaxWidth(150);
                    this.getChildren().add(button2); //scaling / translation / abritrary vector
                } else if (i == 10) {
                    button10 = new TextField();
                    button10.setMaxWidth(150);
                    this.getChildren().add(button10); //triangle color
                }
            }
        }

        private void prepareButtonHandlers() {
            buttons = new Button[nButtons];

            //textboxes and enter buttons
            for (int i = 0; i < buttons.length; ++i) {
                buttons[i] = new Button();
                buttons[i].setMnemonicParsing(true);
                if (i == 0) {
                    buttons[0].setText("Fixed Point:");
                } else if (i == 1) {
                    buttons[1].setText("Angle:");
                } else if (i == 2) {
                    buttons[2].setText("Scale/Trans/Arb vect:");
                } else if (i == 10) {
                    buttons[10].setText("Triangle color:");
                }

                buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (actionEvent.getSource() == buttons[0]) {

                        }
                        else if (actionEvent.getSource() == buttons[1]) {

                        }
                        else if (actionEvent.getSource() == buttons[2]) {

                        }
                        else if (actionEvent.getSource() == buttons[10]) {

                        }
                        // -- process the button
                        //System.out.println(actionEvent.getSource().toString());
                        // -- and return focus back to the pane
                        pane.requestFocus();
                    }
                });
            }

            //action buttons
            int i = 3;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Scene");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[3]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //resets the shape as well
                        sg.addCube(100);
                        sg.renderCube(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Scene rendered/reset.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 4;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Translate");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[4]) {

                        //input textbox here
                        String s = button2.getText();
                        String[] but2 = s.split(",\\s*");
                        double x;
                        double y;
                        double z;
//                        try {
                            if (but2[0] != null || but2[1] != null || but2[2] != null) {
                                x = Double.parseDouble(but2[0]);
                                y = Double.parseDouble(but2[1]);
                                z = Double.parseDouble(but2[2]);
                                graphicsCanvas.renderSurface.clearSurface();

                                //sg.translation(x, y, z);
                                System.out.println("Translated by " + x + ", " + y + ", " + z);
                            }
//                        } catch (Exception e) {
//                            System.out.println("Requires 3 doubles: x, y, and z comma separated from the last textbox.");
//                        }

                        //sg.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 5;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Scale");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[5]) {
                        //input textbox here
                        String but0 = button0.getText(); //fixed point, optional
                        String[] fixed = but0.split(",\\s*");

                        String but2 = button2.getText(); //scale vector, required
                        String[] scale = but2.split(",\\s*");

                        //handle scale first, no fixed point
                        if (fixed[0].equals("")) {
                            if (scale.length == 3) {
                                //process scale only, without a fixed point
                                try {
                                    if (scale[0] != null || scale[1] != null || scale[2] != null) {
                                        double x = Double.parseDouble(scale[0]);
                                        double y = Double.parseDouble(scale[1]);
                                        double z = Double.parseDouble(scale[2]);
                                        //System.out.println("x: " + x + "y: " + y + "z: " + z);
                                        //add z != 0 for cube
                                        if (x == 0 || y == 0 || z == 0) {
                                            System.out.println("for scaling: x, y, and z cannot be 0 as dimensions will be reduced. Use 1 to keep the same scale by that axis");
                                        } else {
                                            graphicsCanvas.renderSurface.clearSurface();
                                            //sg.scaling(x, y, z);
                                            //sg.render(graphicsCanvas.renderSurface.getSurface());
                                            graphicsCanvas.renderSurface.insertArray();
                                            graphicsCanvas.repaint();
                                            System.out.println("Scaled object by " + x + ", " + y + ", " + z);
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Requires 3 doubles: x, y, and z comma separated from the last textbox.");
                                    System.out.println("Values larger than 1 increase the size, values less than 0 decrease it.");
                                }
                            } else if (fixed.length == 2 || fixed.length == 1) {
                                //there are some values that have been inputted but others that have not
                                System.out.println("For scale, x, y, and z need to be inputted in the last textbox.");
                            } else {
                                System.out.println("Too many inputs in the textbox.");
                            }
                            //handle both scaled and fixed point
                        } else if (fixed.length == 3 && scale.length == 3) {
                            //process scale with fixed point
                            try {
                                double fx = Double.parseDouble(fixed[0]);
                                double fy = Double.parseDouble(fixed[1]);
                                double fz = Double.parseDouble(fixed[2]);
                                double sx = Double.parseDouble(scale[0]);
                                double sy = Double.parseDouble(scale[1]);
                                double sz = Double.parseDouble(scale[2]);
                                //System.out.println("x: " + sx + " y: " + sy + " z: " + sz);
                                //add z != 0 for cube
                                if (sx == 0 || sy == 0 || sz == 0) {
                                    System.out.println("for fixed/scaling: x, y cannot be 0.");
                                } else {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    //sg.scaling(sx, sy, sz, fx, fy, fz);
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Scaled object by " + sx + ", " + sy + ", " + sz + " from fixed point " + fx + ", " + fy + ", " + fz);
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox. Fixed point is already defined.");
                            }
                        } else if (fixed.length == 2 || fixed.length == 1 || scale.length == 2 || scale.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For fixed point and scale, x, y, and z need to be inputted in first and last textbox respectively");
                        } else {
                            System.out.println("Too many inputs in either textbox.");
                        }
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 6;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate X");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[6]) {
                        //input textbox here
                        String s = button0.getText(); //fixed point, optional
                        String[] but0 = s.split(",\\s*");

                        String but1 = button1.getText(); //angle, required
                        double angle;

                        if (but0[0].equals("")) {
                            //process angle only, without a fixed point
                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateX(angle);
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the x axis by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox.");
                            }
                        } else if (but0.length == 3) {
                            //fixed point information is null
                            //process angle only, with a fixed point
                            double x = Double.parseDouble(but0[0]);
                            double y = Double.parseDouble(but0[1]);
                            double z = Double.parseDouble(but0[2]);

                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateX(angle, x, y, z); //z will be passed for cube
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the x axis by " + angle + " degrees via fixed point " + x + ", " + y + ", " + z);
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted in the first textbox.");
                        } else {
                            System.out.println("Too many inputs in the textbox.");
                        }
                    }
                }
            });

            i = 7;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate Y");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[7]) {
                        //input textbox here
                        String s = button0.getText(); //fixed point, optional
                        String[] but0 = s.split(",\\s*");

                        String but1 = button1.getText(); //angle, required
                        double angle;

                        if (but0[0].equals("")) {
                            //process angle only, without a fixed point
                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateY(angle);
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the y axis by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox.");
                            }
                        } else if (but0.length == 3) {
                            //fixed point information is null
                            //process angle only, with a fixed point
                            double x = Double.parseDouble(but0[0]);
                            double y = Double.parseDouble(but0[1]);
                            double z = Double.parseDouble(but0[2]);
                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateY(angle, x, y, z); //z will be passed for cube
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the y axis by " + angle + " degrees via fixed point " + x + ", " + y + ", " + z);
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted in the first textbox.");
                        } else {
                            System.out.println("Too many inputs in the textbox.");
                        }
                    }
                }
            });

            i = 8;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate Z");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[8]) {
                        //input textbox here
                        String s = button0.getText(); //fixed point, optional
                        String[] but0 = s.split(",\\s*");

                        String but1 = button1.getText(); //angle, required
                        double angle;
                        if (but0[0].equals("")) {
                            //process angle only, without a fixed point
                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateZ(angle);
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the z axis by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox.");
                            }
                        } else if (but0.length == 3) {
                            //fixed point information is null
                            //process angle only, with a fixed point
                            double x = Double.parseDouble(but0[0]);
                            double y = Double.parseDouble(but0[1]);
                            double z = Double.parseDouble(but0[2]);
                            try {
                                if (but1 != null) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    angle = Double.parseDouble(but1);
                                    //sg.rotateZ(angle, x, y, z); //z will be passed for cube
                                    //sg.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate along the z axis by " + angle + " degrees via fixed point " + x + ", " + y + ", " + z);
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the second textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted in the first textbox.");
                        } else {
                            System.out.println("Too many inputs in the textbox.");
                        }
                    }
                }
            });

            i = 9;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Arbitrary");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[9]) {
                        //input textbox here
                        String s1 = button0.getText(); //fixed point, required
                        String[] but0 = s1.split(",\\s*");

                        String but1 = button1.getText(); //angle of rotation, required

                        String s2 = button2.getText(); //vector axis of rotation, required
                        String[] but2 = s2.split(",\\s*");

                        if (but0.length == 3 && !but1.equals("") && but2.length == 3) {
                            //fixed point
                            double fx = Double.parseDouble(but0[0]);
                            double fy = Double.parseDouble(but0[1]);
                            double fz = Double.parseDouble(but0[2]);
                            //angle in degrees
                            double angle = Double.parseDouble(but1);
                            //axis of rotation vector
                            double ax = Double.parseDouble(but2[0]);
                            double ay = Double.parseDouble(but2[1]);
                            double az = Double.parseDouble(but2[2]);
                            if (ax < 0.000000001 && ay < 0.000000001 && az < 0.000000001) {
                                System.out.println("Axis of rotation cannot be 0, 0, 0.");
                            } else {
                                graphicsCanvas.renderSurface.clearSurface();
                                //sg.arbitrary(new double[]{fx, fy, fz}, angle, new double[]{ax, ay, az});
                                //sg.render(graphicsCanvas.renderSurface.getSurface());
                                graphicsCanvas.renderSurface.insertArray();
                                graphicsCanvas.repaint();
                                System.out.println("Rotated along arbitrary axis of <" + ax + ", " + ay + ", " + az + "> by " + angle + " degrees around fixed point " + fx + ", " + fy + ", " + fz + ".");
                                pane.requestFocus();
                            }
                        } else {
                            System.out.println("Invalid input for arbitrary.");
                            System.out.println("Requires a fixed point of x, y, z in the top textbox;");
                            System.out.println("one angle in degrees in the middle textbox;");
                            System.out.println("and a vector for the arbitrary axis of rotation in <x, y, z> in the bottom textbox.");
                        }
                    }
                }
            });
        }
    }
}
