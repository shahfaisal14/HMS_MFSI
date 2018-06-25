/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.util.Date;
import java.util.Set;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class DoctorVO extends UserVO {

	private static final long serialVersionUID = -4538831323597900818L;

	private Set<String> qualifications;
	
	private HospitalVO hospital;
	
	private DepartmentVO department;
	
	private Set<PatientVO> patients;
	
	private Set<AppointmentVO> appointments;
	
	public DoctorVO(){}

	/**
	 * 
	 * @param dataStoreId
	 * @param userId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param dateOfBirth
	 */
	public DoctorVO(Long dataStoreId, String userId, String firstName, String middleName, String lastName,
			String email, Date dateOfBirth) {
		super(dataStoreId, userId, firstName, middleName, lastName, email, dateOfBirth, new RoleVO("doctor", "Doctor"));
	}
}
