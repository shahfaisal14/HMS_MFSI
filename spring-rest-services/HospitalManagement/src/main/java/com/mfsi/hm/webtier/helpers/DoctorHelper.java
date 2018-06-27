/**
 * 
 */
package com.mfsi.hm.webtier.helpers;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_PARAM_EXCEPTION;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.daotier.services.DoctorService;

/**
 * @author shah
 *
 */
@Component("doctorHelper")
public class DoctorHelper {

	@Autowired
	DoctorService doctorService;
	
	public RestResponseVO createDoctor(DoctorVO doctorVO) {
		RestResponseVO response = new RestResponseVO();
		
		UserVO loggedInUser = LoggedInUserContext.getUser();
		Boolean isValidCreateDoctorRequest = validateCreateDoctorRequest(doctorVO, loggedInUser);
		
		if(isValidCreateDoctorRequest){
			BizResponseVO bizResponse = doctorService.createDoctor(doctorVO, loggedInUser);
			response.setResponseType(bizResponse.getResponseType());
			response.setResponseData(bizResponse.getResponseData());
			response.setMessage(bizResponse.getMessage());	
		}
		
		return response;
	}

	public RestResponseVO getDoctorsList() {
		RestResponseVO response = new RestResponseVO();
		
		BizResponseVO bizResponse = doctorService.getDoctorsList();
		response.setResponseType(bizResponse.getResponseType());
		response.setResponseData(bizResponse.getResponseData());
		response.setMessage(bizResponse.getMessage());
		
		return response;
	}
	
	/**
	 * 
	 * @param doctorVO
	 * @param loggedInUser
	 * @return Boolean
	 * @throws AccessDeniedException
	 * @throws RequiredParameterException
	 */
	private Boolean validateCreateDoctorRequest(DoctorVO doctorVO, UserVO loggedInUser) {
		if(!(loggedInUser.getRole().getId().equals(RoleAccessLevel.ADMIN.getRole()))){
			throw new AccessDeniedException(ERROR_CODE_ACCESS_LEVEL, 
					SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_LEVEL, null, APP_LOCALE));
		} else if(StringUtils.isBlank(doctorVO.getFirstName())){
			Object[] values = { "firstName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(doctorVO.getLastName())){
			Object[] values = { "lastName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(doctorVO.getEmail())){
			Object[] values = { "email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(doctorVO.getDateOfBirth() == null){
			Object[] values = { "dateOfBirth" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
}
