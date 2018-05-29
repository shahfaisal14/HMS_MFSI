/**
 * 
 */
package com.mfsi.hm.daotier.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Login;
import com.mfsi.hm.daotier.models.LoginAttempts;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.Token;
import com.mfsi.hm.daotier.models.User;
import com.mfsi.hm.daotier.repositories.LoginAttemptsRepository;
import com.mfsi.hm.daotier.repositories.LoginRepository;
import com.mfsi.hm.daotier.repositories.TokenRepository;
import com.mfsi.hm.daotier.repositories.UserRepository;

/**
 * @author shah
 *
 */
@Service("userDataService")
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private LoginAttemptsRepository loginAttemptsRepository;
	
	public static Logger LOGGER = LogManager.getLogger(UserDataServiceImpl.class.getName());
	
	@Override
	public Login saveLogin(Login login) {
		if(login != null){
			login = loginRepository.save(login);
		}
		return login;
	}

	@Override
	public Token getToken(String authToken) {
		Token token = null;
		
		if(authToken != null && authToken.isEmpty()){
			token = tokenRepository.findByToken(authToken);
		}
		return token;
	}

	@Override
	public User findUser(String userId) {
		User user = null;
		if(userId != null && userId.trim().length() > 0){
			user = userRepository.findByUserId(userId);
			if(user == null){
				LOGGER.debug("User does not exist in database with userId: " + userId);
			}
		}
		return user;
	}

	@Override
	public Login getLogin(String userId) {
		Login login = null;
		if(userId != null && userId.trim().length()>0){
			login = loginRepository.findLogin(userId);
			if(login == null){
				LOGGER.debug("No Login information found in database for userId: " + userId);
			}
		}		
		return login;
	}
	
	@Override
	public User saveUser(User user) {

		if (user != null) {
			user = userRepository.save(user);
		}
		return user;
	}
	
	@Override
	public void removeLoginAttempts(String userId) {
		if(userId != null && userId.trim().length()>0){
			loginAttemptsRepository.deleteByUserId(userId);
		}
	}

	@Override
	public LoginAttempts getLoginAttempts(String userId) {
		LoginAttempts loginAttempts = null;
		if(userId != null && userId.trim().length()>0){
			loginAttempts = loginAttemptsRepository.findByUserId(userId);
		}
		return loginAttempts;
	}
	
	@Override
	public Token saveToken(Token token) {
		if(token != null){
			token = tokenRepository.save(token);
		}
		return token;
	}
	
	@Override
	public LoginAttempts save(LoginAttempts attempts) {
		if(attempts != null){
			attempts = loginAttemptsRepository.save(attempts);
		}
		return attempts;
	}
	
	@Override
	public Role getRole(String userId) {
		Role roles = null;
		if (userId != null && !userId.isEmpty()) {
			User user = userRepository.findByUserId(userId);
			if (user != null) {
				roles = user.getRole();
			}
		}
		return roles;
	}
}
