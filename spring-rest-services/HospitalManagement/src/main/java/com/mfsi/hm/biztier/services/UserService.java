/**
 * 
 */
package com.mfsi.hm.biztier.services;

import com.mfsi.hm.biztier.vos.LoginVO;
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

}
