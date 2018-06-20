/**
 * 
 */
package com.mfsi.hm.core.util;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author shah
 *
 */
public class StringHelper {
	
	private static final String ALPHABATES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHA_NUMBER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHA_SPECIAL_NUMBER = "0123456789!@#$&*()_-+=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHA_SPECIAL = "!@#$&*()_-+=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String randomString(Integer length, Boolean specialCharaters, Boolean numbers) {

		StringBuilder builder = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		
		String baseString = null;
		if (specialCharaters && numbers) {
			baseString = ALPHA_SPECIAL_NUMBER;
		} else if (specialCharaters) {
			baseString = ALPHA_SPECIAL;
		} else if (numbers) {
			baseString = ALPHA_NUMBER;
		} else {
			baseString = ALPHABATES;
		}

		for (int i = 0; i < length; i++)
			builder.append(baseString.charAt(random.nextInt(baseString.length())));

		return builder.toString();
	}
	
	public static Date dateTime(Date date, Long hours){
		Calendar expireDate = Calendar.getInstance();
	    expireDate.setTimeInMillis(date.getTime() + hours);
		expireDate.getTime(); 
		return expireDate.getTime();
	}
	
	public static String trimSpaces(String sourceString){
		String result = null;
		if(StringUtils.isNotBlank(sourceString)){
			result = sourceString.replaceAll(" ", "_");
		}
		return result;
	}
	
	/**
	 *  @return true if first String is bigger than second
	 */
	public static Boolean compare(String first, String second){
		if(first.compareTo(second) > 0)
			return true;
		return false;
	}
}
