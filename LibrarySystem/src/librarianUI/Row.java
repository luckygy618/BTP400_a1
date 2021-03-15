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

import javafx.beans.property.SimpleStringProperty;

/**
 * This is a class called Row that contains the data of the book table in the
 * mysql database. When the viewAllBooks method is called, this Row object will
 * be showed on the scrollable table of the page.
 * 
 * @author GuoyuCao
 * @version 1.0
 * @since 1.0
 */
public class Row {
	SimpleStringProperty book_id;
	SimpleStringProperty isbn;
	SimpleStringProperty title;
	SimpleStringProperty author;
	SimpleStringProperty borrowed_by;
	SimpleStringProperty available;
	SimpleStringProperty hold_by;

	/**
	 * This is the constructor of the Row class
	 * 
	 * @param bid This is Book ID
	 * @param isb This is Librarian ID
	 * @param til This is books' Title
	 * @param aut This is books' author
	 * @param bor This is id of who borrowed the book
	 * @param ava This is a status that shows if the book is available
	 * @param hol This is a status to show if the book is on hold by librarian
	 */
	Row(String bid, String isb, String til, String aut, String bor, String ava, String hol) {
		book_id = new SimpleStringProperty(bid);
		isbn = new SimpleStringProperty(isb);
		title = new SimpleStringProperty(til);
		author = new SimpleStringProperty(aut);
		borrowed_by = new SimpleStringProperty(bor);
		available = new SimpleStringProperty(ava);
		hold_by = new SimpleStringProperty(hol);

	}

	/**
	 * This is the query method that returns the get() method to get the book_id.
	 * This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "book_id" /></cellValueFactory>}
	 * 
	 * @return book_id.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getBook_id() {
		return book_id.get();
	}

	/**
	 * This is the query method that returns the get() method to get the ISBN. This
	 * method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "isbn" /></cellValueFactory>}
	 * 
	 * @return isbn.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getIsbn() {
		return isbn.get();
	}

	/**
	 * This is the query method that returns the get() method to get the book's
	 * title. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "title" /></cellValueFactory>}
	 * 
	 * @return title.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getTitle() {
		return title.get();
	}

	/**
	 * This is the query method that returns the get() method to get the book's
	 * author. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "author" /></cellValueFactory>}
	 * 
	 * @return author.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getAuthor() {
		return author.get();
	}

	/**
	 * This is the query method that returns the get() method to get who borrowed
	 * the book. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "borrowed_by" /></cellValueFactory>}
	 * 
	 * @return borrowed_by.get() This is the get() method so that the
	 *         cellValueFactory could get the value and display the value on the
	 *         scrollable table.
	 */
	public String getBorrowed_by() {
		return borrowed_by.get();
	}

	/**
	 * This is the query method that returns the get() method to get the book's
	 * available status. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "available" /></cellValueFactory>}
	 * 
	 * @return available.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getAvailable() {
		return available.get();
	}

	/**
	 * This is the query method that returns the get() method to get which librarian
	 * holds the book. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "hold_by" /></cellValueFactory>}
	 * 
	 * @return hold_by.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getHold_by() {
		return hold_by.get();
	}

}
