package org.apdoer.manager.exception;

import lombok.Getter;
import org.apdoer.manager.enums.ExceptionCodeEnum;

/**
 * @author apdoer
 * @date 2018-11-23
 * 统一异常处理
 */
@Getter
public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 793287091294753721L;
    private ExceptionCodeEnum exceptionCodeEnum;

    public BadRequestException(String msg){
        super(msg);
    }


    public BadRequestException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
