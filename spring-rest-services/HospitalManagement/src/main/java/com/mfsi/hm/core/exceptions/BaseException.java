/**
 * 
 */
package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

/**
 * @author shah
 *
 */
public class BaseException extends RuntimeException{

	@Transient
	private static final long serialVersionUID = -3431562487253616836L;
	
	private String code;
	
	private String message;
	
	private String category;
	
	private String userId;
	
	
	public BaseException(String message) {
		super(message);
		this.message = message;
	}
	
	public BaseException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public BaseException(String code, String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.code = code;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
