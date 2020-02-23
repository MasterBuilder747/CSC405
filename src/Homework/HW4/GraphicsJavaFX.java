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
                    button0.setMaxWidth(60);
                    this.getChildren().add(button0);
                } else if (i == 1) {
                    button1 = new TextField();
                    button1.setMaxWidth(60);
                    this.getChildren().add(button1);
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

                            System.out.println(button0.getText());
                        }
                        else if (actionEvent.getSource() == buttons[1]) {

                            System.out.println(button1.getText());
                        }
                        // -- process the button
                        System.out.println(actionEvent.getSource().toString());
                        // -- and return focus back to the pane
                        pane.requestFocus();
                    }
                });
            }

            //action buttons
            int i = 2;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Scene");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[2]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Scene rendered.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 3;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Translate");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[3]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        sc.translation(20, 20, 0);
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Translated by [textbox]");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 4;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Scale");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[4]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        //this cannot be 0
                        sc.scaling(1.2, 1.2, 1);
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Scaled by [textbox]");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 5;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate X");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[5]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        sc.rotateX(45);
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Rotate by X.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 6;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate Y");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[6]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        sc.rotateY(45);
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Rotate by Y.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 7;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Rotate Z");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[7]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        sc.rotateZ(45);
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Rotate by Z.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });

            i = 8;
            buttons[i] = new Button();
            buttons[i].setMnemonicParsing(true);
            buttons[i].setText("Origin");
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[8]) {
                        graphicsCanvas.renderSurface.clearSurface();

                        //input textbox here
                        sc.toOrigin();
                        sc.render(graphicsCanvas.renderSurface.getSurface());

                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Moved to origin.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });
        }
    }
}
