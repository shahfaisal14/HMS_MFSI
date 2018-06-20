/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class TokenValidationVO {
	
	private String message;
	
	private String responseType ;
	
	private UserVO user ;
	
	private String errorCode;

}
