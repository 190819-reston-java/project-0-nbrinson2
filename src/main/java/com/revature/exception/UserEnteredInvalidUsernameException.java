package com.revature.exception;

public class UserEnteredInvalidUsernameException extends RuntimeException {

	public UserEnteredInvalidUsernameException() {
		this("User entered invalid username, please make a different selection");
	}

	public UserEnteredInvalidUsernameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidUsernameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidUsernameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserEnteredInvalidUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
