/**
 * 
 */
package com.mfsi.hm.biztier.services;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.CHANGE_PASSWORD_MAIL_FAILURE;
import static com.mfsi.hm.core.common.Constants.CHANGE_PASSWORD_MAIL_SUCCESS;
import static com.mfsi.hm.core.common.Constants.CREATE_USER_BODY;
import static com.mfsi.hm.core.common.Constants.CREATE_USER_LOGIN_FAILURE;
import static com.mfsi.hm.core.common.Constants.CREATE_USER_MAIL_FAILURE;
import static com.mfsi.hm.core.common.Constants.CREATE_USER_MAIL_SUCCESS;
import static com.mfsi.hm.core.common.Constants.CREATE_USER_SUBJECT;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_ACCESS_DENIED;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_INVALID_CREDENTIALS;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_TOKEN_EXCEPTION;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_USER_LOGIN;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_ACCESS_DENIED;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_INVALID_CREDENTIALS;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_EXIST;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_USER_ACTIVE;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_USER_EXIST;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_USER_TERMINATED;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_USER_LOGIN_FAILURE;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_USER_LOGIN_TOKEN_ISSUE;
import static com.mfsi.hm.core.common.Constants.FORGOT_PASSWORD_BODY;
import static com.mfsi.hm.core.common.Constants.FORGOT_PASSWORD_SUBJECT;
import static com.mfsi.hm.core.common.Constants.MAX_LOGIN_ATTEMPTS;
import static com.mfsi.hm.core.common.Constants.PASSWORD_CHANGED_FAILURE;
import static com.mfsi.hm.core.common.Constants.PASSWORD_CHANGED_SUCCESS;
import static com.mfsi.hm.core.common.Constants.SYSTEM_OF_RECORDX;
import static com.mfsi.hm.core.common.Constants.TEMP_CODE_EXPIRY_TIME;
import static com.mfsi.hm.core.common.Constants.USER_LOGGED_OUT;
import static com.mfsi.hm.core.common.Constants.VERSION_NUMBER;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.mfsi.hm.core.util.SystemUtil;
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
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDataService userDataService;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private EmailService emailService;
	
	private Long maxAttemps = 10L;
	private Long tempAuthCodeExpiryTime = 9000000L;
	
	@Override
	public BizResponseVO validateToken(String authToken) {
		BizResponseVO bizResponse = new BizResponseVO();
		Token token = userDataService.getToken(authToken);
		
		if(token != null){
			User user = userDataService.findUser(token.getUserId());
			Role role = user != null ? user.getRole() : null;
			
			if(user != null & role != null){
				if(!user.getIsTerminated()){
					if(user.getIsActive()){
						UserVO userVO = convertUserModelToVO(user);
						bizResponse.setResponseType(ResponseType.SUCCESS);
						bizResponse.setResponseData(userVO);
					} else {
						throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
								SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_ACTIVE, null, APP_LOCALE));
					}
				} else {
					throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
							SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_TERMINATED, null, APP_LOCALE));
				}
			} else {
				throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
						SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_USER_EXIST, null, APP_LOCALE));
			}
		} else {
			throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXIST, null, APP_LOCALE));
		}
		
		return bizResponse;
	}

	@Override
	public Boolean saveLoginObject(Login login) {
		Boolean isLoginObjectSaved = Boolean.FALSE;
		Login loginSave = null;
		loginSave = userDataService.saveLogin(login);
		
		if(loginSave != null){
			isLoginObjectSaved = Boolean.TRUE;
		}
		
		return isLoginObjectSaved;
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
				} else {
					throw new UserLoginException(ERROR_CODE_USER_LOGIN, 
							SpringHelper.getMessage(ERROR_MESSAGE_USER_LOGIN_FAILURE, null, APP_LOCALE));
				}

			} else{
				throw new AccessDeniedException(ERROR_CODE_ACCESS_DENIED, 
						SpringHelper.getMessage(ERROR_MESSAGE_ACCESS_DENIED, null, APP_LOCALE));
			}
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		
		
		return bizResponse;
	}

	@Override
	public BizResponseVO forgotPassword(String userId) {
		BizResponseVO bizResponse = new BizResponseVO();

		Login login = userDataService.getLogin(userId);
		if (login != null) {
			
			Boolean isMailSent = Boolean.FALSE;			
			String tempAuthCode = StringHelper.randomString(20, Boolean.FALSE, Boolean.FALSE);

			login.setTempAuthCode(tempAuthCode);
			Configuration configuration = coreService.findByConfiguration(TEMP_CODE_EXPIRY_TIME);
			if(configuration != null && configuration.getValue() != null){
				tempAuthCodeExpiryTime = Long.parseLong(configuration.getValue());
			}
			login.setExpiryDuration(tempAuthCodeExpiryTime);
			login.setAuthCodeCreatedTime(new Date());

			Login loginObject = userDataService.saveLogin(login);
			if(loginObject != null){
				User user = loginObject.getUser(); 
				
				String subject = SpringHelper.getMessage(FORGOT_PASSWORD_SUBJECT, null, APP_LOCALE);
				Object []values = {getUserName(login), tempAuthCode};
				String emailBody = SpringHelper.getMessage(FORGOT_PASSWORD_BODY, values, APP_LOCALE);
				String []toEmails = {user.getEmail()};
				isMailSent = emailService.sendEMail(toEmails, subject, emailBody);
				
				if (Boolean.TRUE.equals(isMailSent)) {
					bizResponse.setResponseType(ResponseType.SUCCESS);
					bizResponse.setMessage(SpringHelper.getMessage(CHANGE_PASSWORD_MAIL_SUCCESS, null, APP_LOCALE));
				} else {
					bizResponse.setResponseType(ResponseType.ERROR);
					bizResponse.setMessage(SpringHelper.getMessage(CHANGE_PASSWORD_MAIL_FAILURE, null, APP_LOCALE));
				}
			} else {
				bizResponse.setResponseType(ResponseType.ERROR);
				bizResponse.setMessage(SpringHelper.getMessage(PASSWORD_CHANGED_FAILURE, null, APP_LOCALE));
			}
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		} 

		return bizResponse;
	}

	@Override
	public BizResponseVO resetPassword(String userId, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		BizResponseVO bizResponse = new BizResponseVO();
		Login login = null;
		login = userDataService.getLogin(userId);
		if (login != null) {
			login.setPassword(DigestUtils.sha256Hex(password + login.getPassSalt()));
			login.setTempAuthCode(null);
			login.setExpiryDuration(null);
			Login loginObject = userDataService.saveLogin(login);
			if(loginObject != null){
				bizResponse.setResponseType(ResponseType.SUCCESS);
				bizResponse.setMessage(SpringHelper.getMessage(PASSWORD_CHANGED_SUCCESS, null, APP_LOCALE));
			} else {
				bizResponse.setResponseType(ResponseType.ERROR);
				bizResponse.setMessage(SpringHelper.getMessage(PASSWORD_CHANGED_FAILURE, null, APP_LOCALE));
			}
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return bizResponse;
	}

	@Override
	public BizResponseVO changePassword(String userId, String oldPassword, String newPassword)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		BizResponseVO bizResponse = new BizResponseVO();
		Login login = null;
		login = userDataService.getLogin(userId);
		if (login != null) {
			if (DigestUtils.sha256Hex(oldPassword + login.getPassSalt()).equals(login.getPassword())) {
				login.setPassword(DigestUtils.sha256Hex(newPassword + login.getPassSalt()));
				Login loginObject = userDataService.saveLogin(login);
				if(loginObject != null){
					bizResponse.setResponseType(ResponseType.SUCCESS);
					bizResponse.setMessage(PASSWORD_CHANGED_SUCCESS);
				} else {
					bizResponse.setResponseType(ResponseType.ERROR);
					bizResponse.setMessage(PASSWORD_CHANGED_FAILURE);
				}
			} else {
				throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
						SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
			}
		} else {
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
		return bizResponse;
	}

	@Override
	public BizResponseVO doLogout(String authToken, String userName) {
		BizResponseVO bizResponse = new BizResponseVO();
		Token token = userDataService.findByToken(authToken);
		if(token != null){
			userDataService.deleteToken(authToken);
			bizResponse.setMessage(SpringHelper.getMessage(USER_LOGGED_OUT, null, APP_LOCALE));
			bizResponse.setResponseType(ResponseType.SUCCESS);
			return bizResponse;
		} else{
			throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
		}
	}
	
	@Override
	public BizResponseVO saveLoginAndMailCredentials(User user){
		BizResponseVO response = new BizResponseVO();
		
		Login login = new Login();
		SystemUtil.setBaseModelValues(login, user.getUserId(), SYSTEM_OF_RECORDX);
		String passSalt = StringHelper.randomString(30, Boolean.FALSE, Boolean.TRUE);
		
		String password = StringHelper.randomString(10, Boolean.TRUE, Boolean.TRUE);
		String sha256SaltedPassword = DigestUtils.sha256Hex(password + passSalt);
		login.setAuthCodeCreatedTime(new Date());
		login.setExpiryDuration(tempAuthCodeExpiryTime);
		login.setPassSalt(passSalt);
		login.setPassword(sha256SaltedPassword);
		login.setTempAuthCode(null);
		login.setUser(user);
		
		// Save the login object in database
		Boolean isLoginInfoSaved = saveLoginObject(login);
	
		
		// If login is saved in database then attempt to send mail to created user for login credentials.
		Boolean isMailSent = Boolean.FALSE;
		
		if(isLoginInfoSaved){
			String subject = SpringHelper.getMessage(CREATE_USER_SUBJECT, null, APP_LOCALE);
			Object []values = {getUserName(login), login.getUser().getRole().getName(), login.getUser().getUserId(),  password};
			String emailBody = SpringHelper.getMessage(CREATE_USER_BODY, values, APP_LOCALE);
			String []toEmails = {user.getEmail()};
			isMailSent = emailService.sendEMail(toEmails, subject, emailBody);
			
			if (Boolean.TRUE.equals(isMailSent)) {
				response.setResponseType(ResponseType.SUCCESS);
				response.setMessage(SpringHelper.getMessage(CREATE_USER_MAIL_SUCCESS, null, APP_LOCALE));
			} else {
				response.setResponseType(ResponseType.ERROR);
				response.setMessage(SpringHelper.getMessage(CREATE_USER_MAIL_FAILURE, null, APP_LOCALE));
			}
		} else {
			response.setResponseType(ResponseType.ERROR);
			response.setMessage(SpringHelper.getMessage(CREATE_USER_LOGIN_FAILURE, null, APP_LOCALE));
		}
		
		return response;
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
			roleVO.setName(role.getName());
			roleVO.setId(role.getId());
		}
		return roleVO;
	}
	
	private LoginSuccessVO convertLoginSuccessVOToModel(ValidateUserVO validatedUser, Login login) {
		User user = login.getUser();
		LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
		loginSuccessVO.setUserName(getUserName(login));
		loginSuccessVO.setUserCode(user.getUserId());
		loginSuccessVO.setUserRole(user.getRole().getId());
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
		
		} else if (login.getTempAuthCode() != null && login.getTempAuthCode().equals(code)) {
			if(StringHelper.dateTime(login.getAuthCodeCreatedTime(), login.getExpiryDuration()).compareTo(StringHelper.dateTime(new Date(), 0L)) > 0){
				userDataService.removeLoginAttempts(userId);
				
				String authToken = generateToken(userId);
				validatedUser.setAuthToken(authToken);
			} else{
				throw new InvalidCredentialsException(ERROR_CODE_INVALID_CREDENTIALS, 
						SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS, null, APP_LOCALE));
			}
			
		} else {
			UserLoginException exception = new UserLoginException(ERROR_CODE_USER_LOGIN, 
					SpringHelper.getMessage(ERROR_MESSAGE_INVALID_CREDENTIALS,	 null, APP_LOCALE));
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
			} else {
				loginAttempts = new LoginAttempts(userId, userId, currentDate, currentDate, SYSTEM_OF_RECORDX, VERSION_NUMBER);
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
			throw new UserLoginException(ERROR_CODE_USER_LOGIN, 
					SpringHelper.getMessage(ERROR_MESSAGE_USER_LOGIN_TOKEN_ISSUE, null, APP_LOCALE));
	}
	
	private Token saveToken(String authToken, String userId) {
		Date currentDate = new Date();
		Token token = new Token(userId, userId, currentDate, currentDate, SYSTEM_OF_RECORDX, VERSION_NUMBER);
		token.setUserId(userId);
		token.setToken(authToken);
		token = userDataService.saveToken(token);
		return token;
	}
}
