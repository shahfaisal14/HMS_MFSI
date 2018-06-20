/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Laboratory;
import com.mfsi.hm.daotier.models.Patient;
import com.mfsi.hm.daotier.models.Room;
import com.mfsi.hm.daotier.models.Speciality;

import lombok.Data;

/**
 * @author shah
 *
 */
@Data
public class HospitalVO {
	
	private Long hospitalId;

	private String name;
	
	private String address;
	
	private String contact;
	
	private String email;
	
	private Boolean isActive;
	
	private Speciality speciality;
	
	private Set<Department> departments;
	
	private Set<Laboratory> laboratories;
	
	private Set<Room> rooms;
	
	private Set<Doctor> doctors;
	
	private Set<Patient> patients;
}
