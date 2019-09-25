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
public class UpdateMysqlException extends RuntimeException {

    private static final long serialVersionUID = -5747918514701798260L;
    private ExceptionCodeEnum exceptionCodeEnum;



    public UpdateMysqlException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
