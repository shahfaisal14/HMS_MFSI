/**
 * 
 */
package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

/**
 * @author shah
 *
 */
public class RequiredParameterException extends BaseException {

	@Transient
	private static final long serialVersionUID = 7239404685646668171L;

	public RequiredParameterException(String code, String message) {
		super(code, message);
	}

}
