/**
 * 
 */
package com.mfsi.hm.biztier.vos;

import java.util.Date;
import java.util.Set;

import com.mfsi.hm.daotier.models.Appointment;
import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Room;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shah
 *
 */
@Data
@NoArgsConstructor
public class PatientVO extends UserVO {

	private static final long serialVersionUID = 2380117257256507301L;
	
	private Long dataStoreId;

	private PatientStatus patientStatus;
	
	private Date appointmentDate;
	
	private Doctor doctor;
	
	private Hospital hospital;
	
	private Room room;
	
	private Department department;
	
	private Set<Appointment> appointments;
}
