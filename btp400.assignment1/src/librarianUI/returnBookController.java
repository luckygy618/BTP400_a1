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

import guoyucao.db.manip.pack.MysqlConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is a class called returnBookController, it has the UI page of return books, and when the buttons get clicked, then the matching UI page will pop up. 
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class returnBookController {
	/**
	 * This is the close button
	 */
	@FXML
	private Button returnBookClose;
	/**
	 * This is the return book button
	 */
	@FXML
	private Button returnBookReturn;
	/**
	 * This is the label that dispays error message 
	 */
	@FXML
	private Label returnBookDisplay;
	
	/**
	 * This is the text field of book id
	 */
	@FXML
	private TextField returnBookBookID;
	/**
	 * This is the text field of librarian id
	 */
	@FXML
	private TextField returnBookLibrarianID;
	

	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	public void returnBookWindowClose() {
		
		  Stage stage = (Stage) returnBookClose.getScene().getWindow();
          stage.close();
		
	}
	
	/**
	 * This is the return book method. It uses update sql to change the book's status to be retrieved and available 
	 */
	public void returnBookWindowReturn() {
		
		String errorMessage = "";
		String libID = "";
		String bookID = "";
		
		if(!(returnBookLibrarianID.getText() == null || returnBookLibrarianID.getText().trim().isEmpty())) {
			libID = returnBookLibrarianID.getText();
		}else {
			errorMessage += "Librarian ID cannot be null! Please enter Librarian ID!\n";
		}
		
		if(!(returnBookBookID.getText() == null || returnBookBookID.getText().trim().isEmpty())) {
			bookID = returnBookBookID.getText();
			
		}else {
			errorMessage += "Book ID cannot be null! Please enter Book ID!\n";
		}
		
		errorMessage += VerifyTool.isLibrarianIDExist(libID);
		errorMessage += VerifyTool.isBookIDExist(bookID);
		
		if (errorMessage.length()==0) {
			String sql= "update Books set borrowed_by_id=null,issued_by_id=null, hold_by_id=null,available=true,retrived_by_librian_id=" + libID  +" where book_id=" +bookID;
		
			boolean updateresult = MysqlConnect.update(sql);
			if(updateresult==true) {
				returnBookDisplay.setText("The Book " + bookID+ "is successfully returned by Librarian " + libID + "!" );
			}else {
				returnBookDisplay.setText("Return Book Failed! Please check internet connection.");
			}
			
			
		}else {
			returnBookDisplay.setText(errorMessage);
			errorMessage="";	
		}
	}

		
}
	
	
	
	
	
	
	
	
	
	
	
	

