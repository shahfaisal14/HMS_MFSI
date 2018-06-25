/**
 * 
 */
package com.mfsi.hm.daotier.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
public class Hospital extends BaseDataModel{
	
	@Transient
	private static final long serialVersionUID = 6814952630685334906L;

	@Column(name = "name", unique = true, nullable = false, length = 512)
	private String name;
	
	@Column(name = "address", unique = true, nullable = true, length = 512)
	private String address;
	
	@Column(name = "contact", unique = true, nullable = true, length = 512)
	private String contact;
	
	@Column(name = "email", unique = true, nullable = false, length = 512)
	private String email;
	
	@Column(name = "isActive")
	private Boolean isActive;
	
	@Column(name = "speciality")
	private Speciality speciality;
	
	@OneToMany(mappedBy = "hospital")
	private Set<Department> departments;
	
	@OneToMany(mappedBy = "hospital")
	private Set<Laboratory> laboratories;
	
	@OneToMany(mappedBy = "hospital")
	private Set<Room> rooms;
	
	@OneToMany(mappedBy = "hospital")
	private Set<Doctor> doctors;
	
	@OneToMany(mappedBy = "hospital")
	private Set<Patient> patients;

}
