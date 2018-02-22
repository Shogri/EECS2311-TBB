package enamel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class LineEditorTest {
	
	File file = new File("testingFile.txt");
	
	
	
	@Test
	public void testPause1() throws IOException
	{
		LineEditor.addPause(file, 5);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~pause"))
		       {
		    	   assertEquals("/~pause:5", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testPause2() throws IOException
	{
		LineEditor.addPause(file, 5);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~pause"))
		       {
		    	   assertNotEquals("/~pause: 5", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testPause3() throws IOException
	{
		LineEditor.addPause(file, 5);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~pause"))
		       {
		    	   assertNotEquals("/~pause:", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testSetUpCellButton1() throws IOException 
	{
		LineEditor.setupCellButton(file, 1, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("Cell"))
		       {
		    	   assertEquals("Cell 1", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testSetUpCellButton2() throws IOException 
	{
		LineEditor.setupCellButton(file, 1, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("Cell"))
		       {
		    	   assertNotEquals("Cell 4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testSetUpCellButton3() throws IOException 
	{
		LineEditor.setupCellButton(file, 1, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("Button"))
		       {
		    	   assertEquals("Button 4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testSetUpCellButton4() throws IOException 
	{
		LineEditor.setupCellButton(file, 1, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("Button"))
		       {
		    	   assertNotEquals("Button 1", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testaddDispString1() throws IOException 
	{
		LineEditor.addDispString(file, "Testing display");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-string"))
		       {
		    	   assertEquals("/~disp-string:Testing display", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testaddDispString2() throws IOException 
	{
		LineEditor.addDispString(file, "Testing display");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-string"))
		       {
		    	   assertNotEquals("/~disp-string: Testing display", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testaddRepeat1() throws IOException 
	{
		LineEditor.addRepeat(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~repeat"))
		       {
		    	   assertEquals("/~repeat", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	@Test
	public void testaddRepeat2() throws IOException 
	{
		LineEditor.addRepeat(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~repeat"))
		       {
		    	   assertNotEquals("/~repeat ", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	@Test
	public void testaddEndRepeat1() throws IOException
	{
		LineEditor.addEndRepeat(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~endrepeat"))
		       {
		    	   assertEquals("/~endrepeat", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	@Test
	public void testaddEndRepeat2() throws IOException
	{
		LineEditor.addEndRepeat(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~endrepeat"))
		       {
		    	   assertNotEquals("/~endrepeat ", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test Number 14
	@Test 
	public void testAddRepeatButton1() throws IOException
	{
		LineEditor.addRepeatButton(file, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~repeat-button"))
		       {
		    	   assertEquals("/~repeat-button:4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test number 15
	@Test 
	public void testAddRepeatButton2() throws IOException
	{
		LineEditor.addRepeatButton(file, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~repeat-button"))
		       {
		    	   assertNotEquals("/~repeat-button: 4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test Number 16
	@Test 
	public void testAddRepeatButton3() throws IOException
	{
		LineEditor.addRepeatButton(file, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~repeat-button"))
		       {
		    	   assertNotEquals("/~repeat-button: ", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test Number 17
	@Test
	public void addSkipButton1() throws IOException
	{
		LineEditor.addSkipButton(file, 4, "Identifier");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button"))
		       {
		    	   assertEquals("/~skip-button:4 Identifier", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test Number 18
	@Test
	public void addSkipButton2() throws IOException
	{
		LineEditor.addSkipButton(file, 4, "Identifier");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button"))
		       {
		    	   assertNotEquals("/~skip-button:4Identifier", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test Number 19
	@Test
	public void addSkipButton3() throws IOException
	{
		LineEditor.addSkipButton(file, 4, "Identifier");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button"))
		       {
		    	   assertNotEquals("/~skip-button: 4Identifier", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test 20
	@Test
	public void addUserInput1() throws IOException
	{
		LineEditor.addUserInput(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~user-input"))
		       {
		    	   assertEquals("/~user-input", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 21
	@Test
	public void addUserInput2() throws IOException
	{
		LineEditor.addUserInput(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~user-input"))
		       {
		    	   assertNotEquals("/~user-input ", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 22
	@Test 
	public void testaddSound1() throws IOException
	{
		LineEditor.addSound(file, "testSound.wav");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~sound"))
		       {
		    	   assertEquals("/~sound:testSound.wav", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 23
	@Test 
	public void testaddSound2() throws IOException
	{
		LineEditor.addSound(file, "testSound");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~sound"))
		       {
		    	   assertNotEquals("/~sound:testSound.wav", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 24
	@Test
	public void testaddSound3() throws IOException
	{
		LineEditor.addSound(file, "testSound.wav");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~sound"))
		       {
		    	   assertNotEquals("/~sound: testSound.wav", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
}


