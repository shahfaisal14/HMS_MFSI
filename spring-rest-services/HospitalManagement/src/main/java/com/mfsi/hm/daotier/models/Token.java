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

/**
 * @author shah
 *
 */
@Entity
@Data
public class Token extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 4281969583262495691L;
	
	@Column(name="userId", nullable=false, length=2048)
	private String userId;
	
	@Column(name="token", nullable=false, length=60)
	private String token;

	public Token(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
}
