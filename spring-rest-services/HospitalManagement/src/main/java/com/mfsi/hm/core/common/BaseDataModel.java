/**
 * 
 */
package com.mfsi.hm.core.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author shah
 *
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class BaseDataModel implements Serializable {

	private static final long serialVersionUID = -2700291742769326586L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dataStoreId")
	private Long dataStoreId;
	
	@Column(name = "createdBy", nullable = false, length = 1024)
	@NonNull
	private String createdBy;
	
	@Column(name = "modifiedBy", nullable = false, length = 1024)
	@NonNull
	private String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdDate", nullable = false)
	@NonNull
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedDate", nullable = false)
	@NonNull
	private Date modifiedDate;
	
	@Column(name = "systemOfRecordX", nullable = false, length = 225)
	@NonNull
	private String systemOfRecordX;

	@Version
	@Column(name = "versionNumber")
	@NonNull
	private Long versionNumber;
	
}
