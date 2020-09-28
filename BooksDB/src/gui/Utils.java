package gui;

import javax.swing.ImageIcon;

public class Utils {
	
	//get extension of a file
	public static String getFileExtension(String name) {
		
		int pointIndex = name.lastIndexOf("."); 
		
		if(pointIndex == -1) { //the file has no extension
			return null;
		}
		if(pointIndex == name.length()-1) {
			return null;
		}
		return name.substring(pointIndex+1, name.length()); //right after the dot		
	}
	
	//images
	public static ImageIcon createIcon(String path) {
		java.net.URL url = Utils.class.getResource(path);
		
		if(url == null) {
			System.err.println("Can't load image :( " + path);
		}
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;		
	}

}
