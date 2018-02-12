package enamel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LineEditor { //model in the model view controller for the scenario file

	public static void setupCellButton(File file, int cells, int buttons) throws IOException
	{
		FileWriter fw = new FileWriter(file.getAbsolutePath(), false);
		fw.write(System.lineSeparator());
		fw.write("Cell " + cells + "\n");
		fw.write("Button " + buttons + "\n");
		fw.close(); 
	}
	
	public static void setDisplay(File file, int cellnum, int pins) throws IOException, NumberFormatException
	{
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		fw.write(System.lineSeparator());
		fw.write("/~disp-cell-pins:" + cellnum + " " + pins);
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
}
