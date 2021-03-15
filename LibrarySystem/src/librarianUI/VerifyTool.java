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
import guoyucao.db.manip.pack.MysqlConnect;


/**
 * This is a class called VerifyTool that contains the methods to verify the data that retrieved from the text field.
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class VerifyTool {

	/**
	 * This is the method that checks if the user entered librarian id is existing in the database. 
	 * @param libID This is the librarian id that the user entered. 
	 * @return errorMessage  This is the error message string that returns what's wrong of the user's input.
	 */
	public static String isLibrarianIDExist(String libID) {
		String errorMessage="";
		
		ArrayList<Object[]> checkLib =new ArrayList<Object[]>();
		if(libID.length()>0) {
		String checkLibID = "select * from Librarian where librarian_id = " + "\"" + libID + "\";";
		checkLib = MysqlConnect.select(checkLibID);
		}else {
			errorMessage +="Librarian ID cannot be null, please enter Librarian ID";
		}
		if (libID.length()<=0||checkLib.size() <= 0) {
			errorMessage += "Librarian ID does not exist, please enter valid Librarian ID!\n";
		}
			
		return errorMessage;	
	}
	
	/**
	 * This is the method that checks if the user entered book id is existing in the database and if the entered id is numeric. 
	 * @param bookID This is the librarian id that the user entered. 
	 * @return errorMessage  This is the error message string that returns what's wrong of the user's input.
	 */
	public static String isBookIDAvailable(String bookID) {
		String errorMessage = "";
		int bookID_num = -1;
		ArrayList<Object[]> checkBookID =new ArrayList<Object[]>();
		if(bookID.length()>0 ) {

			try{
				bookID_num =Integer.parseInt(bookID);
				}catch(NumberFormatException e){
					bookID_num = -1;
					errorMessage += "Book ID must be numbers! Please enter numeric Book ID!\n";
				}
	
		String checkBook = "select * from Books where book_id =" + bookID_num + " and available=1 and  borrowed_by_id is null and hold_by_id is null;";
		checkBookID = MysqlConnect.select(checkBook);
		} else {
			errorMessage +="Book ID cannot be null, please enter Book ID";
		}
		
		if (checkBookID.size() <= 0) {
			errorMessage += "Currently no available Book ID: "+ bookID + " exist, please enter an available Book ID.\n";
		}
		return errorMessage;
	}
	
	/**
	 * This is the method that checks if the user entered student id is existing in the database. 
	 * @param studentID This is the librarian id that the user entered. 
	 * @return errorMessage  This is the error message string that returns what's wrong of the user's input.
	 */
	public static String isStudentIDExist(String studentID) {
		String errorMessage="";
		ArrayList<Object[]> checkStu =new ArrayList<Object[]>();
		if(studentID.length()>0) {
		String checkstudentID = "select * from students where student_no = " + "\"" + studentID + "\";";
		checkStu = MysqlConnect.select(checkstudentID);
		}else {
			errorMessage +="Student ID cannot be null, please enter Student ID";
		}
		if (checkStu.size() <= 0) {
			errorMessage += "Student ID does not exist, please enter valid Student ID!\n";
		}
			
		return errorMessage;	
	}
	

	/**
	 * This is the method that checks if the user entered book id is existing in the database and if the entered id is numeric. 
	 * @param bookID This is the librarian id that the user entered. 
	 * @return errorMessage  This is the error message string that returns what's wrong of the user's input.
	 */
	public static String isBookIDExist(String bookID) {
		String errorMessage = "";
		int bookID_num = -1;
		ArrayList<Object[]> checkBookID =new ArrayList<Object[]>();
		if(bookID.length()>0 ) {

			try{
				bookID_num =Integer.parseInt(bookID);
				}catch(NumberFormatException e){
					bookID_num = -1;
					errorMessage += "Book ID must be numbers! Please enter numeric Book ID!\n";
				}
	
		String checkBook = "select * from Books where book_id =" + bookID_num + " ;";
		checkBookID = MysqlConnect.select(checkBook);
		} else {
			errorMessage +="Book ID cannot be null, please enter Book ID";
		}
		
		if (checkBookID.size() <= 0) {
			errorMessage += "Currently no available Book ID: "+ bookID + " exist, please enter an available Book ID.\n";
		}
		return errorMessage;
	}
	
	
	
	
}
