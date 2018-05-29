/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class ValidateUserVO implements Serializable {

	@Transient
	private static final long serialVersionUID = 5930426081723888573L;

	private String message;

	private String authToken;

}
