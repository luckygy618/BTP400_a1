package unit_test.librarian.testCase;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import guoyucao.db.manip.pack.*;

/**
 * This is the JUNIT test class that tests all the entire Librarian module,
 * each test case tests a controller of the Librarian module. 
 * If any test case failed, the failed result will be shown on the "Failure Trace" window.
 * 
 * @author GuoYuCao
 * @version 1.0
 * @since 1.0
 */
public class TestLibrarianControllers {

	/**
	 * This is the @BeforeAll method that clears the test table and insert 2 default
	 * rows into this test table when the test class initialized.
	 */
	@BeforeAll
	public static void init() {

		final boolean init3 = MysqlConnect.update("create table testBooks as select * from Books;");
		String sqlini1 = "insert into testBooks values (1101,\"ABCDEFG123\",\"HarryPotter Test\",\"Author Test\",true,null,null,null,null,\"100\",2020);";
		final boolean init1 = MysqlConnect.insert(sqlini1);
		String sqlini2 = "insert into testBooks values (1102,\"ABCDEFG456\",\"HarryPotter Test 2\",\"Author Test 2\",true,null,null,null,null,\"100\",2020);";
		final boolean init2 = MysqlConnect.insert(sqlini2);

		String sqlini4 = "insert into testBooks values (1103,\"ABCDEFG789\",\"HarryPotter Test 3\",\"Author Test 3\",false,\"1001\",null,null,null,null,2020);";
		final boolean init4 = MysqlConnect.insert(sqlini4);
		System.out.println("Add default rows: " + init1 + " , " + init2 + " , " + init3 + " , " + init4);
		System.out.println("---Initialize test table ends---");

	}

	/**
	 * This is the @AfterAll method that runs after all the test case methods are
	 * done. It clears all the rows in the test table.
	 */
	@AfterAll
	public static void cleanup() {
		System.out.println("---Ending test cases, clear all rows in test table---");
		MysqlConnect.delete("drop table testBooks;");
		System.out.println("---All test cases end---");
	}

