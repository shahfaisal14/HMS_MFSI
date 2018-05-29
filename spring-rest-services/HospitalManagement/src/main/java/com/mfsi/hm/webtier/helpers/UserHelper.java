/**
 * 
 */
package com.mfsi.hm.webtier.helpers;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_INVALID_CREDENTIALS;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_INVALID_CREDENTIALS;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.UserService;
import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.InvalidCredentialsException;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;

/**
 * @author shah
 *
 */
@Component
public class UserHelper {
	
	@Autowired
	private UserService userService;

	public RestResponseVO doLogin(LoginVO loginVO) {
		RestResponseVO response = new RestResponseVO();
		
		if(StringUtils.isNoneBlank(loginVO.getUserCode()) && StringUtils.isNoneBlank(loginVO.getPassCode())){
			BizResponseVO bizResponse = userService.doLogin(loginVO);
			
			if (bizResponse.getResponseType().equals(ResponseType.SUCCESS)) {
				response.setResponseType(ResponseType.SUCCESS);	
			} else {	
				response.setResponseType(ResponseType.ERROR);
			}
			response.setResponseData(bizResponse.getResponseData());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		
		return response;
	}
	
}
