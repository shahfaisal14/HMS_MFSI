/**
 * 
 */
package com.mfsi.hm.daotier.services;

import static com.mfsi.hm.core.common.Constants.*;
import static com.mfsi.hm.core.util.ModelVOMapper.convertDoctorModelToVO;
import static com.mfsi.hm.core.util.ModelVOMapper.convertDoctorVOToModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.services.UserService;
import com.mfsi.hm.biztier.vos.DoctorVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.daotier.models.Doctor;
import com.mfsi.hm.daotier.models.Login;
import com.mfsi.hm.daotier.models.User;

/**
 * @author shah
 *
 */
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	DoctorDataService doctorDataService;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	RoleDataService roleDataService;
	
	@Autowired
	UserService userService;
	
	@Override
	public BizResponseVO createDoctor(DoctorVO doctorVO, UserVO loggedInUser) {
		BizResponseVO response = new BizResponseVO();
		Doctor doctor = convertDoctorVOToModel(doctorVO, loggedInUser.getUserId());
		
		Doctor newDoctor = doctorDataService.createDoctor(doctor);
		
		if(newDoctor != null){
			User user = userDataService.findUser(newDoctor.getUserId());
			response = userService.saveLoginAndMailCredentials(user);
			
			if(response != null){
				response.setResponseType(ResponseType.SUCCESS);
				response.setMessage(USER_CREATE_SUCCESS);
				response.setResponseData(newDoctor);
			}	
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(USER_CREATE_ERROR);
		}
		
		return response;
	}
	
	@Override
	public BizResponseVO getDoctorsList(){
		BizResponseVO response = new BizResponseVO();
		
		List<Doctor> doctors = doctorDataService.getDoctorsList();
		List<DoctorVO> doctorsVO = new ArrayList<DoctorVO>();
		
		if(doctors != null){
			for(Doctor doctor : doctors){
				doctorsVO.add(convertDoctorModelToVO(doctor));
			}
			response.setResponseType(ResponseType.SUCCESS);
			response.setMessage("List of doctors fetched successfully.");
			response.setResponseData(doctorsVO);	
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage("No record found for doctors.");
		}
		return response;
	}
}
