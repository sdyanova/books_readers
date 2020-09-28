package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Reader> readers;
	
	private Connection con;
	
	private int port;
	private String user;
	private String password;
	
	public Database() {
		readers = new LinkedList<Reader>();//for all readers
	}
	
	//connecting & disconnecting the prefs dialog with db
	public void configure(int port, String user, String password) throws Exception {
		this.port = port;
		this.user=user;
		this.password=password;
		
		if(con!=null) {
			disconnect();
			connect();
		}	
	}
	
	
	public void connect() throws Exception {
		
		if(con!=null) return;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		
		String url = "jdbc:mysql://localhost:3306/books_schema?serverTimezone=UTC";
		//String url = "jdbc:mysql://localhost:3306/myjavaprojectdb1?serverTimezone=UTC";
		con = (Connection)DriverManager.getConnection(url, "user1", "Mjppass1983!");
		
		System.out.println("Connected:"+con);
	}
	
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}
	
	
	//save data(readers) to database - CHECK FIRST IF THERE IS ANY IN THE DB
	public void save() throws SQLException {
		//returns count as result
		String checkSql = "select count(*) as count from readers where id=?";
		//check if there is any
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		
		//inserting 
		String insertSql = "insert into readers (id, name, age, education_level, pin_id, eu_citizen, gender,occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		//updating 
		String updateSql = "update readers set name=?, age=?, education_level=?, pin_id=?, eu_citizen=?, gender=?,occupation=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		for(Reader reader: readers) {
			int id = reader.getId();
			
			String name = reader.getName();
			String occupation = reader.getOccupation();
			AgeCategory age = reader.getAgeCategory();
			EducationLevel edu = reader.getEduLevel();
			String pin = reader.getPinId();
			boolean isEu = reader.isEuCitizen();
			Gender gender = reader.getGender();
			
			//look for the first one and replace it with id
			checkStmt.setInt(1, id);
			
		ResultSet checkResult=checkStmt.executeQuery();
		//moving to the first row
		checkResult.next();
		
		int count = checkResult.getInt(1);
		
		//update/insert in db
			if(count==0) {
				System.out.println("Inserting reader with id: "+id);
				
				int col = 1;
				insertStatement.setInt(col++, id); 
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, edu.name());
				insertStatement.setString(col++, pin);
				insertStatement.setBoolean(col++, isEu);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occupation);
				
				insertStatement.executeUpdate();
			}
			else {
				System.out.println("Updating reader with id: "+id);
				
				int col = 1;
				 
				updateStatement.setString(col++, name);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, edu.name());
				updateStatement.setString(col++, pin);
				updateStatement.setBoolean(col++, isEu);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occupation);
				updateStatement.setInt(col++, id);
				
				updateStatement.executeUpdate();
			}
		}
		updateStatement.close();	
		insertStatement.close();
		checkStmt.close();
	}
	
	//loading data from db
	public void load() throws SQLException {
		readers.clear();
		
		String sql = "select id, name, age, education_level, pin_id, eu_citizen, gender,occupation from readers order by name";
		Statement selectStatement = con.createStatement();
		
				
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String edu = results.getString("education_level");
			String pinId = results.getString("pin_id");
			boolean isEu = results.getBoolean("eu_citizen");
			String gender = results.getString("gender");
			String occ = results.getString("occupation");
			
			Reader reader = new Reader(id, name,occ, AgeCategory.valueOf(age), EducationLevel.valueOf(edu), pinId, isEu, Gender.valueOf(gender));
			readers.add(reader);
		}
		results.close();
		selectStatement.close();
	}

	
	public void addReader(Reader reader) {
		readers.add(reader);
	}
	
	public void removeReader(int index) {
		readers.remove(index);
	}
	
	public List <Reader> getReaders(){
		return Collections.unmodifiableList(readers);  //the list interface, returning the value of the arraylist up unmodifiable
	}
	
	//saving data from file object
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
	
		Reader[] persons = readers.toArray(new Reader[readers.size()]);
		//passing the above array to file
		oos.writeObject(persons);
		
		oos.close();
	}
	
	//loading data from file
	public void loadFromFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Reader[] persons = (Reader[])ois.readObject();
			
			readers.clear();
			//converting the array to a list
			readers.addAll(Arrays.asList(persons));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}
}
