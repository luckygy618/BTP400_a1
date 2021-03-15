package admin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import guoyucao.db.manip.pack.*;

/**
 * The DeleteLibrarianController contains methods to allow Admin users to delete Librarians.
 * 
 * @author Tim Lai
 * @version 1.0
 * @since 1.0
 */
public class DeleteLibrarianController {
	/**
	 * The close button closes the window.
	 */
	@FXML
	private Button deleteLibrarianClose;
	/**
	 * The delete Librarian button that deletes the librarian from the database.
	 */
	@FXML
	private Button deleteLibrarianDelete;
	/**
	 * The text field which is used to collect the email of the Librarian to delete.
	 */
	@FXML
	private TextField deleteLibrarianEmail;
	/**
	 * The label that displays messages
	 */
	@FXML
	private Label deleteLibrarianDisplay;
	
	/**
	 * The deleteLibrarianWindowClose() method closes the UI window when user clicks on it.
	 * When the Close button is clicked, this method will be called.
	 */
	public void deleteLibrarianWindowClose() {

		Stage stage = (Stage) deleteLibrarianClose.getScene().getWindow();
		stage.close();

	}
	
	/**
	 * The deleteLibrarianWindowDelete() method sends a delete SQL query to the database to delete
	 * the librarian from the database.
	 * 
	 * When the corresponding button is clicked, this method will be called.
	 */
	public void deleteLibrarianWindowDelete() {
		// Set a local variable to store the value of error messages.
		String errorMessage = "";
		// Set a local variable to store the value of the email address of the Librarian to delete.
		String libEmail = "";
		
		// Check to ensure that the user did not leave the email address field empty.
		if (!(deleteLibrarianEmail.getText() == null || deleteLibrarianEmail.getText().trim().isEmpty())) {
			// If the deleteLibrarianEmail text field is not empty set the libEmail variable to its value.
			libEmail = deleteLibrarianEmail.getText();
			
			// Create a select query to check whether a Librarian with the provided email address exists in the database.
			String checkSql = "select * from Librarian where email = " + "\"" + libEmail + "\";";
			ArrayList<Object[]> checkExist = new ArrayList<Object[]>();
			// Run the select query.
			checkExist = MysqlConnect.select(checkSql);
			
			// If there are no Librarians with the provided email address, add an error message.
			if (!(checkExist.size() > 0)) {
				errorMessage += "Entered Email Address does not exist! Please use a different Email Address!";
			}
		} else {
			// If the deleteLibrarianEmail text field is empty add an error message.
			errorMessage += "Librarian Email cannot be null! Please enter Librarian Email!\n";
		}
		
		// Check if the error message variable is empty.
		if (errorMessage.length() == 0) {
			// If the error message variable is empty, create a delete SQL query.
			String query = "delete from Librarian where email=\"" + libEmail + "\";";
			
			// Store the result of the delete SQL query.
			boolean deleteResult = MysqlConnect.delete(query);
			// Check if the result of the delete query is successful.
			if (deleteResult == true) {
				// If the result of the delete query is successful set a success message.
				deleteLibrarianDisplay.setText("The Librarian \"" + libEmail + "\" was successfully deleted!");
			} else {
				// If the result of the delete query is not successful set a failure message.
				deleteLibrarianDisplay.setText("Deleting the Librarian failed! Please check internet connection.");
			}

		} else {
			// Display any existing error messages.
			deleteLibrarianDisplay.setText(errorMessage);
			// Clear the errorMessage variable.
			errorMessage = "";
		}

	}

}
