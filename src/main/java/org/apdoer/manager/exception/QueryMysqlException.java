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
public class QueryMysqlException extends RuntimeException {

    private static final long serialVersionUID = 7971765573828130543L;
    private ExceptionCodeEnum exceptionCodeEnum;



    public QueryMysqlException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
