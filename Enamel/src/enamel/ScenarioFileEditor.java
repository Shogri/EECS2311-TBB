package enamel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class ScenarioFileEditor extends JFrame implements ActionListener { //view and controller

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String output; // global variable for output from GUI's
	public String filename;
	public File filePath; // global variable for absolute path of file
	public boolean fileState; // true means new file, false means existing file(no use right now)
	public boolean iscancelled;
	public File selectedfile;
	public String selectedfilepath;
	// -------------- GUI fields ---------------
	private JTextArea mainTextArea;
	private JPanel contentPane; 
	private JFrame frame;
	private JButton button_create_scenario;
	private JButton button_existing_scenario;
	private JButton button_save_scenario;
	private JButton button_add_button;
	private JButton button_add_cells;
	private JButton button_clear_cells;
	private JButton button_sound_file;
	private JLabel label_title;
	private JLabel label_selected_scenario;
	private JList list;
	
	
	
	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ScenarioFileEditor() throws IOException {
		editorWindow();
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
		//frame = new JFrame("TreBBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		//JLabel Popup
		label_title = new JLabel("Scenario Editor\r\n");
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_title.setBounds(31, 16, 161, 40);
		label_title.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(label_title);
		
		//JList
				//list = new JList();
				//list.setBounds(10, 67, 374, 264);
				//contentPane.add(list);
		
		//button to create a new scenario
		button_create_scenario = new JButton("Create New Scenario");
		button_create_scenario.setBounds(216, 11, 170, 23);
		contentPane.add(button_create_scenario);
		button_create_scenario.addActionListener(this);
		
		//button to edit an existing scenario
		button_existing_scenario = new JButton("Edit Existing Scenario");
		button_existing_scenario.setBounds(216, 33, 169, 23);
		contentPane.add(button_existing_scenario);
		button_existing_scenario.addActionListener(this);
		
		
		//label that states the selected scenario
		label_selected_scenario = new JLabel("Selected Scenario:");
		label_selected_scenario.setBounds(402, 16, 121, 40);
		contentPane.add(label_selected_scenario);
		
		//button that adds a new field
		button_add_cells = new JButton("Number of cells");
		button_add_cells.setBounds(402, 66, 183, 23);
		contentPane.add(button_add_cells);
		button_add_cells.addActionListener(this);
		
		//Button to edit a field
		button_add_button = new JButton("Number of buttons");
		button_add_button.setBounds(402, 99, 183, 23);
		contentPane.add(button_add_button);
		button_add_button.addActionListener(this);
				
		
		//button that deletes a field
		button_clear_cells = new JButton("Clear all Cells");
		button_clear_cells.setBounds(402, 132, 183, 23);
		contentPane.add(button_clear_cells);
		button_clear_cells.addActionListener(this);
		
		//button that saves the current scenario
		button_save_scenario = new JButton("Save Scenario");
		button_save_scenario.setBounds(402, 167, 183, 23);
		contentPane.add(button_save_scenario);
		button_save_scenario.addActionListener(this);
		
		//Text Area
		mainTextArea = new JTextArea();
		mainTextArea.setEditable(true);
		mainTextArea.setBounds(5, 65, 380, 300);
		contentPane.add(mainTextArea);
		
		//Button for sound file
		button_sound_file = new JButton("Add sound file");
		button_sound_file.setBounds(402, 200, 183, 23);
		contentPane.add(button_sound_file);
		button_sound_file.addActionListener(this);
		
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
	
	

	public boolean isScenarioFile(String file) // this may not be needed
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
	/*public void WriteButton(String inputButton, File f) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		fw.write(System.lineSeparator());
		fw.write(inputButton + "\n");
		fw.close();
		}
*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//button_create_scenario
		//button_edit_scenario
		if (e.getSource().equals(this.button_existing_scenario))
		{
			
			JFileChooser chooser1 = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
			chooser1.setFileFilter(filter);
			int returnVal = chooser1.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
			}
			
			if (returnVal == JFileChooser.ERROR_OPTION)
			{
				return;
			}
			
			if (returnVal == JFileChooser.CANCEL_OPTION)
			{
				return;
			}
			
			this.selectedfile = chooser1.getSelectedFile();
			this.selectedfilepath = chooser1.getSelectedFile().getAbsolutePath();
		}
		
		if (e.getSource().equals(this.button_create_scenario))
		{
			filename = JOptionPane.showInputDialog(this, "Type in file name:");
			this.selectedfile = new File(filename + ".txt");
			this.selectedfilepath = selectedfile.getAbsolutePath();
		}
		
		if (e.getSource().equals(this.button_save_scenario))
		{
			JFileChooser fileSaver = new JFileChooser();
			//JFileChooser chooser1 = new JFileChooser();
			//FileNameExtensionFilter filter = new FileNameExtensionFilter("Factory Scenario Files", "txt");
			//chooser1.setFileFilter(filter);
			int returnVal = fileSaver.showSaveDialog(ScenarioFileEditor.this);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				String writtenStuff = mainTextArea.getText();
				System.out.println(writtenStuff);
				try {
					FileWriter fw = new FileWriter(fileSaver.getSelectedFile()+".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					for(String fgh : mainTextArea.getText().split("\\n"))
					{
						if(fgh.startsWith("Display"))
						{ 
							
							bw.write("/~disp-cell-pins:"+fgh.substring(8));
							bw.newLine();
						}
						
						else if(fgh.startsWith("Reset"))
						{
							bw.write("/~reset-buttons");
							bw.newLine();
						}
						
						else if(fgh.startsWith("Clear Cells"))
						{
							bw.write("/~disp-clearAll");
						}
						
						else if(fgh.startsWith("Name of the sound file"))
						{
							bw.write("/~sound:"+fgh.substring(23));
						}
						else
						{
						bw.write(fgh);
						bw.newLine();
						}
						}
					 bw.close();
			         fw.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		}
		
		if (e.getSource().equals(this.button_add_cells))
		{
			mainTextArea.append("Cell ");
		}
		
		if (e.getSource().equals(this.button_add_button))
		{
			mainTextArea.append("Button ");
		}
		
		if (e.getSource().equals(this.button_clear_cells))
		{
			mainTextArea.append("Clear Cells");
		}
		
		if(e.getSource().equals(this.button_sound_file))
		{
			mainTextArea.append("Name of the sound file: ");
		}
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

