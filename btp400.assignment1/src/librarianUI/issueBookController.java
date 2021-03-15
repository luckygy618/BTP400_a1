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
 * This is a class called issueBookController, it has the UI page of issue books, and when the buttons get clicked, then the matching UI page will pop up. 
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class issueBookController {
	/**
	 * This is the close button
	 */
	@FXML
	private Button issueBookClose;
	/**
	 * This is the issue book button
	 */
	@FXML
	private Button issueBookIssue;
	/**
	 * This is the label that displays the error message
	 */
	@FXML
	private Label issueBookDisplay;
	/**
	 * This is the text field of book id
	 */
	@FXML
	private TextField issueBookBookID;
	/**
	 * This is the text field of librarian id
	 */
	@FXML
	private TextField issueBookLibrarianID;
	/**
	 * This is the text field of student id
	 */
	@FXML
	private TextField issueBookStudentID;

	
	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	public void issueBookWindowClose() {

		Stage stage = (Stage) issueBookClose.getScene().getWindow();
		stage.close();

	}

	/**
	 * This is the issue book method that uses update query to set the books' status to "borrow_by_id" with the value of student id
	 * to express the librarian issued the book to students
	 */
	public void issueBookWindowIssue() {
		String errorMessage = "";
		String libID = "";
		String bookID = "";
		String studentID = "";
		if (!(issueBookLibrarianID.getText() == null || issueBookLibrarianID.getText().trim().isEmpty())) {
			libID = issueBookLibrarianID.getText();
		} else {
			errorMessage += "Librarian ID cannot be null! Please enter Librarian ID!\n";
		}

		if (!(issueBookStudentID.getText() == null || issueBookStudentID.getText().trim().isEmpty())) {
			studentID = issueBookStudentID.getText();
		} else {
			errorMessage += "Student ID cannot be null! Please enter Student ID!\n";
		}

		if (!(issueBookBookID.getText() == null || issueBookBookID.getText().trim().isEmpty())) {
			bookID = issueBookBookID.getText();

		} else {
			errorMessage += "Book ID cannot be null! Please enter Book ID!\n";
		}

		errorMessage += VerifyTool.isLibrarianIDExist(libID);
		errorMessage += VerifyTool.isBookIDAvailable(bookID);
		errorMessage += VerifyTool.isStudentIDExist(studentID);

		if (errorMessage.length() == 0) {
			String sql = "update Books set borrowed_by_id=" + studentID + " ,available=false, " + " issued_by_id="
					+ libID + ",retrived_by_librian_id=null " + " where book_id=" + bookID;

			boolean updateresult = MysqlConnect.update(sql);
			if (updateresult == true) {
				issueBookDisplay.setText(
						"The Book is successfully issue by Librarian " + libID + " to Student " + studentID + "!");
			} else {
				issueBookDisplay.setText("Issue Book Failed! Please check internet connection.");
			}

		} else {
			issueBookDisplay.setText(errorMessage);
			errorMessage = "";
		}

	}

}
