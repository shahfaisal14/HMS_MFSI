/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.ERROR_HOSPITAL_SAVE;
import static com.mfsi.hm.core.common.Constants.SUCCESS_HOSPITAL_SAVE;
import static com.mfsi.hm.core.util.ModelVOMapper.convertDepartmentModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertDepartmentVOToModel;
import static com.mfsi.hm.core.util.ModelVOMapper.convertHospitalModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertHospitalVoToModel;
import static com.mfsi.hm.core.util.ModelVOMapper.convertLaboratoryModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertLaboratoryVOToModel;
import static com.mfsi.hm.core.util.ModelVOMapper.convertRoomModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertRoomVOToModel;
import static com.mfsi.hm.core.util.ModelVOMapper.convertSpecialityModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertSpecialityVOToModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.DepartmentVO;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.LaboratoryVO;
import com.mfsi.hm.biztier.vos.RoomVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.daotier.models.Department;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Laboratory;
import com.mfsi.hm.daotier.models.Room;
import com.mfsi.hm.daotier.services.DepartmentDataService;
import com.mfsi.hm.daotier.services.HospitalDataService;
import com.mfsi.hm.daotier.services.LaboratoryDataService;
import com.mfsi.hm.daotier.services.RoomDataService;

