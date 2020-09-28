package model;

public enum EducationLevel {
	preschool("Preschool"),
	primary_school("Primary school"),
	secondary_school("Secondary school"),
	bachelor_degree("Bachelor degree"),
	master_degree("Master degree"),
	doctoral_degree("Doctoral degree"),
	other("Other");
	
	private String text;
	
	private EducationLevel(String text) {
		this.text=text;
	}

	@Override
	public String toString() {
		return text;
	}
}
