/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Transient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class LaboratoryVO implements Serializable {

	private static final long serialVersionUID = -3900807280272064129L;
	
	private Long dataStoreId;

	private String laboratoryId;
	
	private String name;
	
	private Set<MedicalTestVO> medicalTests;
	
	private HospitalVO hospital;
}
