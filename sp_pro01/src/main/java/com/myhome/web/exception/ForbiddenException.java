package com.myhome.web.exception;

public class ForbiddenException extends Exception {
	public ForbiddenException() {}
	
	public ForbiddenException(String message) {
		super(message, null);
	}
}
