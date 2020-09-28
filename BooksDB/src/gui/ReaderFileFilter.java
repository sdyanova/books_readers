package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ReaderFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		
		if(file.isDirectory()) {
			return true;
		}
	
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name); //returns all stuf after the file name
		
		if(extension==null) {
			return false;
		}
		
		if(extension.equals("rdr")) {
			return true;
		}	
		return false;
	}

	@Override
	public String getDescription() {
		return "Reader database files(*.rdr)";
	}

}
