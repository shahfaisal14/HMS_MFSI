package com.mfsi.hm.daotier.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Appointment extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = -3189448461000774218L;
	
	Appointment(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, String systemOfRecordX, Long versionNumber) {
		new BaseDataModel(createdBy, modifiedBy, createdDate, modifiedDate, systemOfRecordX, versionNumber);
	}
	
	@ManyToOne
	private Doctor doctor;
	
	@ManyToOne
	private Patient patient;
	
	@Column(name = "appointmentDate")
	private LocalDateTime date;

}
