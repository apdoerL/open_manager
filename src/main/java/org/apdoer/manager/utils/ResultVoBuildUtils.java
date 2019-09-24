package org.apdoer.manager.utils;


import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.model.vo.ResultVo;


/**
 * @author apdoer
 */
public class ResultVoBuildUtils {
	private ResultVoBuildUtils() {}
	
	/**
	 * 构造ResultVo 对象
	 * @param code
	 * 			结果码值
	 * @param msg
	 * 			结果消息
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildResultVo(int code, String msg) {
		return new ResultVo(code, msg);
	}
	
	/**
	 * 构造ResultVo 对象
	 * @param code 
	 * 			结果码值
	 * @param msg
	 * 			结果消息
	 * @param data
	 * 			结果数据
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildResultVo(int code, String msg, Object data) {
		return new ResultVo(code, msg, data);
	}
	
	/**
	 * 构造success的ResultVo对象（code=0,msg="success"）
	 * @param data
	 * 			结果数据
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildSuccessResultVo(Object data) {
		return new ResultVo(ExceptionCodeEnum.SUCCESS.getCode(), "success", data);
	}
	
	/**
	 * 构造success的ResultVo对象（code=0,msg="success"）
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildSuccessResultVo() {
		return new ResultVo(ExceptionCodeEnum.SUCCESS.getCode(), "成功");
	}

	/**
	 * 构造success的ResultVo对象（code=1,msg="faild"）
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildFaildResultVo() {
		return new ResultVo(ExceptionCodeEnum.FAIL.getCode(), "faild");
	}
	
	public static ResultVo buildFaildResultVo(String msg) {
		return new ResultVo(ExceptionCodeEnum.FAIL.getCode(), msg);
	}
	
	/**
	 * 构造success的ResultVo对象（code=1,msg="faild"）
	 * @param data
	 * 			结果数据
	 * @return ResultVo
	 * 			结果对象
	 */
	public static ResultVo buildFaildResultVo(Object data) {
		return new ResultVo(ExceptionCodeEnum.FAIL.getCode(), "faild", data);
	}
	
	public static ResultVo buildFaildResultVo(Integer code, String msg) {
        return new ResultVo(code, msg);
    }
}
