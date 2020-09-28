package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.AgeCategory;
import model.EducationLevel;
import model.Reader;

public class ReaderTableModel extends AbstractTableModel {
	
	private List<Reader> db;
	
	//column names
	private String [] colNames =  {"ID","Name","Occupation","Age Category","Education Level","EU Citizen","PIN" };
	
	
	public ReaderTableModel () {
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	//overrided in order to edit cells
	@Override
	public boolean isCellEditable(int rowIndex, int col) {
		
		switch(col) {
			case 1:
				return true;
			case 2:
				return true;
			case 3:
				return true;
			case 4:
				return true;
			case 5:
				return true;
			case 6:
				return true;
			default: 
				return false;
		}
	}
	
	//overrided method in order to save changed data in cells
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		if(db == null) return;
		Reader reader = db.get(row);
		
		switch (col) {
		
		case 1:
			reader.setName((String)value);
			break;
		case 2:
			reader.setOccupation((String)value);
			break;
		case 3:
			reader.setAgeCategory((AgeCategory)value);
			break;
		case 4:
			reader.setEduLevel((EducationLevel)value);
			break;
		case 5:
			reader.setEuCitizen((Boolean)value);
			break;
		case 6:
			reader.setPinId((String)value);
			break;
		default:
			return;
		}
	}
	
	

	//returns classes
	@Override
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return AgeCategory.class;
		case 4:
			return EducationLevel.class;
		case 5:
			return Boolean.class;
		case 6: 
			return String.class;
		default:
			return null;
		}
	}


	public void setData(List<Reader> db) {
		this.db = db;
	}
	
	@Override
	public int getRowCount() {
		return db.size();
	}
	@Override
	public int getColumnCount() {
		return 7;
	}
	//calling methodts on reader
	@Override
	public Object getValueAt(int row, int col) {
		Reader reader = db.get(row);
		
		switch(col) {
		case 0:
			return reader.getId();
		case 1:
			return reader.getName();
		case 2:
			return reader.getOccupation();
		case 3:
			return reader.getAgeCategory();
		case 4:
			return reader.getEduLevel();
		case 5:
			return reader.isEuCitizen();
		case 6: 
			return reader.getPinId();
		}	
		return null;
	}
}
