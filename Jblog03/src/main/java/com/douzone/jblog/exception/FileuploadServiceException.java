package com.douzone.jblog.exception;

public class FileuploadServiceException extends RuntimeException {
	public FileuploadServiceException(String message) {
		super(message);
	}
	
	public FileuploadServiceException() {
		super("Fileupload Exception Occurs");
	}
}
