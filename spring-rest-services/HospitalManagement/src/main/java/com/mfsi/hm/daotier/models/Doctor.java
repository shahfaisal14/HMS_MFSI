/**
 * 
 */
package com.mfsi.hm.daotier.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Doctor extends User {
	
	@Transient
	private static final long serialVersionUID = 300054063368430935L;
	
	Doctor(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, String systemOfRecordX, Long versionNumber) {
		new User(createdBy, modifiedBy, createdDate, modifiedDate, systemOfRecordX, versionNumber);
	}
	
	@ElementCollection
	@CollectionTable(name = "DoctorQualificationsMapping", joinColumns = @JoinColumn(name = "doctorQualificationId"))
	@Column(name = "qualifications")
	private Set<String> qualifications;
	
	@ManyToOne
	private Hospital hospital;
	
	@ManyToOne
	private Department department;
	
	@OneToMany(mappedBy = "doctor")
	private Set<Patient> patients;
	
	
	@OneToMany(mappedBy = "doctor")
	private Set<Appointment> appointments;
}
