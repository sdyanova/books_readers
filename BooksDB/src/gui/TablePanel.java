package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.EducationLevel;
import model.Reader;

public class TablePanel extends JPanel {
	
	private JTable table;
	private ReaderTableModel tableModel;
	private JPopupMenu popup;
	private ReaderTableListener readerTableListener;
	
	public TablePanel() {
		
		tableModel = new ReaderTableModel();
		table = new JTable(tableModel);//passing the tableModel to the table
		popup = new JPopupMenu();
		
		table.setDefaultRenderer(EducationLevel.class, new EducationLevelRenderer() );
		table.setDefaultEditor(EducationLevel.class, new EducationLevelEditor());
		table.setRowHeight(25);
		
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);//selecting a row
				
				System.out.println(row);
				if(e.getButton()==MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(readerTableListener != null) {
					readerTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row); //notifiys the listeners for the change
				}				
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	//setting the table to accept a list of reader objects
	public void setData(List<Reader> db) {
		tableModel.setData(db);
	}
	//updating data
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setReaderTableListener(ReaderTableListener listener) {
		this.readerTableListener = listener;
	}
}
