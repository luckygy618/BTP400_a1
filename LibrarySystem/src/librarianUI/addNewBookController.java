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
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import guoyucao.db.manip.pack.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This is a class called addNewBookController that contains all the event listener methods of the Add New Book UI page.There are some buttons on this
 * page, and when the buttons get clicked, then the matching UI page will pop up. 
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class addNewBookController {
	/**
	 * This is the close button
	 */
	@FXML
	private Button addBookClose;
/**
 *  This is save button
 */
	@FXML
	private Button addBookSave;
/**
 * This is the text field of Librarian ID
 */
	@FXML
	private TextField addBookLibrarianID;
	/**
	 * This is the text field of ISBN
	 */
	@FXML
	private TextField addBookISBN;
	/**
	 * This is the text field of bokk title
	 */
	@FXML
	private TextField addBookTitle;
	/**
	 * This is the text field of author
	 */
	@FXML
	private TextField addBookAuthor;
	/**
	 * This is the text field of published year
	 */
	@FXML
	private TextField addBookPublishedDate;
	/**
	 * This is the text field of book description
	 */
	@FXML
	private TextField addBookDescription;
	/**
	 * This is the label that displays error messages
	 */
	@FXML
	private Label addBookDisplay;
	
	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	
	public void addNewBookClose() {

		Stage stage = (Stage) addBookClose.getScene().getWindow();
		stage.close();

	}

	/**
	 * This is the function that saves the new book into database. It uses a insert sql query to insert a row in the Books  table based on the 
	 * user input.
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	
	public void addNewBookSave() {
	
		String errorMessage = "";
		String query = "";
		String libID = "";
	
		int pub_year = -1;
		
		if(!(addBookLibrarianID.getText() == null || addBookLibrarianID.getText().trim().isEmpty())) {
			 libID = addBookLibrarianID.getText();
		}else {
			errorMessage += "Librarian ID cannot be null! Please enter Librarian ID!\n";
		}
		
		ArrayList<Object[]> sqlResult =new ArrayList<Object[]>();
		if(libID.length()>0) {
		String checkLibID = "select * from Librarian where librarian_id = " + "\"" + libID + "\"";
		 sqlResult = MysqlConnect.select(checkLibID);
		}
		if (sqlResult.size() <= 0) {
			errorMessage += "Librarian ID is not valiad, please enter valid Librarian ID!\n";
		}

		if (addBookISBN.getText() == null || addBookISBN.getText().trim().isEmpty()) {
			errorMessage += "Please enter ISBN!\n";
		}
		if (addBookTitle.getText() == null || addBookTitle.getText().trim().isEmpty()) {
			errorMessage += "Please enter Book Title!\n";
		}
		if (addBookAuthor.getText() == null || addBookAuthor.getText().trim().isEmpty())
		 {
			errorMessage += "Please enter Book Author!\n";
		}
		
		if (addBookPublishedDate.getText() == null || addBookPublishedDate.getText().trim().isEmpty())
		 {
			errorMessage += "Please enter Published Year!\n";
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		LocalDateTime now = LocalDateTime.now();
		int currentYear = -1;
			
		if(!(addBookPublishedDate.getText() == null || addBookPublishedDate.getText().trim().isEmpty())) {
		try{
			pub_year =Integer.parseInt(addBookPublishedDate.getText());
			currentYear = Integer.parseInt(dtf.format(now));
			}catch(NumberFormatException e){
				pub_year = -1;
				currentYear =-1;
				errorMessage += "Published Year ID must be numbers! Please enter numeric Published Year!\n";
			}
		}

		if(errorMessage.length()==0) {
		if (addBookISBN.getText().length() >= 254) {
			errorMessage += "ISBN cannot be longer than 254 characters!\n";
		}
		if (addBookTitle.getText().length() >= 254) {
			errorMessage += "Book Title cannot be longer than 254 characters!\n";
		}
		if (addBookAuthor.getText().length() >= 254) {
			errorMessage += "Book Author cannot be longer than 254 characters!\n";
		}
		

		
		if (pub_year > currentYear && pub_year < 1900&&currentYear>-1) {
			errorMessage += "Published Year must between 1900 to " + currentYear +  "!\n";
		}
		if (addBookDescription.getText().length() >= 254) {
			errorMessage += "Description cannot be longer than 254 characters!\n";
		}
		}
		
		if (errorMessage.length()==0) {
			String isbn=addBookISBN.getText();
			String title=addBookTitle.getText();
			String author=addBookAuthor.getText();
			int publicYear = Integer.parseInt(addBookPublishedDate.getText());
			String description = addBookDescription.getText();
			String librarianID = addBookLibrarianID.getText();
		
			query = "insert into Books (ISBN,title,author,available,borrowed_by_id,hold_by_id,description,issued_by_id,retrived_by_librian_id,published_year) values ( "
					+" \"" +isbn+"\"," + "\"" + title +"\"," + "\"" + author +"\"," + "true," + "null,null," + "\"" + description
					+ "\",null," + "\"" +librarianID +"\"," +  publicYear + ");";
	
			String checkSql ="select * from Books where ISBN = " + "\"" + isbn  +"\";";
			 ArrayList<Object[]>   checkExist = new  ArrayList<Object[]>();
			 checkExist = MysqlConnect.select(checkSql);
			if (checkExist.size()>0) {
				errorMessage += "Entered ISBN already exist! Please check!";
				addBookDisplay.setText(errorMessage);
			}else {
				boolean   insertresult = MysqlConnect.insert(query);
				if(insertresult==true) {
					addBookDisplay.setText("The Book is successfully added!");
				}else {
					addBookDisplay.setText("Add Book Failed! Please check internet connection.");
				}
			}
		
		}else {
			addBookDisplay.setText(errorMessage);
			errorMessage="";
		}
		

	}

}
