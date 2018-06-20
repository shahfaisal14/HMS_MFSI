/**
 * 
 */
package com.mfsi.hm.core.common;

import java.util.Base64;

/**
 * @author shah
 *
 */
public class EncodingHelper {
	public synchronized static String encodeString(String string, int encodeLevel) {

		String encodedString = string;
		for (int i = 0; i < encodeLevel; i++) {
			if (encodedString != null) {
				encodedString = Base64.getEncoder().encodeToString(encodedString.getBytes());
			}
		}
		return encodedString;
	}
	
	public synchronized static String decodeString(String encodedString, int encodeLevel){
		
		String result = encodedString;
		for(int i = 0; i<encodeLevel; i++){
			result = new String(Base64.getDecoder().decode(result));
		}
		return result;
	}
}
