package PhongShading;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FieldPanel extends JPanel {

	JTextField field;
	
	public FieldPanel (String label)
	{
		setLayout(new GridLayout(1, 2, 2, 2));
		
		JLabel tag = new JLabel(label, JLabel.HORIZONTAL);
		field = new JTextField();
		
		add(tag);
		add(field);
		
	}
	
	public JTextField Field ()
	{
		return field;
	}

}
