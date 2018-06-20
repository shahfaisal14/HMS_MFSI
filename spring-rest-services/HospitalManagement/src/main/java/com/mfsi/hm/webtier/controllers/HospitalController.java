/**
 * 
 */
package com.mfsi.hm.webtier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.common.BaseController;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.pagination.FilterInfoVO;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.webtier.helpers.HospitalHelper;

/**
 * @author shah
 *
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController extends BaseController {

	@Autowired
	private HospitalHelper hospitalHelper;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> addHospital(@RequestBody HospitalVO hospitalVO){
		
		ResponseEntity<RestResponseVO> response = null;
		UserVO loggedInUser = LoggedInUserContext.getUser();
		
		RestResponseVO restResponseVO = hospitalHelper.addHospital(hospitalVO, loggedInUser.getUserId());
		response = new ResponseEntity<RestResponseVO>(restResponseVO, HttpStatus.OK);
	
		return response;
	}
	
	@RequestMapping(value="/getList", method=RequestMethod.POST)
	public ResponseEntity<RestResponseVO> getHospitalList(@RequestBody FilterInfoVO filterInfoVO) {
		
		ResponseEntity<RestResponseVO> response = null;
		UserVO loggedInUser = LoggedInUserContext.getUser();
		
		RestResponseVO restResponseVO = hospitalHelper.getHospitalList(filterInfoVO, loggedInUser.getUserId());
		response = new ResponseEntity<RestResponseVO>(restResponseVO, HttpStatus.OK);
		
		return response;
	}
}
