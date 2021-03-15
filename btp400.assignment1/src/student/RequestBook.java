package student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import guoyucao.db.manip.pack.MysqlConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The RequestBook class. This class contains the controller for the request a book page.
 * 
 * @author Neil An
 * @version 1.0
 * @since 1.0
 */
public class RequestBook {
	//Search button.
	@FXML
	private Button requestButton;

	//Text field to read user inputted student id.
	@FXML
	private TextField studentID;
	
	//Text field to read user inputted ISBN.
	@FXML
	private TextField uISBN;
	
	//Label to show result of search.
	@FXML
	private Label message;
	
	/**
	 * requestBook is called when the search button is clicked. This method will try to request a book
	 * for the user, else create a waiting ticket and display their place in line.
	 */
	public void requestBook()
	{
		//String to hold the user inputted student id.
		String inID = "";
		//String to hold the user inputted ISBN.
		String inISBN = "";
		
		//Array to hold the student data from a sql call.
		ArrayList<Object[]> studentList = new ArrayList<Object[]>();
		//Array to hold the book data from a sql call.
		ArrayList<Object[]> resultList = new ArrayList<Object[]>();
		
		//Get the user input if if the text fields are not empty.
		if (!(studentID.getText() == null || studentID.getText().trim().isEmpty()
				|| uISBN.getText() == null || uISBN.getText().trim().isEmpty()))
		{
			inID = studentID.getText();
			inISBN = uISBN.getText();
		}
		
		//Check if there is user input.
		if (inID.length() > 0 && inISBN.length() > 0)
		{
			//Use sql to select all student number that match the user student number.
			//This is to verify that the entered student number is valid.
			String studentQuery = "select student_no from students where student_no = " + "\"" + inID + "\"";
			studentList = MysqlConnect.select(studentQuery);
			//Check if student number exists.
			if (studentList.size() > 0)
			{
				//Use sql to select availability of books that match the user inputted ISBN.
				String bookQuery = "select available from Books where ISBN = " + "\"" + inISBN + "\"";
				resultList = MysqlConnect.select(bookQuery);
				//Check if book exists.
				if (resultList.size() <= 0)
				{
					message.setText("Invalid ISBN.");
				}
				else
				{
					//For Object[] in resultList, and for Object in Object[], extract the string from
					//the data and add it to strRecord.
					for (Object[] objArray : resultList) {
						for (Object obj : objArray) {
							//If the book is available, send request.
							if (obj.toString() == "true")
							{
								message.setText("Request successfully sent.");
							}
							//If the book is unavailable, create waiting ticket.
							else
							{
								//Array to hold the waiting list data from a sql call.
								ArrayList<Object[]> waitList = new ArrayList<Object[]>();
								//Get the current time and format it.
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
								LocalDateTime now = LocalDateTime.now();
								//Insert a new waiting list ticket into the database.
								boolean insertWaitingTicket = MysqlConnect.insert(
										"insert into wait_ticket (ISBN, studentID , request_date) values ( "
										+ "\"" + inISBN + "\"," + "\"" + inID + "\"," + "\"" + dtf.format(now) + "\");");
								//If the waiting ticket was successfully added, print on page the users
								//position in line for the book.
								if (insertWaitingTicket)
								{
									String waitQuery = "select ISBN from wait_ticket where ISBN = " + "\"" + inISBN + "\"";
									waitList = MysqlConnect.select(waitQuery);
									String successMessage = "Request successfully added. You are " + waitList.size() + " in line.";
									message.setText(successMessage);
								}
								//The waiting ticket was not successfully added.
								//Print on page error message.
								else
								{
									message.setText("Wait ticket request failed. Try again.");
								}

							}
						}
					}
				}
			}
			else
			{
				message.setText("Student does not exist.");
			}
		}
	}
}
