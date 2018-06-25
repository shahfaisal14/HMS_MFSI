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

import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.core.common.BaseController;
import com.mfsi.hm.core.responses.RestResponseVO;
import com.mfsi.hm.webtier.helpers.HeadHelper;

/**
 * @author shah
 *
 */
@RestController
@RequestMapping("/head")
public class HeadController extends BaseController {

	@Autowired
	HeadHelper headHelper;
	
	@RequestMapping(value="/create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseVO> createHead(@RequestBody HeadVO headVO){
		
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = null;
		
		responseVO = headHelper.createHead(headVO);
		response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		
		return response;
	}
}
