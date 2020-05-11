package PhongShading;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PhongShading.Utilities.Point3D;

public class ControlPanel extends JPanel {

	private GraphicsFrame gf;

	private FieldPanel sourcex = new FieldPanel("Light X");
	private FieldPanel sourcey = new FieldPanel("Light Y");
	private FieldPanel sourcez = new FieldPanel("Light Z");
	public FieldPanel reflectangle = new FieldPanel("Angle ");
	public ControlPanel (GraphicsFrame _gf)
	{
		gf = _gf;
		
		setLayout(new GridLayout(20, 1, 2, 2));

		JButton circleButton = new JButton("Circle");
		circleButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gf.getGraphicPanel().shader.light = new Point3D(Double.parseDouble(sourcex.Field().getText()), Double.parseDouble(sourcey.Field().getText()), Double.parseDouble(sourcez.Field().getText()));
						gf.getGraphicPanel().shader.fillSphere();
						gf.getGraphicPanel().repaint();
					}
				}
			);
		
		JButton sphereButton = new JButton("Shade Circle");
		sphereButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gf.getGraphicPanel().shader.reset();
						gf.getGraphicPanel().shader.light = new Point3D(Double.parseDouble(sourcex.Field().getText()), Double.parseDouble(sourcey.Field().getText()), Double.parseDouble(sourcez.Field().getText()));
						gf.getGraphicPanel().shader.shadeSphere();
						gf.getGraphicPanel().repaint();
					}
				}
			);
		
		JButton cylinderButton = new JButton("Shade Cylinder");
		cylinderButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gf.getGraphicPanel().shader.reset();
						gf.getGraphicPanel().shader.light = new Point3D(Double.parseDouble(sourcex.Field().getText()), Double.parseDouble(sourcey.Field().getText()), Double.parseDouble(sourcez.Field().getText()));
						gf.getGraphicPanel().shader.shadeCylinder();
						gf.getGraphicPanel().repaint();
					}
				}
			);

		sourcex.Field().setText("1");
		sourcey.Field().setText("1");
		sourcez.Field().setText("-1");
		

		add(circleButton);
		add(sourcex);
		add(sourcey);
		add(sourcez);
		add(sphereButton);
		add(cylinderButton);
		//add(reflectangle);
		
	}
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(100, 500);
	}

}
