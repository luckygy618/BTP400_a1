package unit_test.admin.testCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import guoyucao.db.manip.pack.MysqlConnect;

class TestAdminControllers {
	
	/**
	 * This is the @BeforeAll method that clears the test table and inserts default
	 * rows into test tables when the test class is initialized.
	 */
	@BeforeAll
	public static void init() {
		
		System.out.println("--- Initialize test tables ---");
		
		final boolean initTestLibrarianTable = MysqlConnect.update("create table testLibrarian as select * from Librarian;");
		String librarianRowSql1 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"guy@incognito.com\", \"abc123\", \"Guy\", \"Incognito\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initlibrarianRow1 = MysqlConnect.insert(librarianRowSql1);
		String librarianRowSql2 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"spongebob@squarepants.com\", \"abc123\", \"Spongebob\", \"Squarepants\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initLibrarianRow2 = MysqlConnect.insert(librarianRowSql2);
		String librarianRowSql3 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"bojack@horseman.com\", \"abc123\", \"Bojack\", \"Horseman\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initLibrarianRow3 = MysqlConnect.insert(librarianRowSql3);
		
		System.out.println("Add default Librarian rows: " + initTestLibrarianTable + " , " + initlibrarianRow1 + " , " + initLibrarianRow2 + " , " + initLibrarianRow3);
		
		System.out.println("--- Initialize test tables ends ---");

	}
	
	/**
	 * This is the @AfterAll method that runs after all the test case methods are
	 * done. It clears all the rows in the test table.
	 */
	@AfterAll
	public static void cleanup() {
		System.out.println("--- Ending test cases, clear all rows in test table ---");
		MysqlConnect.delete("drop table testLibrarian;");
		System.out.println("--- All test cases end ---");
	}
	
	/**
	 * This is the test case that tests the registerLibrarianWindowSave() method of the
	 * RegisterLibrarianController class. This test method checks if the
	 * RegisterLibrarianController could register a new librarian in the database successfully.
	 */
	@DisplayName("1. Test Register Librarian method: ")
	@Test
	void testRegisterLibrarian() {

		String email = "john@mulaney.com"; // original data should get from the UI text field registerLibrarianEmail.getText();
		String password = "abc123"; // original data should get from the UI text field registerLibrarianPassword.getText();
		String firstName = "John"; // original data should get from the UI text field registerLibrarianFirstName.getText();
		String lastName = "Mulaney"; // original data should get from the UI text field registerLibrarianLastName.getText();
		String credentials = "Bachelor's degree";// original data should get from the UI text field
													// registerLibrarianCredentials.getText();
		String bio = "John Mulaney is very small and has no money.";// original data should get from the UI text field registerLibrarianBio.getText();
		String level = "junior librarian";// original data should get from the UI text field registerLibrarianLevel.getText();

		String query = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"" + email + "\", \"" + password + "\", \"" + firstName + "\", \"" + lastName + "\", \"" + credentials + "\", \"" + bio + "\", \"" + level + "\");";

		/**
		 *  This insertResult is the return value of calling the query to the database. It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean insertResult = MysqlConnect.insert(query);
		System.out.println("---Test \"Register Librarian\" method---");
		String sql = "select * from testLibrarian where email=\"" + email + "\"";
		
		/**
		 *  This getResult is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResult = MysqlConnect.select(sql);

		assertTrue(insertResult);// assert if the sql method returns true when correct sql query sent

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResult);
		assertEquals(email, getResult.get(0)[1]);
		assertEquals(password, getResult.get(0)[2]);
		assertEquals(firstName, getResult.get(0)[3]);
		assertEquals(lastName, getResult.get(0)[4]);
		assertEquals(credentials, getResult.get(0)[5]);
		assertEquals(bio, getResult.get(0)[6]);
		assertEquals(level, getResult.get(0)[7]);

		System.out.println("---Test \"Register Librarian\" method ends---");

	}
	
	/**
	 * This is the test case that tests the deleteLibrarianWindowDelete() method of the
	 * DeleteLibrarianController class. This test method checks if the
	 * RegisterLibrarianController could delete a librarian from the database successfully.
	 */
	@DisplayName("2. Test Delete Librarian method: ")
	@Test
	void testDeleteLibrarian() {

		System.out.println("---Test \"Delete Librarian\" method---");
		String email = "john@mulaney.com"; // original data should get from the UI text field registerLibrarianEmail.getText();
		String sql = "delete from testLibrarian where email=\"" + email + "\";";
		
		/**
		 *  This deleteResult is the return value of calling the query to the database. 
		 *  It returns true if the query success and returns false if the query failed or does not effect any rows.
		 */
		boolean deleteResult = MysqlConnect.delete(sql);

		assertTrue(deleteResult);// assert if the sql method returns true when correct sql query sent

		String sql2 = "select * from testLibrarian where email=\"" + email + "\";";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);

		// assert if the data successfully deleted from the database
		assertNotNull("select method return the result. ", getResultSet);
		assertEquals(0, getResultSet.size());
		System.out.println("---Test \"Delete Librarian\" method ends---");

	}
	
	/**
	 * This is the test case that tests the viewAllLibrariansWindowReport() method of the
	 * ViewAllLibrariansController class. This test method checks if the
	 * ViewAllLibrariansController could select all librarians from the database successfully.
	 */
	@DisplayName("3. Test View All Librarians method: ")
	@Test
	void testViewAllLibrarians() {

		System.out.println("---Test \"View All Librarians\" method---");

		String sql = "select * from testLibrarian order by librarian_id";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql);

		// assert if the data successfully added into the database
		assertNotNull("select method return the result. ", getResultSet);

		System.out.println("---Test \"View All Librarians\" method ends---");

	}

}
