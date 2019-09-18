package com.revature.exception;

public class AccessDeniedUserNotAdminException extends RuntimeException {

	public AccessDeniedUserNotAdminException() {
		this("************ACCESS DENIED, REQUIRES ADMINISTRATIVE PRIVELEGES*************");
	}
	
	public AccessDeniedUserNotAdminException(String message) {
		super(message);
	}
}
