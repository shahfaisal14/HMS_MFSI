/**
 * 
 */
package com.mfsi.hm.webtier.helpers;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_INVALID_CREDENTIALS;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_INVALID_CREDENTIALS;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_PARAM_EXCEPTION;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mfsi.hm.biztier.services.UserService;
import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.biztier.vos.TokenValidationVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.InvalidCredentialsException;
import com.mfsi.hm.core.exceptions.RequiredParameterException;
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

	public RestResponseVO doLogin(LoginVO loginVO) {
		RestResponseVO response = new RestResponseVO();
		
		if(StringUtils.isNoneBlank(loginVO.getUserCode()) && StringUtils.isNoneBlank(loginVO.getPassCode())){
			BizResponseVO bizResponse = userService.doLogin(loginVO);
			
			response.setResponseType(bizResponse.getResponseType());
			response.setResponseData(bizResponse.getResponseData());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		
		return response;
	}

	public RestResponseVO createUser(UserVO loggedInUser, UserVO userVO) {
		
		RestResponseVO response = new RestResponseVO();
		BizResponseVO bizResponse = userService.createUser(loggedInUser, userVO);
		
		response.setResponseType(bizResponse.getResponseType());
		response.setMessage(bizResponse.getMessage());
		response.setResponseData(bizResponse.getResponseData());
		
		return response;
	}
	
	/**
	 * To reset password
	 * 
	 * @return ResponseEntity<JSONResponse>
	 */
	public RestResponseVO forgotPassword(String userId) {
		RestResponseVO response = new RestResponseVO();
		if (StringUtils.isNotBlank(userId)) {
			BizResponseVO bizResponse = userService.forgotPassword(userId);
			response.setResponseType(bizResponse.getResponseType());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return response;
	}

	public RestResponseVO resetPassword(String userId, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		RestResponseVO response = new RestResponseVO();
		if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(password)) {
			
			BizResponseVO bizResponse = userService.resetPassword(userId, password);
			response.setResponseType(bizResponse.getResponseType());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return response;
	}

	/**
	 * To change password
	 * 
	 * @requestParam oldPassword
	 * @requestParam newPassword
	 * @return ResponseEntity<JSONResponse>
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public RestResponseVO changePassword(String userId, String oldPassword, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		RestResponseVO response = new RestResponseVO();
		if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {

			BizResponseVO bizResponse = userService.changePassword(userId, oldPassword, newPassword);
			response.setResponseType(bizResponse.getResponseType());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return response;
	}
	
	public RestResponseVO doLogout(String authToken, HttpServletRequest request) {
		RestResponseVO response = new RestResponseVO();
		if (StringUtils.isNoneBlank(authToken)) {
			String userName = null;
			BizResponseVO bizResponse = userService.doLogout(authToken, userName);
			response.setResponseType(bizResponse.getResponseType());
			response.setResponseData(bizResponse.getResponseData());
			response.setMessage(bizResponse.getMessage());
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return response;
	}
	
	/**
	 * 
	 * @param userVO
	 * @param loggedInUser
	 * @return boolean
	 * @throws AccessDeniedException
	 * @throws RequiredParameterException
	 */
	protected boolean validateUser(UserVO userVO) {
		
		if(userVO.getRole() == null){
			Object[] values = { "RoleVO" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(userVO.getFirstName())){
			Object[] values = { "firstName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(userVO.getLastName())){
			Object[] values = { "lastName" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(StringUtils.isBlank(userVO.getEmail())){
			Object[] values = { "email" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else if(userVO.getDateOfBirth() == null){
			Object[] values = { "dateOfBirth" };
			throw new RequiredParameterException(ERROR_MESSAGE_PARAM_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_PARAM_EXCEPTION, values, APP_LOCALE));
		} else {
			return true;
		}
	}
	
}
