package model;

import java.io.Serializable;

public class Reader implements Serializable{
	
	private static final long serialVersionUID = -1558664364124112640L;
	private static int count = 1;
	
	
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EducationLevel eduLevel;
	private String pinId;
	private boolean euCitizen;
	private Gender gender;
	
	public Reader(String name, String occupation, AgeCategory ageCategory, 
			EducationLevel eduLevel, String pinId, 
			boolean euCitizen, Gender gender ) {
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.eduLevel = eduLevel;
		this.pinId = pinId;
		this.euCitizen = euCitizen;
		this.gender = gender;
		
		this.id = count;
		count++; 
	}
	
	//overloaded constructor which calls the otheð one and sets the id
	public Reader(int id, String name, String occupation, AgeCategory ageCategory, 
			EducationLevel eduLevel, String pinId, 
			boolean euCitizen, Gender gender ) {
		
		this(name, occupation, ageCategory, eduLevel, pinId, euCitizen, gender );
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EducationLevel getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(EducationLevel eduLevel) {
		this.eduLevel = eduLevel;
	}
	public String getPinId() {
		return pinId;
	}
	public void setPinId(String pinId) {
		this.pinId = pinId;
	}
	public boolean isEuCitizen() {
		return euCitizen;
	}
	public void setEuCitizen(boolean euCitizen) {
		this.euCitizen = euCitizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", occupation=" + occupation + ", ageCategory=" + ageCategory
				+ ", eduLevel=" + eduLevel + ", pinId=" + pinId + ", euCitizen=" + euCitizen + ", gender=" + gender + "]";
	}
	
}
