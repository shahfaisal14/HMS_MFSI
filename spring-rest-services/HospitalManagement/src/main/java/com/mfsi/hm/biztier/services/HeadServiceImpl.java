/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_ERROR;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.HeadVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.util.SystemUtil;
import com.mfsi.hm.daotier.models.Head;
import com.mfsi.hm.daotier.models.Hospital;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.services.HeadDataService;
import com.mfsi.hm.daotier.services.HospitalDataService;
import com.mfsi.hm.daotier.services.RoleDataService;
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
	
	@Autowired 
	private HospitalDataService hospitalDataService;
	
	@Autowired
	private RoleDataService roleDataService;
	
	
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
	
	/**
	 * 
	 * @param headVO
	 * @param loggedInUserId
	 * @return Head
	 */
	private Head convertHeadVOToModel(HeadVO headVO, String loggedInUserId){
		Head head = null;
		
		if(headVO != null){
			head = new Head();
			head.setDataStoreId(headVO.getDataStoreId());
			head.setUserId(headVO.getEmail());
			head.setEmail(headVO.getEmail());
			head.setFirstName(headVO.getFirstName());
			head.setMiddleName(headVO.getMiddleName());
			head.setLastName(headVO.getLastName());
			head.setIsActive(true);
			head.setIsTerminated(false);
			
			Role role = roleDataService.getRoleById(RoleAccessLevel.HEAD.getRole());
			head.setRole(role);
			
			SystemUtil.setBaseModelValues(head, loggedInUserId, SYSTEM_OF_RECORDX);
		}
		return head;
	}

}
