package gui;

public class TypeInfo {
	private String name;
	private int id;
	private boolean checked;
	
	public TypeInfo(String name, int id, boolean checked) {
		super();
		this.name = name;
		this.id = id;
		this.checked=checked;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//returns name of the object
	public String toString() {
		return name;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked=checked;
	}
}
