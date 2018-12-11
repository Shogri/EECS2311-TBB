package enamel;

import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JList;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import javax.swing.JToolBar;

public class ScenarioFileEditor extends JFrame implements ActionListener, ListSelectionListener { // view
	private static Component parent;
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
	public File finalfile;
	public String finalfilepath;;
	// -------------- GUI fields ---------------
	private JTextArea mainTextArea;
	private JPanel contentPane;
	private JFrame frame;
	private JButton button_create_scenario;
	private JButton button_existing_scenario;
	private JButton button_save_scenario;
	private JButton button_edit_field;
	private JButton button_delete_field;
	private JButton button_play_file;
	private JLabel label_title;
	private JLabel label_selected_scenario;
	private JList list;
	private JList list_1;
	private boolean isSaved = false;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	String[] addfield_selections = { "Add a field...", "A) Display", "B) Add Text", "C) Ask Question",
			"D) Specify Correct Answer Key", "E) Begin Correct Answer Explanation", "F) End Correct Answer Explanation",
			"G) Specify Wrong Answer Key", "H) Begin Wrong Answer Explanation", "I) End Wrong Answer Explanation",
			"J) Display A string","K) Import Sound File", "L) Clear cell", "M) Add Pause"};
	JComboBox add_field_dropdown;
	JScrollPane scroll;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ScenarioFileEditor() throws IOException {
		setResizable(false);

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
		// this.getRootPane().setDefaultButton(KeyEvent.VK_ENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(true);
		setSize(800, 500);
		// JLabel Popup
		label_title = new JLabel("Scenario Editor\r\n");
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_title.setBounds(31, 16, 161, 40);
		label_title.setAlignmentX(CENTER_ALIGNMENT);
		label_title.setFocusable(false);
		contentPane.add(label_title);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 500, 264);
		scrollPane.setFocusable(false);
		contentPane.add(scrollPane);

