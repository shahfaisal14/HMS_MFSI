/**
 * 
 */
package com.mfsi.hm.webtier.helpers;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_ACCESS_LEVEL;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_PARAM_EXCEPTION;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_PARAM_EXCEPTION;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.HospitalService;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;

/**
 * @author shah
 *
 */
@Component
public class HospitalHelper {
	
	@Autowired
	private HospitalService hospitalService;

	public RestResponseVO addHospital(HospitalVO hospitalVO) {
		
		RestResponseVO response = new RestResponseVO();
		UserVO loggedInUser = LoggedInUserContext.getUser();
		boolean isValidRequest = validateHospitalRequest(hospitalVO, loggedInUser);
		if(!isValidRequest){
			response.setResponseType(ResponseType.ERROR);
			response.setMessage("Invalid Add Hospital Request.");
		}
		
		BizResponseVO bizResponse = hospitalService.addHospital(hospitalVO, loggedInUser);
		
		response.setResponseType(bizResponse.getResponseType());
		response.setMessage(bizResponse.getMessage());
		response.setResponseData(bizResponse.getResponseData());
		
		return response;
	}
	
	public RestResponseVO getHospitalList() {
		RestResponseVO response = new RestResponseVO();
		
		BizResponseVO bizResponse = hospitalService.getHospitalsList();
		response.setResponseType(bizResponse.getResponseType());
		response.setResponseData(bizResponse.getResponseData());
		response.setMessage(bizResponse.getMessage());
		
		return response;
	}
	
	public RestResponseVO getHospitalById(Long dataStoreId) {
		RestResponseVO response = new RestResponseVO();
		if(dataStoreId != null){
			BizResponseVO bizResponse = hospitalService.getHospitalById(dataStoreId);
			response.setResponseType(bizResponse.getResponseType());
			response.setMessage(bizResponse.getMessage());
			response.setResponseData(bizResponse.getResponseData());
		} else {
			Object[] values = { "Hospital dataStoreId " };
			throw new RequiredParameterException(ERROR_CODE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		}
		return response;
	}
	
	private Boolean validateHospitalRequest(HospitalVO hospitalVO, UserVO loggedInUser){
		
		if(!loggedInUser.getRole().getId().equals(RoleAccessLevel.ADMIN.getRole())){
			throw new AccessDeniedException(ERROR_CODE_ACCESS_LEVEL, 
					SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_LEVEL, null, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getName())){
			Object[] values = { "name" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getAddress())){
			Object[] values = { "address" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getContact())){
			Object[] values = { "contact" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getEmail())){
			Object[] values = { "email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
}
