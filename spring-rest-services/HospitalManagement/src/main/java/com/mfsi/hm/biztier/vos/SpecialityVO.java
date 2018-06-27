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
public class SpecialityVO implements Serializable {

	private static final long serialVersionUID = 1421602807346641105L;
	
	private String name;
	
	private String description;

}
