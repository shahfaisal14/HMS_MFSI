/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.biztier.comparators.EntitlementComparator;
import com.mfsi.hm.biztier.vos.EntitledModuleVO;
import com.mfsi.hm.biztier.vos.LoginSuccessVO;
import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.biztier.vos.RoleVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.biztier.vos.ValidateUserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.AccessDeniedException;
import com.mfsi.hm.core.exceptions.InvalidCredentialsException;
import com.mfsi.hm.core.exceptions.TokenException;
import com.mfsi.hm.core.exceptions.UserLoginException;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.util.StringHelper;
import com.mfsi.hm.daotier.models.Configuration;
import com.mfsi.hm.daotier.models.Login;
import com.mfsi.hm.daotier.models.LoginAttempts;
import com.mfsi.hm.daotier.models.Module;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.Token;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.services.UserDataService;

/**
 * @author shah
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Long VERSION = null;

	@Autowired
	private UserDataService userDataService;
	
	@Autowired
	private CoreService coreService;
	
	private Long maxAttemps = 10L;
	
	@Override
	public BizResponseVO validateToken(String authToken) {
		BizResponseVO bizResponse = new BizResponseVO();
		Token token = userDataService.getToken(authToken);
		
		if(token != null){
			User user = userDataService.findUser(token.getUserId());
			Role role = user != null ? user.getRole() : null;
			
			if(user != null & role != null){
				if(user.getIsTerminated()){
					if(user.getIsActive()){
						UserVO userVO = convertUserModelToVO(user);
						bizResponse.setResponseType(ResponseType.SUCCESS);
						bizResponse.setResponseData(userVO);
					} else {
						throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_ACTIVE, null, APP_LOCALE));
					}
				} else {
					throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_TERMINATED, null, APP_LOCALE));
				}
			} else {
				throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_EXIST, null, APP_LOCALE));
			}
		} else {
			throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXIST, null, APP_LOCALE));
		}
		
		return bizResponse;
	}

	@Override
	public Boolean saveLoginObject(Login login) {
		Boolean isLoginObjectSave = Boolean.FALSE;
		Login loginSave = null;
		loginSave = userDataService.saveLogin(login);
		
		if(loginSave != null){
			isLoginObjectSave = Boolean.TRUE;
		}
		
		return isLoginObjectSave;
	}
	
	@Override
	public BizResponseVO doLogin(LoginVO loginVO) {
		BizResponseVO bizResponse = new BizResponseVO();
		
		String userCode = loginVO.getUserCode();
		String password = loginVO.getPassCode();
		
		Login login = userDataService.getLogin(userCode);
		User user = (login != null) ? login.getUser() : null;
		
		if(user != null){
			if (Boolean.TRUE.equals(user.getIsActive()) && Boolean.FALSE.equals(user.getIsTerminated())) {
				ValidateUserVO validateUser = null;
				try{
					validateUser = validatePassword(login, userCode, password);
				}catch(UserLoginException exception){
					if(exception.getShouldLock() != null && exception.getShouldLock()){
						user.setIsActive(Boolean.FALSE);
						user.setModifiedDate(new Date());
						userDataService.saveUser(user);
						login.setUser(user);
					}
					throw new InvalidCredentialsException(exception.getCode(), exception.getMessage(), exception);
				}
				
				if(validateUser != null){
					LoginSuccessVO loginSuccessVO = convertLoginSuccessVOToModel(validateUser, login);
					bizResponse.setResponseType(ResponseType.SUCCESS);
					bizResponse.setResponseData(loginSuccessVO);
					bizResponse.setMessage(validateUser.getMessage());
					if(StringUtils.isNoneBlank(validateUser.getAuthToken())){
						// TODO: Audit stuff
					}
				} else {
					throw new UserLoginException(ERROR_CODE_USER_LOGIN, SpringHelper.getMessage(ERROR_MESSAGE_USER_LOGIN_FAILURE, null, APP_LOCALE));
				}

			} else{
				throw new AccessDeniedException(ERROR_CODE_ACCESS_DENIED, SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_DENIED, null, APP_LOCALE));
			}
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		
		
		return bizResponse;
	}
	
	private UserVO convertUserModelToVO(User user){
		UserVO userVO = null;
		if (user != null) {
			userVO = new UserVO();
			userVO.setDataStoreId(user.getDataStoreId());
			userVO.setFirstName(user.getFirstName());
			userVO.setMiddleName(user.getMiddleName());
			userVO.setLastName(user.getLastName());
			userVO.setUserId(user.getUserId());
			userVO.setEmail(user.getEmail());
			userVO.setRole(convertRoleModelToVO(user.getRole()));
		}
		return userVO;
	}
	
	private RoleVO convertRoleModelToVO(Role role){
		RoleVO roleVO = new RoleVO();
		if(role != null){
			role.getName();
			role.getRoleId();
		}
		return roleVO;
	}
	
	private LoginSuccessVO convertLoginSuccessVOToModel(ValidateUserVO validatedUser, Login login) {
		User user = login.getUser();
		LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
		loginSuccessVO.setUserName(getUserName(login));
		loginSuccessVO.setUserCode(user.getUserId());
		loginSuccessVO.setAuthToken(validatedUser.getAuthToken());
		getEntitledModule(loginSuccessVO);
		return loginSuccessVO;
	}
	
	private void getEntitledModule(LoginSuccessVO loginSuccessVO) {
		Role role = userDataService.getRole(loginSuccessVO.getUserCode());
		if(role != null){
			Set<Module> modules = role.getModules();
			List<EntitledModuleVO> entitledModuleVOs = getEntitledModuleVO(modules);
			loginSuccessVO.setEntitledModules(entitledModuleVOs);
		} else{
			
		}
	}
	
	private List<EntitledModuleVO> getEntitledModuleVO(Set<Module> modules) {
		List<EntitledModuleVO> entitledModuleVOs = new ArrayList<>();
		Iterator<Module> modulesItr = modules.iterator();
		while (modulesItr.hasNext()) {
			EntitledModuleVO entitledModuleVO = new EntitledModuleVO();
			Module module = modulesItr.next();
			entitledModuleVO.setModuleId(module.getModuleId());
			entitledModuleVO.setName(module.getName());
			entitledModuleVO.setVisibleName(module.getVisibleName());
			entitledModuleVO.setRelativeUrl(module.getRelativeUrl());
			entitledModuleVO.setOrderNumber(module.getOrderNumber());
			entitledModuleVO.setImageUrl(module.getImageUrl());
			entitledModuleVO.setDescription(module.getDescription());
			entitledModuleVO.setCanCreate(module.getCanCreate());
			entitledModuleVO.setCanDelete(module.getCanDelete());
			entitledModuleVO.setCanRead(module.getCanRead());
			entitledModuleVO.setCanUpdate(module.getCanUpdate());
			entitledModuleVOs.add(entitledModuleVO);
		}
		Collections.sort(entitledModuleVOs, new EntitlementComparator());
		return entitledModuleVOs;
	}
	
	private String getUserName(Login login){
		User user = login.getUser();
		StringBuffer midName = null;
		StringBuffer lastName = null;
		StringBuffer firstName = new StringBuffer(user.getFirstName());
		if (user.getMiddleName() != null) {
			midName = new StringBuffer(user.getMiddleName());
		}
		if (user.getLastName() != null) {
			lastName = new StringBuffer(user.getLastName());
		}

		String userName = "";
		if (firstName != null && midName != null && lastName != null) {
			userName = firstName.append(" ").append(midName).append(" ").append(lastName).toString();
		}
		if (firstName != null && midName != null) {
			userName = firstName.append(" ").append(midName).toString();
		}
		if (firstName != null && lastName != null) {
			userName = firstName.append(" ").append(lastName).toString();
		}
		return userName;
	}
	
	private ValidateUserVO validatePassword(Login login ,String userId, String code) {
		ValidateUserVO validatedUser = new ValidateUserVO();
	
		if (login.getPassword().equals(DigestUtils.sha256Hex(code + login.getPassSalt()))) {
			userDataService.removeLoginAttempts(userId);
			
			String authToken = generateToken(userId);
			validatedUser.setAuthToken(authToken);
		
		} else if (login.getTempAuthCode() != null && DigestUtils.sha256Hex(login.getTempAuthCode()).equals(code)) {
			if(StringHelper.dateTime(login.getAuthCodeCreatedTime(), login.getExpiryDuration()).compareTo(StringHelper.dateTime(new Date(), 0L)) > 0){
				userDataService.removeLoginAttempts(userId);
				
				String authToken = generateToken(userId);
				validatedUser.setAuthToken(authToken);
			} else{
				throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
			}
			
		} else {
			UserLoginException exception = new UserLoginException(ERROR_CODE_USER_LOGIN, SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS,	 null, APP_LOCALE));
			Configuration configuration = coreService.findByConfiguration(MAX_LOGIN_ATTEMPTS);
			if(configuration != null && configuration.getValue() != null){
				maxAttemps = Long.parseLong(configuration.getValue());
			}
			Date currentDate = new Date();
			LoginAttempts loginAttempts = userDataService.getLoginAttempts(userId);
			if(loginAttempts != null){
				Long previousAttempt = loginAttempts.getWrongAttempts();
				Long currentAttempt = previousAttempt+1;
				if(maxAttemps < currentAttempt){
					exception.setShouldLock(Boolean.TRUE);
				}
				loginAttempts.setModifiedDate(currentDate);
				loginAttempts.setWrongAttempts(currentAttempt);
				userDataService.save(loginAttempts);
			}else{
				loginAttempts = new LoginAttempts(userId, userId, currentDate, currentDate, SYSTEM_OF_RECORDX, VERSION);
				loginAttempts.setUserId(userId);
				loginAttempts.setWrongAttempts(1L);
				userDataService.save(loginAttempts);
			}
			throw exception;
		}
		
		return validatedUser;
	}
	
	private String generateToken(String userId){
		String authToken = StringHelper.randomString(50, false, true);
		if(saveToken(authToken, userId) != null)
			return authToken;
		else
			throw new UserLoginException(ERROR_CODE_USER_LOGIN, SpringHelper.getMessage(ERROR_MESSAGE_USER_LOGIN_TOKEN_ISSUE, null, APP_LOCALE));
	}
	
	private Token saveToken(String authToken, String userId) {
		Date currentDate = new Date();
		Token token = new Token(userId, userId, currentDate, currentDate, SYSTEM_OF_RECORDX, VERSION);
		token.setUserId(userId);
		token.setToken(authToken);
		token = userDataService.saveToken(token);
		return token;
	}
	
}
