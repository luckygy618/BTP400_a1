/**********************************************
Assignment 1
Course:<BTP400> - Winter 2021
Last Name:<Cao>
First Name:<GuoYu>
ID:<061341145>
Section:<NAA>
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature: GuoYuCao
Date:<2021-03-10>
**********************************************/

package librarianUI;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import accounts.LibrarianRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This is a class called MyController that contains all the event listener methods of the Librarian System UI page.There are some buttons on this
 * page, and when the buttons get clicked, then the matching UI page will pop up. 
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class MyController implements Initializable {
	
	/**
	 * This is the default initialize method of the Initializable interface
	 * 
	 * @param arg0  The URL 
	 * @param arg1  The ResourceBundle
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * The Logout button on the UI
	 */
	@FXML
	private Button Logout;
	/**
	 * The Add New Book button on the UI
	 */
	@FXML
	private Button addBookBtn;
	/**
	 * The Hold Book button on the UI
	 */
	@FXML
	private Button holdBookBtn;
	/**
	 * The Issue Book button on the UI
	 */
	@FXML
	private Button issueBookBtn;
	
	/**
	 * The Return Book button on the UI
	 */
	@FXML
	private Button returnBookBtn;
	/**
	 * The View All Book button on the UI
	 */
	@FXML
	private Button viewAllBookBtn;
	/**
	 * The Label on the UI that displays text content
	 */
	@FXML
	private Label labelDisplay;
	
	@FXML
	private Label accountName;
	@FXML
	private Label accountEmail;
	@FXML
	private Label accountLevel;

	/**
	 * The Label on the UI that displays text content
	 */
	
	/**
	 * initAccount method is used to initialize the account data after the user logins.
	 * This data is then displayed on the page.
	 * 
	 * @param account An ArrayList of Object arrays which stores the user's account information collected
	 * from the database.
	 */
	public void initAccount(ArrayList<Object[]> account) {
		ArrayList<ArrayList<String>> accountRecord = new ArrayList<ArrayList<String>>();
		for (Object[] objArray : account) {
			ArrayList<String> accountData = new ArrayList<String>();
			for (Object obj : objArray) {

				String str = obj + "";
				accountData.add(str);
			}
			accountRecord.add(accountData);
			
			LibrarianRow accountRow = new LibrarianRow(accountData.get(1), accountData.get(2), accountData.get(3), accountData.get(4), accountData.get(5), accountData.get(6), accountData.get(7));
			
			this.accountName.setText("Welcome " + accountRow.getFirstName() + " " + accountRow.getLastName());
			this.accountEmail.setText("Email: " + accountRow.getEmail());
			this.accountLevel.setText("Level: " + accountRow.getLevel());
		}
	}
	
	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */

	// When user click on myButton
	// this method will be called.
	public void LogOutFn(ActionEvent event) {
		/**
		 * Create a state object to get the current window.
		 * Then use close() method to close the current window.
		 */
		Stage stage = (Stage) Logout.getScene().getWindow();
		stage.close();

	}


	/**
	 * This is the function that shows the Add new book UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */

	public void addNewBook(ActionEvent event) {
		/**
		 * Use try catch to catch the potential exception 
		 */
		try {
			/**
			 * Create a new state.
			 */
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called addBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane addBookPane = FXMLLoader.load(getClass().getResource("/librarianUI/addNewBook.fxml"));
			Scene s = new Scene(addBookPane, 500, 500);
			stage.setTitle("Add New Book");
			stage.setScene(s);
			/**
			 * Create a new Scene to hold the fxml file and then display this scene.
			 */
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * This is the function that shows the Hold Book UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */

	public void holdBook(ActionEvent event) {
		try {
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called holdBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane holdBookPane = FXMLLoader.load(getClass().getResource("/librarianUI/holdBook.fxml"));
			Scene s = new Scene(holdBookPane, 500, 500);
			stage.setScene(s);
			stage.setTitle("Hold Book");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This is the function that shows the Issue Book UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */
	public void issueBookFn(ActionEvent event) {
		try {
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called issueBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane issueBookPane = FXMLLoader.load(getClass().getResource("/librarianUI/issueBook.fxml"));
			Scene s = new Scene(issueBookPane, 500, 500);
			stage.setScene(s);
			stage.setTitle("Issue Book");
			stage.show();
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	/**
	 * This is the function that shows the Return Book UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */
	public void returnBook(ActionEvent event) {
		try {
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called returnBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane returnBookPane = FXMLLoader.load(getClass().getResource("/librarianUI/returnBook.fxml"));
			Scene s = new Scene(returnBookPane, 500, 500);
			stage.setScene(s);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This is the function that shows the View All Book UI page when user click on it
	 * 
	 * @param event the ActionEvent object that listening the button. When the button is clicked, this method will be called.
	 */
	public void viewAllBooks(ActionEvent event) {
		try {
			Stage stage = new Stage();
			/**
			 * Create a AnchorPane called viewBookPane to hold the content.
			 * And use FXMLLoader.load to load the fxml file from the source folder.
			 * The fxml file contains all the UI components include labels, text field, and buttons,etc.
			 */
			AnchorPane viewBookPane = FXMLLoader.load(getClass().getResource("/librarianUI/viewAllBooks.fxml"));
			Scene s = new Scene(viewBookPane, 500, 500);
			stage.setScene(s);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
