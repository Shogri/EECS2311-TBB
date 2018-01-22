package enamel;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToyAuthoring {
	private static Component parent;

	public static void main(String[] args) throws IOException {

		ScenarioParser s = new ScenarioParser(true);
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Factory Scenario Files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			s.setScenarioFile(chooser.getSelectedFile().getAbsolutePath()); //uses JFileChooser to generate path of file
		}
	}
}