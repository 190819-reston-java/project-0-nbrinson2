package com.revature.exception;

public class UserEnteredInvalidPasswordException extends RuntimeException {

	public UserEnteredInvalidPasswordException() {
		this("User entered invalid password, please make a different selection");
	}

	public UserEnteredInvalidPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidPasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
