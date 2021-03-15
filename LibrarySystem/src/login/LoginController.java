package login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import librarianUI.MyController;
import student.StudentController;

import java.util.ArrayList;

import admin.AdminController;
import guoyucao.db.manip.pack.MysqlConnect;

/**
 * The class called LoginController allows users to login by collecting an email and password from the user and
 * validating their account by checking whether the provided email exists in the database and whether it is
 * associated with the provided password using SQL select queries.
 * 
 * @author Tim Lai
 * @version 1.0
 * @since 1.0
 */
public class LoginController {
	/**
	 * The close button closes the window.
	 */
	@FXML
	private Button loginClose;
	/**
	 * The login button calls the loginWindowLogin() method when clicked, to validate the user's email and password
	 * in the database and if it successfully validates the user, it redirects them based on their account type.
	 */
	@FXML
	private Button login;
	/**
	 * The text field which is used to collect the email address of the account to validate.
	 */
	@FXML
	private TextField loginEmail;
	/**
	 * The text field which is used to collect the password of the account to validate.
	 */
	@FXML
	private TextField loginPassword;
	/**
	 * The label that displays messages
	 */
	@FXML
	private Label loginDisplay;
	
	/**
	 * The loginWindowClose() method closes the UI window when user clicks on it.
	 * When the Close button is clicked, this method will be called.
	 */
	public void loginWindowClose() {

		Stage stage = (Stage) loginClose.getScene().getWindow();
		stage.close();

	}
	
	/**
	 * The loginWindowLogin() method validates a user's email and password to ensure that the user exists
	 * in the database and then redirects the user based on their account type. It sends select SQL queries
	 * to check whether a user with the provided email address exists in the Admin, Librarian or students
	 * tables and then checks whether the provided password is associated with that user if it does.
	 * 
	 *  When the corresponding button is clicked, this method will be called.
	 */
	public void loginWindowLogin() {
		// A local variable to store the value of error messages.
		String errorMessage = "";
		// Set local variables to store the values of the email, password and account type.
		String email = "";
		String password = "";
		String accountType = "";
		
		// Check to ensure that the user did not leave the email address field empty.
		if (!(loginEmail.getText() == null || loginEmail.getText().trim().isEmpty())) {
			// If the loginEmail text field is not empty set the email variable to its value.
			email = loginEmail.getText();
		} else {
			// If the loginEmail text field is empty add an error message.
			errorMessage += "Email Address cannot be null! Please enter an Email Address!\n";
		}
		
		// Check to ensure that the user did not leave the password field empty.
		if (!(loginPassword.getText() == null || loginPassword.getText().trim().isEmpty())) {
			// If the loginPassword text field is not empty set the password variable to its value.
			password = loginPassword.getText();
		} else {
			// If the loginPassword text field is empty add an error message.
			errorMessage += "Password cannot be null! Please enter a Password!\n";
		}
		
		// Create a select query to check whether the email address entered by the user exists in the Admin table.
		String adminSql = "select * from Admin where email = " + "\"" + email + "\";";
		ArrayList<Object[]> adminExist = new ArrayList<Object[]>();
		// Save the result of the select query.
		adminExist = MysqlConnect.select(adminSql);
		
		// Create a select query to check whether the email address entered by the user exists in the Librarian table.
		String librarianSql = "select * from Librarian where email = " + "\"" + email + "\";";
		ArrayList<Object[]> librarianExist = new ArrayList<Object[]>();
		// Save the result of the select query.
		librarianExist = MysqlConnect.select(librarianSql);
		
		// Create a select query to check whether the email address entered by the user exists in the students table.
		String studentSql = "select * from students where email = " + "\"" + email + "\";";
		ArrayList<Object[]> studentExist = new ArrayList<Object[]>();
		// Save the result of the select query.
		studentExist = MysqlConnect.select(studentSql);
		
		/**
		 * Check if the number of items returned by the select queries on the Admin, Librarian or students
		 * tables are greater than 0 and if one of them has a number of items greater than 0, set the account
		 * type to the name of that table.
		 */
		if (adminExist.size() > 0) {
			accountType = "Admin";
		} else if (librarianExist.size() > 0) {
			accountType = "Librarian";
		} else if (studentExist.size() > 0) {
			accountType = "students";
		} else {
			errorMessage += "Email Address does not exist! Please use a different Email Address!\n";
		}
		
		// Create a select query to check whether the email address entered by the user exists in the accountType table.
		String accountSql = "select * from " + accountType + " where email = " + "\"" + email + "\" and password = \"" + password + "\";";
		ArrayList<Object[]> account = new ArrayList<Object[]>();
		// Save the result of the select query.
		account = MysqlConnect.select(accountSql);
		
		// Check if the number of items returned by the select query on the accountType table is greater than 0.
		if (account.size() > 0) {
			// Check if the account type is equal to Admin, Librarian or students.
			if (accountType == "Admin") {
				try {
					/**
					 * If the account type is equal to Admin, redirect the user to the Admin dashboard and pass
					 * the account data to the AdminController class.
					 */
					Stage newStage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/AdminScene.fxml"));
					AnchorPane adminPane = loader.load();
					AdminController controller = loader.getController();
					controller.initAccount(account);
					Scene s = new Scene(adminPane, 500, 500);
					newStage.setTitle("Admin System");
					newStage.setScene(s);
					newStage.show();
					
					loginWindowClose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accountType == "Librarian") {
				try {
					/**
					 * If the account type is equal to Librarian, redirect the user to the Librarian dashboard and pass
					 * the account data to the MyController class.
					 */
					Stage newStage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/librarianUI/MyScene.fxml"));
					AnchorPane librarianPane = loader.load();
					MyController controller = loader.getController();
					controller.initAccount(account);
					Scene s = new Scene(librarianPane, 500, 500);
					newStage.setTitle("Librarian System");
					newStage.setScene(s);
					newStage.show();
					
					loginWindowClose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accountType == "students") {
				try {
					/**
					 * If the account type is equal to students, redirect the user to the students dashboard and pass
					 * the account data to the StudentController class.
					 */
					Stage newStage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/StudentHome.fxml"));
					AnchorPane studentPane = loader.load();
					StudentController controller = loader.getController();
					controller.initAccount(account);
					Scene s = new Scene(studentPane, 750, 700);
					newStage.setScene(s);
					newStage.setTitle("Student System");
					newStage.show();
					
					loginWindowClose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			// If the provided password is not associated with the provided email address, add an error message.
			errorMessage += "Password does not exist! Please use a different Password!\n";
		}
		
		// Check if the error message variable is empty.
		if (errorMessage.length() == 0) {
			// If the error message variable is empty, set a success message.
			loginDisplay.setText("You have successfully logged in as " + email + "!");
		} else {
			// Display any existing error messages.
			loginDisplay.setText(errorMessage);
			// Clear the errorMessage variable.
			errorMessage = "";
		}

	}
}
