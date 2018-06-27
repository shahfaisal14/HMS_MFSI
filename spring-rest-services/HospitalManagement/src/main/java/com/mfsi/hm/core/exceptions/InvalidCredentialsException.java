package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

public class InvalidCredentialsException extends BaseException {
	
	@Transient
	private static final long serialVersionUID = -4656092557545184028L;

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidCredentialsException(String code, String message) {
		super(code, message);
	}
	
	public InvalidCredentialsException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}
}
