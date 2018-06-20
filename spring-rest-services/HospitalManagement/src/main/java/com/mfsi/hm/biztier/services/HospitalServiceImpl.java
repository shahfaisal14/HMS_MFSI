/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.ERROR_HOSPITAL_SAVE;
import static com.mfsi.hm.core.common.Constants.IS_ACTIVE_HOSPITAL;
import static com.mfsi.hm.core.common.Constants.SUCCESS_HOSPITAL_SAVE;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.core.pagination.FilterInfoVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.core.util.SystemUtil;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.services.HospitalDataService;

/**
 * @author shah
 *
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDataService hospitalDataService;

	@Override
	public BizResponseVO addHospital(HospitalVO hospitalVO, String userId) {
		
		BizResponseVO response = new BizResponseVO();
		
		Hospital hospital = convertHospitalVoToModel(hospitalVO, userId);
		
		hospital = hospitalDataService.addHospital(hospital);
		
		if(hospital != null){
			response.setResponseType(ResponseType.SUCCESS);
			response.setMessage(SUCCESS_HOSPITAL_SAVE);
			response.setResponseData(hospital.getDataStoreId());
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(ERROR_HOSPITAL_SAVE);
		}
		
		return response;
	}
	
	private Hospital convertHospitalVoToModel(HospitalVO hospitalVO, String userId){
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

	@Override
	public RestResponseVO getHospitalList(FilterInfoVO filterInfoVO) {
		// TODO Auto-generated method stub
		return null;
	}
}
