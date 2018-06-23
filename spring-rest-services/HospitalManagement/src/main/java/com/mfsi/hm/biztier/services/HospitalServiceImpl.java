/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.ERROR_HOSPITAL_SAVE;
import static com.mfsi.hm.core.common.Constants.SUCCESS_HOSPITAL_SAVE;
import static com.mfsi.hm.core.util.ModelVOMapper.convertHospitalModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertHospitalVoToModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
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
	public BizResponseVO addHospital(HospitalVO hospitalVO, UserVO loggedInUser) {

		BizResponseVO response = new BizResponseVO();
		Hospital hospital = convertHospitalVoToModel(hospitalVO, loggedInUser.getUserId());
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

	@Override
	public BizResponseVO getHospitalsList() {
		BizResponseVO response = new BizResponseVO();
		
		List<Hospital> hospitals = hospitalDataService.getHospitalsList();
		List<HospitalVO> hospitalsVO = new ArrayList<HospitalVO>();
		
		if(hospitals != null){
			for(Hospital hospital : hospitals){
				hospitalsVO.add(convertHospitalModelToVO(hospital));
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
}
