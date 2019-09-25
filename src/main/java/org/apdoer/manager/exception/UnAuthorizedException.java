package org.apdoer.manager.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apdoer.manager.enums.ExceptionCodeEnum;


/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/16 18:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 4261053754090440758L;
    private ExceptionCodeEnum exceptionCodeEnum;



    public UnAuthorizedException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
