package unit_test.dbmanip.testCase;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import guoyucao.db.manip.pack.*;

/**
 * This is the JUNIT test class that tests all the entire guoyucao.db.manip.pack
 * module, each method is tested by both success and fail situations. If any
 * test case failed, the failed result will be shown on the "Failure Trace"
 * window.
 * 
 * @author GuoYuCao
 * @version 1.0
 * @since 1.0
 */
public class TestDBmanipulation {

	/**
	 * This is the @BeforeAll method that clears the test table and insert 2 default
	 * rows into this test table when the test class initialized.
	 */
	@BeforeAll
	public static void init() {
		System.out.println("---Initialize test table---");
		String sql = "delete from test where student_id is not null";
		MysqlConnect.delete(sql);

		String sql2 = "insert into test values(\"1001\",\"HarryPotter@myseneca.ca\",\"hp123\",\"1001\",\"Harry\",\"Potter\",\"1\");";
		final boolean getResult2 = MysqlConnect.insert(sql2);
		String sql3 = "insert into test values(\"1003\",\"HarryPotter@gmail.ca\",\"hp123\",\"1003\",\"Harry\",\"Potter\",\"1\");";
		final boolean getResult3 = MysqlConnect.insert(sql3);
		System.out.println("Add default rows: " + getResult2 + " , " + getResult3);
		System.out.println("---Initialize test table ends---");
	}

	/**
	 * This is the @AfterAll method that runs after all the test case methods are
	 * done. It clears all the rows in the test table.
	 */
	@AfterAll
	public static void cleanup() {
		System.out.println("---Ending test cases, clear all rows in test table---");
		String sql = "delete from test where student_id is not null";
		final boolean getResult = MysqlConnect.delete(sql);
		System.out.println("Clear all rows: " + getResult);
		System.out.println("---All test cases end---");
	}

	/**
	 * This is the test case that tests the select method of the MysqlConnect class.
	 * This test method assert both the successful situation and the fail situations
	 * of the sent sql query.
	 */
	@DisplayName("1. Test select method: ")
	@Test
	void testSelect() {

		System.out.println("---Test \"select\" method---");
		String sql = "select * from test where student_id=1001";
		String sqlNull = "select * from test where student_id=\"DoesNotExist\"";

		final ArrayList<Object[]> getResult = MysqlConnect.select(sql);
		final ArrayList<Object[]> getNullResult = MysqlConnect.select(sqlNull);

		assertEquals(0, getNullResult.size());// test if the sql returns empty result set when selecting wrong data

		assertNotNull("select method return the result. ", getResult);
		assertEquals("1001", getResult.get(0)[0]);
		assertEquals("HarryPotter@myseneca.ca", getResult.get(0)[1]);
		assertEquals("hp123", getResult.get(0)[2]);
		assertEquals("1001", getResult.get(0)[3]);
		assertEquals("Harry", getResult.get(0)[4]);
		assertEquals("Potter", getResult.get(0)[5]);
		assertEquals("1", getResult.get(0)[6]);
		System.out.println("---Test \"select\" method ends---");

	}

	/**
	 * This is the test case that tests the insert method of the MysqlConnect class.
	 * This test method assert both the successful situation and the fail situations
	 * of the sent sql query.
	 */
	@DisplayName("2. Test insert method: ")
	@Test
	void testInsert() {

		System.out.println("---Test \"insert\" method---");
		String sql = "insert into test values(\"1002\",\"PotterHarry@myseneca.ca\",\"ph123\",\"1002\",\"Potter\",\"Harry\",\"2\");";
		final boolean getResult = MysqlConnect.insert(sql);

		assertTrue(getResult);// assert if the insert method returns true when correct sql query sent

		String sql2 = "select * from test where student_id=1002";
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);
		assertEquals("1002", getResultSet.get(0)[0]);
		assertEquals("PotterHarry@myseneca.ca", getResultSet.get(0)[1]);
		assertEquals("ph123", getResultSet.get(0)[2]);
		assertEquals("1002", getResultSet.get(0)[3]);
		assertEquals("Potter", getResultSet.get(0)[4]);
		assertEquals("Harry", getResultSet.get(0)[5]);
		assertEquals("2", getResultSet.get(0)[6]);
		System.out.println("---Test \"insert\" method ends---");

	}

	/**
	 * This is the test case that tests the update method of the MysqlConnect class.
	 * This test method assert both the successful situation and the fail situations
	 * of the sent sql query.
	 */
	@DisplayName("3. Test update method: ")
	@Test
	void testUpdate() {

		System.out.println("---Test \"update\" method---");
		String sql = "update test set email = \"PotterHarry@YourSeneca.ca\" where student_id=1002 ";
		final boolean getResult = MysqlConnect.update(sql);

		assertTrue(getResult);// assert if the update method returns true when correct sql query sent

		String sqlNull = "update test set email = \"PotterHarry@YourSeneca.ca\" where student_id=\"DoesNotExist\" ";
		final boolean getResultNull = MysqlConnect.update(sqlNull);
		assertFalse(getResultNull);// assert if the update method returns false when wrong sql query sent

		String sql2 = "select * from test where student_id=1002";
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);
		assertEquals("1002", getResultSet.get(0)[0]);
		assertEquals("PotterHarry@YourSeneca.ca", getResultSet.get(0)[1]);
		assertEquals("ph123", getResultSet.get(0)[2]);
		assertEquals("1002", getResultSet.get(0)[3]);
		assertEquals("Potter", getResultSet.get(0)[4]);
		assertEquals("Harry", getResultSet.get(0)[5]);
		assertEquals("2", getResultSet.get(0)[6]);
		System.out.println("---Test \"update\" method ends---");

	}

	/**
	 * This is the test case that tests the delete method of the MysqlConnect class.
	 * This test method assert both the successful situation and the fail situations
	 * of the sent sql query.
	 */
	@DisplayName("4. Test delete method: ")
	@Test
	void testDelete() {

		System.out.println("---Test \"delete\" method---");
		String sql = "delete from test where student_id=1003 ";
		final boolean getResult = MysqlConnect.update(sql);
		assertTrue(getResult);// assert if the delete method returns true when correct sql query sent

		String sqlNull = "delete from test where student_id=1003 ";
		final boolean getResultNull = MysqlConnect.update(sqlNull);
		assertFalse(getResultNull);// assert if the delete method returns false when no row is changed by query

		String sql2 = "select * from test where student_id=1003";
		final ArrayList<Object[]> getResultSet = MysqlConnect.select(sql2);
		assertEquals(0, getResultSet.size());
		System.out.println("---Test \"delete\" method ends---");

	}
}
