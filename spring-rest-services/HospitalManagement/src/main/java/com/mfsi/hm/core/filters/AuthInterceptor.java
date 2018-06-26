/**
 * 
 */
package com.mfsi.hm.core.filters;

import static com.mfsi.hm.core.common.Constants.APP_LOCALE;
import static com.mfsi.hm.core.common.Constants.ERROR_CODE_TOKEN_EXCEPTION;
import static com.mfsi.hm.core.common.Constants.ERROR_MESSAGE_TOKEN_EXIST;
import static com.mfsi.hm.core.common.Constants.OPTIONS;

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
	
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		System.out.println(request.getMethod());

		if(OPTIONS.equals(request.getMethod())){
			response.setStatus(HttpServletResponse.SC_OK);	
		} else if (request.getRequestURL().indexOf("/doLogin") == -1 && 
				request.getRequestURL().indexOf("/forgotPassword") == -1) {
			
			String authToken = request.getHeader("X-Auth-Token");
			System.out.println("Auth token is " + authToken + " for request " + request.getMethod());

			if (StringUtils.isBlank(authToken)) {
				throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, 
						SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXIST, null, APP_LOCALE));
			} else if(StringUtils.isNotBlank(authToken) && isValidToken(authToken)){
				response.setStatus(HttpServletResponse.SC_OK);
				return true;
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidToken(String authToken){
		TokenValidationVO tokenValidationVO = userHelper.getUserDetails(authToken);
		if(ResponseType.SUCCESS.getType().equals(tokenValidationVO.getResponseType())){
			UserVO loggedInUser = tokenValidationVO.getUser();
			LoggedInUserContext.setUser(loggedInUser);
			return true;
		}
		return false;
	}
}
