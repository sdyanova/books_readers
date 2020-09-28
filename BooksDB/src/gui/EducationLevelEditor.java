package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.EducationLevel;

public class EducationLevelEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	private JComboBox combo;
	
	public EducationLevelEditor() {
		combo = new JComboBox(EducationLevel.values());
	}
	
	@Override
	public Object getCellEditorValue() {
		return combo.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		combo.setSelectedItem(value);
		
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();	
			}
		});
		
		return combo;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}

}
