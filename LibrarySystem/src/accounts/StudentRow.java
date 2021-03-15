package accounts;

import javafx.beans.property.SimpleStringProperty;


public class StudentRow {
	SimpleStringProperty email;
	SimpleStringProperty password;
	SimpleStringProperty studentNo;
	SimpleStringProperty surname;
	SimpleStringProperty forename;
	SimpleStringProperty level;
	
	/**
	 * The constructor of the StudentRow class.
	 * @param email The Student account's email address.
	 * @param password The Student account's email password.
	 * @param studentNo The Student account's email student number.
	 * @param surname The Student account's surname.
	 * @param forename The Student account's forename.
	 * @param level The Student account's hierarchy level.
	 */
	public StudentRow(String email, String password, String studentNo, String surname, String forename, String level) {
		this.email = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty(password);
		this.studentNo = new SimpleStringProperty(studentNo);
		this.surname = new SimpleStringProperty(surname);
		this.forename = new SimpleStringProperty(forename);
		this.level = new SimpleStringProperty(level);
	}
	
	/**
	 * This is the query method that returns the get() method to get the email. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="email" /></cellValueFactory>}
	 * 
	 * @return email.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the password. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="password" /></cellValueFactory>}
	 * 
	 * @return password.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getPassword() {
		return password.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the studentNo. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="studentNo" /></cellValueFactory>}
	 * 
	 * @return studentNo.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getStudentNo() {
		return studentNo.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the surname. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="surname" /></cellValueFactory>}
	 * 
	 * @return surname.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getSurname() {
		return surname.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the forename. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="forename" /></cellValueFactory>}
	 * 
	 * @return forename.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getForename() {
		return forename.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the level. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="level" /></cellValueFactory>}
	 * 
	 * @return level.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getLevel() {
		return level.get();
	}
}