	/**
	 * This is the test case that tests the Add New Book method of the
	 * addNewBookController class. This test method assert if the
	 * addNewBookController could set up the database correctly.
	 */
	@DisplayName("1. Test Add New Book method: ")
	@Test
	void testAddNewBook() {

		String isbn = "EEEEEEEE001"; // original data should get from the UI text field addBookISBN.getText();
		String title = "Harry Potter"; // original data should get from the UI text field addBookTitle.getText();
		String author = "J.K. Rowling"; // original data should get from the UI text field addBookAuthor.getText();
		int publicYear = 2020;// original data should get from the UI text field
								// addBookPublishedDate.getText();
		String description = "This is a test data";// original data should get from the UI text field
													// addBookDescription.getText();
		String librarianID = "100";// original data should get from the UI text field addBookLibrarianID.getText();

		String query = "insert into testBooks (ISBN,title,author,available,borrowed_by_id,hold_by_id,description,issued_by_id,retrived_by_librian_id,published_year) values ( "
				+ " \"" + isbn + "\"," + "\"" + title + "\"," + "\"" + author + "\"," + "true," + "null,null," + "\""
				+ description + "\",null," + "\"" + librarianID + "\"," + publicYear + ");";

		/**
		 *  This insertResult is the return value of calling the query to the database. It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean insertResult = MysqlConnect.insert(query);
		System.out.println("---Test \"Add New Book\" method---");
		String sql = "select * from testBooks where ISBN=\"EEEEEEEE001\"";
		
		/**
		 *  This getResult is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResult = MysqlConnect.select(sql);

		assertTrue(insertResult);// assert if the sql method returns true when correct sql query sent

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResult);
		assertEquals(isbn, getResult.get(0)[1]);
		assertEquals(title, getResult.get(0)[2]);
		assertEquals(author, getResult.get(0)[3]);
		assertEquals(description, getResult.get(0)[7]);
		assertEquals(publicYear, getResult.get(0)[10]);
		assertEquals(librarianID, getResult.get(0)[9]);

		System.out.println("---Test \"Add New Book\" method ends---");

	}

	
	/**
	 * This is the test case that tests the Hold Book method of the
	 * holdBookController class. This test method assert if the holdBookController
	 * could set up the database correctly.
	 */
	@DisplayName("2. Test Hold Book method: ")
	@Test
	void testHoldBook() {

		System.out.println("---Test \"Hold Book\" method---");
		String librarianID = "100";// original data should get from the UI text field
									// holdBookLibrarianID.getText();
		String bookID = "1101";
		String sql = "update testBooks set hold_by_id=" + librarianID + " ,available=false,retrived_by_librian_id=null "
				+ " where book_id=" + bookID;
		
		/**
		 *  This updateresult is the return value of calling the query to the database. 
		 *  It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean updateresult = MysqlConnect.update(sql);

		assertTrue(updateresult);// assert if the sql method returns true when correct sql query sent

		String sql2 = "select * from testBooks where book_id=1101";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResultSet);
		assertEquals(1101, getResultSet.get(0)[0]);
		assertEquals(false, getResultSet.get(0)[4]);
		assertEquals(librarianID, getResultSet.get(0)[6]);
		System.out.println("---Test \"Hold Book\" method ends---");

	}

	/**
	 * This is the test case that tests the Issue Book method of the
	 * IssueBookController class. This test method assert if the IssueBookController
	 * could set up the database correctly.
	 */
	@DisplayName("3. Test Issue Book method: ")
	@Test
	void testIssueBook() {

		System.out.println("---Test \"Issue Book\" method---");
		String studentID = "1001";// original data should get from the UI text field issueBookStudentID.getText();
		String librarianID = "100";// original data should get from the UI text field
									// issueBookLibrarianID.getText();
		int bookID = 1102;
		String sql = "update testBooks set borrowed_by_id=" + studentID + " ,available=false, " + " issued_by_id="
				+ librarianID + ",retrived_by_librian_id=null " + " where book_id=" + bookID;

		/**
		 *  This updateresult is the return value of calling the query to the database. 
		 *  It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean updateresult = MysqlConnect.update(sql);

		assertTrue(updateresult);// assert if the sql method returns true when correct sql query sent

		String sql2 = "select * from testBooks where book_id=1102";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResultSet);
		assertEquals(1102, getResultSet.get(0)[0]);
		assertEquals(false, getResultSet.get(0)[4]);
		assertEquals(studentID, getResultSet.get(0)[5]);
		assertEquals(librarianID, getResultSet.get(0)[8]);
		System.out.println("---Test \"Issue Book\" method ends---");

	}

	/**
	 * This is the test case that tests the Return Book method of the
	 * ReturnBookController class. This test method assert if the
	 * ReturnBookController could set up the database correctly.
	 */
	@DisplayName("4. Test Return Book method: ")
	@Test
	void testReturnBook() {

		System.out.println("---Test \"Return Book \" method---");

		String libID = "100";
		String bookID = "1103";

		String sql = "update testBooks set borrowed_by_id=null,issued_by_id=null, hold_by_id=null,available=true,retrived_by_librian_id="
				+ libID + " where book_id=" + bookID;
		
		/**
		 *  This updateresult is the return value of calling the query to the database. 
		 *  It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean updateresult = MysqlConnect.update(sql);

		assertTrue(updateresult);// assert if the sql method returns true when correct sql query sent

		String sql2 = "select * from testBooks where book_id=1103";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResultSet);
		assertEquals(1103, getResultSet.get(0)[0]);
		assertEquals(true, getResultSet.get(0)[4]);
		assertEquals(null, getResultSet.get(0)[5]);
		assertEquals(libID, getResultSet.get(0)[9]);

		System.out.println("---Test \"Return Book \" method ends---");

	}

	
	/**
	 * This is the test case that tests the View All Books method of the
	 * viewAllBooksController class. This test method assert if the
	 * viewAllBooksController could set up the database correctly.
	 */
	@DisplayName("5. Test View All Books method: ")
	@Test
	void testViewAllBooks() {

		System.out.println("---Test \"Return Book \" method---");

		String sql2 = "select * from testBooks order by book_id";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResultSet);
		assertEquals(1000, getResultSet.get(0)[0]);
		assertEquals("GIRL JACKED", getResultSet.get(0)[2]);
		assertEquals(1005, getResultSet.get(1)[0]);
		assertEquals("Girl Javaed", getResultSet.get(1)[2]);

		System.out.println("---Test \"View All Books \" method ends---");

	}

}
