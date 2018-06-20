/**
 * 
 */
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
public class Laboratory extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = -6635638171800382552L;
	
	@Column(name = "laboratoryId")
	private String laboratoryId;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany
	private Set<MedicalTest> medicalTests;
	
	@ManyToOne
	private Hospital hospital;
}
