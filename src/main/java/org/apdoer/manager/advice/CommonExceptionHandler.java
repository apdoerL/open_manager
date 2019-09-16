package org.apdoer.manager.advice;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.exception.EntityExistException;
import org.apdoer.manager.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;

/**
 * 统一异常处理
 * @author apdoer
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e){
        log.error("Throwable:{}",e);
        OpenManagerException apiError = new OpenManagerException(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理接口无权访问异常AccessDeniedException
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
        log.error("AccessDeniedException:{}",e);
        OpenManagerException apiError = new OpenManagerException(FORBIDDEN.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理自定义异常 BadRequestException
     * @param e
     * @return
     */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<OpenManagerException> badRequestException(BadRequestException e) {
        log.error("BadRequestException:{}",e);
        OpenManagerException apiError = new OpenManagerException(e.getStatus(),e.getMessage());
        return buildResponseEntity(apiError);
	}

    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<OpenManagerException> entityExistException(EntityExistException e) {
        log.error("EntityExistException:{}",e);
        OpenManagerException apiError = new OpenManagerException(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理 EntityNotFound
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<OpenManagerException> entityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException:{}",e);
        OpenManagerException apiError = new OpenManagerException(NOT_FOUND.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OpenManagerException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException:{}",e);
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        OpenManagerException apiError = new OpenManagerException(BAD_REQUEST.value(),msg.toString());
        return buildResponseEntity(apiError);
    }

    /**
     * 统一返回
     * @param apiError
     * @return
     */
    private ResponseEntity<OpenManagerException> buildResponseEntity(OpenManagerException apiError) {
        return new ResponseEntity(apiError, valueOf(apiError.getStatus()));
    }
}
