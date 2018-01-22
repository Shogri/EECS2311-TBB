package enamel;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToyAuthoring {
	private static Component parent;

	public static void main(String[] args) throws IOException {

		ScenarioParser s = new ScenarioParser(true);
		JFileChooser chooser1 = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Factory Scenario Files", "txt");
		chooser1.setFileFilter(filter);
		int returnVal = chooser1.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
			s.setScenarioFile(chooser1.getSelectedFile().getAbsolutePath()); //uses JFileChooser to generate path of file
		}
	}
}