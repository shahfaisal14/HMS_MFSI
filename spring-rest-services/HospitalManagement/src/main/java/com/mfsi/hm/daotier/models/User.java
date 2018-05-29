/**
 * 
 */
package com.mfsi.hm.daotier.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class User extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 257131852995544961L;
	
	@Column(name = "userId", nullable = false, length = 2048)
	private String userId;
	
	@Column(name = "firstName", nullable = false, length = 2048)
	private String firstName;
	
	@Column(name = "middleName", nullable = true, length = 2048)
	private String middleName;
	
	@Column(name = "lastName", nullable = true, length = 2048)
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 1024)
	private String email;
	
	@Column(name = "description", nullable = true, length = 4096)
	private String description;
	
	@Column(name = "isActive", nullable = false)
	private Boolean isActive;
	
	@Column(name = "isTerminated", nullable = false)
	private Boolean isTerminated;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="role", nullable= false)
	private Role role;

}
