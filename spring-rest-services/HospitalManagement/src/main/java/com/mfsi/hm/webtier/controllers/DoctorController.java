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

import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.core.common.BaseController;
import com.mfsi.hm.core.pagination.FilterInfoVO;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.webtier.helpers.DoctorHelper;

/**
 * @author shah
 *
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController extends BaseController {
	
	@Autowired
	DoctorHelper doctorHelper;
	
	@RequestMapping(value="/create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> createDoctor(@RequestBody DoctorVO doctorVO){
		
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		
		responseVO = doctorHelper.createDoctor(doctorVO);
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value="getList", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> getDoctorsList(){
		
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		
		responseVO = doctorHelper.getDoctorsList();
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		
		return response;
	}
}
