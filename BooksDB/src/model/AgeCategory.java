package model;

public enum AgeCategory {
	child("Child"),
	adult("Adult"), 
	senior("Senior");


	private String text;
	
	private AgeCategory(String text) {
		this.text=text;
	}

	@Override
	public String toString() {
		return text;
	}
	
}

