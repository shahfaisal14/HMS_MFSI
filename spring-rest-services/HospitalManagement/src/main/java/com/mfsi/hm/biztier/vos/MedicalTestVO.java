/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;

import javax.persistence.Transient;

import com.mfsi.hm.daotier.models.Laboratory;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class MedicalTestVO implements Serializable {

	private static final long serialVersionUID = -1070231668695872888L;
	
	private Long dataStoreId;

	private String name;

	private String type;
	
	private String description;
	
	private Long charges;
	
	private LaboratoryVO laboratory;
}
