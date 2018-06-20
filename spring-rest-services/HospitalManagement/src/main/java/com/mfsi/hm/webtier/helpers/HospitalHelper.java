/**
 * 
 */
package com.mfsi.hm.webtier.helpers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.HospitalService;
import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
import com.mfsi.hm.core.pagination.FilterInfoVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;

import static com.mfsi.hm.core.common.Constants.*;

/**
 * @author shah
 *
 */
@Component
public class HospitalHelper {
	
	@Autowired
	private HospitalService hospitalService;

	public RestResponseVO addHospital(HospitalVO hospitalVO, String userId) {
		RestResponseVO response = new RestResponseVO();
		boolean isValidRequest = validateHospitalRequest(hospitalVO);
		if(!isValidRequest){
			response.setResponseType(ResponseType.ERROR);
			response.setMessage("Invalid Add Hospital Request.");
		}
		
		BizResponseVO bizResponse = hospitalService.addHospital(hospitalVO, userId);
		
		response.setResponseType(bizResponse.getResponseType());
		response.setMessage(bizResponse.getMessage());
		response.setResponseData(bizResponse.getResponseData());
		
		return response;
	}
	
	public RestResponseVO getHospitalList(FilterInfoVO filterInfoVO, String userId) {
		RestResponseVO response = new RestResponseVO();
		
		response = hospitalService.getHospitalList(filterInfoVO);
		
		
		return response;
	}
	
	private Boolean validateHospitalRequest(HospitalVO hospitalVO){
		
		if(StringUtils.isBlank(hospitalVO.getName())){
			Object[] values = { "name" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getAddress())){
			Object[] values = { "address" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getContact())){
			Object[] values = { "Contact" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(hospitalVO.getEmail())){
			Object[] values = { "Email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
}
