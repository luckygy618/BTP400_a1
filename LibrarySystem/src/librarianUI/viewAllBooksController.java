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

import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;

import guoyucao.db.manip.pack.MysqlConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * This is a class called viewAllBooksController, it has the UI page of return books, and when the buttons get clicked, then the matching UI page will pop up. 
 * When the generate report button is pressed, it reads the data from database then displays the data on the scrollable table view. 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class viewAllBooksController {

	@FXML
	private Button viewAllBooksClose;
	@FXML
	private Button viewAllBooksReport;
	@FXML
	private AnchorPane viewAllBooksScrollAnPane;

	@FXML
	private ScrollPane viewAllBooksScrollPane;

	@FXML
	private TableView<Row> tableViewPane;

	@FXML
	private Label viewAllBooksDisplay;

	private final ObservableList<Row> data = FXCollections.observableArrayList();

	
	/**
	 * This is the function that closes the UI page when user click on it
	 * 
	 *  When the button is clicked, this method will be called.
	 */
	public void viewAllBooksWindowClose() {

		Stage stage = (Stage) viewAllBooksClose.getScene().getWindow();
		stage.close();

	}

	/**
	 * This is the viewAllBooksWindowReport method that it displays all the books details in the current database 
	 */
	public void viewAllBooksWindowReport() {

		String errorMessage = "";
		ArrayList<Object[]> getReport = new ArrayList<Object[]>();
		String sql = "select book_id,ISBN,title,author,borrowed_by_id,available,hold_by_id from Books;";
		getReport = MysqlConnect.select(sql);
		if (getReport.size() <= 0) {
			errorMessage += "Currently there is no Book in the library!";
		} else {
			try {
				File file = new File("AllBooksDetail.txt");
				if (file.exists()) {
					file.delete();
				}
				FileWriter fw = new FileWriter("AllBooksDetail.txt", true);
				fw.write("");
				fw.flush();
				fw.write("Details of All Books in Library");
				fw.write("\r\n");
				ArrayList<ArrayList<String>> strRecord = new ArrayList<ArrayList<String>>();
				for (Object[] objArray : getReport) {
					ArrayList<String> strLise = new ArrayList<String>();
					for (Object obj : objArray) {

						String str = obj + "";
						strLise.add(str);
					}
					strRecord.add(strLise);

				}

				for (ArrayList<String> strObj : strRecord) {
					for (int i = 0; i < strObj.size(); i++) {
						fw.write(strObj.get(i));
						fw.write(",");
					}
					fw.write("\r\n");
					Row newRow = new Row(strObj.get(0), strObj.get(1), strObj.get(2), strObj.get(3), strObj.get(4),
							strObj.get(5), strObj.get(6));
					data.add(newRow);
				}

				fw.close();

				tableViewPane.setItems(data);

			} catch (Exception e) {
				System.out.println("Writting File Failed " + e);
			}

			if (errorMessage.length() > 0) {
				viewAllBooksDisplay.setText(errorMessage);
			} else {

				viewAllBooksDisplay.setText("Detailed report File has been generated to current Working directory!");

			}

		}

	}

}
