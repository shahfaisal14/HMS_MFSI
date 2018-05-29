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
	
	private String message = null;
	
	private String responseType = null;
	
	private UserVO user = null;
	
	private String errorCode = null;

}
