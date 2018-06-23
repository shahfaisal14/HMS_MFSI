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
	
	public static final Long VERSION_NUMBER = 0L;
	
	public static final String USER_SESSION_TOKEN = "current.session.token";
	public static final String USER_SESSION_USER_NAME = "current.session.user.name";
	public static final String SYSTEM = "SYSTEM";
	public static final String SYSTEM_OF_RECORDX = "HCGF";
	public static final String SYSTEM_OF_RECORDX_SYSTEM = "HCGF";
	public static final String SEND_PAYMENT_NOTIFICATION = "send.payment.notification";
	public static final String THIS = "THIS";
	public static final String IS_REQUIRED = "THIS field is required to access the API.";
	public static final String SUCCESS_MESSAGE = "You have got required data sucessfuly.";
	public static final boolean IS_ACTIVE_HOSPITAL = true;
	public static final boolean IS_ACTIVE_USER = true;
	
	public static final String MAX_LOGIN_ATTEMPTS = "max.wrong.login.attemps";
	
	
	//Error Codes
	public static final String ERROR_CODE_INVALID_CREDENTIALS = "ERR 001";
	public static final String ERROR_MESSAGE_INVALID_CREDENTIALS = "invalid.credentials";
	public static final String ERROR_CODE_ACCESS_DENIED = "ERR 002";
	public static final String ERROR_MESSAGE_ACCESS_DENIED = "access.denied";
	public static final String ERROR_CODE_USER_LOGIN = "ERR 003";
	public static final String ERROR_MESSAGE_USER_LOGIN_TOKEN_ISSUE = "token.generation.failed";
	public static final String ERROR_MESSAGE_USER_LOGIN_FAILURE = "login.failure";
	public static final String ERROR_CODE_TOKEN_EXCEPTION = "ERR 004";
	public static final String ERROR_MESSAGE_TOKEN_EXCEPTION = "token.diff.session";
	public static final String ERROR_MESSAGE_TOKEN_EXIST = "token.not.exist";
	public static final String ERROR_MESSAGE_TOKEN_USER_EXIST = "token.user.not.exist";
	public static final String ERROR_MESSAGE_TOKEN_USER_TERMINATED = "token.user.terminated";
	public static final String ERROR_MESSAGE_TOKEN_USER_ACTIVE = "token.user.not.active";
	public static final String ERROR_CODE_PARAM_EXCEPTION = "ERR 005";
	public static final String ERROR_MESSAGE_PARAM_EXCEPTION = "missing.param";
	public static final String ERROR_CODE_ACCESS_LEVEL = "ERR 006";
	public static final String ERROR_MESSAGE_ACCESS_LEVEL = "Your access level is low to perform this action.";
	public static final String ERROR_MESSAGE_ACCESS_LEVEL_USER_CREATE = "error.access.level.user.create";
	public static final String ERROR_CODE_AES_ENCRYPTION= "ERR 007";
	
	
	public static final String USER_LOGGED_OUT = "user.logout";	
	public static final String TEMP_CODE_EXPIRY_TIME = "temp.auth-code.expiry.time";
	
	public static final String FORGOT_PASSWORD_SUBJECT = "forgot.password.email.subject";
	public static final String FORGOT_PASSWORD_BODY = "forgot.password.email.body";
	
	public static final String CREATE_USER_SUBJECT = "create.user.email.subject";
	public static final String CREATE_USER_BODY = "create.user.email.body";
	
	// API specific messages
	public static final String ERROR_HOSPITAL_SAVE = "error.hospital.save";
	public static final String SUCCESS_HOSPITAL_SAVE = "success.hospital.save";
	
	public static final String USER_CREATE_SUCCESS = "user.create.success";
	public static final String USER_CREATE_ERROR = "user.create.error";
	
	public static final String PASSWORD_CHANGED_SUCCESS = "change.password.success" ;
	public static final String PASSWORD_CHANGED_FAILURE = "change.password.failure" ;
	public static final String CHANGE_PASSWORD_MAIL_SUCCESS = "password.mail.success";
	public static final String CHANGE_PASSWORD_MAIL_FAILURE = "password.mail.failure";
	public static final String CREATE_USER_MAIL_SUCCESS = "create.user.mail.success";
	public static final String CREATE_USER_MAIL_FAILURE = "create.user.mail.failure";
	
	public static final String CREATE_USER_LOGIN_FAILURE = "create.user.login.failure";
	
	// Base Controller Messages
	public static final String TOKEN_ERROR = "TOKEN_ERROR";
	public static final String ACCESS_ERROR = "ACCESS_ERROR";
	public static final String AUTHERIZATION_ERROR = "AUTHERIZATION_ERROR";
	
	public static final String AUTHERIZATION_ERROR_MESSAGE = "Not able to get supported role list for this API.";
	public static final String ACCESS_ERROR_MESSAGE = "You are not autherized to call this API.";
	
	// Date Formats
	public static final String MM_DD_YYYY_DATE_FORMAT = "MM/dd/yyyy";
	public static final String MM_DD_YYYY_HH_MM_SS_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
}
