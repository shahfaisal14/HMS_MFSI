package com.mfsi.hm.webtier.helpers;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_PARAM_EXCEPTION;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.PatientService;
import com.mfsi.hm.biztier.vos.PatientVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.RestResponseVO;

@Component
public class PatientHelper {
	
	@Autowired
	private PatientService patientService;

	public RestResponseVO registerPatient(PatientVO patientVO){
		RestResponseVO response = null;
		
		BizResponseVO bizResponse = null;
		
		UserVO loggedInUser = LoggedInUserContext.getUser();
		Boolean isValidPatientRegisterRequest = validateRegisterPatientRequest(patientVO, loggedInUser);
		
		if(isValidPatientRegisterRequest){
			bizResponse = patientService.registerPatient(patientVO);
		}
		
		if(bizResponse != null){
			response = new RestResponseVO();
			response.setResponseType(bizResponse.getResponseType());
			response.setMessage(bizResponse.getMessage());
			response.setResponseData(bizResponse.getResponseData());
		}
		
		return response;
	}
	
	private Boolean validateRegisterPatientRequest(PatientVO  patientVO, UserVO loggedInUser) {
		if(!(loggedInUser.getRole().getId().equals(RoleAccessLevel.HEAD.getRole()))){
			throw new AccessDeniedException(ERROR_CODE_ACCESS_LEVEL, 
					SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_LEVEL, null, APP_LOCALE));
		} else if(StringUtils.isBlank(patientVO.getFirstName())){
			Object[] values = { "firstName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(patientVO.getLastName())){
			Object[] values = { "lastName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(patientVO.getEmail())){
			Object[] values = { "email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(patientVO.getDateOfBirth() == null){
			Object[] values = { "dateOfBirth" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
}
