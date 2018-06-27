package com.mfsi.hm.daotier.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="hospital_data_store_id", nullable= false)
	private Hospital hospital;
	
	@OneToMany(mappedBy = "department")
	private Set<Doctor> doctors;
	
	@OneToMany(mappedBy = "department")
	private Set<Patient> patients;
}
