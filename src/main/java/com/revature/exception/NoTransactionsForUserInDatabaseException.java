package com.revature.exception;

public class NoTransactionsForUserInDatabaseException extends RuntimeException {

	public NoTransactionsForUserInDatabaseException() {
		this("You have neglected your bank account, no transactions found\n");
	}

	public NoTransactionsForUserInDatabaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoTransactionsForUserInDatabaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoTransactionsForUserInDatabaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoTransactionsForUserInDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
