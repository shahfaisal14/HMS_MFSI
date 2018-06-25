/**
 * 
 */
package com.mfsi.hm.daotier.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.mfsi.hm.core.common.BaseDataModel;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class Speciality extends BaseDataModel {

	@Transient
	private static final long serialVersionUID = 5999300725725052575L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

}