		list = new JList(this.listModel);
		list.setBounds(10, 67, 500, 264);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					ke.consume();
					list.setSelectedIndex(0);
				}
			}
		});
		contentPane.add(list);

		// scrollPane = new JScrollPane();
		// scrollPane.setBounds(10, 67, 374, 264);
		// contentPane.add(scrollPane);

		list_1 = new JList(this.listModel);
		scrollPane.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.addListSelectionListener(this);
		list_1.setFocusable(false);

		// button to create a new scenario
		button_create_scenario = new JButton("Create New Scenario");
		button_create_scenario.getAccessibleContext().setAccessibleName("New Scenario"); // for
																							// screen
																							// reader
		button_create_scenario.getAccessibleContext()
				.setAccessibleDescription("Press enter to select create a new scenario"); // for
																							// screen
																							// reader
		button_create_scenario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					newScenario();
				}
			}
		});
		button_create_scenario.setBounds(216, 11, 170, 23);
		contentPane.add(button_create_scenario);
		button_create_scenario.addActionListener(this);

		// button to edit an existing scenario
		button_existing_scenario = new JButton("Edit Existing Scenario");
		button_existing_scenario.getAccessibleContext().setAccessibleName("Edit Existing Scenario"); // for
																										// screen
																										// reader
		button_existing_scenario.getAccessibleContext()
				.setAccessibleDescription("Press enter to select edit an already existing scenario");
		button_existing_scenario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					editScenario();
				}
			}
		});
		button_existing_scenario.setBounds(216, 33, 170, 23);
		contentPane.add(button_existing_scenario);
		button_existing_scenario.addActionListener(this);

		// Button to edit a field
		button_edit_field = new JButton("Edit Field");
		button_edit_field.getAccessibleContext().setAccessibleName("Edit a field");
		button_edit_field.getAccessibleContext().setAccessibleDescription("Press enter to edit a field");
		button_edit_field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				int c = arg0.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					// editScenario();
				}
			}
		});
		button_edit_field.setBounds(550, 99, 214, 23);
		contentPane.add(button_edit_field);
		button_edit_field.addActionListener(this);

		// label that states the selected scenario

		label_selected_scenario = new JLabel("Selected Scenario:");
		label_selected_scenario.setBounds(402, 16, 121, 40);
		label_selected_scenario.setFocusable(false);
		label_selected_scenario = new JLabel("No Selected Scenario");
		label_selected_scenario.setHorizontalAlignment(SwingConstants.CENTER);
		label_selected_scenario.setBounds(402, 16, 362, 40);

		contentPane.add(label_selected_scenario);

		// button that deletes a field
		button_delete_field = new JButton("Delete Field");
		button_delete_field.getAccessibleContext().setAccessibleName("Delete Current Field");
		button_delete_field.getAccessibleContext().setAccessibleDescription("Press enter to delete the current field");
		button_delete_field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					deleteField();
				}
			}
		});
		button_delete_field.setBounds(550, 132, 214, 23);
		contentPane.add(button_delete_field);
		button_delete_field.addActionListener(this);

		// button that saves the current scenario
		button_save_scenario = new JButton("Save Scenario");
		button_save_scenario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					saveScenario();
				}
			}
		});
		button_save_scenario.setBounds(550, 167, 214, 23);
		contentPane.add(button_save_scenario);
		button_save_scenario.getAccessibleContext().setAccessibleName("Save Current Scenario");
		button_save_scenario.getAccessibleContext()
				.setAccessibleDescription("Press enter to save the current Scenario");
		button_save_scenario.addActionListener(this);

		// combo box dropdown for adding fields

		add_field_dropdown = new JComboBox(this.addfield_selections);
		// add_field_dropdown.addKeyListener(new KeyAdapter() {
		// public void keyPressed(KeyEvent e) {
		// int c = e.getKeyCode();
		// if (c == KeyEvent.VK_ENTER) {
		// add_field_dropdown.showPopup();
		// add_field_dropdown.setSelectedIndex(1);
		// }
		// }
		// });
		//
		add_field_dropdown.setFocusable(true);
		// add_field_dropdown.putClientProperty("JComboBox.isTableCellEditor",
		// Boolean.TRUE);
		add_field_dropdown.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				add_field_dropdown.showPopup();
				// add_field_dropdown.addKeyListener(new KeyAdapter() {
				// public void keyPressed(KeyEvent e) {
				// int c = e.getKeyCode();
				// int i = 0;
				// if (c == KeyEvent.VK_DOWN) {
				// i++;
				// add_field_dropdown.setSelectedIndex(i);
				// }
				// }
				// });
			}
		});

		add_field_dropdown.setToolTipText("Add a field...");
		add_field_dropdown.setBounds(550, 65, 214, 20);
		contentPane.add(add_field_dropdown);

		// play current file button
		button_play_file = new JButton("Play Current File ");
		button_play_file.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_ENTER) {
					playFile();
				}
			}
		});
		button_play_file.addActionListener(this);
		button_play_file.getAccessibleContext().setAccessibleName("Play the current file");
		button_play_file.getAccessibleContext().setAccessibleName("Press enter to play the current file");
		button_play_file.setBounds(550, 201, 214, 23);
		contentPane.add(button_play_file);

		add_field_dropdown.addActionListener(this);

		Set forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS); // change
																								// focus
																								// keys
																								// to
																								// up
																								// and
																								// down
		Set newForwardKeys = new HashSet(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);

		Set backwardKeys = getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		Set newBackwardKeys = new HashSet(backwardKeys);
		newBackwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackwardKeys);

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

	

	public void newScenario() {
		
		filename = JOptionPane.showInputDialog(this, "Type in file name:");
		System.out.println("Test 1");
		if (this.filename == null) 
			{
				JOptionPane.showMessageDialog(null, "Error: Please type in a file name");
				return;
			}
		
		this.selectedfile = new File(filename + ".txt");
		this.selectedfilepath = selectedfile.getAbsolutePath();

		// popup, ask for file information
		String new_Scenario_config = JOptionPane.showInputDialog(this,
				" Enter Number of cells, followed by a space, " + "followed by the number of buttons");

		// popup, ask for file information

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
		this.label_selected_scenario.setText("Selected Scenario: " + this.selectedfile.getName());
		
	}
	
	/**
	 * Method to Edit an existing Scenario
	 */
	public void editScenario() {
		this.listModel.clear();
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
		
		System.out.println("Test1: passed");
		
		this.label_selected_scenario.setText(chooser1.getSelectedFile().getName());
		
		System.out.println("test 2: Passed");
		
		this.label_selected_scenario.setText("Selected Scenario: " + chooser1.getSelectedFile().getName());
		System.out.println("test 3:Passed");
		try {
			System.out.println("Test 4: Passed");
			LineEditor.parseScenario(this.selectedfile, this.listModel);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void saveScenario() {
		if (this.selectedfile == null) {
			JOptionPane.showMessageDialog(null, "Error: Please select a file");
		} else {
			JFileChooser fileSaver = new JFileChooser();
			// JFileChooser chooser1 = new JFileChooser();
			// FileNameExtensionFilter filter = new
			// FileNameExtensionFilter("Factory Scenario Files", "txt");
			// chooser1.setFileFilter(filter);
			int returnVal = fileSaver.showSaveDialog(ScenarioFileEditor.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String writtenStuff = mainTextArea.getText();
				System.out.println(writtenStuff);
				try {
					FileWriter fw = new FileWriter(fileSaver.getSelectedFile() + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					for (String fgh : mainTextArea.getText().split("\\n")) {
						if (fgh.startsWith("Display")) {

							bw.write("/~disp-cell-pins:" + fgh.substring(8));
							bw.newLine();
						}

						else if (fgh.startsWith("Reset")) {
							bw.write("/~reset-buttons");
							bw.newLine();
						} else {
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

	}

	public void deleteField() {
		if (!this.list_1.isSelectionEmpty()) {
			int selected = this.list_1.getSelectedIndex();
			System.out.println(selected);
			this.listModel.remove(selected);
		}

	}
	public void deleteField(int field)
	{
		if (!this.list_1.isSelectionEmpty()) 
		{
			int selected = this.list_1.getSelectedIndex();
			System.out.println(selected);
			this.listModel.remove(selected+field);
		}
			
			
			
			
	}
	public void playFile() {
		if (this.selectedfile == null) {
			JOptionPane.showMessageDialog(null, "Error: Please select a file");
			return;
		}
		Thread starterCodeThread = new Thread("Starter Code Thread") {
			public void run() {
				ScenarioParser s = new ScenarioParser(true);
				s.setScenarioFile(selectedfilepath);
			}
		};
		starterCodeThread.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ----------------edit existing scenario--------------------

		if (e.getSource().equals(this.button_existing_scenario)) // NOTE: check
																	
		{
			this.editScenario();
		}

		// --------------Create a new Scenario----------------

		if (e.getSource().equals(this.button_create_scenario)) {
			// create new file
			this.newScenario();
			
		}

		// ---------Save current scenario---------------

		if (e.getSource().equals(this.button_save_scenario)) {
 			if (this.selectedfile == null) 
 			{
 				JOptionPane.showMessageDialog(null, "Error: Please select a file");
 			} else 
 			{
 				try 
 				{
 					// TODO
 					this.saveFile();
 					this.deleteTmpFile();
 				} catch (Exception e1) 
 				{
 					e1.printStackTrace();
 				}
 				this.isSaved = true;
 			}
 			
}
	
	/*
	 * To add a field (dropdown)
	 */
	
	
		


		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();
			/**
			 * To display a character
			 */
			if (option.equals("A) Display")) {

				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				}
				JTextField cellnum = new JTextField(1);
				JTextField config = new JTextField(8);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Cell#"));
				myPanel.add(cellnum);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				myPanel.add(new JLabel("Letter"));
				myPanel.add(config);

				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Please Enter Cell# and Letter to be Displayed", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					this.listModel.addElement(option + " cell number " + Integer.parseInt(cellnum.getText())
							+ ", With configuration " + Integer.parseInt(config.getText()));
				//	this.list_1.add
					try {
						LineEditor.addDispCellPins(this.selectedfile, Integer.parseInt(cellnum.getText()),
								Integer.parseInt(config.getText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			this.isSaved = false;
		}
	//}
		/*if (e.getSource().equals(this.button_edit_field)) {
			//System.out.println(this.listModel.get(0));
			int selected = list_1.getSelectedIndex();
			String starting = this.listModel.getElementAt(selected);
			if(starting.startsWith("A)"))
			{
				JTextField cellnum = new JTextField(1);
				JTextField config = new JTextField(8);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Cell#"));
				myPanel.add(cellnum);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				myPanel.add(new JLabel("Letter"));
				myPanel.add(config);

				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Please Enter Cell# and Letter to be Displayed", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					this.listModel.add(selected+1, "A)" + " cell number " + Integer.parseInt(cellnum.getText())
							+ ", With configuration " + Integer.parseInt(config.getText()));
					try {
						LineEditor.addDispCellPins(this.selectedfile, Integer.parseInt(cellnum.getText()),
								Integer.parseInt(config.getText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			//String wrong_key = JOptionPane.showInputDialog(this,
			//		"What key does the user need to press for the wrong answer?");
			//System.out.println(selected);
			//this.listModel.add(selected+1, "yeet");
			deleteField();
		}
		
	*/
	
	if (e.getSource().equals(this.button_edit_field)) {
		//System.out.println(this.listModel.get(0));
		int selected = list_1.getSelectedIndex();
		String starting = this.listModel.getElementAt(selected);
		String afterStarting = this.listModel.getElementAt(selected+1);
		System.out.println("Selected " +selected);
		if(starting.startsWith("A)"))
		{
			JTextField cellnum = new JTextField(1);
			JTextField config = new JTextField(8);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Cell#"));
			myPanel.add(cellnum);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Letter"));
			myPanel.add(config);

			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Please Enter Cell# and Letter to be Displayed", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.listModel.add(selected, "A)" + " cell number " + Integer.parseInt(cellnum.getText())
						+ ", With configuration " + Integer.parseInt(config.getText()));
				try {
					LineEditor.addDispCellPins(selected,this.selectedfile, Integer.parseInt(cellnum.getText()),
							Integer.parseInt(config.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			deleteField();
		}
		
		if(starting.startsWith("Say:"))
		{
			if(afterStarting.startsWith("First"))
			{
				String disp_cell_config = JOptionPane.showInputDialog(this, "Enter Question");
				if (disp_cell_config == null) {
					return;
				} else {
					this.listModel.add(selected,"Say: "+ disp_cell_config);
					try {
						LineEditor.addString(selected, this.selectedfile, disp_cell_config);
					}

					catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		}
			else
			{
				String addText = JOptionPane.showInputDialog(this,
						"Please enter the text that you would like to be read out");
				if (addText.isEmpty()) {
					return;
				} 
				
				else {
					
					this.listModel.add(selected, "Say: " +addText);
					try {
						LineEditor.addString(selected, this.selectedfile, addText);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
				
				
		}
		
		/*
		if(starting.startsWith("Say:"))
		{
			String addText = JOptionPane.showInputDialog(this,
					"Please enter the text that you would like to be read out");
			if (addText.isEmpty()) {
				return;
			} 
			
			else {
				
				this.listModel.add(selected, "B) " +addText);
				try {
					LineEditor.addString(selected, this.selectedfile, addText);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				deleteField();
		}
		}
		*/
			/*
			String cellsToActivate = JOptionPane.showInputDialog(this,
					"Enter the keys you'd like to activate" + ", separated by a comma");
			if (cellsToActivate == null) {
				return;
			} else {
				String[] AcKeys = cellsToActivate.split(",");
				try {
					this.listModel.add(selected+1,"First activated key: "+AcKeys[0]);
					this.listModel.add(selected+2,"Second activated key: "+AcKeys[1]);
					this.listModel.add(selected+3,"-----The scenario file now requires user input-----");
					LineEditor.activateKeys(selected+1,this.selectedfile, Integer.valueOf(AcKeys[0]));
					LineEditor.activateKeys(selected+2,this.selectedfile, Integer.valueOf(AcKeys[1]));
					//LineEditor.addUserInput(this.selectedfile);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			*/
			deleteField();
			
		}
		
		if(starting.startsWith("D)"))
		{
			String wrong_key = JOptionPane.showInputDialog(this,
					"What key does the user need to press for the correct answer?");
			if (wrong_key == null) {
				return;
			} else {
				this.listModel.add(selected,"D) Correct Answer: " + wrong_key);
				try {
					LineEditor.setKey(selected, this.selectedfile, Integer.valueOf(wrong_key));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
			deleteField();
		}
		
		if(starting.startsWith("E)"))
		{
			this.listModel.add(selected, "Correct answer explanation starts here");
			deleteField();
		}
		
		if(starting.startsWith("F)"))
		{
			this.listModel.add(selected,"F) Correct answer explanation ends here");
			try {
				LineEditor.addSkip(this.selectedfile, "NEXTT");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			deleteField();
		}
		
		if(starting.startsWith("G)"))
		{
			String wrong_key = JOptionPane.showInputDialog(this,
					"What key does the user need to press for the wrong answer?");
			if (wrong_key == null) {
				return;
			} else {
				this.listModel.add(selected,"G) Wrong Answer: " + wrong_key);
				try {
					LineEditor.setKey(selected, this.selectedfile, Integer.valueOf(wrong_key));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		
		}
			deleteField();
		}
		
		if(starting.startsWith("H)"))
		{
			this.listModel.add(selected,"Wrong answer explanation starts here");
			deleteField();
		}
		
		if(starting.startsWith("I)"))
		{
			this.listModel.add(selected,"I) Wrong answer explanation ends here");
			this.listModel.add(selected+1," ");
			this.listModel.add(selected+2," ");
			try {
				LineEditor.addSkip(this.selectedfile, "NEXTT");
				LineEditor.nextQuestion(this.selectedfile);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			deleteField();
		}
			
		if(starting.startsWith("J)"))
		{
			JTextField stringTBdisplay = new JTextField(8);
			JTextField config = new JTextField(8);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("String to be displayed"));
			myPanel.add(stringTBdisplay);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Please Enter The String to be displayed", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.listModel.add(selected, "J)"+(stringTBdisplay.getText()));
				try {
					LineEditor.addDispString(selected, this.selectedfile, stringTBdisplay.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				deleteField();
		}
	}
		if(starting.startsWith("K)"))
		{
			JFileChooser soundChooser = new JFileChooser();
			FileNameExtensionFilter soundFilter = new FileNameExtensionFilter("Sound file", "wav");
			soundChooser.setFileFilter(soundFilter);
			int returnval = soundChooser.showOpenDialog(parent);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				String soundName = soundChooser.getSelectedFile().getName();
				this.listModel.add(selected, "K)Playing Sound: " + soundName);
				try {
					LineEditor.importSound(selected,this.selectedfile, soundName);
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
		}
			deleteField();
		}
		if(starting.startsWith("L)"))
		{
			JTextField cellTbCleared = new JTextField(3);
			//JTextField config = new JTextField(8);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Cell to be cleared"));
			myPanel.add(cellTbCleared);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Please Enter The Cell to be Cleared", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.listModel.add(selected,"L)" + " " + (cellTbCleared.getText()));
				try {
					LineEditor.addDispClearCell(selected,this.selectedfile, Integer.parseInt(cellTbCleared.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
			deleteField();
		}
		
		if(starting.startsWith("M)"))
		{
			JTextField pauseLength = new JTextField(3);
			//JTextField config = new JTextField(8);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("How long should the pause be?"));
			myPanel.add(pauseLength);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"How long do you want the pause to be?", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.listModel.add(selected, "M) Add Pause" + " " + (pauseLength.getText()));
				try {
					LineEditor.addPause(selected,this.selectedfile, Integer.parseInt(pauseLength.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
			deleteField();
		}
	}

	
		
		
		/*
		 * Ask a Question
		 */
	//public void askQuestion(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("C) Ask Question")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					String disp_cell_config = JOptionPane.showInputDialog(this, "Enter Question");
					if (disp_cell_config == null) {
						return;
					} else {
						this.listModel.addElement("Say: "+disp_cell_config);
						try {
							LineEditor.addString(this.selectedfile, disp_cell_config);
						}

						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					String cellsToActivate = JOptionPane.showInputDialog(this,
							"Enter the keys you'd like to activate" + ", separated by a comma");
					if (cellsToActivate == null) {
						return;
					} else {
						String[] AcKeys = cellsToActivate.split(",");
						try {
							this.listModel.addElement("First activated key: "+AcKeys[0]);
							this.listModel.addElement("Second activated key: "+AcKeys[1]);
							this.listModel.addElement("-----The scenario file now requires user input-----");
							LineEditor.activateKeys(this.selectedfile, Integer.valueOf(AcKeys[0]));
							LineEditor.activateKeys(this.selectedfile, Integer.valueOf(AcKeys[1]));
							LineEditor.addUserInput(this.selectedfile);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			this.isSaved = false;
		}
	//}
		

		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("G) Specify Wrong Answer Key")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					String wrong_key = JOptionPane.showInputDialog(this,
							"What key does the user need to press for the wrong answer?");
					if (wrong_key == null) {
						return;
					} else {
						this.listModel.addElement("G) Wrong Answer: " + wrong_key);
						try {
							LineEditor.setKey(this.selectedfile, Integer.valueOf(wrong_key));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			this.isSaved = false;
		}
		//}
		/*
		 * To specify the correct answer key
		 */
		//public void correctAnswer(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("D) Specify Correct Answer Key")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					String wrong_key = JOptionPane.showInputDialog(this,
							"What key does the user need to press for the correct answer?");
					if (wrong_key == null) {
						return;
					} else {
						this.listModel.addElement("D) Correct Answer: " + wrong_key);
						try {
							LineEditor.setKey(this.selectedfile, Integer.valueOf(wrong_key));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			this.isSaved = false;
		}
		//}
		/*
		 * To import a sound file
		 */
		//public void importSound(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("K) Import Sound File")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					JFileChooser soundChooser = new JFileChooser();
					FileNameExtensionFilter soundFilter = new FileNameExtensionFilter("Sound file", "wav");
					soundChooser.setFileFilter(soundFilter);
					int returnval = soundChooser.showOpenDialog(parent);
					if (returnval == JFileChooser.APPROVE_OPTION) {
						String soundName = soundChooser.getSelectedFile().getName();
						this.listModel.addElement("K) Playing Sound: " + soundName);
						try {
							LineEditor.importSound(this.selectedfile, soundName);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			this.isSaved = false;
		}
		//}
		/**
		 * Adds text to the sound file
		 * @param e
		 */
		//public void addText(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();
			
			if (option.equals("B) Add Text")) {
				if (this.selectedfile == null) {
					
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} 
				else {
					String addText = JOptionPane.showInputDialog(this,
							"Please enter the text that you would like to be read out");
					if (addText.isEmpty()) {
						return;
					} 
					
					else {
						
						this.listModel.addElement("Say: " +addText);
						try {
							LineEditor.addString(this.selectedfile, addText);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			this.isSaved = false;
		}
		
		//}
		/**
		 * For Beginning the Correct Answer Explanation
		 */
	//	public void beginCorrectExp(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("E) Begin Correct Answer Explanation")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					
					try {
						LineEditor.addBlankLine(this.selectedfile);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					this.listModel.addElement("Correct answer explanation starts here");

				}
			}
			this.isSaved = false;
		}
		//}
		/*
		 * For Ending the Correct Answer Explanation
		 */
		//public void endCorrectExp(ActionEvent e) {
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("F) End Correct Answer Explanation")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					this.listModel.addElement("F) Correct answer explanation ends here");
					try {
						LineEditor.addSkip(this.selectedfile, "NEXTT");
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
			this.isSaved = false;
		}
		/*
		 * For Beginning the Wrong Answer Explanation
		 */
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("H) Begin Wrong Answer Explanation")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					
					try {
						LineEditor.addBlankLine(this.selectedfile);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					this.listModel.addElement("Wrong answer explanation starts here");
				}
			}
			this.isSaved = false;
		}
		/*
		 * For ending the wrong answer explanation
		 */
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("I) End Wrong Answer Explanation")) {
				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				} else {
					this.listModel.addElement("I) Wrong answer explanation ends here");
					this.listModel.addElement(" ");
					this.listModel.addElement(" ");
					try {
						LineEditor.addSkip(this.selectedfile, "NEXTT");
						LineEditor.nextQuestion(this.selectedfile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
			this.isSaved = false;
		}
		
		/*
		 * To Display a String
		 */
		if (e.getSource().equals(this.add_field_dropdown)) {
			JComboBox cb = (JComboBox) e.getSource();
			String option = (String) cb.getSelectedItem();

			if (option.equals("J) Display A string")) {

				if (this.selectedfile == null) {
					JOptionPane.showMessageDialog(null, "Error: Please select a file");
					return;
				}
				JTextField stringTBdisplay = new JTextField(8);
				JTextField config = new JTextField(8);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("String to be displayed"));
				myPanel.add(stringTBdisplay);
				
				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Please Enter The String to be displayed", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					this.listModel.addElement(option + " " + (stringTBdisplay.getText()));
					try {
						LineEditor.addDispString(this.selectedfile, stringTBdisplay.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			this.isSaved = false;
		}
		/*
		 * To clear a certain cell
		 */
			if (e.getSource().equals(this.add_field_dropdown)) {
				JComboBox cb = (JComboBox) e.getSource();
				String option = (String) cb.getSelectedItem();

				if (option.equals("L) Clear cell")) {

					if (this.selectedfile == null) {
						JOptionPane.showMessageDialog(null, "Error: Please select a file");
						return;
					}
					JTextField cellTbCleared = new JTextField(3);
					//JTextField config = new JTextField(8);
					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("Cell to be cleared"));
					myPanel.add(cellTbCleared);
					
					int result = JOptionPane.showConfirmDialog(null, myPanel,
							"Please Enter The Cell to be Cleared", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						this.listModel.addElement(option + " " + (cellTbCleared.getText()));
						try {
							LineEditor.addDispClearCell(this.selectedfile, Integer.parseInt(cellTbCleared.getText()));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				this.isSaved = false;
			}
			
			/*
			 * To Add a Pause
			 */
			if (e.getSource().equals(this.add_field_dropdown)) {
				JComboBox cb = (JComboBox) e.getSource();
				String option = (String) cb.getSelectedItem();

				if (option.equals("M) Add Pause")) {

					if (this.selectedfile == null) {
						JOptionPane.showMessageDialog(null, "Error: Please select a file");
						return;
					}
					JTextField pauseLength = new JTextField(3);
					//JTextField config = new JTextField(8);
					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("How long should the pause be?"));
					myPanel.add(pauseLength);
					
					int result = JOptionPane.showConfirmDialog(null, myPanel,
							"How long do you want the pause to be?", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						this.listModel.addElement(option + " " + (pauseLength.getText()));
						try {
							LineEditor.addPause(this.selectedfile, Integer.parseInt(pauseLength.getText()));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				this.isSaved = false;
			}
	
			/**
			 * Edit field
			 */


		// append("display", disp_cell_config);
		// this.list.add
		// edit selected field
		/*if (e.getSource().equals(this.button_edit_field)) {

		}
*/
		// delete selected field
		if (e.getSource().equals(this.button_delete_field)) {
			this.deleteField();
			this.isSaved = false;
		}
		// play file
		if (e.getSource().equals(this.button_play_file)) {
			this.playFile();
			this.isSaved = false;
		}
	}
	
	
	
	private void arsu()
	{
		//int i =3;
		System.out.println(this.list_1.getSelectedValue());
	}

 	private boolean savepopup() //true = exit without saving. false = don't do anything
 	{
 		
 		if (!this.isSaved)
 		{
 			int dialogResult;
 			dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you would like to exit without saving?",
 					"Warning", JOptionPane.YES_NO_CANCEL_OPTION);
 			if (dialogResult == JOptionPane.YES_OPTION)
 			{
 				return true;
 			}
 		}
 		return false;
 	}
 	
 	private void saveFile() throws IOException
  	{
  		if (!this.isSaved)
  		{
 			//this.selectedfile.renameTo(new File(this.selectedfile.getParent() + "\\" + this.filename + "_tmp"));
 			//this.selectedfile.renameTo(new File(this.selectedfile.getParent() + "\\" + this.filename + "_tmp"));
  			//this.finalfile = new File(this.selectedfile.getParent() + "\\" + this.filename);
 			//this.finalfile = new File(this.filename);
 			this.tmpfile = (new File(this.selectedfile.getParent() + "\\" + this.filename + "_tmp"));
 			this.finalfile = new File(this.filename+".txt");
  			this.finalfilepath = finalfile.getAbsolutePath();
 			System.out.println("Final File: " + finalfile + " Selected file: " + selectedfile);
 		System.out.println("Final File: " + this.finalfile + " Selected file: " + selectedfile);
  			Files.copy(this.selectedfile.toPath(), this.finalfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
  			this.isSaved = true;
  		}
 	}
 	
 	
 	private void deleteTmpFile()
 	{
 		if (this.finalfile != null) //make sure there's a file that's already saved
 		{
 			this.tmpfile.delete();
 		} else
 		{
 			System.out.println("There are no other files present!");
 		}
}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
	}
}