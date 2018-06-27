/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class TokenValidationVO implements Serializable {
	
	private static final long serialVersionUID = 452135574940510501L;

	private String message;
	
	private String responseType ;
	
	private UserVO user ;
	
	private String errorCode;

}
