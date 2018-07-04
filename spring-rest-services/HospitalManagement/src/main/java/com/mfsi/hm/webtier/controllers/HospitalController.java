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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfsi.hm.biztier.vos.HospitalVO;
import com.mfsi.hm.core.common.BaseController;
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
		
		RestResponseVO restResponseVO = hospitalHelper.addHospital(hospitalVO);
		response = new ResponseEntity<RestResponseVO>(restResponseVO, HttpStatus.OK);
	
		return response;
	}
	
	@RequestMapping(value="/getList", method=RequestMethod.GET)
	public ResponseEntity<RestResponseVO> getHospitalList() {
		
		ResponseEntity<RestResponseVO> response = null;
		
		RestResponseVO restResponseVO = hospitalHelper.getHospitalList();
		response = new ResponseEntity<RestResponseVO>(restResponseVO, HttpStatus.OK);
		
		return response;
	}
	
	public ResponseEntity<RestResponseVO> getHospitalById(@RequestParam(value="dataStoreId", required=true) Long dataStoreId){
		
		ResponseEntity<RestResponseVO> response = null;
		
		RestResponseVO restResponseVO = hospitalHelper.getHospitalById(dataStoreId);
		response = new ResponseEntity<RestResponseVO>(restResponseVO, HttpStatus.OK);
		
		return response;
	}
}
