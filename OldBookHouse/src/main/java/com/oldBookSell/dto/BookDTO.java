package com.oldBookSell.dto;

/**
 * This is BookDTO that is use to transfer data in
 * BookServiceImpl class.	
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

public class BookDTO {
	
	private int bookId;
	private String bookName;// title
	private String authors;
	private String description;
	private String publisher;
	private String publishedDate;
	private String categories;
	private String isbnType10;
	private String isbnNo1;
	private String isbnType13;
	private String isbnNo2;
	private String smallThumbnail;// small image size
	private String thumbnail;// large image size
	private float amount;
	private String currencyCode;
	private int quantity;
	private String bookStatus = "sell";

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getIsbnType10() {
		return isbnType10;
	}

	public void setIsbnType10(String isbnType10) {
		this.isbnType10 = isbnType10;
	}

	public String getIsbnNo1() {
		return isbnNo1;
	}

	public void setIsbnNo1(String isbnNo1) {
		this.isbnNo1 = isbnNo1;
	}

	public String getIsbnType13() {
		return isbnType13;
	}

	public void setIsbnType13(String isbnType13) {
		this.isbnType13 = isbnType13;
	}

	public String getIsbnNo2() {
		return isbnNo2;
	}

	public void setIsbnNo2(String isbnNo2) {
		this.isbnNo2 = isbnNo2;
	}

	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public BookDTO() {
		super();
	}
	
}
