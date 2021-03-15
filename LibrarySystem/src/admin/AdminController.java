package admin;

import java.util.ArrayList;

import accounts.AdminRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AdminController {
	/**
	 * The Logout button on the UI
	 */
	@FXML
	private Button logout;
	/**
	 * The Register Librarian button in the UI
	 */
	@FXML
	private Button registerLibrarianBtn;
	/**
	 * The Delete Librarian button in the UI
	 */
	@FXML
	private Button deleteLibrarianBtn;
	/**
	 * The View All Librarians button in the UI
	 */
	@FXML
	private Button viewAllLibrariansBtn;
	/**
	 * The Account Name label in the UI
	 */
	@FXML
	private Label accountName;
	/**
	 * The Account Email label in the UI
	 */
	@FXML
	private Label accountEmail;
	/**
	 * The Account Level label in the UI
	 */
	@FXML
	private Label accountLevel;
	
	/**
	 * initAccount method is used to initialize the account data after the user logins.
	 * This data is then displayed on the page.
	 * 
	 * @param account An ArrayList of Object arrays which stores the user's account information collected
	 * from the database.
	 */
	public void initAccount(ArrayList<Object[]> account) {
		// Create an ArrayList of ArrayList<String> to hold the data.
		ArrayList<ArrayList<String>> accountRecord = new ArrayList<ArrayList<String>>();
		// For Object[] in account, and for Object in Object[], extract the string from
		// the data and add it to the accountRecord.
		for (Object[] objArray : account) {
			ArrayList<String> accountData = new ArrayList<String>();
			for (Object obj : objArray) {

				String str = obj + "";
				accountData.add(str);
			}
			accountRecord.add(accountData);
			
			// Create a StudentRow using the account data.
			AdminRow accountRow = new AdminRow(accountData.get(1), accountData.get(2), accountData.get(3), accountData.get(4), accountData.get(5));
			
			// Set the text on the page using the account data.
			this.accountName.setText("Welcome " + accountRow.getFirstName() + " " + accountRow.getLastName());
			this.accountEmail.setText("Email: " + accountRow.getEmail());
			this.accountLevel.setText("Level: " + accountRow.getLevel());
		}
	}
	
	/**
	 * The logoutFn() method closes the UI page when user click on the Logout button and returns the user to the
	 * Login page when the button is clicked.
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */
	public void logoutFn(ActionEvent event) {
		// Use try catch to catch the potential exception
		try {
			// Create a new stage for the Login window.
			Stage newStage = new Stage();
			/**
			 * Create a AnchorPane called addBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane registerLibrarianPane = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
			// Create a new Scene to hold the fxml file and then display this scene.
			Scene s = new Scene(registerLibrarianPane, 500, 500);
			newStage.setScene(s);
			newStage.setTitle("Login to System");
			newStage.show();
			
			// Collect the value of the current window and close it.
			Stage oldStage = (Stage) logout.getScene().getWindow();
			oldStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The registerLibrarian() method shows the Register Librarian UI page when user click on it the corresponding
	 * button.
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will
	 * be called.
	 */
	public void registerLibrarian(ActionEvent event) {
		// Use try catch to catch the potential exception 
		try {
			// Create a new stage.
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called addBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane registerLibrarianPane = FXMLLoader.load(getClass().getResource("/admin/RegisterLibrarian.fxml"));
			// Create a new Scene to hold the fxml file and then display this scene.
			Scene s = new Scene(registerLibrarianPane, 500, 500);
			stage.setScene(s);
			stage.setTitle("Register New Librarian");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The deleteLibrarian() method shows the Delete Librarian UI page when user click on it the corresponding
	 * button.
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will
	 * be called.
	 */
	public void deleteLibrarian(ActionEvent event) {
		// Use try catch to catch the potential exception
		try {
			// Create a new stage.
			Stage stage = new Stage();
			AnchorPane deleteLibrarianPane = FXMLLoader.load(getClass().getResource("/admin/DeleteLibrarian.fxml"));
			// Create a new Scene to hold the fxml file and then display this scene.
			Scene s = new Scene(deleteLibrarianPane, 500, 500);
			stage.setScene(s);
			stage.setTitle("Delete Librarian");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The viewAllLibrarians() method shows the View All Librarians UI page when user click on it the corresponding
	 * button.
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will
	 * be called.
	 */
	public void viewAllLibrarians(ActionEvent event) {
		// Use try catch to catch the potential exception
		try {
			// Create a new stage.
			Stage stage = new Stage();
			AnchorPane viewAllLibrariansPane = FXMLLoader.load(getClass().getResource("/admin/ViewAllLibrarians.fxml"));
			// Create a new Scene to hold the fxml file and then display this scene.
			Scene s = new Scene(viewAllLibrariansPane, 500, 500);
			stage.setScene(s);
			stage.setTitle("View All Librarians");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
