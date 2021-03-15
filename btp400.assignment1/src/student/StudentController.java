package student;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import accounts.StudentRow;
import guoyucao.db.manip.pack.MysqlConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The StudentController class. This class contains the controller for the home page.
 * 
 * @author Neil An
 * @version 1.0
 * @since 1.0
 */
public class StudentController implements Initializable {

	//Search button.
	@FXML
	private Button search;

	//Request book button.
	@FXML
	private Button request;

	//View borrowed books button.
	@FXML
	private Button borrowed;

	//Text field to read user inputted book title.
	@FXML
	private TextField uBook;

	//Table to hold book info.
	@FXML
	private TableView<Row> tableViewPane;
	
	//Label to show result of search.
	@FXML
	private Label result;
	
	//Label to hold account name.
	@FXML
	private Label accountName;
	
	//Label to hold account email.
	@FXML
	private Label accountEmail;
	
	//Label to hold account level.
	@FXML
	private Label accountLevel;
	
	//Scrollable to scroll through table.
	@FXML
	private AnchorPane viewAllBooksScrollAnPane;
	
	//Data from sql call.
	private final ObservableList<Row> data = FXCollections.observableArrayList();

	/**
	 * initialize method is called after all @FXML members are injected. Nothing
	 * needs to be initialized in this case.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Don't need to add anything here.
	}
	
	/**
	 * initAccount method is used to initialize the account data after the user logins.
	 * This data is then displayed on the page.
	 * 
	 * @param account An ArrayList of Object arrays which stores the user's account information collected
	 * from the database.
	 */
	public void initAccount(ArrayList<Object[]> account) {
		//Create an ArrayList of ArrayList<String> to hold the data.
		ArrayList<ArrayList<String>> accountRecord = new ArrayList<ArrayList<String>>();
		//For Object[] in account, and for Object in Object[], extract the string from
		//the data and add it to the accountRecord.
		for (Object[] objArray : account) {
			ArrayList<String> accountData = new ArrayList<String>();
			for (Object obj : objArray) {

				String str = obj + "";
				accountData.add(str);
			}
			accountRecord.add(accountData);
			
			//Create a StudentRow using the account data.
			StudentRow accountRow = new StudentRow(accountData.get(1), accountData.get(2), accountData.get(3), accountData.get(4), accountData.get(5), accountData.get(6));
			
			//Set the text on the page using the account data.
			this.accountName.setText("Welcome " + accountRow.getForename() + " " + accountRow.getSurname());
			this.accountEmail.setText("Email: " + accountRow.getEmail());
			this.accountLevel.setText("Level: " + accountRow.getLevel());
		}
	}

	/**
	 * searchBook method is called whenever the user clicks the search button. This method
	 * will get the user inputted book title and return a list of books that match the title.
	 * The results will be shown in the table on the page.
	 * @param event The event which triggers this method
	 */
	public void searchBook(ActionEvent event) {
		//String to hold the user inputted book title.
		String inBook = "";
		//String to hold an error message.
		String error = "";
		
		//Array to hold the data from a sql call.
		ArrayList<Object[]> resultList = new ArrayList<Object[]>();
		
		//Clear the items on the table.
		tableViewPane.getItems().clear();
		
		//Get the user inputted book title if if the text field is not empty.
		if (!(uBook.getText() == null || uBook.getText().trim().isEmpty())) {
			inBook = uBook.getText();
		}

		//Check if there is user input.
		if (inBook.length() > 0) {
			//Use sql to select all books that match the user inputted title.
			String bookQuery = "select title,author,description,ISBN,published_year,available "
					+ "from Books where title = " + "\"" + inBook + "\"";
			//Hold the matching results.
			resultList = MysqlConnect.select(bookQuery);
			//ArrayList to hold records from sql call.
			ArrayList<ArrayList<String>> strRecord = new ArrayList<ArrayList<String>>();
			//For Object[] in resultList, and for Object in Object[], extract the string from
			//the data and add it to strRecord.
			for (Object[] objArray : resultList) {
				ArrayList<String> strList = new ArrayList<String>();
				for (Object obj : objArray) {

					String str = obj + "";
					strList.add(str);
				}
				strRecord.add(strList);
			}
			//For ArrayList<String> strObj in strRecord, create a new Row containing the data
			//from strObj. 
			for (ArrayList<String> strObj : strRecord) {
				Row newRow = new Row(strObj.get(0), strObj.get(1), strObj.get(2), strObj.get(3),
						strObj.get(4), strObj.get(5));
				data.add(newRow);
			}
			//Update the table to show the results.
			tableViewPane.setItems(data);
		}
		//If no results are found, create and set an error message.
		if (resultList.size() <= 0) {
			error += "Book title is not valid.";
			result.setText(error);
		}
	}

	/**
	 * showRequest is called whenever the "Issue a Request" button is clicked. This method
	 * will create and show a new stage for the request page.
	 * @param event The event which triggers this method
	 */
	public void showRequest(ActionEvent event) {
		try {
			Stage stage = new Stage();
			AnchorPane requestPane = FXMLLoader.load(getClass().getResource("ShowRequest.fxml"));
			Scene s = new Scene(requestPane, 500, 300);
			stage.setTitle("Request an Issue");
			stage.setScene(s);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * viewBorrowed is called whenever the "View Borrowed" button is clicked. This method
	 * will create and show a new stage for the borrowed page.
	 * @param event The event which triggers this method
	 */
	public void viewBorrowed(ActionEvent event) {
		try {
			Stage stage = new Stage();
			AnchorPane borrowedPane = FXMLLoader.load(getClass().getResource("ViewBorrowed.fxml"));
			Scene s = new Scene(borrowedPane, 700, 700);
			stage.setTitle("View Borrowed Books");
			stage.setScene(s);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}