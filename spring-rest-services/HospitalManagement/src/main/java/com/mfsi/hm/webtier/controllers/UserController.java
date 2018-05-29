/**
 *
 */
package com.mfsi.hm.webtier.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.core.common.BaseController;
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

}
