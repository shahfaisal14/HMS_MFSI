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
public class UserVO implements Serializable {

	@Transient
	private static final long serialVersionUID = -8753645127744337493L;

	private Long dataStoreId;
	
	private String userId;
		
	private String firstName;
	
	private String middleName;
	
	private String lastName;
						
	private String password;
		
	private String email;
		
	private RoleVO role;
			
	private Boolean isActive;
	
	private Boolean isTerminated;
	
}
