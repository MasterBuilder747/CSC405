package Homework.HW2LineDrawingGUI;

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

    @Override
    public void start(Stage mainStage)
    {
    	// -- Application title
        mainStage.setTitle("Homework 2 Joseph Audras");

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
        private int nButtons = 2;

        private TextField textField;
        
        public ControlBoxInner()
        {
        	super();
        	
            // -- set up buttons
            prepareButtonHandlers();

            // -- add the buttons to an V (vertical) Box (container)
            for (int i = 0; i < buttons.length; ++i) {
            	this.getChildren().add(buttons[i]);
            }
        }
        
        private void prepareButtonHandlers() {
        	buttons = new Button[nButtons];

        	//two point button
        	buttons[0] = new Button();
        	buttons[0].setMnemonicParsing(true);
        	buttons[0].setText("Two Point");
        	buttons[0].setOnAction(new EventHandler<ActionEvent>() {
        	    @Override
                public void handle(ActionEvent actionEvent) {
        	        if (actionEvent.getSource() == buttons[0]) {
        	            // display two point image
                        //two point
                        graphicsCanvas.renderSurface.clearSurface();
                        //then add the twopoint image
                        Lines.mainTwoPoint(graphicsCanvas.renderSurface.getSurface());
                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Two-point image rendered.");
        	        }
        	        // focus back to the pane
                    pane.requestFocus();
        	    }
        	});

        	//parametric button
            buttons[1] = new Button();
            buttons[1].setMnemonicParsing(true);
            buttons[1].setText("Parametric");
            buttons[1].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == buttons[1]) {
                        // display parametric image
                        //parametric
                        graphicsCanvas.renderSurface.clearSurface();
                        //then add the parametric image
                        Lines.mainParametric(graphicsCanvas.renderSurface.getSurface());
                        graphicsCanvas.renderSurface.insertArray();
                        graphicsCanvas.repaint();
                        System.out.println("Parametric image rendered.");
                    }
                    // focus back to the pane
                    pane.requestFocus();
                }
            });
        }
    }
}
