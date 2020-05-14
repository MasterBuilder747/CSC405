/*
Final Project
Name: Joseph Audras
Professor: Dr. Reinhart
Class: CSC 405-1
Date due: 5-14-20
*/

package FinalProject;

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

    int window = 1024;
    int WIDTH = window;
    int HEIGHT = window;

    //triangle drawing variables
    int count = 0;
    int triIndex = 0;
    int size = 1;
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
        mainStage.setTitle("Final Project Joseph Audras");

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
    private void prepareActionHandlers(Pane container) {
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
    public class GraphicsCanvasInner extends Canvas {

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
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {

                    }
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
        private int nButtons = 9;

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
                    buttons[0].setText("Radius:");
                } else if (i == 1) {
                    buttons[1].setText("RGB Color:");
                } else if (i == 2) {
                    buttons[2].setText("Orbit radius:");
                } else if (i == 10) {
                    buttons[10].setText("Theta:");
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
            buttons[i].setText("Create Star");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[3]) {
                        //graphicsCanvas.renderSurface.clearSurface();

                        //take in: RGB color, radius

                        //resets the shape as well
                        sg.addCircle(512, 512, 50);
                        //sg.circle.fb.print();
                        //sg.circle.renderFill(graphicsCanvas.renderSurface.getSurface(), Color.rgb(0, 0, 0, 1.0));
                        sg.sphere.render(graphicsCanvas.renderSurface.getSurface(), 1, 1, -1, Color.rgb(255, 255, 0));

                        //graphicsCanvas.renderSurface.getSurface().print();
                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Star Created.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });


            i = 4;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Create Planet");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[4]) {
                        sg.addCube(100);
                        sg.c.t.translation(512, 512, 0);
                        sg.c.t.arbitrary(new double[]{512, 512, 0}, 45, new double[]{1, 1, 1});
                        sg.c.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Created Planet.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 5;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Move");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[5]) {
                        //graphicsCanvas.renderSurface.clearSurface();
                        size++;
                        //sg.sphere.move(256, 240);
                        sg.sphere.render(graphicsCanvas.renderSurface.getSurface(), 1, 1, -1, Color.rgb(255, 255, 0));
                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                    }
                }
            });

            i = 6;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Set Orbit");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[6]) {

                    }
                }
            });

            i = 7;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Set Theta");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[7]) {

                    }
                }
            });

            i = 8;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Animate");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[8]) {

                    }
                }
            });
        }
    }
}
