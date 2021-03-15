package accounts;

import javafx.beans.property.SimpleStringProperty;

/**
 * The AdminRow class contains the data of the Admin table in the MySQL database. When the init()
 * method of the AdminController class is called, this AdminRow object will be used to display
 * data from the table.
 * 
 * @author Tim Lai
 * @version 1.0
 * @since 1.0
 */
public class AdminRow {
	SimpleStringProperty email;
	SimpleStringProperty password;
	SimpleStringProperty firstName;
	SimpleStringProperty lastName;
	SimpleStringProperty level;
	
	/**
	 * The constructor of the AdminRow class.
	 * @param email The Admin account's email address.
	 * @param password The Admin account's password.
	 * @param firstName The Admin account's first name.
	 * @param lastName The Admin account's last name.
	 * @param level The Admin account's hierarchy level.
	 */
	public AdminRow(String email, String password, String firstName, String lastName, String level) {
		this.email = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty(password);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
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
	 * This is the query method that returns the get() method to get the firstName. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="firstName" /></cellValueFactory>}
	 * 
	 * @return firstName.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getFirstName() {
		return firstName.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the lastName. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="lastName" /></cellValueFactory>}
	 * 
	 * @return lastName.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getLastName() {
		return lastName.get();
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
