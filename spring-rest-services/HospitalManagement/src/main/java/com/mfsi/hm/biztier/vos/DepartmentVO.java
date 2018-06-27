/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class DepartmentVO implements Serializable {

	private static final long serialVersionUID = 2038040671517519679L;
	
	private Long dataStoreId;

	private String departmentId;
	
	private String name;
	
	private String description;
	
	private String facilities;
	
	private HospitalVO hospital;
	
	private Set<DoctorVO> doctors;
	
	private Set<PatientVO> patients;
}
