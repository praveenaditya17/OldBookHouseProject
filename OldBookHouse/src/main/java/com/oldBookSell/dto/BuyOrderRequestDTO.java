package com.oldBookSell.dto;

/**
 * This is BuyOrderRequestDTO that is use to transfer data in
 * BuyOrderRequestServiceImpl class.
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

public class BuyOrderRequestDTO {
	
	private int buyOrderRequestId;
	private String bookName;// title
	private String authors;
	private String smallThumbnail;// small image size
	private float amount;
	private int quantity;
	private String checkStatus;
	private int bookId;
	private String userId;
	private String addressId;
	private int dileveryPersonId;
	private String status="Failed";
	private String transactionId;

	public int getBuyOrderRequestId() {
		return buyOrderRequestId;
	}

	public void setBuyOrderRequestId(int buyOrderRequestId) {
		this.buyOrderRequestId = buyOrderRequestId;
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

	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}	

}
