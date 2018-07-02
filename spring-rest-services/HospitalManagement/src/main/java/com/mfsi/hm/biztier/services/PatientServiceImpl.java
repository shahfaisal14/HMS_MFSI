/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.USER_CREATE_ERROR;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_SUCCESS;
import static com.mfsi.hm.core.util.ModelVOMapper.converPatientVOToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.PatientVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.daotier.models.Patient;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.services.PatientDataService;
import com.mfsi.hm.daotier.services.UserDataService;

/**
 * @author shah
 *
 */
@Service("patientService")
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDataService patientDataService;
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDataService userDataService; 
	
	@Override
	public BizResponseVO registerPatient(PatientVO patientVO) {
		
		BizResponseVO response = new BizResponseVO();
		UserVO loggedInUser = LoggedInUserContext.getUser(); 
		
		Patient patient = patientDataService.registerPatient(converPatientVOToModel(patientVO, loggedInUser.getUserId()));
		
		if(patient != null){
			User user = userDataService.findUser(patient.getUserId());
			response = userService.saveLoginAndMailCredentials(user);
			
			if(response != null){
				response.setResponseType(ResponseType.SUCCESS);
				response.setMessage(USER_CREATE_SUCCESS);
				response.setResponseData(patient);
			}
			response.setMessage("Patient register success.");
			response.setResponseType(ResponseType.SUCCESS);
			response.setResponseData(patient);
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(USER_CREATE_ERROR);
		}
		
		return response;
	}

}
