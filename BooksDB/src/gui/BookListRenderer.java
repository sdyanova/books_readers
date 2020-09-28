package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Book;

public class BookListRenderer implements ListCellRenderer{
	
	private JPanel panel;
	private JLabel label;
	
	private Color selectedColor;
	private Color normalColor;

	public BookListRenderer(){
		panel = new JPanel();
		label = new JLabel();
		
		selectedColor = new Color(210,210,255);
		normalColor = Color.WHITE;
		
		label.setIcon(Utils.createIcon("/pics/books-appearance.png"));
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panel.add(label);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, 
			Object value, int index, boolean isSelected, boolean cellHasFocus) {

		Book book = (Book) value;	
		
		label.setText(book.getTitle());
		
		panel.setBackground(cellHasFocus ? selectedColor: normalColor);
		//label.setOpaque(true);
		return panel;
	}

}
