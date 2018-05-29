/**
 * 
 */
package com.mfsi.hm.daotier.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class Login extends BaseDataModel {
	
	@Transient
	private static final long serialVersionUID = -8656298562200523397L;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "user", nullable = false)
	private User user;

	@Column(name = "password", nullable = false, length = 2048)
	private String password;
	
	@Column(name = "passSalt", nullable = true, length = 60)
	private String passSalt;

	@Column(name = "tempAuthCode", nullable = true, length = 200)
	private String tempAuthCode;

	@Column(name = "authCodeCreatedTime", nullable = true)
	private Date authCodeCreatedTime;

	@Column(name = "expiryDuration", nullable = true)
	private Long expiryDuration;

	@Column(name = "secretKey", nullable = true, length = 100)
	private String secretKey;
	
	public Login(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX,
			Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}

}
