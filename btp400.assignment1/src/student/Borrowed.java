package student;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import guoyucao.db.manip.pack.MysqlConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The Borrowed class. This class contains the controller for the view borrowed books page.
 * 
 * @author Neil An
 * @version 1.0
 * @since 1.0
 */
public class Borrowed {
	//Search button.
	@FXML
	private Button searchBorrowButton;
	
	//Scrollable to scroll through table.
	@FXML
	private AnchorPane viewAllBooksScrollAnPane;
	
	//Table to hold borrowed book info.
	@FXML
	private TableView<Row> tableViewPane;
	
	//Text field to read user inputted student id.
	@FXML
	private TextField studentID;
	
	//Label to show any errors.
	@FXML
	private Label errorMessage;
	
	//Data from sql call.
	private final ObservableList<Row> data = FXCollections.observableArrayList();
	
	/**
	 * searchBorrowed is called when the search button is clicked. This method will get results from a
	 * sql call based on the users input and display the results in the table on the page. In addition,
	 * this function will generate an output file containing the borrowed books info.
	 */
	public void searchBorrowed()
	{
		//String to hold the user inputted student id.
		String inID = "";
		//String to hold an error message.
		String error = "";

		//Array to hold the data from a sql call.
		ArrayList<Object[]> resultList = new ArrayList<Object[]>();
		
		//Clear the items on the table.
		tableViewPane.getItems().clear();
		
		//Set the error message to show nothing.
		errorMessage.setText("");
		
		//Get the user inputted student id if if the text field is not empty.
		if (!(studentID.getText() == null || studentID.getText().trim().isEmpty())) {
			inID = studentID.getText();
		}

		//Check if there is user input.
		if (inID.length() > 0) {
			//Use sql to select all books that match the user inputted title.
			String bookQuery = "select title,author,description,ISBN,published_year,available "
					+ "from Books where borrowed_by_id = " + "\"" + inID + "\"";
			//Hold the matching results.
			resultList = MysqlConnect.select(bookQuery);
			//If no results are found, create and set an error message.
			if (resultList.size() <= 0) {
				error += "No results for id: " + inID;
				errorMessage.setText(error);
			}
			//If there are results, show the results in the table and generate an output
			//file with the results.
			else
			{
				//Try creating the file, else catch the error.
				try
				{
					//Create the name of the file to write to using student id.
					String textName = "BorrowedBooks" + inID + ".txt";
					//Create the file.
					File file = new File(textName);
					//Delete the file if it already exists.
					if (file.exists()) {
						file.delete();
					}
					//Create a new FileWriter to write to the file.
					FileWriter fw = new FileWriter(textName, true);
					fw.write("");
					fw.flush();
					//Create header of the file.
					fw.write("Borrowed Books");
					fw.write("\r\n");
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
					//Write the record and a comma to the file.
					for (ArrayList<String> strObj : strRecord) {
						for (int i = 0; i < strObj.size(); i++) {
							fw.write(strObj.get(i));
							if (i != strObj.size() - 1)
							{
								fw.write(", ");
							}
						}
						fw.write("\r\n");
						//Create a new row with the book info.
						Row newRow = new Row(strObj.get(0), strObj.get(1), strObj.get(2), strObj.get(3),
								strObj.get(4), strObj.get(5));
						data.add(newRow);
					}
					//Update the table to show the results.
					tableViewPane.setItems(data);
					//Close the file writer.
					fw.close();
				}
				catch (Exception e)
				{
					System.out.println("Writing File Failed " + e);
				}
			}
		}
	}
}
