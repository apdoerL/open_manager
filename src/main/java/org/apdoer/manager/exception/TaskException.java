package org.apdoer.manager.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apdoer.manager.enums.ExceptionCodeEnum;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/25 15:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskException extends RuntimeException{
    private static final long serialVersionUID = 202900322660073499L;
    private ExceptionCodeEnum exceptionCodeEnum;

    public TaskException(ExceptionCodeEnum exceptionCodeEnum){
        super();
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
