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

/**
 * This is a class called ConnectInfo that contains the connection information for connecting to the databse.
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class ConnectInfo {
	
	/**
	 * This is the Mysql JDBC driver
	 */
	private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * This is the URL of the database. In this app, I am using a Mysql databse that running on my Vultr cloud instance
	 */
	private String DB_URL = "jdbc:mysql://155.138.131.160:3306/BTP400?serverTimezone=UTC";
	/**
	 * This is the user name
	 */
	private String USER = "user1";
	/**
	 * This is the password.
	 */
	private String PASS = "btp400";
	
	/**
	 * This is the query method that returns the JDBC driver 
	 * @return JDBC_DRIVER This  is the Mysql JDBC driver
	 */
	public String getDriver() {
		return JDBC_DRIVER;
	}

	/**
	 * This is the query method that returns the URL of the database
	 * @return DB_URL This  is the Mysql  URL address
	 */
	public String getURL() {
		return DB_URL;
	}
	/**
	 * This is the query method that returns the username of the database
	 * @return USER This  is the username of the database
	 */
	public String getUser() {
		return USER;
	}
	
	/**
	 * This is the query method that returns the password of the database
	 * @return USER This  is the password of the database
	 */
	public String getPassword() {
		return PASS;
	}
	
	/**
	 * This is the modifier method that set the JDBC driver of the database
	 * @param str This  is the  JDBC driver string of the database
	 */
	public void setDriver(String str) {
		 JDBC_DRIVER = str;
	}
	
	/**
	 * This is the modifier method that set the URL of the database
	 * @param str This  is the  URL string of the database
	 */
	public void setURL(String str) {
		 DB_URL = str;;
	}
	/**
	 * This is the modifier method that set the username of the database
	 * @param str This  is the  username string of the database
	 */
	public void setUser(String str) {
		 USER = str;
	}
	/**
	 * This is the modifier method that set the password of the database
	 * @param str This  is the password string of the database
	 */
	public void setPassword(String str) {
		 PASS = str;
	}

}
