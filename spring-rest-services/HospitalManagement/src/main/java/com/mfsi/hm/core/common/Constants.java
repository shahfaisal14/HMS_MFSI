/**
 * 
 */
package com.mfsi.hm.core.common;

import java.util.Locale;

/**
 * @author shah
 *
 */
public class Constants {
	
	public static final Locale APP_LOCALE = new Locale("en", "US");
	
	public static final Long VERSION = 0L;
	
	public static final String USER_SESSION_TOKEN = "current.session.token";
	public static final String USER_SESSION_USER_NAME = "current.session.user.name";
	public static final String SYSTEM = "SYSTEM";
	public static final String SYSTEM_OF_RECORDX = "HCGF";
	public static final String SEND_PAYMENT_NOTIFICATION = "send.payment.notification";
	public static final String THIS = "THIS";
	public static final String IS_REQUIRED = "THIS field is required to access the API.";
	public static final String SUCCESS_MESSAGE = "You have got required data sucessfuly.";
	
	public static final String MAX_LOGIN_ATTEMPTS = "max.wrong.login.attemps";
	
	
	//Error Codes
	public static final String ERROR_CODE_INVALID_CREDENTIALS = "ERR 001";
	public static final String ERROR_MESSAGE_INVALID_CREDENTIALS = "invalid.credentials";
	public static final String ERROR_CODE_ACCESS_DENIED = "ERR 002";
	public static final String ERROR_MESSAGE_ACCESS_DENIED = "access.denied";
	public static final String ERROR_CODE_USER_LOGIN = "ERR 003";
	public static final String ERROR_MESSAGE_USER_LOGIN_TOKEN_ISSUE = "token.generation.failed";
	public static final String ERROR_MESSAGE_USER_LOGIN_FAILURE = "login.failure";
	public static final String ERROR_CODE_TOKEN_EXCEPTION= "ERR 004";
	public static final String ERROR_MESSAGE_TOKEN_EXCEPTION= "token.diff.session";
	public static final String ERROR_MESSAGE_TOKEN_EXIST= "token.not.exist";
	public static final String ERROR_MESSAGE_TOKEN_USER_EXIST= "token.user.not.exist";
	public static final String ERROR_MESSAGE_TOKEN_USER_TERMINATED="token.user.terminated";
	public static final String ERROR_MESSAGE_TOKEN_USER_ACTIVE= "token.user.not.active";
	
	// Base Controller Messages
	public static final String TOKEN_ERROR = "TOKEN_ERROR";
	public static final String ACCESS_ERROR = "ACCESS_ERROR";
	public static final String AUTHERIZATION_ERROR = "AUTHERIZATION_ERROR";
	
	public static final String AUTHERIZATION_ERROR_MESSAGE = "Not able to get supported role list for this API.";
	public static final String ACCESS_ERROR_MESSAGE = "You are not autherized to call this API.";
}
