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
public class Configuration extends BaseDataModel {
	
	@Transient
	private static final long serialVersionUID = -6594946973697756043L;
	
	@Column(name = "code", nullable = false, length = 2048)
	private String code;
	
	@Column(name = "value", nullable = false, length = 2048)
	private String value;
	
	@Column(name = "description", nullable = true, length = 2048)
	private String description;

	public Configuration(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
}
