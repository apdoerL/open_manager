package org.apdoer.manager.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * 正则表达式验证工具类，验证数据是否符合正则规范
 * @author apdoer
 * @date 2018/8/30
 */
public class RegexUtils {

	private static final int PASSWORD_LENGTH_UNDER_LIMIT = 6;
	private static final int PASSWORD_LENGTH_ABOVE_LIMIT = 20;
	/**
	 * 判断字符串是否符合正则表达式
	 * @param str 需要校验的字符串
	 * @param regex 正则表达式
	 * @return 返回是否验证通过的boolean
	 */
	public static boolean find(String str, String regex) {
		Pattern p = compile(regex);
		Matcher m = p.matcher(str);
		boolean b = m.find();
		return b;
	}
	
	/**
	 * 判断输入的字符串是否符合Email格式.
	 * @param email 需要校验的邮箱地址
	 * @return 符合Email格式返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}
	
	
	/**
	 * 判断输入的密码字符串是否符合6-20个英文、数字字符
	 * @param password 密码字符串
	 * @return 是否匹配的boolean结果
	 */
	public static boolean isPassword(String password) {
		if (password == null || password.length() < PASSWORD_LENGTH_UNDER_LIMIT || password.length() > PASSWORD_LENGTH_ABOVE_LIMIT) {
			return false;
		}
		Pattern pattern = compile("^[a-zA-Z0-9]{6,20}$");
		return pattern.matcher(password).matches();
	}

}
