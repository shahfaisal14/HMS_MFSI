/**
 * 
 */
package com.mfsi.hm.core.exceptions;

import javax.persistence.Transient;

/**
 * @author shah
 *
 */
public class AccessDeniedException extends BaseException {
	
	@Transient
	private static final long serialVersionUID = -1054965371629657065L;

	public AccessDeniedException(String code, String message) {
		super(code, message);
	}
}
