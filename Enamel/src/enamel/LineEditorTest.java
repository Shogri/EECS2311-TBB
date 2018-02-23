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
	
	//Test 25
	@Test
	public void testaddSkip1() throws IOException
	{
		LineEditor.addSkip(file, "identifier");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip:"))
		       {
		    	   assertEquals("/~skip:identifier", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test 26
	@Test
	public void testaddSkip2() throws IOException
	{
		LineEditor.addSkip(file, "identifier");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip:"))
		       {
		    	   assertNotEquals("/~skip: identifier", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 27
	@Test
	public void testaddDispClearAll1() throws IOException
	{
		LineEditor.addDispClearAll(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-clearAll"))
		       {
		    	   assertEquals("/~disp-clearAll", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test 28
	@Test
	public void testaddDispClearAll2() throws IOException
	{
		LineEditor.addDispClearAll(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-clearAll"))
		       {
		    	   assertNotEquals("/~disp-clearAll ", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test 29
	@Test
	public void testaddDispClearCell1() throws IOException
	{
		LineEditor.addDispClearCell(file, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-clear-cell:"))
		       {
		    	   assertEquals("/~disp-clear-cell:4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	//Test 30
	@Test
	public void testaddDispClearCell2() throws IOException
	{
		LineEditor.addDispClearCell(file, 4);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-clear-cell:"))
		       {
		    	   assertNotEquals("/~disp-clear-cell: 4", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 31
	@Test
	public void testaddDispCellPins1() throws IOException
	{
		LineEditor.addDispCellPins(file, 1, 11100000);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-pins:"))
		       {
		    	   assertEquals("/~disp-cell-pins:1 11100000", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test32
	@Test
	public void testaddDispCellPins2() throws IOException
	{
		LineEditor.addDispCellPins(file, 1, 11100000);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-pins:"))
		       {
		    	   assertNotEquals("/~disp-cell-pins:111100000", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test33
	@Test
	public void testaddDispCellChar1() throws IOException
	{
		LineEditor.addDispCellChar(file, 1, "a");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-char:"))
		       {
		    	   assertEquals("/~disp-cell-char:1 a", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test34
	@Test
	public void testaddDispCellChar2() throws IOException
	{
		LineEditor.addDispCellChar(file, 1, "a");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-char:"))
		       {
		    	   assertNotEquals("/~disp-cell-char:1a", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test35
	@Test
	public void testaddDispCellRaise1() throws IOException
	{
		LineEditor.addDispCellRaise(file, 1, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-raise:"))
		       {
		    	   assertEquals("/~disp-cell-raise:1 2", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 36
	@Test
	public void testaddDispCellRaise2() throws IOException
	{
		LineEditor.addDispCellRaise(file, 1, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-raise:"))
		       {
		    	   assertNotEquals("/~disp-cell-raise: 1 2", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 37
	@Test
	public void testaddDispCellLower1() throws IOException
	{
		LineEditor.addDispCellLower(file, 1, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-lower:"))
		       {
		    	   assertEquals("/~disp-cell-lower:1 2", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 38
	@Test
	public void testaddDispCellLower2() throws IOException
	{
		LineEditor.addDispCellLower(file, 1, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~disp-cell-lower:"))
		       {
		    	   assertNotEquals("/~disp-cell-lower: 1 2", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 39
	@Test
	public void testactivateKeys1() throws IOException
	{
		LineEditor.activateKeys(file, 1);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button:"))
		       {
		    	   assertEquals("/~skip-button:0 ONEE", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test 40
	@Test
	public void testactivateKeys2() throws IOException
	{
		LineEditor.activateKeys(file, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button:"))
		       {
		    	   assertEquals("/~skip-button:1 TWOO", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	//Test 41
	@Test
	public void testactivateKeys3() throws IOException
	{
		LineEditor.activateKeys(file, 1);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button:"))
		       {
		    	   assertNotEquals("/~skip-button:1 TWOO", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 42
	@Test
	public void testactivateKeys4() throws IOException
	{
		LineEditor.activateKeys(file, 2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith("/~skip-button:"))
		       {
		    	   assertNotEquals("/~skip-button:0 ONEE", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 43
	@Test
	public void testAddString1() throws IOException
	{
		LineEditor.addString(file, "Test addition");
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertEquals("Test addition", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 44
	@Test
	public void testAddString2() throws IOException
	{
		LineEditor.addString(file, "");
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty())
		       {
		    	   assertNotEquals("Test addition", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 45
	@Test
	public void testsetKey1() throws IOException
	{
		LineEditor.setKey(file,1);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertEquals("/~ONEE", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 46
	@Test
	public void testsetKey2() throws IOException
	{
		LineEditor.setKey(file,2);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertEquals("/~TWOO", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test47
	@Test
	public void testnextQuestion() throws IOException
	{
		LineEditor.nextQuestion(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertEquals("/~NEXTT", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}
	
	//Test 48
	@Test
	public void testimportSound1() throws IOException
	{
		LineEditor.importSound(file, "testSound.wav");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertEquals("/~sound:testSound.wav", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 49
	@Test
	public void testImportSound2() throws IOException
	{
		LineEditor.importSound(file, "testSound");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertNotEquals("/~sound:testSound.wav", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	//Test 50
	@Test
	public void testimportSound3() throws IOException
	{
		LineEditor.importSound(file, "testSound.wav");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.isEmpty() != true)
		       {
		    	   assertNotEquals("/~sound:testSound", line);
		       }
		    }
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
	
	
}


