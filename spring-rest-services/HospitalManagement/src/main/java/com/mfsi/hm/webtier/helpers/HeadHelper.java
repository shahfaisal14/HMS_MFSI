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

import com.mfsi.hm.biztier.services.HeadService;
import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.RestResponseVO;

/**
 * @author shah
 *
 */
@Component("headHelper")
public class HeadHelper {
	
	@Autowired
	HeadService headService;

	public RestResponseVO createHead(HeadVO headVO) {
		RestResponseVO response = new RestResponseVO();
		
		UserVO loggedInUser = LoggedInUserContext.getUser();
		Boolean isValidCreateHeadRequest = validateCreateHeadRequest(headVO, loggedInUser);
		
		if(isValidCreateHeadRequest){
			BizResponseVO bizResponse = headService.createHead(headVO, loggedInUser);
			response.setResponseType(bizResponse.getResponseType());
			response.setResponseData(bizResponse.getResponseData());
			response.setMessage(bizResponse.getMessage());	
		}
		
		return response;
	}

	private Boolean validateCreateHeadRequest(HeadVO headVO, UserVO loggedInUser) {
		if(!(loggedInUser.getRole().getId().equals(RoleAccessLevel.ADMIN.getRole()))){
			throw new AccessDeniedException(ERROR_CODE_ACCESS_LEVEL, 
					SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_LEVEL, null, APP_LOCALE));
		} else if(StringUtils.isBlank(headVO.getFirstName())){
			Object[] values = { "firstName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(headVO.getLastName())){
			Object[] values = { "lastName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(headVO.getEmail())){
			Object[] values = { "email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(headVO.getDateOfBirth() == null){
			Object[] values = { "dateOfBirth" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
}
