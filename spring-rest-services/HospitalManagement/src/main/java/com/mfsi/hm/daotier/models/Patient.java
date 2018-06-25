/**
 * 
 */
package com.mfsi.hm.daotier.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.mfsi.hm.biztier.vos.PatientStatus;
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
public class Patient extends User {

	@Transient
	private static final long serialVersionUID = -887491660578713029L;

	public Patient(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, String systemOfRecordX,
			Long versionNumber) {
		super(createdBy, modifiedBy, createdDate, modifiedDate, systemOfRecordX, versionNumber);
	}
	
	@Column(name = "patientStatus")
	private PatientStatus patientStatus;
	
	@Column(name = "appointmentDate")
	private Date appointmentDate;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private Doctor doctor;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private Hospital hospital;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private Room room;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	private Department department;
	
	@OneToMany(mappedBy = "patient")
	private Set<Appointment> appointments;
}
