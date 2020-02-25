/*
Homework 4
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 2-21-20
*/
package Homework.HW4;

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

public class GraphicsJavaFX extends Application
{
    int WIDTH = 512;
    int HEIGHT = 512;

    int imageMode = 0;

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

    SceneGraph sc = new SceneGraph();

    @Override
    public void start(Stage mainStage)
    {
        // -- Application title
        mainStage.setTitle("Homework 4 Joseph Audras");

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

        public GraphicsCanvasInner(int width, int height)
        {
            super(width, height);

            // -- get the context for drawing on the canvas
            graphicsContext = this.getGraphicsContext2D();

            // -- set up event handlers for mouse
            prepareActionHandlers();

            renderSurface = new RenderSurface((int)width, (int)height);

        }

        // -- check the active keys and render graphics
        //update display
        public void repaint()
        {
            double height = this.getHeight();
            double width = this.getWidth();

            // -- clear canvas
            graphicsContext.clearRect(0, 0, width, height);

            graphicsContext.setStroke(Color.RED);
            //sc.render(renderSurface.getSurface());
            graphicsContext.drawImage(renderSurface, 0, 0, this.getWidth(), this.getHeight());
        }

        //display image
        public void paintTwoPoint() {
            //two point
            graphicsCanvas.renderSurface.clearSurface();
            //then add the twopoint image
            Lines.mainTwoPoint(renderSurface.getSurface());
            graphicsCanvas.renderSurface.insertArray();
        }

        //display image
        public void paintParametric() {
            //parametric
            renderSurface.clearSurface();
            //then add the parametric image
            Lines.mainParametric(renderSurface.getSurface());
            renderSurface.insertArray();
        }

        private void prepareActionHandlers()
        {

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
        private int nButtons = 9;

        private TextField button0;
        private TextField button1;
        private TextField button2;

        public ControlBoxInner()
        {
            super();

            // -- set up buttons
            prepareButtonHandlers();

            // -- add the buttons to an V (vertical) Box (container)
            for (int i = 0; i < buttons.length; ++i) {
                this.getChildren().add(buttons[i]);
                if (i == 0) {
                    button0 = new TextField();
                    button0.setMaxWidth(100);
                    this.getChildren().add(button0); //fixed point / translation
                } else if (i == 1) {
                    button1 = new TextField();
                    button1.setMaxWidth(100);
                    this.getChildren().add(button1); //angle in degrees
                } else if (i == 2) {
                    button2 = new TextField();
                    button2.setMaxWidth(100);
                    this.getChildren().add(button2); //scaling
                }
            }
        }

        private void prepareButtonHandlers() {
            buttons = new Button[nButtons];

            //textboxes and enter buttons
            for (int i = 0; i < buttons.length; ++i) {
                buttons[i] = new Button();
                buttons[i].setMnemonicParsing(true);
                buttons[i].setText("Enter box " + i + ":"); //trans (x,y,z) and rotate (deg)
                buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (actionEvent.getSource() == buttons[0]) {

                        }
                        else if (actionEvent.getSource() == buttons[1]) {

                        }
                        else if (actionEvent.getSource() == buttons[2]) {

                        }
                        // -- process the button
                        System.out.println(actionEvent.getSource().toString());
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
                        sc.resetShape();
                        sc.render(graphicsCanvas.renderSurface.getSurface());

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
                        String s = button0.getText();
                        String[] but0 = s.split(",\\s*");
                        double x;
                        double y;
                        double z;
                        try {
                            if (but0[0] != null || but0[1] != null || but0[2] != null) {
                                x = Double.parseDouble(but0[0]);
                                y = Double.parseDouble(but0[1]);
                                z = Double.parseDouble(but0[2]);
                                graphicsCanvas.renderSurface.clearSurface();
                                sc.translation(x, y, z);
                                System.out.println("Translated by " + x + ", " + y + ", " + z);
                            }
                        } catch (Exception e) {
                            System.out.println("Requires 3 doubles: x, y, and z comma separated from the top textbox.");
                        }

                        sc.render(graphicsCanvas.renderSurface.getSurface());

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
                        String s = button0.getText();
                        String[] but0 = s.split(",\\s*");
                        try {
                            if (but0[0] != null || but0[1] != null || but0[2] != null) {
                                double x = Double.parseDouble(but0[0]);
                                double y = Double.parseDouble(but0[1]);
                                double z = Double.parseDouble(but0[2]);
                                //add z != 0 for cube
                                if (x != 0 & y != 0) {
                                    graphicsCanvas.renderSurface.clearSurface();
                                    sc.scaling(x, y, z, 100, 100, 0);
                                    System.out.println("Scaled by " + x + ", " + y + ", " + z);
                                } else {
                                    System.out.println("0 is not accepted for x or y as the object will not render.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Requires 3 doubles: x, y, and z comma separated from the top textbox.");
                            System.out.println("Values larger than 1 increase the size, values less than 0 decrease it.");
                        }

                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
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
                                    sc.rotateX(angle);
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox.");
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
                                    sc.rotateX(angle, x, y, z); //z will be passed for cube
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted.");
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
                                    sc.rotateY(angle);
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox.");
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
                                    sc.rotateY(angle, x, y, z); //z will be passed for cube
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted.");
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
                                    sc.rotateZ(angle);
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox.");
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
                                    sc.rotateZ(angle, x, y, z); //z will be passed for cube
                                    sc.render(graphicsCanvas.renderSurface.getSurface());
                                    graphicsCanvas.renderSurface.insertArray();
                                    graphicsCanvas.repaint();
                                    System.out.println("Rotate by " + angle + " degrees.");
                                    pane.requestFocus();
                                }
                            } catch (Exception e) {
                                System.out.println("Requires one double of angle in degrees in the bottom textbox. Fixed point is already defined.");
                            }
                        } else if (but0.length == 2 || but0.length == 1) {
                            //there are some values that have been inputted but others that have not
                            System.out.println("For a fixed point, x, y, and z need to be inputted.");
                        } else {
                            System.out.println("Too many inputs in the textbox.");
                        }
                    }
                }
            });
        }
    }
}
