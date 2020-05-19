package com.oldBookSell.dto;

/**
 * This is SellOrderRequestDTO that is use to transfer data in
 * SellOrderRequestDTO class.	
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

public class SellOrderRequestDTO {

	/* SellOrderRequest class Data Member*/
	private int sellOrderRequestId;
    private String book_name;//title
    private String authors;
    private String description;
    private String publisher;
    private String publishedDate; 
    private String categories="all";
    private String isbn_type_10;
    private String isbnNo1; 
    private String isbn_type_13; 
    private String isbnNo2;
    private String smallThumbnail;//small image size 
    private String thumbnail;//large image size
    private float amount=0.0f;
    private String currencyCode="IND";
    private String addressId;
    private String user_id;
    private int quantity=1;
    private String check_status="ProcessingOrder";
    private int dileveryPersonId;
    private String feedBack;
 
    public String getBook_name() {
		return book_name;
	}
	
    public void setBook_name(String book_name) {
		this.book_name = book_name;
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
	
    public String getIsbn_type_10() {
		return isbn_type_10;
	}
	
    public void setIsbn_type_10(String isbn_type_10) {
		this.isbn_type_10 = isbn_type_10;
	}
	
    public String getIsbnNo1() {
		return isbnNo1;
	}
	
    public void setIsbnNo1(String isbnNo1) {
		this.isbnNo1 = isbnNo1;
	}
	
    public String getIsbn_type_13() {
		return isbn_type_13;
	}
	
    public void setIsbn_type_13(String isbn_type_13) {
		this.isbn_type_13 = isbn_type_13;
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
	
	public String getCheck_status() {
		return check_status;
	}
	
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getAddressId() {
		return addressId;
	}
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public int getDileveryPersonId() {
		return dileveryPersonId;
	}
	
	public void setDileveryPersonId(int dileveryPersonId) {
		this.dileveryPersonId = dileveryPersonId;
	}
	
	public int getSellOrderRequestId() {
		return sellOrderRequestId;
	}
	
	public void setSellOrderRequestId(int sellOrderRequestId) {
		this.sellOrderRequestId = sellOrderRequestId;
	}
	
	public String getFeedBack() {
		return feedBack;
	}
	
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	public SellOrderRequestDTO() {
		super();
	}
}
