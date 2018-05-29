/**
 * 
 */
package com.mfsi.hm.configuration;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

/**
 * @author shah
 *
 */
@Service
public class SpringHelper {
	public static Logger LOGGER = LogManager.getLogger(SpringHelper.class);
	
	private static ApplicationContext context;
	
	@Autowired
	public SpringHelper(ApplicationContext ac) {
		context = ac;
	}
	
	public static String getMessage(String key, Object []values, Locale locale) {
		String messages = null;
		try {
			return context.getMessage(key, values, locale);			
		} catch (NoSuchMessageException e){
			LOGGER.error(e.getMessage(),e);
		}
		return messages;
		
	}
}
