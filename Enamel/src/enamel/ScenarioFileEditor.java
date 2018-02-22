package enamel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class ScenarioFileEditor extends JFrame implements ActionListener, ListSelectionListener { // view
																									// and
																									// controller

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String output; // global variable for output from GUI's
	public String filename;
	public File filePath; // global variable for absolute path of file
	public boolean fileState; // true means new file, false means existing
								// file(no use right now)

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
	String[] addfield_selections = { "Add a field...", "Display", "User Input", "Sound", "True/False Question" };
	JComboBox add_field_dropdown;
	JScrollPane scroll;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ScenarioFileEditor() throws IOException {

		editorWindow();
		// int y = JOptionPane.showConfirmDialog(null, "New File?");
		// if (y == JOptionPane.YES_OPTION) {
		// filename = JOptionPane.showInputDialog(this, "Type in file name:");
		// filePath = new File(filename + ".txt");
		// fileState = true;
		// this.editorWindow();
		// } else if (y == JOptionPane.NO_OPTION) {
		//
		// JOptionPane.showMessageDialog(null, "Select your existing file");
		// this.launcher();
		// while (!this.isScenarioFile(filename) && this.iscancelled == false){
		// this.launcher();
		//
		// }
		// fileState = false;
		// } else {
		// System.exit(0); //terminate
		// }
	}

	public void editorWindow() {
		// frame = new JFrame("TreBBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JLabel Popup
		label_title = new JLabel("Scenario Editor\r\n");
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_title.setBounds(31, 16, 161, 40);
		label_title.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(label_title);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 374, 264);
		contentPane.add(scrollPane);

		list = new JList(this.listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);

		// button to create a new scenario
		button_create_scenario = new JButton("Create New Scenario");
		button_create_scenario.setBounds(216, 11, 170, 23);
		contentPane.add(button_create_scenario);
		button_create_scenario.addActionListener(this);

		// button to edit an existing scenario
		button_existing_scenario = new JButton("Edit Existing Scenario");
		button_existing_scenario.setBounds(216, 33, 169, 23);
		contentPane.add(button_existing_scenario);
		button_existing_scenario.addActionListener(this);

		// Button to edit a field
		button_edit_field = new JButton("Edit Field");
		button_edit_field.setBounds(402, 99, 183, 23);
		contentPane.add(button_edit_field);
		button_edit_field.addActionListener(this);

		// label that states the selected scenario
		label_selected_scenario = new JLabel("Selected Scenario:");
		label_selected_scenario.setBounds(402, 16, 121, 40);
		contentPane.add(label_selected_scenario);

		// button that deletes a field
		button_delete_field = new JButton("Delete Field");
		button_delete_field.setBounds(402, 132, 183, 23);
		contentPane.add(button_delete_field);
		button_delete_field.addActionListener(this);

		// button that saves the current scenario
		button_save_scenario = new JButton("Save Scenario");
		button_save_scenario.setBounds(402, 167, 183, 23);
		contentPane.add(button_save_scenario);
		button_save_scenario.addActionListener(this);

		// combo box dropdown for adding fields
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

		if (returnVal == JFileChooser.CANCEL_OPTION) {
			return;
		}

		chooser1.removeAll();
		//
		// if (!isScenarioFile(chooser1.getSelectedFile().getAbsolutePath()))
		// {
		// System.out.println("failed");
		// JOptionPane.showMessageDialog(null, "Error: Please select a Scenario
		// file");
		// this.launcher();
		// }

		try {
			filename = chooser1.getSelectedFile().getAbsolutePath();
			filePath = new File(filename);
		} catch (Exception e) {
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

	public void WriteText(String input, File f) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		fw.write(System.lineSeparator());
		fw.write(input);
		fw.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// edit existing scenario
		if (e.getSource().equals(this.button_existing_scenario)) // NOTE: get
																	// this to
																	// check for
																	// non-scenario
																	// files
		{
			JFileChooser chooser1 = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
			chooser1.setFileFilter(filter);
			int returnVal = chooser1.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
			}

			if (returnVal == JFileChooser.ERROR_OPTION) {
				return;
			}

			if (returnVal == JFileChooser.CANCEL_OPTION) {
				return;
			}
			this.selectedfile = chooser1.getSelectedFile();
			this.selectedfilepath = chooser1.getSelectedFile().getAbsolutePath();
			this.filename = chooser1.getSelectedFile().getAbsolutePath();
			this.label_selected_scenario.setText(chooser1.getSelectedFile().getName());
			try {
				BufferedReader x = new BufferedReader(new FileReader(chooser1.getSelectedFile()));
				while (x.readLine() != null) {
					this.listModel.addElement(x.readLine());
				}
				x.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Create a new Scenario
		if (e.getSource().equals(this.button_create_scenario)) {
			// create new file
			filename = JOptionPane.showInputDialog(this, "Type in file name:");
			this.selectedfile = new File(filename + ".txt");
			this.selectedfilepath = selectedfile.getAbsolutePath();

			// popup, ask for file information
			String new_Scenario_config = JOptionPane.showInputDialog(this,
					" Enter Number of cells, followed by a space, " + "followed by the number of buttons");
			if (new_Scenario_config == null) {
				return;
			}
			String[] info = new_Scenario_config.split(" ");

			// add elements to list
			this.listModel.addElement("Cell:" + info[0]);
			this.listModel.addElement("Button:" + info[1]);

			// write into new file appropriately
			try {
				LineEditor.setupCellButton(this.selectedfile, Integer.parseInt(info[0]), Integer.parseInt(info[1]));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.label_selected_scenario.setText(this.selectedfile.getName());
		}

		// Save current scenario
		if (e.getSource().equals(this.button_save_scenario)) {
			if (this.filename == null) {
				JOptionPane.showMessageDialog(null, "Error: Please select a file");
			} else {
				//JFileChooser fileSaver = new JFileChooser();
				// JFileChooser chooser1 = new JFileChooser();
				// FileNameExtensionFilter filter = new
				// FileNameExtensionFilter("Factory Scenario Files", "txt");
				// chooser1.setFileFilter(filter);
				//int returnVal = fileSaver.showSaveDialog(ScenarioFileEditor.this);
				//if (returnVal == JFileChooser.APPROVE_OPTION) {
					//String writtenStuff = this.listModel.getElementAt(1);
					//System.out.println(writtenStuff);
					try {
						FileWriter fw = new FileWriter((this.selectedfile));
						BufferedWriter bw = new BufferedWriter(fw);
						for (int i = 0; i < this.listModel.size(); i++) {
							String fgh = this.listModel.getElementAt(i);
							
							if (fgh.startsWith("Cell:")){
								bw.write(fgh);
								bw.newLine();
							}
							if(fgh.startsWith("Button:")){
								bw.write(fgh);
								bw.newLine();
								bw.newLine();
							}
							
							if (fgh.startsWith("Display")) {
								bw.write("/~disp-cell-pins:" + fgh.substring(8));
								bw.newLine();
							}
							if (fgh.startsWith("Reset")) {
								bw.write("/~reset-buttons");
								bw.newLine();
							}
							if(fgh.startsWith("Question:")){
								bw.write("/~pause:1");
								bw.newLine();
								bw.write("/~disp-cell-clear:0");
								bw.newLine();
								bw.write("/~disp-string:" + fgh.charAt(fgh.length() - 2));
								bw.newLine();
								bw.write("Is the Braille Cell displaying the letter " + fgh.charAt(fgh.length() - 2) + "?");
								bw.newLine();
								bw.write("Press the button 1 for true, the button 2 for false.");
								bw.newLine();
								bw.write("/~skip-button:0 ONEE");
								bw.newLine();
								bw.write("/~skip-button:1 TWOO");
								bw.newLine();
								bw.write("/~user-input");
								bw.newLine();
								bw.write("/~ONEE");
								bw.newLine();
								bw.write("/~sound:correct.wav");
								bw.newLine();
								bw.write("That's correct! The letter displayed on the cell was " + fgh.charAt(fgh.length() - 2) + ".");
								bw.newLine();
								bw.write("/~TWOO");
								bw.newLine();
								bw.write("/~sound:wrong.wav");
								bw.newLine();
								bw.write("Sorry! That's incorrect. The cell is displaying the character " + fgh.charAt(fgh.length() - 2) + ".");
								bw.newLine();
								bw.write("/~skip:NEXTT");
								
								System.out.println("works");
							}
							System.out.println(fgh);
						}
						bw.close();
						fw.close();
					}
					 catch (IOException e1) {

						e1.printStackTrace();
					}
				}
			}
		
		// add a field (dropdown)
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("Display")) {
				if (this.filename == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					String disp_cell_config = JOptionPane.showInputDialog(this,
							" Enter Braille cell number, followed by a space, "
									+ "followed by pins that you want punched in");
					String[] info = disp_cell_config.split(" ");
					this.listModel.addElement(option + " cell number " + info[0] + ", With configuration " + info[1]);

					try {
						LineEditor.addDispCellPins(this.selectedfile, Integer.parseInt(info[0]),
								Integer.parseInt(info[1]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("True/False Question")) {
				if (this.filename == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					String[] choices = { "A", "B", "C", "D", "E", "F" ,"G", "H", "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
					String input = (String) JOptionPane.showInputDialog(null, "Choose Letter To Display",
					        "True False Question", JOptionPane.QUESTION_MESSAGE, null, // Use                                              // icon
					        choices, // Array of choices
					        choices[0]); // Initial choice
					this.listModel.addElement("Question: Is the letter displayed: " + input + "?");
				}
			}
		}
		// append("display", disp_cell_config);
		// this.list.add
		// edit selected field
		if (e.getSource().equals(this.button_edit_field)) {

		}

		// delete selected field
		if (e.getSource().equals(this.button_delete_field)) {
			if (!this.list.isSelectionEmpty()) {
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
