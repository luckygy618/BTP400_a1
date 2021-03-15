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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import guoyucao.db.manip.pack.*;

/**
 * This is a class called holdBookController that contains all the event listener methods of the Hold Book UI page.
 * There are some buttons on this page, and when the buttons get clicked, then the matching UI page will pop up. 
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class holdBookController {
	/**
	 * This is the close button
	 */
	@FXML
	private Button holdBookClose;
	/**
	 * This is the hold book button that saves the change 
	 */
	@FXML
	private Button holdBookHold;
	/**
	 * This is the text field of book id
	 */
	@FXML
	private TextField holdBookBookID;
	/**
	 * This is the text field of librarian id
	 */
	@FXML
	private TextField holdBookLibrarianID;
	/**
	 * This is the label that displays the error message
	 */
	@FXML
	private Label holdBookDisplay;

	
	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	public void holdBookWindowClose() {

		Stage stage = (Stage) holdBookClose.getScene().getWindow();
		stage.close();

	}

	/**
	 * This is the method that send a update sql query to the database to change the books' status to on hold, available to false.
	 * So that the book is on holding by the librarian.
	 */
	public void holdBookWindowHold() {

		String errorMessage = "";
		String libID = "";
		String bookID = "";

		if (!(holdBookLibrarianID.getText() == null || holdBookLibrarianID.getText().trim().isEmpty())) {
			libID = holdBookLibrarianID.getText();
		} else {
			errorMessage += "Librarian ID cannot be null! Please enter Librarian ID!\n";
		}

		if (!(holdBookBookID.getText() == null || holdBookBookID.getText().trim().isEmpty())) {
			bookID = holdBookBookID.getText();

		} else {
			errorMessage += "Book ID cannot be null! Please enter Book ID!\n";
		}

		errorMessage += VerifyTool.isLibrarianIDExist(libID);
		errorMessage += VerifyTool.isBookIDAvailable(bookID);

		if (errorMessage.length() == 0) {
			String sql = "update Books set hold_by_id=" + libID + " ,available=false,retrived_by_librian_id=null "
					+ " where book_id=" + bookID;

			boolean updateresult = MysqlConnect.update(sql);
			if (updateresult == true) {
				holdBookDisplay.setText("The Book is successfully holded by Librarian " + libID + "!");
			} else {
				holdBookDisplay.setText("Hold Book Failed! Please check internet connection.");
			}

		} else {
			holdBookDisplay.setText(errorMessage);
			errorMessage = "";
		}

	}

}
