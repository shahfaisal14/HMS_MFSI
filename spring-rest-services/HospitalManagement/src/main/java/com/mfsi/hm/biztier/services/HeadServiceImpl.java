/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.USER_CREATE_ERROR;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_SUCCESS;
import static com.mfsi.hm.core.util.ModelVOMapper.convertHeadVOToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.daotier.models.Head;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.services.HeadDataService;
import com.mfsi.hm.daotier.services.HospitalDataService;
import com.mfsi.hm.daotier.services.UserDataService;

/**
 * @author shah
 *
 */
@Service("headService")
public class HeadServiceImpl implements HeadService {

	@Autowired
	private HeadDataService headDataService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDataService userDataService;
	
	@Autowired HospitalDataService hospitalDataService;
	
	@Override
	public BizResponseVO createHead(HeadVO headVO, UserVO loggedInUser) {
		BizResponseVO response = new BizResponseVO();
		Head head = convertHeadVOToModel(headVO, loggedInUser.getUserId());
		
		// Get hospital and
		Hospital hospital = hospitalDataService.findByName(headVO.getHospital().getName());
		head.setHospital(hospital);
		
		Head newHead = headDataService.createHead(head);
		
		if(newHead != null){
			User user = userDataService.findUser(newHead.getUserId());
			response = userService.saveLoginAndMailCredentials(user);
			
			if(response != null){
				response.setResponseType(ResponseType.SUCCESS);
				response.setMessage(USER_CREATE_SUCCESS);
				response.setResponseData(newHead);
			}
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(USER_CREATE_ERROR);
		}
		
		return response;
	}

}
