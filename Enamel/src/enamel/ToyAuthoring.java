package enamel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToyAuthoring //extends JFrame implements ActionListener{
{
private static Component parent;
	
	JButton yes;
    JButton no;
    JFrame frame;
	/**
	 * A class to help educators load scenarios </br>
	 * Scenarios are <code> .txt </code> files used to help teach Braille. </br>
	 * </br>
	 * The class loads a scenario by prompting the user for a status about their visual impairment  and accordingly opens up a file chooser for them
	 * to choose and load a scenario
	 * 
	 */
    private ToyAuthoring()
	{
    	
	}
		/*frame = new JFrame("Visual Impairment Status");
		JPanel p = new JPanel();
		p.setSize(400, 800);
		p.setLayout(new GridLayout(2,2));
		
		
		String button1Text = "Yes"; //Yes Button text
		String button2Text = "No"; //No Button text
		
		yes = new JButton();
		yes.setText(button1Text);
		yes.setFont(new Font("Monospaced", Font.CENTER_BASELINE,24));
		no = new JButton();
		no.setText(button2Text);
		no.setFont(new Font("Monospaced", Font.CENTER_BASELINE,24));
		p.add(yes);
		p.add(no);
		yes.addActionListener(this);
		no.addActionListener(this);
		
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 100);
		frame.setSize(300, 300);
		yes.setSize(10,1);
		frame.setResizable(true);
		frame.setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source.equals(yes));
		{
			try
			{
				frame.dispose();
				launcher(true);
				
				
			}
			catch (Exception e3)
			{
				e3.printStackTrace();
			}
			
		}
		
		if(source.equals(no))
		{
			try
			{
				launcher(false);
			}
			catch (Exception e3)
			{
				e3.printStackTrace();
			}
		}
		
	} 
	
	
	/**
	 * 
	 * @param t 
	 * 
	 * Indicates whether the user is visually impaired or not </br>
	 *<code> True </code> implies visual impairment </br>
	 *<p>
	 *
	 *This method opens up a File chooser, and helps the user load a particular scenario</br>
	 *and parse it using the <code> ScenarioParser </code> class
	 *</p>
	 *
	 * @throws IOException
	 */
	public void launcher(boolean t) throws IOException
	{
		
	} 
	public static void main(String[] args) {
		
		ScenarioParser s = new ScenarioParser(true);
		JFileChooser chooser1 = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Factory Scenario Files", "txt");
		chooser1.setFileFilter(filter);
		int returnVal = chooser1.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
			s.setScenarioFile(chooser1.getSelectedFile().getAbsolutePath()); //uses JFileChooser to generate path of file
		}
		
	}
}
