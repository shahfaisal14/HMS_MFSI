package com.mfsi.hm.daotier.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class Department extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 1531876059901740616L;
	
	@Column(name = "departmentId")
	private String departmentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "facilities")
	private String facilities;
	
	@ManyToOne
	private Hospital hospital;
	
	@OneToMany(mappedBy = "department")
	private Set<Doctor> doctors;
	
	@OneToMany(mappedBy = "department")
	private Set<Patient> patients;
}
