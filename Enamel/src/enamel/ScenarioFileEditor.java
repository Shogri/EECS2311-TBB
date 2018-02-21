package enamel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScenarioFileEditor extends JFrame implements ActionListener, ListSelectionListener { //view and controller

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String output; // global variable for output from GUI's
	public String filename;
	public File filePath; // global variable for absolute path of file
	public boolean fileState; // true means new file, false means existing file(no use right now)
	
	public File selectedfile;
	public String selectedfilepath;
	
	public File tmpfile;
	public String tmpfilepath;
	// -------------- GUI fields ---------------
	private JTextArea mainTextArea;
	private JPanel contentPane; 
	private JFrame frame;
	private JButton button_create_scenario;
	private JButton button_existing_scenario;
	private JButton button_save_scenario;
	private JButton button_edit_field;
	private JButton button_delete_field;
	private JLabel label_title;
	private JLabel label_selected_scenario;
	private JList list;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	String[] addfield_selections = {"Add a field...", "Display", "User Input", "Sound"};
	JComboBox add_field_dropdown;
	
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
		
		list = new JList(this.listModel);
		list.setBounds(10, 67, 374, 358);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.addListSelectionListener(this);
	    list.setVisibleRowCount(5);
		contentPane.add(list);
		
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
		
		//Button to edit a field
		button_edit_field = new JButton("Edit Field");
		button_edit_field.setBounds(402, 99, 183, 23);
		contentPane.add(button_edit_field);
		button_edit_field.addActionListener(this);
		
		//label that states the selected scenario
		label_selected_scenario = new JLabel("No Selected Scenario");
		label_selected_scenario.setHorizontalAlignment(SwingConstants.CENTER);
		label_selected_scenario.setBounds(402, 16, 183, 40);
		contentPane.add(label_selected_scenario);
		
		//button that deletes a field
		button_delete_field = new JButton("Delete Field");
		button_delete_field.setBounds(402, 132, 183, 23);
		contentPane.add(button_delete_field);
		button_delete_field.addActionListener(this);
		
		//button that saves the current scenario
		button_save_scenario = new JButton("Save Scenario");
		button_save_scenario.setBounds(402, 167, 183, 23);
		contentPane.add(button_save_scenario);
		button_save_scenario.addActionListener(this);
		
		//combo box dropdown for adding fields
		add_field_dropdown = new JComboBox(this.addfield_selections);
		add_field_dropdown.setToolTipText("Add a field...");
		add_field_dropdown.setBounds(402, 65, 183, 20);
		contentPane.add(add_field_dropdown);
		add_field_dropdown.addActionListener(this);	
	}
	
	public void launcher() throws IOException {
		JFileChooser chooser1 = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
		chooser1.setFileFilter(filter);
		int returnVal = chooser1.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
		}
		
		if (returnVal == JFileChooser.CANCEL_OPTION)
		{
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
		
		//edit existing scenario
		if (e.getSource().equals(this.button_existing_scenario)) //NOTE: get this to check for non-scenario files
		{
			JFileChooser chooser1 = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
			chooser1.setFileFilter(filter);
			int returnVal = chooser1.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
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
			this.label_selected_scenario.setText("Selected Scenario: " + chooser1.getSelectedFile().getName());
		}
		
		//Create a new Scenario
		if (e.getSource().equals(this.button_create_scenario))
		{
			//create new file
			filename = JOptionPane.showInputDialog(this, "Type in file name:");
			this.selectedfile = new File(filename + ".txt");
			this.selectedfilepath = selectedfile.getAbsolutePath();
			
			//popup, ask for file information
			String 	new_Scenario_config = JOptionPane.showInputDialog(this, " Enter Number of cells, followed by a space, "
					+ "followed by the number of buttons");
			String[] info = new_Scenario_config.split(" ");
			
			//add elements to list
			this.listModel.addElement("Cell:" + info[0]);
			this.listModel.addElement("Button:" + info[1]);
			
			//write into new file appropriately
			try 
			{
				LineEditor.setupCellButton(this.selectedfile, Integer.parseInt(info[0]), Integer.parseInt(info[1]));
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			this.label_selected_scenario.setText("Selected Scenario: " + this.selectedfile.getName());
		}
		
		//Save current scenario
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
						else
						{
						bw.write(fgh);
						bw.newLine();
						}
						}
					 bw.close();
			         fw.close();
				} catch (IOException e1) 
				{
					
					e1.printStackTrace();
				}
			}
		}
		//add a field (dropdown)
		if (e.getSource().equals(this.add_field_dropdown))
		{
			JComboBox cb = (JComboBox)e.getSource();
			String option = (String)cb.getSelectedItem();
			
			if (option.equals("Display"))
			{
				//String disp_cell_config = JOptionPane.showInputDialog(this, " Enter Braille cell number, followed by a space, "
					//	+ "followed by pins that you want punched in");
				JTextField cellnums = new JTextField(5);
			    JTextField letter = new JTextField(1);
			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Cell number: "));
			    myPanel.add(cellnums);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Displayed Letter: "));
			    myPanel.add(letter);

			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Display a letter", JOptionPane.OK_CANCEL_OPTION);
				
				BrailleCell cell = new BrailleCell();
				try {
					String pinrepresentation = cell.getPinRepresentation(letter.getText().charAt(0));
					this.listModel.addElement(option + " cell number " + cellnums.getText() + ", displaying letter " + letter.getText());
					LineEditor.addDispCellPins(selectedfile, Integer.parseInt(cellnums.getText()), Integer.parseInt(pinrepresentation));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		}
		//append("display", disp_cell_config);
		//this.list.add
		//edit selected field
		if (e.getSource().equals(this.button_edit_field))
		{
			
		}
		
		//delete selected field
		if (e.getSource().equals(this.button_delete_field))
		{
			if (!this.list.isSelectionEmpty())
			{
				int selected = this.list.getSelectedIndex();
				System.out.println(selected);
				this.listModel.remove(selected);
			}
		}
}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
	}
}
