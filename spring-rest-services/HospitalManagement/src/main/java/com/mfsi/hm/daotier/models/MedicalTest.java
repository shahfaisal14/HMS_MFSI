/**
 * 
 */
package com.mfsi.hm.daotier.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class MedicalTest extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 6598592790414325758L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "charges")
	private Long charges;
	
	@ManyToOne
	private Laboratory laboratory;
}
