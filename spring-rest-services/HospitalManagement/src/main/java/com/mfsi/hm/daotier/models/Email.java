/**
 * 
 */
package com.mfsi.hm.daotier.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Email extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = -7022742196094404368L;
	
	public Email(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
	
	@Column(name = "host", nullable = false, length = 512)
	private String host;
	
	@Column(name = "port", nullable = false)
	private Integer port;
	
	@Column(name = "fromEmail", nullable = false, length = 1024)
	private String fromEmail;
	
	@Column(name = "fromName", nullable = false, length = 1024)
	private String fromName;
	
	@Column(name = "userName", nullable = false, length = 1024)
	private String userName;
	
	@Column(name = "password", nullable = false, length = 1024)
	private String password;
	
	@Column(name = "auth", nullable = false)
	private Boolean auth;
	
	@Column(name = "starttls", nullable = false)
	private Boolean starttls;
	
	@Column(name = "isActive", nullable = false)
	private Boolean isActive;
}
