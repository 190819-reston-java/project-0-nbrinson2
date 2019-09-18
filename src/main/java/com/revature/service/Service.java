package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.exception.DuplicateUserInDatabaseException;
import com.revature.exception.NoTransactionsForUserInDatabaseException;
import com.revature.exception.NotEnoughMoneyInAccountException;
import com.revature.exception.UserEnteredInvalidPasswordException;
import com.revature.exception.UserEnteredInvalidUsernameException;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repository.TransactionDAO;
import com.revature.repository.TransactionDAOImplPJDBC;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImplPJDBC;

public class Service {
	
	private User selectedUser = null;
	private UserDAO userDAO = new UserDAOImplPJDBC();
	private List<Transaction> userTransactionList = null;
	private TransactionDAO transactionDAO = new TransactionDAOImplPJDBC();
	
	
	private static String validPasswordCharacters = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static Logger serviceLogger = Logger.getLogger(Service.class);
	
	public void loginToAccount(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public void logoutOfAccount() {
		this.selectedUser = null;
		this.userTransactionList = null;
	}
	
	public List<Transaction> getUserTransactionList() {
		return userTransactionList;
	}

	public void withdrawalMoney(double amt) throws NotEnoughMoneyInAccountException {
		if(this.selectedUser.getBalance() - amt < 0) {
			throw new NotEnoughMoneyInAccountException();
		} else {
			this.selectedUser.setBalance(this.getBalance() - amt);
			createTransactionInDB(0, -amt, selectedUser.getId());
			this.updateSelectedUserInDB();
		}	
	}
	
	public void depositMoney(double amt) {
		this.selectedUser.setBalance(this.getBalance() + amt);
		createTransactionInDB(0, amt, selectedUser.getId());
		
		this.updateSelectedUserInDB();
	}
	
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
		userTransactionList = this.transactionDAO.getTransactions(selectedUser);
		
	}
	
	public void createTransactionInDB(long id, double amt, long users_id) {
		transactionDAO.createTransaction(new Transaction(id, amt, users_id));
	}
	
	public void updateSelectedUserInDB() {
		this.userDAO.updateUser(selectedUser);
		
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	
	public User getUser(long id) {
		return userDAO.getUser(id);
	}
	
	public double getBalance() {
		double x = selectedUser.getBalance();
		return x;
	}
	
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
	
	public boolean verifyCorrectUserEnteredUsername(String userName) {
		
		try {
			userDAO.getUser(userName).getUserName();			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean verifyCorrectUserEnteredPassword(String password) {
		if (selectedUser.getUserPassword().equals(password)) {
			return true;
		}
		
		return false;
	}
	
	public List<Transaction> getTransactions() {
		userTransactionList.clear();
		userTransactionList.addAll(transactionDAO.getTransactions(this.getSelectedUser()));
		if (userTransactionList.size() == 0) {
			serviceLogger.info("No transactions found in database for user: " + selectedUser.getUserName());
			throw new NoTransactionsForUserInDatabaseException();
		}
		return userTransactionList;
	}
	
	public static boolean formatCheckerForMoney(String s) {
		boolean out = false;
		if (s.matches("[0-9]+[.][0-9][0-9]") || 
				s.matches("[0-9]+[.][0-9]") ||
				s.matches("[0-9]+")) {
			out = true;
		}
		
		serviceLogger.debug("Entered incorrect input: " + s);
		return out;
	}

	public void createAccount(String username, String password) {
	
		if(userDAO.getUser(username) == null) {
		
			if (validatePasswordAndUsername(username) == true && 
				validatePasswordAndUsername(password) == true) {
					serviceLogger.debug("New user created with username: " + username +
										" and with password: " + password);
					User newUser = new User(0, username, password, 0);
					userDAO.createUser(newUser);
					serviceLogger.info("Account successfully created!");
			} else {
				if (validatePasswordAndUsername(username) == false) {
					serviceLogger.debug("Invalid input from user for username: " + username);
					throw new UserEnteredInvalidUsernameException();	
				} else {
					serviceLogger.debug("Invalid input from user for password: " + password);
					throw new UserEnteredInvalidPasswordException();
				}			
			}
		} else {
			serviceLogger.debug("Invalid input from user, " + username + " is a duplicate");
			throw new DuplicateUserInDatabaseException();
		}
	}	


	public static boolean validatePasswordAndUsername(String userInput) {
		boolean out = true;
		for (int i = 0; i < userInput.length(); i++) {
			if (i+1 == userInput.length()) {
				out = validPasswordCharacters.contains(userInput.substring(i));
				if (out == false) {
					serviceLogger.debug("Validation changed to false by character: " + userInput.substring(i));
					return out;
				}
			} else {
				out = validPasswordCharacters.contains(userInput.substring(i, i+1));
				if (out == false) {
					serviceLogger.debug("Validation changed to false by character: " + userInput.substring(i, i+1));
					return out;
				}
			}
		}
		
		if (userInput.length() == 0) {
			serviceLogger.debug("Validation changed to false due to no input");
			out = false;
		}
		
		return out;
	}
}