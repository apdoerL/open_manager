package org.apdoer.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 异常错误码枚举   0400-0499：服务响应 0500-0699：请求参数验证 0700-0899：业务验证
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionCodeEnum {

	/**
	 * 0400-0499：服务响应
	 */
	/*成功*/
	SUCCESS( 0, "success" ),

	/*失败*/
	FAIL( 101080400, "fail" ),

	/*请求超时*/
	REQUEST_TIMEOUT( 101080401, "request timeout" ),

	/*未知异常*/
	UNKNOWN_EXCEPTION_CODE( 101080402, "unknown_exception_code" ),

	/*无权限用户*/
	UNAUTHORIZED_USERS( 101080404, "unauthorized users" ),

	/**
	 * 请求参数验证 0700-0899
	 */


	/**
	 * 0700-0899：业务验证
	 */


	;
	private int code;

	private String value;

	private static Map<Integer, String> map = new HashMap<Integer, String>();

	private static List<ExceptionCodeEnum> list = new ArrayList<ExceptionCodeEnum>();

	static {
		for (ExceptionCodeEnum status : ExceptionCodeEnum.values()) {
			map.put(status.getCode(), status.getValue());
		}
		list.addAll(Arrays.asList(ExceptionCodeEnum.values()));
	}

	/**
	 * 返回map类型形式
	 *
	 * @return
	 */
	public static Map<Integer, String> getMap() {
		return map;
	}

	/**
	 * 返回list类型形式
	 *
	 * @return
	 */
	public static List<ExceptionCodeEnum> getList() {
		return list;
	}

	/**
	 * 根据code获取枚举类型
	 *
	 * @param codeNo
	 * @return
	 */
	public static ExceptionCodeEnum getCategory(int codeNo) {
		for (ExceptionCodeEnum status : list) {
			if (status.getCode() == codeNo) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 根据code获取枚举类型中文名称
	 *
	 * @param codeNo
	 * @return
	 */
	public static String getName(int codeNo) {
		for (ExceptionCodeEnum status : list) {
			if (status.getCode() == codeNo) {
				return status.getValue();
			}
		}
		return null;
	}
}
