/**
 * 
 */
package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

/**
 * @author shah
 *
 */
public class AESException extends BaseException {
	
	@Transient
	private static final long serialVersionUID = 2908622063610710748L;

	public AESException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}
}
