/**
 * 
 */
package com.mfsi.hm.biztier.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.mfsi.hm.biztier.vos.LoginVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.core.responses.BizResponseVO;
import com.mfsi.hm.daotier.models.Login;

/**
 * @author shah
 *
 */
public interface UserService {

	public Boolean saveLoginObject(Login login);
	
	public BizResponseVO validateToken(String authToken);

	public BizResponseVO doLogin(LoginVO loginVO);
	
	public BizResponseVO doLogout(String authToken, String userName);

	public BizResponseVO createUser(UserVO loggedInUser, UserVO userVO);
	
	public BizResponseVO forgotPassword(String userId);
	
	public BizResponseVO resetPassword(String userId, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	public BizResponseVO changePassword(String userId, String oldPassword, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
