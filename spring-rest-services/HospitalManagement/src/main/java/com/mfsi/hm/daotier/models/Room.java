/**
 * 
 */
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
public class Room extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = -6547791157349510671L;

	@Column(name = "roomNumber")
	private String roomNumber;
	
	@Column(name = "totalBeds")
	private Integer totalBeds;
	
	@Column(name = "occupiedBeds")
	private Integer occupiedBeds;
	
	@Column(name = "availableBeds")
	private Integer availableBeds;
	
	@Column(name = "chargesPerDay")
	private String chargesPerDay;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name="hospital_data_store_id", nullable=false)
	Hospital hospital;
	
	@OneToMany(mappedBy = "room")
	private Set<Patient> patients;

}
