/**
 * 
 */
package com.mfsi.hm.daotier.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Entity
@Data
public class Head extends User {

	@Transient
	private static final long serialVersionUID = 4111186295733275294L;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="hospital", nullable=false)
	private Hospital hospital;

}
