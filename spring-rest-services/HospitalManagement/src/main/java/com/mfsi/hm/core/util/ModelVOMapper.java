/**
 * 
 */
package com.mfsi.hm.core.util;

import static com.mfsi.hm.core.common.Constants.IS_ACTIVE_HOSPITAL;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;

import org.springframework.beans.factory.annotation.Autowired;

import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.RoleVO;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.services.RoleDataService;

/**
 * @author shah
 *
 */
public class ModelVOMapper {

	
	@Autowired
	private static RoleDataService roleDataService;
	
	/**
	 * 
	 * @param doctor
	 * @return DoctorVO
	 */
	public static DoctorVO convertDoctorModelToVO(Doctor doctor){
		DoctorVO doctorVO = new DoctorVO();
		if(doctor != null){
			doctorVO.setUserId(doctor.getEmail());
			doctorVO.setEmail(doctor.getEmail());
			doctorVO.setFirstName(doctor.getFirstName());
			doctorVO.setMiddleName(doctor.getMiddleName());
			doctorVO.setLastName(doctor.getLastName());
			doctorVO.setHospital(doctor.getHospital());
			doctorVO.setAppointments(doctor.getAppointments());
			doctorVO.setDepartment(doctor.getDepartment());
			doctorVO.setDescription(doctor.getDescription());
			doctorVO.setPatients(doctor.getPatients());
			doctorVO.setQualifications(doctor.getQualifications());
			doctorVO.setIsActive(true);
			doctorVO.setIsTerminated(false);
			doctorVO.setRole(convertRoleModelToVO(doctor.getRole()));
		}
		
		return doctorVO;
	}
	
	/**
	 * 
	 * @param hospital
	 * @return HospitalVO
	 */
	public static HospitalVO convertHospitalModelToVO(Hospital hospital){
		HospitalVO hospitalVO = new HospitalVO();
		
		if(hospital != null){
			hospitalVO.setAddress(hospital.getAddress());
			hospitalVO.setContact(hospital.getContact());
			hospitalVO.setEmail(hospital.getEmail());
			hospitalVO.setName(hospital.getName());
			hospitalVO.setDepartments(hospital.getDepartments());
			hospitalVO.setDoctors(hospital.getDoctors());
			hospitalVO.setLaboratories(hospital.getLaboratories());
			hospitalVO.setPatients(hospital.getPatients());
			hospitalVO.setRooms(hospital.getRooms());
			hospitalVO.setSpeciality(hospital.getSpeciality());
			hospitalVO.setIsActive(hospital.getIsActive());
		}
		return hospitalVO;
	}
	
	/**
	 * 
	 * @param role
	 * @return RoleVO
	 */
	public static RoleVO convertRoleModelToVO(Role role){
		RoleVO roleVO = new RoleVO();
		if(role != null){
			roleVO.setName(role.getName());
			roleVO.setId(role.getId());
		}
		return roleVO;
	}
	
	/**
	 * 
	 * @param doctorVO
	 * @param loggedInUserId
	 * @return Doctor
	 */
	public static Doctor convertDoctorVOToModel(DoctorVO doctorVO, String loggedInUserId) {
		Doctor doctor = new Doctor();
		if(doctorVO != null){
			doctor.setUserId(doctorVO.getEmail());
			doctor.setEmail(doctorVO.getEmail());
			doctor.setFirstName(doctorVO.getFirstName());
			doctor.setMiddleName(doctorVO.getMiddleName());
			doctor.setLastName(doctorVO.getLastName());
			doctor.setHospital(doctorVO.getHospital());
			doctor.setAppointments(doctorVO.getAppointments());
			doctor.setDepartment(doctorVO.getDepartment());
			doctor.setDescription(doctorVO.getDescription());
			doctor.setPatients(doctorVO.getPatients());
			doctor.setQualifications(doctorVO.getQualifications());
			doctor.setIsActive(true);
			doctor.setIsTerminated(false);
			
			Role role = roleDataService.getRoleById(RoleAccessLevel.DOCTOR.getRole());
			doctor.setRole(role);
		}
		SystemUtil.setBaseModelValues(doctor, loggedInUserId, SYSTEM_OF_RECORDX);
		
		return doctor;
	}
	
	/**
	 * 
	 * @param hospitalVO
	 * @param userId
	 * @return Hospital
	 */
	public static Hospital convertHospitalVoToModel(HospitalVO hospitalVO, String userId){
		Hospital hospital = new Hospital();
		
		if(hospitalVO != null){
			hospital.setAddress(hospitalVO.getAddress());
			hospital.setContact(hospitalVO.getContact());
			hospital.setEmail(hospitalVO.getEmail());
			hospital.setName(hospitalVO.getName());
			hospital.setDepartments(hospitalVO.getDepartments());
			hospital.setDoctors(hospitalVO.getDoctors());
			hospital.setLaboratories(hospitalVO.getLaboratories());
			hospital.setPatients(hospitalVO.getPatients());
			hospital.setRooms(hospitalVO.getRooms());
			hospital.setSpeciality(hospitalVO.getSpeciality());
			hospital.setIsActive(IS_ACTIVE_HOSPITAL);
			SystemUtil.setBaseModelValues(hospital, userId, SYSTEM_OF_RECORDX);
		}
		return hospital;
	}

}