/**
 * @author shah
 *
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDataService hospitalDataService;
	
	@Autowired
	private RoomDataService roomDataService;
	
	@Autowired
	private DepartmentDataService departmentDataService;
	
	@Autowired
	private LaboratoryDataService laboratoryDataService;

	@Override
	public BizResponseVO addHospital(HospitalVO hospitalVO, UserVO loggedInUser) {

		BizResponseVO response = new BizResponseVO();
		Hospital hospital = convertHospitalVoToModel(hospitalVO, loggedInUser.getUserId());
		
		if(hospitalVO.getSpeciality() != null){
			hospital.setSpeciality(convertSpecialityVOToModel(hospitalVO.getSpeciality(), loggedInUser.getUserId()));
		}
		
		hospital = hospitalDataService.addHospital(hospital);
		
		if(hospital != null){
			
			if(hospitalVO.getDepartments() != null){
				Set<Department> departments = convertDepartmentsVOToModel(hospitalVO.getDepartments(), loggedInUser.getUserId(), hospital);
				departmentDataService.createDepartments(departments);
			}
			if(hospitalVO.getRooms() != null){
				Set<Room> rooms = convertRoomsVOToModel(hospitalVO.getRooms(), loggedInUser.getUserId(), hospital);
				roomDataService.createRooms(rooms);
			}
			if(hospitalVO.getLaboratories() != null){
				Set<Laboratory> laboratories = convertLaboratoriesVOToModel(hospitalVO.getLaboratories(), loggedInUser.getUserId(), hospital);
				laboratoryDataService.createLaboratories(laboratories);
			}
			
			response.setResponseType(ResponseType.SUCCESS);
			response.setMessage(SUCCESS_HOSPITAL_SAVE);
			response.setResponseData(hospital.getDataStoreId());
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(ERROR_HOSPITAL_SAVE);
		}
		
		return response;
	}

	@Override
	public BizResponseVO getHospitalsList() {
		BizResponseVO response = new BizResponseVO();
		
		List<Hospital> hospitals = hospitalDataService.getHospitalsList();
		List<HospitalVO> hospitalsVO = null;
		
		if(hospitals != null){
			hospitalsVO = new ArrayList<HospitalVO>();
			for(Hospital hospital : hospitals){
				
				HospitalVO hospitalVO = convertHospitalModelToVO(hospital);
				
				if(hospital.getDepartments() != null){
					hospitalVO.setDepartments(convertDepartmentsModelToVO(hospital.getDepartments()));
				}
				if(hospital.getLaboratories() != null){
					hospitalVO.setLaboratories(convertLaboratoriesModelToVO(hospital.getLaboratories()));
				}
				if(hospital.getRooms() != null){
					hospitalVO.setRooms(convertRoomsModelToVO(hospital.getRooms()));
				}
				if(hospital.getSpeciality() != null){
					hospitalVO.setSpeciality(convertSpecialityModelToVO(hospital.getSpeciality()));
				}
				
				hospitalsVO.add(hospitalVO);
			}
			response.setResponseType(ResponseType.SUCCESS);
			response.setMessage("List of hospitals fetched successfully.");
			response.setResponseData(hospitalsVO);	
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage("No record found for hospitals.");
		}
		return response;
	}

	@Override
	public BizResponseVO getHospitalById(Long dataStoreId) {
		BizResponseVO response = new BizResponseVO();
		Hospital hospital = hospitalDataService.getHospitalById(dataStoreId);
		if (hospital != null) {
			HospitalVO hospitalVO = convertHospitalModelToVO(hospital);
			response.setResponseType(ResponseType.SUCCESS);
			response.setMessage("Hospital fetched successfully");
			response.setResponseData(hospitalVO);
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage("Hospital is not present for the selected Id: " + dataStoreId);
		}
		return response;
	}
	
	/**
	 * 
	 * @param departmentsVO
	 * @param loggedInUserId
	 * @param hospital
	 * @return SetOfDepartment
	 */
	private Set<Department> convertDepartmentsVOToModel(Set<DepartmentVO> departmentsVO, String loggedInUserId, Hospital hospital) {
		
		Set<Department> departments = new HashSet<>();
		
		for(DepartmentVO departmentVO: departmentsVO){
			Department department = convertDepartmentVOToModel(departmentVO, loggedInUserId);
			department.setHospital(hospital);
			departments.add(department);
		}
		
		return departments;
	}
	
	/**
	 * 
	 * @param roomsVO
	 * @param loggedInUserId
	 * @param hospital
	 * @return SetOfRoom
	 */
	private Set<Room> convertRoomsVOToModel(Set<RoomVO> roomsVO, String loggedInUserId, Hospital hospital) {
		
		Set<Room> rooms = new HashSet<>();
		
		for(RoomVO roomVO: roomsVO){
			Room room = convertRoomVOToModel(roomVO, loggedInUserId);
			room.setHospital(hospital);
			rooms.add(room);
		}
		
		return rooms;
	}
	
	/**
	 * 
	 * @param laboratoriesVO
	 * @param loggedInUserId
	 * @param hospital
	 * @return SetOfLaboratory
	 */
	private Set<Laboratory> convertLaboratoriesVOToModel(Set<LaboratoryVO> laboratoriesVO, String loggedInUserId, Hospital hospital) {
		
		Set<Laboratory> laboratories = new HashSet<>();
		
		for(LaboratoryVO laboratoryVO: laboratoriesVO){
			Laboratory laboratory = convertLaboratoryVOToModel(laboratoryVO, loggedInUserId);
			laboratory.setHospital(hospital);
			laboratories.add(laboratory);
		}
		
		return laboratories;
	}
	
	
	/**
	 * 
	 * @param laboratories
	 * @return SetOfLaboratoriesVO
	 */
	private Set<LaboratoryVO> convertLaboratoriesModelToVO(Set<Laboratory> laboratories){
		Set<LaboratoryVO> laboratoriesVO = new HashSet<>();
		
		for(Laboratory laboratory: laboratories){
			LaboratoryVO laboratoryVO = convertLaboratoryModelToVO(laboratory);
			laboratoriesVO.add(laboratoryVO);
		}
		
		return laboratoriesVO;
	}
	
	/**
	 * 
	 * @param departments
	 * @return SetOfDepartmentsVO
	 */
	private Set<DepartmentVO> convertDepartmentsModelToVO(Set<Department> departments){
		Set<DepartmentVO> departmentsVO = new HashSet<>();
		
		for(Department laboratory: departments){
			DepartmentVO laboratoryVO = convertDepartmentModelToVO(laboratory);
			departmentsVO.add(laboratoryVO);
		}
		
		return departmentsVO;
	}
	
	/**
	 * 
	 * @param rooms
	 * @return SetOfRoomVO
	 */
	private Set<RoomVO> convertRoomsModelToVO(Set<Room> rooms){
		Set<RoomVO> roomsVO = new HashSet<>();
		
		for(Room room: rooms){
			RoomVO roomVO = convertRoomModelToVO(room);
			roomsVO.add(roomVO);
		}
		
		return roomsVO;
	}
}
