/**
 * 
 */
package com.mfsi.hm.core.util;

import static com.mfsi.hm.core.common.Constants.IS_ACTIVE_HOSPITAL;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;

import org.springframework.beans.factory.annotation.Autowired;

import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.RoleVO;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Head;
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
			doctorVO.setDataStoreId(doctor.getDataStoreId());
			doctorVO.setUserId(doctor.getEmail());
			doctorVO.setEmail(doctor.getEmail());
			doctorVO.setFirstName(doctor.getFirstName());
			doctorVO.setMiddleName(doctor.getMiddleName());
			doctorVO.setLastName(doctor.getLastName());
			doctorVO.setDescription(doctor.getDescription());
			doctorVO.setQualifications(doctor.getQualifications());
			doctorVO.setIsActive(true);
			doctorVO.setIsTerminated(false);
			doctorVO.setRole(convertRoleModelToVO(doctor.getRole()));
		}
		
		return doctorVO;
	}
	
	public static HeadVO convertHeadModelToVO(Head head){
		HeadVO headVO = new HeadVO();
		
		if(headVO != null){
			headVO.setDataStoreId(head.getDataStoreId());
			headVO.setUserId(head.getEmail());
			headVO.setEmail(head.getEmail());
			headVO.setFirstName(head.getFirstName());
			headVO.setMiddleName(head.getMiddleName());
			headVO.setLastName(head.getLastName());
			headVO.setIsActive(true);
			headVO.setIsTerminated(false);
			headVO.setHospital(convertHospitalModelToVO(head.getHospital()));
			headVO.setRole(convertRoleModelToVO(head.getRole()));
		}
		return headVO;
	}
	
	/**
	 * 
	 * @param hospital
	 * @return HospitalVO
	 */
	public static HospitalVO convertHospitalModelToVO(Hospital hospital){
		HospitalVO hospitalVO = new HospitalVO();
		
		if(hospital != null){
			hospitalVO.setDataStoreId(hospital.getDataStoreId());
			hospitalVO.setAddress(hospital.getAddress());
			hospitalVO.setContact(hospital.getContact());
			hospitalVO.setEmail(hospital.getEmail());
			hospitalVO.setName(hospital.getName());
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
			doctor.setDataStoreId(doctorVO.getDataStoreId());
			doctor.setUserId(doctorVO.getEmail());
			doctor.setEmail(doctorVO.getEmail());
			doctor.setFirstName(doctorVO.getFirstName());
			doctor.setMiddleName(doctorVO.getMiddleName());
			doctor.setLastName(doctorVO.getLastName());
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
	 * @param headVO
	 * @param loggedInUserId
	 * @return Head
	 */
	public static Head convertHeadVOToModel(HeadVO headVO, String loggedInUserId){
		Head head = new Head();
		
		if(headVO != null){
			head.setDataStoreId(headVO.getDataStoreId());
			head.setUserId(headVO.getEmail());
			head.setEmail(headVO.getEmail());
			head.setFirstName(headVO.getFirstName());
			head.setMiddleName(headVO.getMiddleName());
			head.setLastName(headVO.getLastName());
			head.setIsActive(true);
			head.setIsTerminated(false);
			
			Role role = roleDataService.getRoleById(RoleAccessLevel.HEAD.getRole());
			head.setRole(role);
			
		}
		SystemUtil.setBaseModelValues(head, loggedInUserId, SYSTEM_OF_RECORDX);
		return head;
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
			hospital.setDataStoreId(hospitalVO.getDataStoreId());
			hospital.setAddress(hospitalVO.getAddress());
			hospital.setContact(hospitalVO.getContact());
			hospital.setEmail(hospitalVO.getEmail());
			hospital.setName(hospitalVO.getName());
			hospital.setIsActive(IS_ACTIVE_HOSPITAL);
			SystemUtil.setBaseModelValues(hospital, userId, SYSTEM_OF_RECORDX);
		}
		return hospital;
	}

}
