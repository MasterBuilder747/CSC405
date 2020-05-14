package PhongShading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

// -- need to extend JPanel so that we can override some of
//    the default methods -- JPanel inherits from AWT Container
//    (can hold Components) which inherits from AWT Component
//    (can be displayed on a screen)
public class GraphicPanel extends JPanel {

	private GraphicsFrame gf;
	
	private Timer animationTimer = null;	
	private boolean running = false;

	public Shader shader = new Shader();
	public Point surfacenormal = null;
	public Point surfacepoint = null;

	
	public Timer getAnimationTimer()
	{
		return animationTimer;
	}

	public GraphicPanel (GraphicsFrame _gf)
	{
		super();
		
		gf = _gf;

		this.setBackground(Color.black);

		// -- The JPanel can have a mouse listener if desired
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				surfacepoint = new Point(arg0.getX(), arg0.getY());
				double angle[] = new double[1];
				// -- z coordinate is calculated since it is a sphere
				surfacenormal = shader.getNormal(surfacepoint.x, surfacepoint.y, 0, angle);
				String s = (int)angle[0] + "";
				gf.getControlcPanel().reflectangle.Field().setText(s);
				repaint();
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		}
		);
	
		// -- Timer will generate an event every 10mSec once it is started
		//    First parameter is the delay in mSec, second is the ActionListener
		animationTimer = new Timer(5, 
				// -- ActionListener for the timer event
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				}
			}
					
		);

	}


	
	
	// -- this override sets the desired size of the JPanel which is
	//    used by some layout managers -- default desired size is 0,0
	//    which is, in general, not good -- will pull from layout manager
	public Dimension getPreferredSize() 
	{
		return new Dimension(50, 50);
	}
	
	// -- this override is where all the painting should be done. 
	//    DO NOT call it directly. Rather, call repaint() and let the
	//    event handling system decide when to call it
	//    DO NOT put graphics function call elsewhere in the code, although
	//    legal, it's bad practice and could destroy the integrity of the
	//    display
	public void paint(Graphics g)
	{
		// -- the base class paintComponent(g) method ensures 
		//    the drawing area will be cleared properly. Do not
		//    modify any attributes of g prior to sending it to
		//    the base class
		super.paintComponent(g);
		
		// -- for legacy reasons the parameter comes in as type Graphics
		//    but it is really a Graphics2D object. Cast it up since the
		//    Graphics2D class is more capable
		Graphics2D g2d = (Graphics2D)g;

		// -- get the height and width of the JPanel drawing area
		int width = this.getWidth();
		int height = this.getHeight();
		System.out.println(width + "x" + height);
		if (shader.shadebuffer == null) {
			shader.shadebuffer = new int[height][width];
			shader.renderSurface = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}
		else {
			g2d.drawImage(shader.renderSurface, 0, 0, width, height, null);
		}
		
		if (surfacenormal != null) {
			g2d.setColor(Color.black);
			g2d.fillOval(surfacepoint.x - 2, surfacepoint.y - 2, 5, 5);
			g2d.setColor(Color.white);
			g2d.drawLine(surfacepoint.x,  surfacepoint.y, surfacenormal.x, surfacenormal.y);
			surfacenormal = null;
		}
	}
	

}
