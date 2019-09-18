package com.revature.model;

public class User {

	private long id;
	private String userName;
	private String userPassword;
	private double balance;
	
	
	public User(long id, String userName, String userPassword, double balance) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.setBalance(balance);;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", balance=" + balance
				+ "]";
	}
	

}
