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
public class Module extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 1378144216012148878L;
	
	@Column(name = "moduleId", unique = true, nullable = false, length = 225)
	private String moduleId;
	
	@Column(name = "name", nullable = true, length = 1024)
	private String name;
	
	@Column(name = "description", nullable = true, length = 2048)
	private String description;
	
	@Column(name = "visibleName", nullable = true, length = 150)
	private String visibleName;
	
	@Column(name = "relativeUrl", nullable = false, length = 500)
	private String relativeUrl;
	
	@Column(name = "orderNumber", nullable = true, length = 3)
	private Integer orderNumber;

	@Column(name = "imageUrl", length = 100,  nullable = true)
	private String imageUrl;
	
	@Column(name = "canCreate", nullable = true)
	private Boolean canCreate;
	
	@Column(name = "canDelete", nullable = true)
	private Boolean canDelete;
	
	@Column(name = "canUpdate", nullable = true)
	private Boolean canUpdate;
	
	@Column(name = "canRead", nullable = true)
	private Boolean canRead;
	
	public Module(String createdBy, String modifiedBy, Date createdDate, Date modfiedDate, String systemOfRecordX, Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modfiedDate, systemOfRecordX, versionNumber);
	}
}
