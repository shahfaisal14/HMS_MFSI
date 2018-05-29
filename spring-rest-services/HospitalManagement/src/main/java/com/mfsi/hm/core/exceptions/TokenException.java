package com.mfsi.hm.core.exceptions;

public class TokenException extends BaseException {

	private static final long serialVersionUID = 3499092673084473919L;

	public TokenException(String code, String message) {
		super(code, message);	
	}
	
	public TokenException(String code, String message, Throwable cause) {
		super(code, message, cause);	
	}

}
