package admin;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import accounts.LibrarianRow;
import guoyucao.db.manip.pack.MysqlConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewAllLibrariansController {
	/**
	 * The close button closes the window.
	 */
	@FXML
	private Button viewAllLibrariansClose;
	/**
	 * The generate report button selects the librarians from the database, displays them in a report and
	 * saves the report in a file.
	 */
	@FXML
	private Button viewAllLibrariansReport;
	/**
	 * A Scrollpane which is displays an AnchorPane.
	 */
	@FXML
	private ScrollPane viewAllLibrariansScrollPane;
	/**
	 * A AnchorPane which is displayed inside of a ScrollPane.
	 */
	@FXML
	private AnchorPane viewAllLibrariansScrollAnPane;
	/**
	 * A TableView which displays the Librarian data.
	 */
	@FXML
	private TableView<LibrarianRow> tableViewPane;
	/**
	 * The label that displays messages
	 */
	@FXML
	private Label viewAllLibrariansDisplay;
	/**
	 * An ObservableList which stores Librarian data.
	 */
	private final ObservableList<LibrarianRow> data = FXCollections.observableArrayList();
	
	/**
	 * The viewAllLibrariansWindowClose() method closes the UI window when user clicks on it.
	 * When the Close button is clicked, this method will be called.
	 */
	public void viewAllLibrariansWindowClose() {

		Stage stage = (Stage) viewAllLibrariansClose.getScene().getWindow();
		stage.close();

	}
	
	/**
	 * The viewAllLibrariansWindowReport() method displays all the Librarians in the database.
	 * It sends a select SQL query to select all the Librarians in the database, displays them in a
	 * report, and saves a file with the data from the report.
	 * 
	 *  When the corresponding button is clicked, this method will be called.
	 */
	public void viewAllLibrariansWindowReport() {
		// A local variable to store the value of error messages.
		String errorMessage = "";
		ArrayList<Object[]> getReport = new ArrayList<Object[]>();
		// A local variable to store the value of the SQL query.
		String sql = "select email,password,first_name,last_name,credentials,bio,level from Librarian;";
		// Save the result of the select query.
		getReport = MysqlConnect.select(sql);
		
		// Check if the size of the report is less than or equal to 0.
		if (getReport.size() <= 0) {
			// If the size of the report is less than or equal to 0, add an error message.
			errorMessage += "Currently there are no Librarians in the library!";
		} else {
			try {
				// Create a new File object with the file name AllLibrariansDatail.txt.
				File file = new File("AllLibrariansDetail.txt");
				
				// Check if the file exists.
				if (file.exists()) {
					// If the file exists, delete it.
					file.delete();
				}
				
				// Create a new FileWriter object with the file name AllLibrariansDatail.txt.
				FileWriter fw = new FileWriter("AllLibrariansDetail.txt", true);
				fw.write("");
				fw.flush();
				// Add a header to the file
				fw.write("Details of All Librarians");
				fw.write("\r\n");
				
				// Create a variable to store a Librarian record as an ArrayList of ArrayList objects of Strings.
				ArrayList<ArrayList<String>> strRecord = new ArrayList<ArrayList<String>>();
				// Loop through each object array returned by the SQL query and stored in the getReport variable.
				for (Object[] objArray : getReport) {
					// Create an ArrayList of Strings.
					ArrayList<String> strLise = new ArrayList<String>();
					
					// Loop through each object stored in the object array.
					for (Object obj : objArray) {
						// Store the contents of the object in a String.
						String str = obj + "";
						// Add the String to the ArrayList of Strings.
						strLise.add(str);
					}
					
					// Add the ArrayList of Strings to the ArrayList of ArrayLists of Strings
					strRecord.add(strLise);
				}
				
				// Loop through each ArrayList of Strings stored in the ArrayList of ArrayLists of Strings.
				for (ArrayList<String> strObj : strRecord) {
					// Loop while the number of iterations is less than the size of the ArrayList of Strings.
					for (int i = 0; i < strObj.size(); i++) {
						// Write each String to the file.
						fw.write(strObj.get(i));
						// Write a comma to the file.
						fw.write(",");
					}
					
					// Write a newline to the file.
					fw.write("\r\n");
					
					// Create a new LibrarianRow object based on the data stored in the ArrayList of Strings.
					LibrarianRow newRow = new LibrarianRow(strObj.get(0), strObj.get(1), strObj.get(2), strObj.get(3), strObj.get(4),
							strObj.get(5), strObj.get(6));
					
					// Add the LibrarianRow object to the data variable.
					data.add(newRow);
				}
				
				// Close the file.
				fw.close();
				
				// Set the items which are displayed in the table using the data variable.
				tableViewPane.setItems(data);

			} catch (Exception e) {
				// Print an error if there is an exception while writing the file.
				System.out.println("Writing File Failed " + e);
			}
			
			// Check if the length of the errorMessage variable is greater than 0.
			if (errorMessage.length() > 0) {
				// If the length of the errorMessage variable is greater than 0, display all error messages.
				viewAllLibrariansDisplay.setText(errorMessage);
			} else {
				// If the length of the errorMessage variable is not greater than 0, display a success message.
				viewAllLibrariansDisplay.setText("Detailed report File has been generated to current Working directory!");
			}

		}

	}
}
