package com.oldBookSell.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This is Book Class that is use to map the required
 * column in book table
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Entity
@Table(name = "book")
@SequenceGenerator(name = "sequence", initialValue = 50001, allocationSize = 1)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	private int bookId;

	@Column(name = "book_name")
	private String bookName;// title

	@Column(name = "authors")
	private String authors;

	@Column(name = "description", length = 256)
	private String description;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "publishedDate")
	private String publishedDate;

	@Column(name = "categories")
	private String categories;

	@Column(name = "isbn_type_10")
	private String isbnType10;

	@Column(name = "isbnNo1")
	private String isbnNo1;

	@Column(name = "isbn_type_13")
	private String isbnType13;

	@Column(name = "isbnNo2")
	private String isbnNo2;

	@Column(name = "smallThumbnail")
	private String smallThumbnail;// small image size

	@Column(name = "thumbnail")
	private String thumbnail;// large image size

	@Column(name = "amount")
	private float amount;

	@Column(name = "currencyCode")
	private String currencyCode;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "book_status")
	private String bookStatus="sell";

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

	public Book() {
		super();
	}

}
