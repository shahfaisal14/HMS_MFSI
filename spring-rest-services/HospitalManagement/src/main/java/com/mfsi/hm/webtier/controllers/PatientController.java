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

import com.mfsi.hm.biztier.vos.PatientVO;
import com.mfsi.hm.core.common.BaseController;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.webtier.helpers.PatientHelper;

/**
 * @author shah
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientController extends BaseController {

	@Autowired
	private PatientHelper patientHelper;
	
	@RequestMapping(name="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> registerPatient(@RequestBody PatientVO patientVO){
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO restResponse = patientHelper.registerPatient(patientVO);
		
		response = new ResponseEntity<RestResponseVO>(restResponse, HttpStatus.OK);
		return response;
	}
	
}
