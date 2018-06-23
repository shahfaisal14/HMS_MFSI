/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.Date;

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

	protected Long dataStoreId;
	
	protected String userId;
		
	protected String firstName;
	
	protected String middleName;
	
	protected String lastName;
		
	protected String email;
	
	protected Date dateOfBirth;
		
	protected RoleVO role;
	
	protected String description;
			
	protected Boolean isActive;
	
	protected Boolean isTerminated;
	
	public UserVO(){
		
	}

	/**
	 * 
	 * @param dataStoreId
	 * @param userId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 * @param dateOfBirth
	 * @param role
	 */
	public UserVO(Long dataStoreId, String userId, String firstName, String middleName, String lastName,
			String email, Date dateOfBirth, RoleVO role) {
		super();
		this.dataStoreId = dataStoreId;
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.isActive = true;
		this.isTerminated = false;
	}
	
}
