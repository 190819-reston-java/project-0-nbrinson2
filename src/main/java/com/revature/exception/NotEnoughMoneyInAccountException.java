package com.revature.exception;

public class NotEnoughMoneyInAccountException extends RuntimeException {

	public NotEnoughMoneyInAccountException() {
		this("You may want to spend less on fancy clothing");
	}
	
	public NotEnoughMoneyInAccountException(String message) {
		super(message);
	}
}
