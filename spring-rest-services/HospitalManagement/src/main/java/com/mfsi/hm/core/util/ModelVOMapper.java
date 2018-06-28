/**
 * 
 */
package com.mfsi.hm.core.util;

import static com.mfsi.hm.core.common.Constants.IS_ACTIVE_HOSPITAL;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;

import org.springframework.beans.factory.annotation.Autowired;

import com.mfsi.hm.biztier.vos.DepartmentVO;
import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.LaboratoryVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.RoleVO;
import com.mfsi.hm.biztier.vos.RoomVO;
import com.mfsi.hm.biztier.vos.SpecialityVO;
import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Head;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Laboratory;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.Room;
import com.mfsi.hm.daotier.models.Speciality;
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
	 * @param department
	 * @return DepartmentVO
	 */
	public static DepartmentVO convertDepartmentModelToVO(Department department){
		DepartmentVO departmentVO = null;
		if(department != null){
			departmentVO = new DepartmentVO();
			departmentVO.setDataStoreId(department.getDataStoreId());
			departmentVO.setDepartmentId(department.getDepartmentId());
			departmentVO.setDescription(department.getDescription());
			departmentVO.setFacilities(department.getFacilities());
			departmentVO.setName(department.getName());
		}
		
		return departmentVO;
	}
	
	/**
	 * 
	 * @param doctor
	 * @return DoctorVO
	 */
	public static DoctorVO convertDoctorModelToVO(Doctor doctor){
		DoctorVO doctorVO = null;
		if(doctor != null){
			doctorVO = new DoctorVO();
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
		HeadVO headVO = null;
		
		if(head != null){
			headVO = new HeadVO();
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
		HospitalVO hospitalVO = null;
		
		if(hospital != null){
			hospitalVO = new HospitalVO();
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
	 * @param laboratory
	 * @return laboratoryVO
	 */
	public static LaboratoryVO convertLaboratoryModelToVO(Laboratory laboratory){
		LaboratoryVO laboratoryVO = null;
		
		if(laboratory != null){
			laboratoryVO = new LaboratoryVO();
			laboratoryVO.setName(laboratory.getName());
		}
		return laboratoryVO;
	}
	
	/**
	 * 
	 * @param role
	 * @return RoleVO
	 */
	public static RoleVO convertRoleModelToVO(Role role){
		RoleVO roleVO = null;
		if(role != null){
			roleVO = new RoleVO();
			roleVO.setName(role.getName());
			roleVO.setId(role.getId());
		}
		return roleVO;
	}
	
	/**
	 * 
	 * @param room
	 * @return RoomVO
	 */
	public static RoomVO convertRoomModelToVO(Room room){
		RoomVO roomVO = null;
		
		if(room != null){
			roomVO = new RoomVO();
			roomVO.setRoomNumber(room.getRoomNumber());
			roomVO.setTotalBeds(room.getTotalBeds());
			roomVO.setAvailableBeds(room.getAvailableBeds());
			roomVO.setChargesPerDay(room.getChargesPerDay());
		}
		return roomVO;
	}
	
	/**
	 * 
	 * @param speciality
	 * @return SpecialityVO
	 */
	public static SpecialityVO convertSpecialityModelToVO(Speciality speciality){
		SpecialityVO specialityVO = null;
		
		if(speciality != null){
			specialityVO = new SpecialityVO();
			specialityVO.setName(speciality.getName());
			specialityVO.setDescription(speciality.getDescription());
		}
		
		return specialityVO;
	}
	
	
	/**
	 * 
	 * @param departmentVO
	 * @param loggedInUserId
	 * @return Department
	 */
	public static Department convertDepartmentVOToModel(DepartmentVO departmentVO, String loggedInUserId){
		Department department = null;
		if(departmentVO != null){
			department = new Department();
			department.setDataStoreId(departmentVO.getDataStoreId());
			department.setDepartmentId(departmentVO.getDepartmentId());
			department.setDescription(departmentVO.getDescription());
			department.setFacilities(departmentVO.getFacilities());
			department.setName(departmentVO.getName());
			SystemUtil.setBaseModelValues(department, loggedInUserId, SYSTEM_OF_RECORDX);
		}
		return department;
	}
	
	/**
	 * 
	 * @param doctorVO
	 * @param loggedInUserId
	 * @return Doctor
	 */
	public static Doctor convertDoctorVOToModel(DoctorVO doctorVO, String loggedInUserId) {
		Doctor doctor = null;
		if(doctorVO != null){
			doctor = new Doctor();
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
			
			SystemUtil.setBaseModelValues(doctor, loggedInUserId, SYSTEM_OF_RECORDX);
		}
		
		return doctor;
	}
	
	/**
	 * 
	 * @param headVO
	 * @param loggedInUserId
	 * @return Head
	 */
	public static Head convertHeadVOToModel(HeadVO headVO, String loggedInUserId){
		Head head = null;
		
		if(headVO != null){
			head = new Head();
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
			
			SystemUtil.setBaseModelValues(head, loggedInUserId, SYSTEM_OF_RECORDX);
		}
		return head;
	}
	
	/**
	 * 
	 * @param hospitalVO
	 * @param userId
	 * @return Hospital
	 */
	public static Hospital convertHospitalVoToModel(HospitalVO hospitalVO, String userId){
		Hospital hospital = null;
		
		if(hospitalVO != null){
			hospital = new Hospital();
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
	
	/**
	 * 
	 * @param laboratoryVO
	 * @param userId
	 * @return Laboratory
	 */
	public static Laboratory convertLaboratoryVOToModel(LaboratoryVO laboratoryVO, String userId){
		Laboratory laboratory = null;
		
		if(laboratoryVO != null){
			laboratory = new Laboratory();
			laboratory.setName(laboratoryVO.getName());
			SystemUtil.setBaseModelValues(laboratory, userId, SYSTEM_OF_RECORDX);
		}
		return laboratory;
	}
	
	/**
	 * 
	 * @param roomVO
	 * @param userId
	 * @return Room
	 */
	public static Room convertRoomVOToModel(RoomVO roomVO, String userId){
		Room room = null;
		
		if(roomVO != null){
			room = new Room();
			room.setRoomNumber(roomVO.getRoomNumber());
			room.setTotalBeds(roomVO.getTotalBeds());
			room.setAvailableBeds(room.getAvailableBeds());
			room.setChargesPerDay(roomVO.getChargesPerDay());
			SystemUtil.setBaseModelValues(room, userId, SYSTEM_OF_RECORDX);
		}
		return room;
	}
	
	
	/**
	 * 
	 * @param specialityVO
	 * @param userId 
	 * @return Speciality
	 */
	public static Speciality convertSpecialityVOToModel(SpecialityVO specialityVO, String userId){
		Speciality speciality = null;
		
		if(specialityVO != null){
			speciality = new Speciality();
			speciality.setName(specialityVO.getName());
			speciality.setDescription(specialityVO.getDescription());
			SystemUtil.setBaseModelValues(speciality, userId, SYSTEM_OF_RECORDX);
		}
		
		return speciality;
	}

}
