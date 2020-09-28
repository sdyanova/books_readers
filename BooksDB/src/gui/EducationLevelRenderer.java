package gui;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.EducationLevel;

public class EducationLevelRenderer implements TableCellRenderer{

	private JComboBox combo;
	
	public EducationLevelRenderer() {
		
		//returns all possible values  - calling the toString of these objects to be displayed
		combo = new JComboBox(EducationLevel.values());
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) {
		
		combo.setSelectedItem(value);
		
		return combo;
	}

}
