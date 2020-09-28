package gui;

import java.util.EventObject;

public class FormEvent extends EventObject{  //The root class from which all event state objects shall be derived. 
	
	private String name;
	private String occupation;
	private int ageCategory;
	private String eduLevel;
	private String pinId;
	private boolean euCitizen;
	private String gender;
	
	public FormEvent(Object source) {
		super(source); //the okBtn in the FormPanel
		
	}
	
	//storing all the info in this object & relays to the mainFrame
	public FormEvent(Object source, String name, String occupation, int ageCat,
			String eduLevel, String pinId, boolean euCitizen, String gender ) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory=ageCat;
		this.eduLevel = eduLevel;
		this.pinId = pinId;
		this.euCitizen = euCitizen;
		this.gender = gender;
	}

	
	public String getGender() {
		return gender;
	}

	public String getPinId() {
		return pinId;
	}

	public boolean isEuCitizen() {
		return euCitizen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public int getAgeCategory() {
		return ageCategory;
	}
	
	public String getEducationLevel() {
		return eduLevel;
	}
	
	
}
