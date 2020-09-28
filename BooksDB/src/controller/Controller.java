package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EducationLevel;
import model.Gender;
import model.Reader;

public class Controller {
	Database db = new Database();
	
	//readers & DB
	public List<Reader> getReaders(){
		 return db.getReaders();
	}	
	
	public void removeReader(int index) {
		db.removeReader(index);
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	//calling db from view
	public void load() throws SQLException {
		db.load();
	}
	
	public void configure(int port, String user, String password) throws Exception{
		db.configure(port, user, password);
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	//closing the connection
	public void disconnect() {
		db.disconnect();
	};
	
	//adding readers - raising an event
	public void addReader(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String eduLevel = ev.getEducationLevel();
		boolean isEu = ev.isEuCitizen();
		String pinId = ev.getPinId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory=null;
		
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		}
		
		EducationLevel educationLevel;
		
		if(eduLevel.equals("preschool")) {
			educationLevel = EducationLevel.preschool;
		}
		else if(eduLevel.equals("primary_school")) {
			educationLevel = EducationLevel.primary_school;
		}
		else if(eduLevel.equals("secondary_school")) {
			educationLevel = EducationLevel.secondary_school;
		}
		else if(eduLevel.equals("bachelor_degree")) {
			educationLevel = EducationLevel.bachelor_degree;
		}
		else if(eduLevel.equals("master_degree")) {
			educationLevel = EducationLevel.master_degree;
		}
		else if(eduLevel.equals("doctoral_degree")) {
			educationLevel = EducationLevel.doctoral_degree;
		}
		else {
			educationLevel = EducationLevel.other;
			System.err.println(eduLevel);
		}
		
		Gender genderCat;
		
		if(gender.equals("Male")) {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Reader reader = new Reader(name, occupation, ageCategory, educationLevel, 
				pinId, isEu, genderCat);
		db.addReader(reader);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
