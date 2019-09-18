package com.revature.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction extends ArrayList<Transaction> {

	private long id;
	private java.sql.Date transactionDate;
	private double amt;
	private long users_id;

	
	public Transaction(long id, java.sql.Date transactionDate, double amt, long users_id) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.amt = amt;
		this.users_id = users_id;
	}

	public Transaction(long id, double amt, long users_id) {
		super();
		this.id = id;
		this.transactionDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.amt = amt;
		this.users_id = users_id;
	}

	public void setTransactionDate(java.sql.Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public double getAmt() {
		return amt;
	}


	public void setAmt(double amt) {
		this.amt = amt;
	}


	public long getUsers_id() {
		return users_id;
	}


	public void setUsers_id(long users_id) {
		this.users_id = users_id;
	}

	
	public java.sql.Date getTransactionDate() {
		return transactionDate;
	}


	@Override
	public String toString() {
		NumberFormat toCurrencyFormat = NumberFormat.getCurrencyInstance();
		return "Transaction date: " + transactionDate + " Amount: " + toCurrencyFormat.format(amt) + "]\n";
	}
	
	
}
