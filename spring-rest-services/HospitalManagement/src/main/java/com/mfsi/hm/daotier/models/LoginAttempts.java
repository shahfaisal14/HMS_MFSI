package com.mfsi.hm.daotier.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

@Entity
@Data
public class LoginAttempts extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 789590669616463988L;
	
	@Column(name = "userId", nullable = false, length = 2048)
	private String userId;

	@Column(name = "wrongAttempts", nullable = false, length = 2)
	private Long wrongAttempts;
	
	public LoginAttempts(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate,
			String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
}
