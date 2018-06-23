/**
 * 
 */
package com.mfsi.hm.core.filters;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_TOKEN_EXCEPTION;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_EXIST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mfsi.hm.biztier.vos.TokenValidationVO;
import com.mfsi.hm.biztier.vos.UserVO;
import com.mfsi.hm.configuration.SpringHelper;
import com.mfsi.hm.core.exceptions.TokenException;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.webtier.helpers.UserHelper;

/**
 * @author shah
 *
 */
@Component("authInterceptor")
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserHelper userHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String requestURI = request.getRequestURI();
		String authToken = request.getHeader("X-Auth-Token");
		
		if(StringUtils.isNotBlank(authToken)){
			TokenValidationVO tokenValidationVO = userHelper.getUserDetails(authToken);
			if(ResponseType.SUCCESS.getType().equals(tokenValidationVO.getResponseType())){
				UserVO loggedInUser = tokenValidationVO.getUser();
				LoggedInUserContext.setUser(loggedInUser);
				return true;
			}
			return false;
			
		} else if(!(requestURI.equals("/user/doLogin") || requestURI.equals("/user/forgotPassword"))){
			throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
					SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXIST, null, APP_LOCALE));
		} else {
			return true;
		}
	}
}
