/**
 * 
 */
package com.mfsi.hm.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mfsi.hm.core.exceptions.AESException;

/**
 * @author shah
 *
 */
public class AESHelper {
	
	private static Logger LOGGER = LogManager.getLogger(AESHelper.class.getName());
	
	private static String ALGO = "AES";
	private static String MYSQL_ENCODING = "UTF-8";

	private static String KEY = null;
	
	private static Boolean getKey() {
		Boolean keyPresent = false;

		if (KEY != null) {
			keyPresent = true;
		} else {
			BufferedReader bufferedReader = null;
			try {
				String filePath = "/key.txt";				
				bufferedReader = new BufferedReader(new InputStreamReader(AESHelper.class.getResourceAsStream(filePath)));
				StringBuilder builder = new StringBuilder();
				String line = bufferedReader.readLine();
				while (line != null) {
					builder.append(line);
					line = bufferedReader.readLine();
				}
				KEY = EncodingHelper.decodeString(builder.toString(), 3);
				keyPresent = true;

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new AESException(Constants.ERROR_CODE_AES_ENCRYPTION, e.getMessage(), e);
			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage(), e);
						throw new AESException(Constants.ERROR_CODE_AES_ENCRYPTION, e.getMessage(), e);
					}
				}
			}
		}
		return keyPresent;
	}
	
	private static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {

			final byte[] finalKey = new byte[32];

			int i = 0;
			for (byte b : key.getBytes(encoding))
				finalKey[i++ % 32] ^= b;

			return new SecretKeySpec(finalKey, ALGO);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new AESException(Constants.ERROR_CODE_AES_ENCRYPTION, e.getMessage(), e);
		}	
	}
	
	public static String encrypt(String sourceString) {

		String result = null;
		if (getKey()) {
			SecretKeySpec keySpec = generateMySQLAESKey(KEY, MYSQL_ENCODING);
			byte[] text = sourceString.getBytes();
			try {
				Cipher cipher = Cipher.getInstance(ALGO);
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
				byte[] encryptedText = cipher.doFinal(text);
				result = Base64.getEncoder().encodeToString(encryptedText);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new AESException(Constants.ERROR_CODE_AES_ENCRYPTION, e.getMessage(), e);
			}
		}
		return result;
	}
	
	public static synchronized String decrypt(String sourceString) {

		String result = null;

		byte[] unhexedBytes = Base64.getDecoder().decode(sourceString);
		if (getKey()) {
			SecretKeySpec keySpec = generateMySQLAESKey(KEY, MYSQL_ENCODING);
			try {
				Cipher cipher = Cipher.getInstance(ALGO);
				cipher.init(Cipher.DECRYPT_MODE, keySpec);

				byte[] textDecrypted = cipher.doFinal(unhexedBytes);
				result = new String(textDecrypted);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new AESException(Constants.ERROR_CODE_AES_ENCRYPTION, e.getMessage(), e);
			}
		}
		return result;
	}
}
