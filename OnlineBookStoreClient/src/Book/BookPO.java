package Book;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class BookPO implements Serializable {
	private String name;
	private String ISBN;
	private String author;
	private String press;
	private String description;
	private int directoryID;
	private Calendar publishDate;
	private double price;
	private double specialPrice;

	public BookPO(String name, String ISBN, String author, String press,
			String description, int directoryID, Calendar publishDate,
			double price, double specialPrice) {
		super();
		this.name = name;
		this.ISBN = ISBN;
		this.author = author;
		this.press = press;
		this.description = description;
		this.directoryID = directoryID;
		this.publishDate = publishDate;
		this.price = price;
		this.specialPrice = specialPrice;
	}

	public String getName() {
		return name;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public String getPress() {
		return press;
	}

	public String getDescription() {
		return description;
	}

	public int getDirectoryID() {
		return directoryID;
	}

	public Calendar getPublishDate() {
		return publishDate;
	}

	public double getPrice() {
		return price;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}
}
