/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.util.Date;
import java.util.Set;

import javax.persistence.Transient;

import com.mfsi.hm.daotier.models.Appointment;
import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Patient;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class DoctorVO extends UserVO {

	@Transient
	private static final long serialVersionUID = -4538831323597900818L;

	private Set<String> qualifications;
	
	private Hospital hospital;
	
	private Department department;
	
	private Set<Patient> patients;
	
	private Set<Appointment> appointments;
	
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
