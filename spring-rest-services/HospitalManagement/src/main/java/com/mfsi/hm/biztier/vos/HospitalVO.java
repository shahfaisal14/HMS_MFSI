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
public class HospitalVO implements Serializable {
	
	private static final long serialVersionUID = 2028832628205536214L;

	private Long dataStoreId;

	private String name;
	
	private String address;
	
	private String contact;
	
	private String email;
	
	private Boolean isActive;
	
	private SpecialityVO speciality;
	
	private Set<DepartmentVO> departments;
	
	private Set<LaboratoryVO> laboratories;
	
	private Set<RoomVO> rooms;
	
	private Set<DoctorVO> doctors;
	
	private Set<PatientVO> patients;
	
	private HeadVO head;
}
