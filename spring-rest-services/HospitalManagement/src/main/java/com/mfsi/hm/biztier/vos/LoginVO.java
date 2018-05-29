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
public class LoginVO implements Serializable {

	private static final long serialVersionUID = -4428734452371674528L;
	
	private String userCode;
	
	private String passCode;

}
