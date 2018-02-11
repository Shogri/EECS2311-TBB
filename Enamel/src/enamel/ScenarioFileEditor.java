package enamel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class ScenarioFileEditor extends JFrame implements ActionListener { //view and controller

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	public String output; // global variable for output from GUI's
	public String filename;
	public File filePath; // global variable for absolute path of file
	public boolean fileState; // true means new file, false means existing file(no use right now)
	public boolean iscancelled;
	private JButton btnEditExistingScenario;
	private JButton btnEdit;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ScenarioFileEditor() throws IOException {
		this.editorWindow();
		//		int y = JOptionPane.showConfirmDialog(null, "New File?");
//		if (y == JOptionPane.YES_OPTION) {
//			filename = JOptionPane.showInputDialog(this, "Type in file name:");
//			filePath = new File(filename + ".txt");
//			fileState = true;
//			this.editorWindow();
//		} else if (y == JOptionPane.NO_OPTION) {
//			
//			JOptionPane.showMessageDialog(null, "Select your existing file");
//			this.launcher();
//			while (!this.isScenarioFile(filename) && this.iscancelled == false){
//				this.launcher();
//				
//			}
//			fileState = false;
//		} else {
//			System.exit(0); //terminate
//		}
	}

	public void editorWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//JLabel Popup
		JLabel lblNewLabel = new JLabel("Scenario Editor\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(31, 16, 161, 40);
		lblNewLabel.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(10, 67, 354, 240);
		contentPane.add(list);
		
		JButton btnCreateNewScenario = new JButton("Create New Scenario");
		btnCreateNewScenario.setBounds(216, 11, 148, 23);
		contentPane.add(btnCreateNewScenario);
		
		btnEditExistingScenario = new JButton("Edit Existing Scenario");
		btnEditExistingScenario.setBounds(216, 33, 148, 23);
		contentPane.add(btnEditExistingScenario);
		
		btnEdit = new JButton("Edit Field");
		btnEdit.setBounds(374, 99, 183, 23);
		contentPane.add(btnEdit);
		
		JLabel lblSelectedScemario = new JLabel("Selected Scemario:");
		lblSelectedScemario.setBounds(372, 16, 121, 40);
		contentPane.add(lblSelectedScemario);
		
		JButton btnNewField = new JButton("Add Field");
		btnNewField.setBounds(374, 67, 183, 23);
		contentPane.add(btnNewField);
		
		JButton btnDeleteField = new JButton("Delete Field");
		btnDeleteField.setBounds(374, 133, 183, 23);
		contentPane.add(btnDeleteField);
		
		JButton btnSaveScenario = new JButton("Save Scenario");
		btnSaveScenario.setBounds(374, 167, 183, 23);
		contentPane.add(btnSaveScenario);
	}
	
	public void launcher() throws IOException {
		
		this.iscancelled = false;
		JFileChooser chooser1 = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
		chooser1.setFileFilter(filter);
		int returnVal = chooser1.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
		}
		
		if (returnVal == JFileChooser.CANCEL_OPTION)
		{
			this.iscancelled = true;
			return;
		}
		
		chooser1.removeAll();
		//
		//if (!isScenarioFile(chooser1.getSelectedFile().getAbsolutePath()))
		//{
			//System.out.println("failed");
			//JOptionPane.showMessageDialog(null, "Error: Please select a Scenario file");
			//this.launcher();
		//}
		
		try
		{
			filename = chooser1.getSelectedFile().getAbsolutePath();
			filePath = new File(filename);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

	public boolean isScenarioFile(String file)
	{
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader buffread = new BufferedReader(filereader);
			
			String line1 = buffread.readLine();
			System.out.println(line1);
			String line2 = buffread.readLine();
			System.out.println(line2);
			
			if (line1.matches("Cell [0-9+]") && line2.matches("Button [0-9+]"))
			{
				buffread.close();
				return true;
			}else{
				System.out.println("failed");
				JOptionPane.showMessageDialog(null, "Error: Please select a Scenario file");
				buffread.close();
				return false;
				
			}

			
			
		} catch (Exception e)
		{
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * The method WriteCell currently deletes all content of the current chosen
	 * file and outputs the parameter string to the beginning of the parameter
	 * file WARNING DO NOT USE FOR FILES WITH IMPORTANT CONTENT!!
	 * 
	 * @param x
	 * @param f
	 * @throws IOException
	 */

	public void WriteCell(String inputCells, File f) throws IOException {
		
		FileWriter fw = new FileWriter(f);
		fw.write(inputCells + "\n");
		fw.close();
		
		}

	/**
	 * The method WriteButton adds to the end of the current chosen file feel
	 * free to test this
	 * 
	 * @param x
	 * @param f
	 * @throws IOException
	 */
	public void WriteButton(String inputButton, File f) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		fw.write(System.lineSeparator());
		fw.write(inputButton + "\n");
		fw.close();
		}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*
		if (e.getSource() == b1) {
			output = JOptionPane.showInputDialog("Enter Number of Braille Cells (enter a positive integer): ");
			int intCells = Integer.valueOf(output);
			if(intCells <= 0)
			{
				throw new IllegalArgumentException("Please enter a positive argument");
			}
			else
			{
			try {

				this.WriteCell("Cells "+output, filePath);

				this.WriteCell(output, filePath);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}
		}
		if (e.getSource() == b2) {
			//output = "Button " + JOptionPane.showInputDialog("Enter Number of Buttons (enter an integer): ");
			output = JOptionPane.showInputDialog("Enter Number of Buttons (enter an integer): ");
			int intInput = Integer.valueOf(output);
			
			try {

				if(intInput<=0)
				{
					throw new IllegalArgumentException("Please input  positive  integer");
				}
				else
				{
				this.WriteButton("Button "+output, filePath);
				}

				

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == b3) {
			output = JOptionPane.showInputDialog("Enter Command");
			try {
				this.WriteButton(output, filePath);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		*/
	}
}
