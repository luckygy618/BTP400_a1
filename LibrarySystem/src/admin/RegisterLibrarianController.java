package admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import guoyucao.db.manip.pack.*;



public class RegisterLibrarianController {
	/**
	 * The close button closes the window.
	 */
	@FXML
	private Button registerLibrarianClose;
	/**
	 * The save button registers the librarian in the database.
	 */
	@FXML
	private Button registerLibrarianSave;
	/**
	 * The text field which is used to collect the email address of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianEmail;
	/**
	 * The text field which is used to collect the password of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianPassword;
	/**
	 * The text field which is used to collect the first name of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianFirstName;
	/**
	 * The text field which is used to collect the last name of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianLastName;
	/**
	 * The text field which is used to collect the credentials of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianCredentials;
	/**
	 * The text field which is used to collect the bio of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianBio;
	/**
	 * The text field which is used to collect the level of the Librarian to register.
	 */
	@FXML
	private TextField registerLibrarianLevel;
	/**
	 * The label that displays messages
	 */
	@FXML
	private Label registerLibrarianDisplay;
	
	/**
	 * The registerLibrarianWindowClose() method closes the UI window when user clicks on it.
	 * When the Close button is clicked, this method will be called.
	 */
	public void registerLibrarianWindowClose() {

		Stage stage = (Stage) registerLibrarianClose.getScene().getWindow();
		stage.close();

	}
	
	/**
	 * The registerLibrarianWindowSave() method registers the new Librarian in database. It sends an insert SQL
	 * query to insert a row in the Librarian table based on the user input.
	 * 
	 *  When the corresponding button is clicked, this method will be called.
	 */
	public void registerLibrarianWindowSave() {
		// A local variable to store the value of error messages.
		String errorMessage = "";
		// A local variable to store the value of the SQL query.
		String query = "";
		
		// Check to ensure that the user did not leave the email address field empty.
		if (registerLibrarianEmail.getText() == null || registerLibrarianEmail.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Email Address!\n";
		}
		
		// Check to ensure that the user did not leave the password field empty.
		if (registerLibrarianPassword.getText() == null || registerLibrarianPassword.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Password!\n";
		}
		
		// Check to ensure that the user did not leave the first name field empty.
		if (registerLibrarianFirstName.getText() == null || registerLibrarianFirstName.getText().trim().isEmpty()) {
			errorMessage += "Please enter the First Name!\n";
		}
		
		// Check to ensure that the user did not leave the last name field empty.
		if (registerLibrarianLastName.getText() == null || registerLibrarianLastName.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Last Name!\n";
		}
		
		// Check to ensure that the user did not leave the credentials field empty.
		if (registerLibrarianCredentials.getText() == null || registerLibrarianCredentials.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Credentials!\n";
		}
		
		// Check to ensure that the user did not leave the bio field empty.
		if (registerLibrarianBio.getText() == null || registerLibrarianBio.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Biography!\n";
		}
		
		// Check to ensure that the user did not leave the level field empty.
		if (registerLibrarianLevel.getText() == null || registerLibrarianLevel.getText().trim().isEmpty()) {
			errorMessage += "Please enter the Level!\n";
		}
		
		// Check if the error message variable is empty.
		if (errorMessage.length() == 0) {
			/**
			 * If the error message variable is empty, check if the value of the email address
			 * field is equal to or greater than 254 characters
			 */
			if (registerLibrarianEmail.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Email Address cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the password
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianPassword.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Password cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the first name
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianFirstName.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Surname cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the last name
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianLastName.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Forename cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the credentials
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianCredentials.getText().length() >= 254) {
				errorMessage += "Forename cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the bio
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianBio.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Bio cannot be longer than 254 characters!\n";
			}
			
			/**
			 * If the error message variable is empty, check if the value of the level
			 * field is equal to or greater than 254 characters.
			 */
			if (registerLibrarianLevel.getText().length() >= 254) {
				/**
				 * If the value of the email address field is equal to or greater than 254
				 * characters add an error message.
				 */
				errorMessage += "Level cannot be longer than 254 characters!\n";
			}
		}
		
		// Check if the error message variable is empty.
		if (errorMessage.length() == 0) {
			/**
			 * Set local variables to store the values of the email, password, first name, last name
			 * credentials, bio and level of the Librarian which is being registered in the database.
			 */
			String email = registerLibrarianEmail.getText();
			String password = registerLibrarianPassword.getText();
			String firstName = registerLibrarianFirstName.getText();
			String lastName = registerLibrarianLastName.getText();
			String credentials = registerLibrarianCredentials.getText();
			String bio = registerLibrarianBio.getText();
			String level = registerLibrarianLevel.getText();
			
			// Set the value of the insert query.
			query = "insert into Librarian (email,password,first_name,last_name,credentials,bio,level) values ( "
					+ " \"" + email + "\",\"" + password + "\",\"" + firstName + "\","
					+ "\"" + lastName + "\",\"" + credentials + "\",\"" + bio + "\",\"" + level + "\");";
			
			// Create a select query to check whether the email address entered by the user already exists in the Admin table.
			String adminSql = "select * from Admin where email = " + "\"" + email + "\";";
			ArrayList<Object[]> adminExist = new ArrayList<Object[]>();
			// Save the result of the select query.
			adminExist = MysqlConnect.select(adminSql);
			
			// Create a select query to check whether the email address entered by the user already exists in the Librarian table.
			String librarianSql = "select * from Librarian where email = " + "\"" + email + "\";";
			ArrayList<Object[]> librarianExist = new ArrayList<Object[]>();
			// Save the result of the select query.
			librarianExist = MysqlConnect.select(librarianSql);
			
			// Create a select query to check whether the email address entered by the user already exists in the students table.
			String studentSql = "select * from students where email = " + "\"" + email + "\";";
			ArrayList<Object[]> studentExist = new ArrayList<Object[]>();
			// Save the result of the select query.
			studentExist = MysqlConnect.select(studentSql);
			
			// Check if the number of items returned by the select queries on the Admin, Librarian and students tables are greater than 0.
			if (adminExist.size() > 0 || librarianExist.size() > 0 || studentExist.size() > 0) {
				/**
				 * If the number of items returned by the select queries on the Admin, Librarian or students tables
				 * is greater than 0, add an error message and display all error messages.
				 */
				errorMessage += "Entered Email Address already exists! Please use a different Email Address!";
				registerLibrarianDisplay.setText(errorMessage);
			} else {
				/**
				 * If the number of items returned by the select queries on the Admin, Librarian or Student databases
				 * is not greater than 0, run the insert query and store the result.
				 */
				boolean insertResult = MysqlConnect.insert(query);
				// Check if the result of the insert query is successful.
				if (insertResult == true) {
					// If the select query is successful, display a success message.
					registerLibrarianDisplay.setText("The Librarian is successfully registered!");
				} else {
					// If the select query is not successful, display an error message.
					registerLibrarianDisplay.setText("Registering Librarian Failed! Please check internet connection.");
				}
			}

		} else {
			// Display any existing error messages.
			registerLibrarianDisplay.setText(errorMessage);
			// Clear the errorMessage variable.
			errorMessage = "";
		}

	}

}
