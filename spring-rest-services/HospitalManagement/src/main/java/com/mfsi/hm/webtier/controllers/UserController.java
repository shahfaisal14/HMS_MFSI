/**
 *
 */
package com.mfsi.hm.webtier.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.biztier.vos.TokenValidationVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.common.BaseController;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.webtier.helpers.UserHelper;

/**
 * @author shah
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserHelper userHelper;
	
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> doLogin(HttpServletRequest httpServletRequest, @RequestBody LoginVO loginVO) {
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		
		responseVO = userHelper.doLogin(loginVO);
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> createUser(HttpServletRequest request,
			@RequestHeader(value="X-Auth-Token", required=true) String authToken,
			@RequestBody UserVO userVO){
		
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		
		TokenValidationVO validation = validateToken(authToken, request);
		UserVO loggedInUser = validation.getUser();
		
		responseVO = userHelper.createUser(loggedInUser, userVO);
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<RestResponseVO> resetPassword(HttpServletRequest request, 
			@RequestHeader(value = "X-Auth-Token", required = true) String token, 
			@RequestParam(value = "password", required = true) String password) 
					throws NoSuchAlgorithmException, UnsupportedEncodingException{

		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;

		TokenValidationVO tokenValidation = validateToken(token, request);
		responseVO = userHelper.resetPassword(tokenValidation.getUser().getUserId(), password);

		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> forgotPassword(HttpServletRequest request, @RequestParam(value = "userId", required = true) String userId) {

		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO userResponseVO = userHelper.forgotPassword(userId);
		response = new ResponseEntity<RestResponseVO>(userResponseVO, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<RestResponseVO> changePassword(HttpServletRequest request, 
			@RequestHeader(value = "X-Auth-Token", required = true) String token, 
			@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword) 
					throws NoSuchAlgorithmException, UnsupportedEncodingException {

		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;

		TokenValidationVO tokenValidation = validateToken(token, request);
		responseVO = userHelper.changePassword(tokenValidation.getUser().getUserId(), oldPassword, newPassword);

		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/doLogout", method = RequestMethod.DELETE)
	public ResponseEntity<RestResponseVO> doLogOut(HttpServletRequest request, 
			@RequestHeader(value = "X-Auth-Token", required = true) String token) {

		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		try{
			validateToken(token, request);
			responseVO = userHelper.doLogout(token, request);
		}catch(Exception exception){
			responseVO = new RestResponseVO();
			responseVO.setResponseType(ResponseType.SUCCESS);
		}
		
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		return response;
	}
}
