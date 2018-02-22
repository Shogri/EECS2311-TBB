package enamel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

public class LineEditor { //model in the model view controller for the scenario file
	
	private static boolean tabbed;
	private static String TAB = "";

	public static void setupCellButton(File file, int cells, int buttons) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsolutePath(), false);
		fw.write("Cell " + cells);
		fw.write(System.lineSeparator());
		fw.write("Button " + buttons);
		fw.close(); 
	}
	
	public static void addPause(File file, int timeofpause) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~pause:" + timeofpause);
		fw.close();
	}
	
	public static void addDispString(File file, String stringtodisplay) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-string:" + stringtodisplay);
		fw.close();
	}
	
	public static void addRepeat(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~repeat");
		fw.close();
	}
	
	public static void addEndRepeat(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~endrepeat");
		fw.close();
	}
	
	public static void addRepeatButton(File file, int numofbutton) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~repeat-button:" + numofbutton);
		fw.close();
	} 
	
	public static void addSkipButton(File file, int numofbutton, String identifier) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~skip-button:" + numofbutton + " " + identifier);
		fw.close();
	}
	
	public static void addUserInput(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~user-input");
		fw.close();
	}
	
	public static void addSound(File file, String soundFileName) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~sound:" + soundFileName);
		fw.close();
	}
	
	public static void addResetButtons(File file, String soundFileName) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~sound:" + soundFileName);
		fw.close();
	}
	
	public static void addSkip(File file, String identifier) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~skip:" + identifier);
		fw.close();
	}
	
	public static void addDispClearAll(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-clearAll");
		fw.close();
	}
	
	public static void addDispClearCell(File file, int cellnum) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-clear-cell:" + cellnum);
		fw.close();
	}
	
	public static void addDispCellPins(File file, int cellnum, int sequence) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-cell-pins:" + cellnum + " " + sequence);
		fw.close();
	}
	
	public static void addDispCellChar(File file, int cellnum, String character) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-cell-char:" + cellnum + " " + character);
		fw.close();
	}
	
	public static void addDispCellRaise(File file, int cellnum, int pinnum) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-cell-raise:" + cellnum + " " + pinnum);
		fw.close();
	}
	
	public static void addDispCellLower(File file, int cellnum, int pinnum) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-cell-lower:" + cellnum + " " + pinnum);
		fw.close();
	}
	
	public static void activateKeys(File file, int keyNum) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		if(keyNum == 1)
		{
			fw.write("/~skip-button:0 ONEE");
			fw.close();
		}
		if(keyNum == 2)
		{
			fw.write("/~skip-button:1 TWOO");
			fw.close();
		}
	}
	
	public static void addString(File file, String toAdd) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write(toAdd);
		fw.close();
		
	}
	
	public static void setKey(File file, int keyNum) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		if(keyNum==1)
		{
		fw.write("/~ONEE");
		}
		if(keyNum==2)
		{
			fw.write("/~TWOO");
		}
		fw.close();
	}

	public static void nextQuestion(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write(" ");
		fw.write(System.lineSeparator());
		fw.write("/~NEXTT");
		fw.close();
	}

	public static void parseScenario(File file, DefaultListModel<String> listModel) throws IOException, InterruptedException
	{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		
		while (line != null)
		{
			parseLine(line, listModel);
			line = br.readLine();
		}
		
		br.close();
	}
	
	static void parseLine(String fileLine, DefaultListModel<String> listModel) throws InterruptedException {
		// This statement checks if the key phrase /~repeat has been read. 
		if (tabbed) {
			TAB = "     ";
			// Stops assuming that the text is being repeated with the
			// /~endrepeat key phrase
			if (fileLine.length() >= 11 && fileLine.substring(0, 11).equals("/~endrepeat")) {
				tabbed = false;
				TAB = "";
			}
		} else {
			// The key phrase to indicate to play a sound file.
			if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~sound:")) {
				if (tabbed)
				{
					listModel.addElement(TAB +"Playing Sound: "+ fileLine.substring(8));
				}
			}
			// The key phrase to indicate to skip to another part of the
			// scenario.
			else if (fileLine.length() >= 7 && fileLine.substring(0, 7).equals("/~skip:")) {
				listModel.addElement(TAB +"Skip to line # " + fileLine.substring(7));
			}
			// The key phrase to indicate to pause for a specified number of
			// seconds.
			else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~pause:")) {
				listModel.addElement(TAB +"Pause for " + fileLine.substring(8) + " Seconds");
			}
			// The key phrase to assign a button to repeat text.
			else if (fileLine.length() >= 16 && fileLine.substring(0, 16).equals("/~repeat-button:")) {
				listModel.addElement(TAB +"Repeat button number " + fileLine.substring(16));
			}
			// The key phrase to signal that everything after that key phrase
			// will be repeated.
			else if (fileLine.length() >= 8 && fileLine.substring(0, 8).equals("/~repeat")) {
				tabbed = true;
				listModel.addElement(TAB +"Repeat the following: ");
			}
			// The key phrase to reset the action listeners of all of the
			// JButtons.
			else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~reset-buttons")) {
				listModel.addElement(TAB +"Reset all buttons");
			}
			// The key phrase to assign a button to skip to another part of the
			// scenario.
			else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~skip-button:")) {
				listModel.addElement(TAB +"Skip the button numbered " + fileLine.substring(14));
			}
			// The key phrase to clear the display of all of the braille cells.
			else if (fileLine.length() >= 15 && fileLine.substring(0, 15).equals("/~disp-clearAll")) {
				//player.clearAllCells();
				listModel.addElement(TAB +"Clear all existing cells");
			}
			// The key phrase to set a Braille cell to a string.
			else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-pins:")) {
				listModel.addElement(TAB +"Display cell #" + fileLine.substring(17).split(" ")[0] + "for the letter " + fileLine.substring(17).split(" ")[1]);
			}
			// The key phrase to represent a string in Braille.
			else if (fileLine.length() >= 14 && fileLine.substring(0, 14).equals("/~disp-string:")) {
				listModel.addElement(TAB +"Display the string: " + "\'" + fileLine.substring(14) + "\'" );
				
			}
			// The key phrase to change the cell to represent a character in
			// Braille.
			else if (fileLine.length() >= 17 && fileLine.substring(0, 17).equals("/~disp-cell-char:")) {
				listModel.addElement(TAB +"Display the character: " + fileLine.substring(17));
			}
			// The key phrase to raise a pin of the specified Braille cell.
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-raise:")) {
				listModel.addElement(TAB +"Raising cells: " + fileLine.substring(18));
			}
			// The key phrase to lower a pin of the specified Braille cell.
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-lower:")) {
				listModel.addElement(TAB +"Lowering cells: " + fileLine.substring(18));
			}
			// The key phrase to clear a Braille cell.
			else if (fileLine.length() >= 18 && fileLine.substring(0, 18).equals("/~disp-cell-clear:")) {
				listModel.addElement(TAB +"Clear the cells: " + fileLine.substring(18));
			} else if (fileLine.length() >= 21 && fileLine.substring(0, 21).equals("/~disp-cell-lowerPins")) {
				listModel.addElement(TAB +"Lower all pins");
			}
			// The key phrase to wait for the program to receive a user's input.
			else if (fileLine.length() >= 12 && fileLine.substring(0, 12).equals("/~user-input")) {
				listModel.addElement(TAB +"Wait for user input");
			}
			// Anything other than the specified commands above, is to be
			// interpreted as text that
			// will be spoken for the user to hear.
			else if (fileLine.length() >= 4 && fileLine.substring(0, 4).equals("Cell")) {
				listModel.addElement(fileLine);
			}
			
			else if (fileLine.length() >= 6 && fileLine.substring(0, 6).equals("Button")){
				listModel.addElement(fileLine);
			} 	
			
			else if (fileLine.isEmpty())
			{
				listModel.addElement(" ");
			}
			else {
				listModel.addElement(TAB +"Say: " + fileLine);
			}
		}
	}
	
}
