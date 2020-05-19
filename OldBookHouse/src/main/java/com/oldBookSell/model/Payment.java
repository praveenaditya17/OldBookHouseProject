package com.oldBookSell.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This is Payment Class that is use to map the required
 * column in payment table
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Entity
@Table(name = "payment")
@SequenceGenerator(name="seq1", initialValue=6001, allocationSize=1)
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq1")
	private int id;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "payment_id")
	private String paymentId;
	
	@Column(name = "status")
	private String status;

	@Column(name = "created")
	private long created;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name="user_id")
	private String userId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
