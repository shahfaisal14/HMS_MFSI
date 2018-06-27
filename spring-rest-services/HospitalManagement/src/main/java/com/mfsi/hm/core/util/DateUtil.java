/**
 * 
 */
package com.mfsi.hm.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shah
 *
 */
public class DateUtil {
	
	private static Logger LOGGER = LogManager.getLogger(DateUtil.class);

	public static Date convertStringToDate(String dateString, String format) {

		Date result = null;
		if(StringUtils.isNotBlank(dateString)){
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				result = formatter.parse(dateString);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(),e);
			}
		}
		return result;
	}
	
	public static String convertDateToString(Date date, String format) {

		String result = null;
		if(date != null && StringUtils.isNotBlank(format)){
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			result = formatter.format(date);
		}
		return result;
	}
}
