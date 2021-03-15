package unit_test.student.testCase;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import guoyucao.db.manip.pack.MysqlConnect;

/**
 * The JUnit test class to test the Student module. This class will test the 3 main functions of the
 * Student module, which is the search a book function, the issue a request function, and the
 * view all borrowed books function.
 * 
 * @author Neil An
 * @version 1.0
 * @since 1.0
 */
class StudentTestCase {

	/**
	 * init will initialize the test cases so they are ready. This is done before the tests begin.
	 */
	@BeforeAll
	public static void init() {
		//Create testBook table to test from.
		final boolean init3 = MysqlConnect.update("create table testBooks as select * from Books;");
		String sqlini1 = "insert into testBooks values (1101,\"ABCDEFG123\",\"HarryPotter Test\",\"Author Test\",true,\"123\",null,null,null,\"100\",2020);";
		final boolean init1 = MysqlConnect.insert(sqlini1);
		String sqlini2 = "insert into testBooks values (1102,\"ABCDEFG456\",\"HarryPotter Test 2\",\"Author Test 2\",true,\"123\",null,null,null,\"100\",2020);";
		final boolean init2 = MysqlConnect.insert(sqlini2);
		String sqlini3 = "insert into testBooks values (1103,\"ABCDEFG789\",\"HarryPotter Test 3\",\"Author Test 3\",false,\"1001\",null,null,null,null,2020);";
		final boolean init4 = MysqlConnect.insert(sqlini3);
		//Check if table and rows are successfully added.
		System.out.println("Add default rows for testBooks: "
				+ init1 + ", " + init2 + ", " + init3 + ", " + init4);
		

		//Create testWait table to test from.
		final boolean init5 = MysqlConnect.update("create table testWait as select * from wait_ticket;");
		//Check if table was successfully added.
		System.out.println("Add default rows for testWait: " + init5);
	}

	/**
	 * cleanup will delete all tables used for test purposes.
	 */
	@AfterAll
	public static void cleanup() {
		System.out.println("Deleting all test tables.");
		//Drop testBooks table.
		MysqlConnect.delete("drop table testBooks;");
		//Drop testWait table.
		MysqlConnect.delete("drop table testWait;");
	}
	
	/**
	 * Testing search method. This method should ensure that the books added in init were added
	 * successfully and that searching for these books will yield the right results.
	 */
	@Test
	@DisplayName("Testing search book")
	public void testSearch()
	{
		//Search for book title: HarryPotter Test.
		System.out.println("Search for book with title: HarryPotter Test");
		String sqlSearch = "select * from testBooks where title = \"HarryPotter Test\"";
		
		//Create list of search results.
		ArrayList<Object[]> searchResults = MysqlConnect.select(sqlSearch);
		
		//Check if correct book pops up.
		assertEquals(1101, searchResults.get(0)[0]);
		assertEquals("ABCDEFG123", searchResults.get(0)[1]);
		assertEquals("HarryPotter Test", searchResults.get(0)[2]);
		assertEquals("Author Test", searchResults.get(0)[3]);
		
		//Search for book title: HarryPotter Test 2.
		System.out.println("Search for book with title: HarryPotter Test 2");
		sqlSearch = "select * from testBooks where title = \"HarryPotter Test 2\"";
		
		//Create list of search results.
		searchResults = MysqlConnect.select(sqlSearch);

		//Check if correct book pops up.
		assertEquals(1102, searchResults.get(0)[0]);
		assertEquals("ABCDEFG456", searchResults.get(0)[1]);
		assertEquals("HarryPotter Test 2", searchResults.get(0)[2]);
		assertEquals("Author Test 2", searchResults.get(0)[3]);
		
		//Search for book title: HarryPotter Test 3.
		System.out.println("Search for book with title: HarryPotter Test 3");
		sqlSearch = "select * from testBooks where title = \"HarryPotter Test 3\"";
		
		//Create list of search results.
		searchResults = MysqlConnect.select(sqlSearch);

		//Check if correct book pops up.
		assertEquals(1103, searchResults.get(0)[0]);
		assertEquals("ABCDEFG789", searchResults.get(0)[1]);
		assertEquals("HarryPotter Test 3", searchResults.get(0)[2]);
		assertEquals("Author Test 3", searchResults.get(0)[3]);
		
		//Search for book title that does not exist.
		System.out.println("Search for book that does not exist.");
		sqlSearch = "select * from testBooks where title = \"Invalid Book\"";
		
		//Create list of search results.
		searchResults = MysqlConnect.select(sqlSearch);
		
		//Check if no books are returned.
		assertEquals(0, searchResults.size());
	}
	
	/**
	 * Testing view borrowed books. This test method will test for books borrowed by a specific
	 * student id. If a student id does not exist, no books will be returned.
	 */
	@Test
	@DisplayName("Testing view borrowed")
	public void testBorrowed()
	{
		//Search for valid student id.
		System.out.println("Search for books borrowed by student id: 123 (return 2 books)");
		String sqlSearch = "select * from testBooks where borrowed_by_id = 123";
		
		//Create list of search results.
		ArrayList<Object[]> searchResults = MysqlConnect.select(sqlSearch);
		
		//Check if 2 results are returned.
		assertEquals(2, searchResults.size());
		
		//Check if 1st book is valid and desired results appear.
		assertEquals(1101, searchResults.get(0)[0]);
		assertEquals("ABCDEFG123", searchResults.get(0)[1]);
		assertEquals("HarryPotter Test", searchResults.get(0)[2]);
		assertEquals("Author Test", searchResults.get(0)[3]);
		
		//Check if 2nd book is valid and desired results appear.
		assertEquals(1102, searchResults.get(1)[0]);
		assertEquals("ABCDEFG456", searchResults.get(1)[1]);
		assertEquals("HarryPotter Test 2", searchResults.get(1)[2]);
		assertEquals("Author Test 2", searchResults.get(1)[3]);
		
		//Search for invalid student id.
		System.out.println("Search for books borrowed by invalid student id: 555 (return 0 books)");
		sqlSearch = "select * from testBooks where borrowed_by_id = 555";
		
		searchResults = MysqlConnect.select(sqlSearch);
		
		//Check if 0 results are returned.
		assertEquals(0, searchResults.size());
	}
	
	/**
	 * Testing wait ticket. This test method will test adding a waiting ticket.
	 */
	@Test
	@DisplayName("Testing waiting ticket")
	public void testWaitTicket()
	{
		//Create insert sql command.
		String insertQuery = "insert into testWait values (\"RRRRRRRRRR12\",123456789,\'2021-03-10\');";
		boolean testInsert = MysqlConnect.insert(insertQuery);
		
		//Check if waiting_ticket was successfully inserted.
		assertEquals(true, testInsert);
		
		//Search for newly inserted waiting ticket.
		System.out.println("Search for waiting ticket of book with ISBN: RRRRRRRRRR12 (return 1 ticket)");
		String sqlSearch = "select * from testWait where ISBN = \"RRRRRRRRRR12\"";
		ArrayList<Object[]> searchResults = MysqlConnect.select(sqlSearch);
		
		//Check if 1 result is returned.
		assertEquals(1, searchResults.size());
		
		//Check if the correct information for waiting ticket is returned.
		assertEquals("RRRRRRRRRR12", searchResults.get(0)[0]);
		assertEquals("123456789", searchResults.get(0)[1]);
		assertEquals("2021-03-10", searchResults.get(0)[2].toString());
	}
}
