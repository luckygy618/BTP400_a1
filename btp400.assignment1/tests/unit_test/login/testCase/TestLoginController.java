package unit_test.login.testCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import guoyucao.db.manip.pack.MysqlConnect;

class TestLoginController {
	
	/**
	 * This is the @BeforeAll method that clears the test table and inserts default
	 * rows into test tables when the test class is initialized.
	 */
	@BeforeAll
	public static void init() {
		
		System.out.println("--- Initialize test tables ---");
		
		final boolean initTestAdminTable = MysqlConnect.update("create table testAdmin as select * from Admin;");
		String adminRowSql1 = "insert into testAdmin (email, password, first_name, last_name, level) values (\"guy1@incognito.com\", \"abc123\", \"Guy\", \"Incognito\", \"manager\");";
		final boolean initAdminRow1 = MysqlConnect.insert(adminRowSql1);
		String adminRowSql2 = "insert into testAdmin (email, password, first_name, last_name, level) values (\"spongebob1@squarepants.com\", \"abc123\", \"Spongebob\", \"Squarepants\", \"manager\");";
		final boolean initAdminRow2 = MysqlConnect.insert(adminRowSql2);
		String adminRowSql3 = "insert into testAdmin (email, password, first_name, last_name, level) values (\"bojack1@horseman.com\", \"abc123\", \"Bojack\", \"Horseman\", \"manager\");";
		final boolean initAdminRow3 = MysqlConnect.insert(adminRowSql3);
		
		System.out.println("Add default Admin rows: " + initTestAdminTable + " , " + initAdminRow1 + " , " + initAdminRow2 + " , " + initAdminRow3);
		
		final boolean initTestLibrarianTable = MysqlConnect.update("create table testLibrarian as select * from Librarian;");
		String librarianRowSql1 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"guy2@incognito.com\", \"abc123\", \"Guy\", \"Incognito\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initlibrarianRow1 = MysqlConnect.insert(librarianRowSql1);
		String librarianRowSql2 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"spongebob2@squarepants.com\", \"abc123\", \"Spongebob\", \"Squarepants\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initLibrarianRow2 = MysqlConnect.insert(librarianRowSql2);
		String librarianRowSql3 = "insert into testLibrarian (email, password, first_name, last_name, credentials, bio, level) values (\"bojack2@horseman.com\", \"abc123\", \"Bojack\", \"Horseman\", \"PhD\", \"This person is a librarian.\", \"manager\");";
		final boolean initLibrarianRow3 = MysqlConnect.insert(librarianRowSql3);
		
		System.out.println("Add default Librarian rows: " + initTestLibrarianTable + " , " + initlibrarianRow1 + " , " + initLibrarianRow2 + " , " + initLibrarianRow3);
		
		final boolean initTestStudentTable = MysqlConnect.update("create table testStudents as select * from students;");
		String studentRowSql1 = "insert into testStudents (email, password, student_no, surname, forename, level) values (\"guy3@incognito.com\", \"abc123\", \"1001\", \"Incognito\", \"Guy\", \"alumni\");";
		final boolean initStudentRow1 = MysqlConnect.insert(studentRowSql1);
		String studentRowSql2 = "insert into testStudents (email, password, student_no, surname, forename, level) values (\"spongebob3@squarepants.com\", \"abc123\", \"1002\", \"Squarepants\", \"Spongebob\", \"current\");";
		final boolean initStudentRow2 = MysqlConnect.insert(studentRowSql2);
		String studentRowSql3 = "insert into testStudents (email, password, student_no, surname, forename, level) values (\"bojack3@horseman.com\", \"abc123\", \"1003\", \"Horseman\", \"Bojack\", \"undergrad\");";
		final boolean initStudentRow3 = MysqlConnect.insert(studentRowSql3);
		
		System.out.println("Add default Students rows: " + initTestStudentTable + " , " + initStudentRow1 + " , " + initStudentRow2 + " , " + initStudentRow3);
		
		System.out.println("--- Initialize test tables ends ---");

	}
	
	/**
	 * This is the @AfterAll method that runs after all the test case methods are
	 * done. It clears all the rows in the test table.
	 */
	@AfterAll
	public static void cleanup() {
		System.out.println("--- Ending test cases, clear all rows in test table ---");
		MysqlConnect.delete("drop table testAdmin;");
		MysqlConnect.delete("drop table testLibrarian;");
		MysqlConnect.delete("drop table testStudents;");
		System.out.println("--- All test cases end ---");
	}

	/**
	 * This is the test case that tests the loginWindowLogin() method of the
	 * LoginController class. This test method checks if the
	 * LoginController could select all librarians from the database successfully.
	 */
	@DisplayName("1. Test Login method: ")
	@Test
	void testLogin() {

		System.out.println("---Test \"Login\" method---");
		
		String email = "guy1@incognito.com";
		String password = "abc123";
		
		/**
		 *  This getResultSet is the return value of calling the query to the database. 
		 *  It returns an  ArrayList<Object[]> if the query success and returns rows, if the query failed or does not effect any rows it will return empty ArrayList<Object[]> .
		 */
		String adminSql = "select * from testAdmin where email = " + "\"" + email + "\";";
		final ArrayList<Object[]> getAdminResultSet = MysqlConnect.select(adminSql);
		
		String librarianSql = "select * from testLibrarian where email = " + "\"" + email + "\";";
		final ArrayList<Object[]> getLibrarianResultSet = MysqlConnect.select(librarianSql);
		
		String studentSql = "select * from testStudents where email = " + "\"" + email + "\";";
		final ArrayList<Object[]> getStudentResultSet = MysqlConnect.select(studentSql);
		
		String accountSql = "select * from testAdmin where email = " + "\"" + email + "\" and password = \"" + password + "\";";
		final ArrayList<Object[]> getAccountResultSet = MysqlConnect.select(accountSql);

		// assert if the data successfully selected from the database
		assertNotNull("select method return the result. ", getAdminResultSet);
		assertNotNull("select method return the result. ", getLibrarianResultSet);
		assertNotNull("select method return the result. ", getStudentResultSet);
		assertNotNull("select method return the result. ", getAccountResultSet);
		assertEquals(1, getAdminResultSet.size());
		assertEquals(0, getLibrarianResultSet.size());
		assertEquals(0, getStudentResultSet.size());
		assertEquals(1, getAccountResultSet.size());

		System.out.println("---Test \"Login\" method ends---");

	}

}
