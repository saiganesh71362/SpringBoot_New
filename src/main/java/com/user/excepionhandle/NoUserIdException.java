package com.user.excepionhandle;

public class NoUserIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoUserIdException(String message) {
		super(message);
	}
}
