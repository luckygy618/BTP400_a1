package accounts;

import javafx.beans.property.SimpleStringProperty;


public class LibrarianRow {
	SimpleStringProperty email;
	SimpleStringProperty password;
	SimpleStringProperty firstName;
	SimpleStringProperty lastName;
	SimpleStringProperty credentials;
	SimpleStringProperty bio;
	SimpleStringProperty level;

	/**
	 * The constructor of the LibrarianRow class.
	 * @param email The Librarian account's email address.
	 * @param password The Librarian account's password.
	 * @param firstName The Librarian account's first name.
	 * @param lastName The Librarian account's last name.
	 * @param credentials The Librarian account's credentials.
	 * @param bio The Librarian account's bio.
	 * @param level The Librarian account's hierarchy level.
	 */
	public LibrarianRow(String email, String password, String firstName, String lastName, String credentials, String bio, String level) {
		this.email = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty(password);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.credentials = new SimpleStringProperty(credentials);
		this.bio = new SimpleStringProperty(bio);
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
	 * This is the query method that returns the get() method to get the credentials. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="credentials" /></cellValueFactory>}
	 * 
	 * @return credentials.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getCredentials() {
		return credentials.get();
	}
	
	/**
	 * This is the query method that returns the get() method to get the bio. This method has fixed naming rules that
	 * are required by {@code <cellValueFactory><PropertyValueFactory property="bio" /></cellValueFactory>}
	 * 
	 * @return bio.get() This is the get() method so that the cellValueFactory could get the value and display the
	 * value on the scrollable table.
	 */
	public String getBio() {
		return bio.get();
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
