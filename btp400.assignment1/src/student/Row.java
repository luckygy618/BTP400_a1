package student;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Row class. This class represents a row in sql and is used to format sql properly.
 * 
 * @author Neil An
 * @version 1.0
 * @since 1.0
 */
public class Row {
	//Sql properties to be used. Title, author, description, ISBN, availability, and year
	//published are needed.
	SimpleStringProperty title;
	SimpleStringProperty author;
	SimpleStringProperty description;
	SimpleStringProperty isbn;
	SimpleStringProperty available;
	SimpleStringProperty year_pub;

	/**
	 * This is the constructor of the Row class
	 * @param til The title of the book
	 * @param aut The author of the book
	 * @param desc The description of the book
	 * @param isb The isbn number of the book
	 * @param year The year of the book
	 * @param ava The availability of the book
	 */
	Row(String til, String aut, String desc, String isb, String year, String ava) {
		title = new SimpleStringProperty(til);
		author = new SimpleStringProperty(aut);
		description = new SimpleStringProperty(desc);
		isbn = new SimpleStringProperty(isb);
		year_pub = new SimpleStringProperty(year);
		available = new SimpleStringProperty(ava);
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
	 * This is the query method that returns the get() method to get the book's
	 * description. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "description" /></cellValueFactory>}
	 * 
	 * @return description.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getDescription() {
		return description.get();
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
	 * This is the query method that returns the get() method to get the book's
	 * year_pub. This method has fixed naming rules that requires by
	 * {@code <cellValueFactory><PropertyValueFactory property=
	 * "year_pub" /></cellValueFactory>}
	 * 
	 * @return year_pub.get() This is the get() method so that the cellValueFactory
	 *         could get the value and display the value on the scrollable table.
	 */
	public String getYear_pub() {
		return year_pub.get();
	}

}
