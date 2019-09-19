package org.apdoer.manager.exception;

import lombok.Data;
import org.apdoer.manager.enums.ExceptionCodeEnum;


/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/16 18:27
 */
@Data
public class QueryMysqlException extends RuntimeException {

    private ExceptionCodeEnum exceptionCodeEnum;



    public QueryMysqlException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
