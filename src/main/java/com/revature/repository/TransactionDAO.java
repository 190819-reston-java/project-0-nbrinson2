package com.revature.repository;

import java.util.List;

import com.revature.model.Transaction;
import com.revature.model.User;

public interface TransactionDAO {

	Transaction getTransaction(long id);
	Transaction getTransaction(double amt);
	
	List<Transaction> getTransactions(User u);
	boolean createTransaction(Transaction t);
	boolean deleteTransaction();
	boolean updateTransaction();
	
}
