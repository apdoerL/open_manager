package org.apdoer.manager.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.ui.ModelMap;

/**
 * @author apdoer
 */
public class GoogleAuthUtils {

	/***
	 * 生成secret
	 * 
	 * @return
	 */
	public static String getKey() {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey key = gAuth.createCredentials();
		String secretKey = key.getKey();
		return secretKey;
	}

	/***
	 * 生成secret
	 * 
	 * @return
	 */
	public static ModelMap getKey(String hostName, String username) {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		final GoogleAuthenticatorKey key = gAuth.createCredentials();
		String secret = key.getKey();
		String qrcode = "otpauth://totp/" + hostName + ":" + username + "?secret=" + secret;
		ModelMap mm = new ModelMap();
		mm.put( "secret", secret );
		mm.put( "qrcode", qrcode );
		return mm;
	}

	/***
	 * 校验key和password
	 * 
	 * @param secretKey
	 * @param password
	 * @return
	 */
	public static boolean isCodeValid(String secretKey, Integer password) {
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		boolean isCodeValid = gAuth.authorize( secretKey, password );
		return isCodeValid;
	}

}
