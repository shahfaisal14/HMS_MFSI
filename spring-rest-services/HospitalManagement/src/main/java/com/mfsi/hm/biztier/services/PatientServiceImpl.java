/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_ERROR;
import static com.mfsi.hm.core.common.Constants.USER_CREATE_SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.vos.PatientVO;
import com.mfsi.hm.biztier.vos.RoleAccessLevel;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.filters.LoggedInUserContext;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.util.SystemUtil;
import com.mfsi.hm.daotier.models.Patient;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.services.PatientDataService;
import com.mfsi.hm.daotier.services.RoleDataService;
import com.mfsi.hm.daotier.services.UserDataService;

/**
 * @author shah
 *
 */
@Service("patientService")
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDataService patientDataService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDataService userDataService; 
	
	@Autowired
	private RoleDataService roleDataService;
	
	@Override
	public BizResponseVO registerPatient(PatientVO patientVO) {
		
		BizResponseVO response = new BizResponseVO();
		UserVO loggedInUser = LoggedInUserContext.getUser(); 
		
		Patient patient = patientDataService.registerPatient(converPatientVOToModel(patientVO, loggedInUser.getUserId()));
		
		if(patient != null){
			User user = userDataService.findUser(patient.getUserId());
			response = userService.saveLoginAndMailCredentials(user);
			
			if(response != null){
				response.setResponseType(ResponseType.SUCCESS);
				response.setMessage(USER_CREATE_SUCCESS);
				response.setResponseData(patient);
			}
			response.setMessage("Patient register success.");
			response.setResponseType(ResponseType.SUCCESS);
			response.setResponseData(patient);
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(USER_CREATE_ERROR);
		}
		
		return response;
	}
	
	/**
	 * 
	 * @param patientVO
	 * @param loggedInUserId
	 * @return Patient
	 */
	private Patient converPatientVOToModel(PatientVO patientVO, String loggedInUserId){
		Patient patient = null;
		if(patientVO != null){
			patient = new Patient();
			patient.setDataStoreId(patientVO.getDataStoreId());
			patient.setUserId(patientVO.getEmail());
			patient.setEmail(patientVO.getEmail());
			patient.setFirstName(patientVO.getFirstName());
			patient.setMiddleName(patientVO.getMiddleName());
			patient.setLastName(patientVO.getLastName());
			patient.setIsActive(true);
			patient.setIsTerminated(false);
			
			Role role = roleDataService.getRoleById(RoleAccessLevel.PATIENT.getRole());
			patient.setRole(role);
			SystemUtil.setBaseModelValues(patient, loggedInUserId, SYSTEM_OF_RECORDX);
		}
		return patient;
	}
	

}
