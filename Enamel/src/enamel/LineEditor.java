package enamel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LineEditor { //model in the model view controller for the scenario file

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
}
