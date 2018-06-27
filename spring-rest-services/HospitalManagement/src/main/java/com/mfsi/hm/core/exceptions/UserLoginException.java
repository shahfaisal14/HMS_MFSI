/**
 * 
 */
package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

/**
 * @author shah
 *
 */
public class UserLoginException extends BaseException {
	
	@Transient
	private static final long serialVersionUID = 449725356924161958L;
	
	private Boolean shouldLock = false;

	public UserLoginException(String code, String message) {
		super(code, message);
	}

	public Boolean getShouldLock() {
		return shouldLock;
	}

	public void setShouldLock(Boolean shouldLock) {
		this.shouldLock = shouldLock;
	}
}
