package com.revature.exception;

public class TooManyLoginAttemptsProgramExitsException extends RuntimeException{

	public TooManyLoginAttemptsProgramExitsException() {
		this("Too many login attempts, please try again later. Goodbye!");
	}
	
	public TooManyLoginAttemptsProgramExitsException(String message) {
		super(message);
	}
	
}
