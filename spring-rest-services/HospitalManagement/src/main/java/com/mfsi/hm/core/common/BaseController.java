/**
 * 
 */
package com.mfsi.hm.core.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mfsi.hm.biztier.vos.TokenValidationVO;
import com.mfsi.hm.core.exceptions.TokenException;
import com.mfsi.hm.core.responses.ResponseType;
import com.mfsi.hm.core.responses.RestResponseVO;

/**
 * @author shah
 *
 */
//@CrossOrigin("*")
public class BaseController {

	public static Logger LOGGER = LogManager.getLogger(BaseController.class);
	
	@Autowired
	BaseHelper baseHelper;
	
	public TokenValidationVO validateToken(String authToken, HttpServletRequest request){
//		HttpSession session = request.getSession(false);
//		if(session != null){
//			String sessionToken = (String) session.getAttribute(USER_SESSION_TOKEN);
//			if(StringUtils.isNotBlank(sessionToken) && sessionToken.equals(authToken)){
//				return baseHelper.getUserDetails(authToken);
//			} else {
//				throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXCEPTION, null, APP_LOCALE));
//			}
//		} else {
//			throw new TokenException(ERROR_CODE_TOKEN_EXCEPTION, SpringHelper.getMessage(ERROR_MESSAGE_TOKEN_EXCEPTION, null, APP_LOCALE));
//		}
		return baseHelper.getUserDetails(authToken);
	}
	
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<RestResponseVO> defaultErrorHandler(HttpServletRequest httpServletRequest, Throwable e) throws Throwable {
		
		ResponseEntity<RestResponseVO> response = null;
		RestResponseVO responseVO = new RestResponseVO();
		
		LOGGER.error(e.getMessage(), e);
		e.printStackTrace(System.err);
		
		responseVO.setResponseType(ResponseType.ERROR);
		responseVO.setErrorDescription(ExceptionUtils.getRootCauseMessage(e));
		responseVO.setErrorStackTrace(ExceptionUtils.getStackTrace(e));
		
		if(e instanceof TokenException){
			response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.FORBIDDEN);
		} else {
			response = new ResponseEntity<RestResponseVO>(responseVO, HttpStatus.OK);
		}
		
		return response;
	}
}
