package com.revature.exception;

public class DuplicateUserInDatabaseException extends RuntimeException {

	public DuplicateUserInDatabaseException() {
		this("Username not available, please make another selection");
	}

	public DuplicateUserInDatabaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateUserInDatabaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateUserInDatabaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateUserInDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
