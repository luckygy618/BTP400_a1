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
package guoyucao.db.manip.pack;

import java.sql.*;
import java.util.*;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;
/**
 * This is a class called MysqlConnect that contains the methods to manipulate the database, including select, update, delete, and insert methods
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class MysqlConnect {

	/**
	 * This is the method that calling the select query to the database based on the parameter
	 * @param queryStr This is the SQL query string that passed to the database
	 * @return result  This is an {@code ArrayList<Object[]>} that contains all the objects of sql result set, each object is a row that the sql query returns
	 */
	public static ArrayList<Object[]> select(String queryStr) {

		ArrayList<Object[]> result = new ArrayList<Object[]>();
		ConnectInfo connInfo = new ConnectInfo();
		final String JDBC_DRIVER = connInfo.getDriver();
		final String DB_URL = connInfo.getURL();
		final String USER = connInfo.getUser();
		final String PASS = connInfo.getPassword();
		// Step 1: Register JDBC driver
		DbUtils.loadDriver(JDBC_DRIVER);
		// Step 2: Open a connection
		Connection conn = null;
		String sql = queryStr;
		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			QueryRunner queryRunner = new QueryRunner();
			result = (ArrayList<Object[]>) queryRunner.query(conn, sql, new ArrayListHandler());
		} catch (SQLException se) {
			// handle JDBC error
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Goodbye!");
		}
		try {
			DbUtils.close(conn);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return result;
	}


	/**
	 * This is the method that calling the delete query to the database based on the parameter
	 * @param queryStr This is the SQL query string that passed to the database
	 * @return result  This is a boolean that return true when the delete query is success and returns false when the delete failed
	 */
	
	public static boolean delete(String queryStr) {
		boolean result = false;
		int deleteRow = -1;
		ConnectInfo connInfo = new ConnectInfo();
		final String JDBC_DRIVER = connInfo.getDriver();
		final String DB_URL = connInfo.getURL();
		final String USER = connInfo.getUser();
		final String PASS = connInfo.getPassword();
		// Step 1: Register JDBC driver
		DbUtils.loadDriver(JDBC_DRIVER);
		// Step 2: Open a connection
		Connection conn = null;
		String sql = queryStr;
		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			QueryRunner queryRunner = new QueryRunner();
			deleteRow = queryRunner.update(conn, sql);
		} catch (SQLException se) {
			// handle JDBC error
			se.printStackTrace();
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("Goodbye!");
		}
		if (deleteRow > 0) {
			result = true;
		} else {
			result = false;
		}
		
		try {
			DbUtils.close(conn);
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return result;
	}

	/**
	 * This is the method that calling the insert query to the database based on the parameter
	 * @param queryStr This is the SQL query string that passed to the database
	 * @return result  This is a boolean that return true when the insert query is success and returns false when the insert failed
	 */
	public static boolean insert(String queryStr) {
	
		ArrayListHandler myArrayListHandler = new ArrayListHandler();
	
		  boolean result = false;
		 ConnectInfo connInfo = new ConnectInfo();
			final String JDBC_DRIVER = connInfo.getDriver();
			final String DB_URL = connInfo.getURL();
			final String USER = connInfo.getUser();
			final String PASS = connInfo.getPassword();
			// Step 1: Register JDBC driver
			DbUtils.loadDriver(JDBC_DRIVER);
			// Step 2: Open a connection
			Connection conn = null;
			String sql = queryStr;
			try {
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				QueryRunner queryRunner = new QueryRunner();
				 queryRunner.insert(conn, sql,  myArrayListHandler);
				 result = true;
		
			} catch (SQLException se) {
				// handle JDBC error
				se.printStackTrace();
				result=false;
			
			} catch (Exception e) {
				e.printStackTrace();
				result=false;
			} finally {
				System.out.println("Goodbye!");
				
			}
	
			try {
				DbUtils.close(conn);
			} catch (SQLException se) {
				se.printStackTrace();
			}

		return result;
	}

	/**
	 * This is the method that calling the update query to the database based on the parameter
	 * @param queryStr This is the SQL query string that passed to the database
	 * @return result  This is a boolean that return true when the update query is success and returns false when the update failed
	 */
	public static boolean update(String queryStr) {
		boolean result = false;
		int updateRow = -1;
		ConnectInfo connInfo = new ConnectInfo();
		final String JDBC_DRIVER = connInfo.getDriver();
		final String DB_URL = connInfo.getURL();
		final String USER = connInfo.getUser();
		final String PASS = connInfo.getPassword();
		// Step 1: Register JDBC driver
		DbUtils.loadDriver(JDBC_DRIVER);
		// Step 2: Open a connection
		Connection conn = null;
		String sql = queryStr;
		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			QueryRunner queryRunner = new QueryRunner();
			updateRow = queryRunner.update(conn, sql);
		} catch (SQLException se) {
			// handle JDBC error
			se.printStackTrace();
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("Goodbye!");
		}
		if (updateRow > 0) {
			result = true;
		} else {
			result = false;
		}
		
		try {
			DbUtils.close(conn);
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return result;
	}

}
