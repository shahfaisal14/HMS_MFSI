package com.mfsi.hm.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.UserService;
import com.mfsi.hm.biztier.vos.TokenValidationVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;


@Component("baseHelper")
public class BaseHelper {
	
	@Autowired
	UserService userService;
	
	public TokenValidationVO getUserDetails(String authToken){
		TokenValidationVO validation = new TokenValidationVO();
		
		BizResponseVO bizResponse = userService.validateToken(authToken);
		
		if(ResponseType.SUCCESS.equals(bizResponse.getResponseType())){
			validation.setResponseType(bizResponse.getResponseType().toString());
			UserVO userData = (UserVO) bizResponse.getResponseData();
			validation.setUser(userData);
		} else {
			validation.setResponseType(bizResponse.getResponseType().toString());
			validation.setMessage(bizResponse.getMessage());
			validation.setErrorCode(bizResponse.getErrorCode());
		}
		
		return validation;
	}
}
