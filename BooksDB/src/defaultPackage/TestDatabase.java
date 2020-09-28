package defaultPackage;
import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EducationLevel;
import model.Gender;
import model.Reader;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test");
		
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		db.addReader(new Reader("Emilia Clark", "actress", AgeCategory.adult, EducationLevel.bachelor_degree, "8989898989", false, Gender.female));
		db.addReader(new Reader("Romeo Beckham", "singer", AgeCategory.child, EducationLevel.primary_school, "211898989", true, Gender.male));
		db.addReader(new Reader("Victoria Beckham", "fashion designer", AgeCategory.adult, EducationLevel.bachelor_degree, "21189999", true, Gender.female));
		
		try {
			db.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnect();
	}
}
