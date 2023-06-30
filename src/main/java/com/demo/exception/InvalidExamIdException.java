package com.demo.exception;

public class InvalidExamIdException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidExamIdException(String message) {
		super(message);
	}
}
