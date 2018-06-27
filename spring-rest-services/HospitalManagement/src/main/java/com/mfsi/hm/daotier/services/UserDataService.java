package com.mfsi.hm.daotier.services;

import com.mfsi.hm.daotier.models.Login;
import com.mfsi.hm.daotier.models.LoginAttempts;
import com.mfsi.hm.daotier.models.Role;
import com.mfsi.hm.daotier.models.Token;
import com.mfsi.hm.daotier.models.User;

public interface UserDataService {
	
	public Login saveLogin(Login login);

	public Token getToken(String authToken);

	public User findUser(String userId);

	public Login getLogin(String userCode);

	public User saveUser(User user);
	
	public void removeLoginAttempts(String userId);

	public LoginAttempts getLoginAttempts(String userId);

	public Token saveToken(Token token);

	public LoginAttempts save(LoginAttempts loginAttempts);

	public Role getRole(String userCode);
	
	public Token findByToken(String authToken);

	public void deleteToken(String authToken);
	
}
