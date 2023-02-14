package com.douzone.jblog.exception;

public class UserRepositoryException extends RuntimeException {
	private static final long serialVersionID = 1L;
	
	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}
	public UserRepositoryException(String message) {
		super(message);
	}
}
