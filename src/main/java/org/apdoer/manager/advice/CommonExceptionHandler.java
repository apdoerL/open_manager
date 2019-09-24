package org.apdoer.manager.advice;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.*;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

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
     * 处理查库异常
     * @param e
     * @return
     */
    @ExceptionHandler(QueryMysqlException.class)
    @ResponseBody
    public ResultVo handleQueryMysqlException(QueryMysqlException e){
        log.error("QueryMysqlException:",e);
        return buildResultVo(e.getExceptionCodeEnum());
    }

    /**
     * 处理更新数据异常
     * @param e
     * @return
     */
    @ExceptionHandler(UpdateMysqlException.class)
    @ResponseBody
    public ResultVo handleQueryMysqlException(UpdateMysqlException e){
        log.error("UpdateMysqlException:",e);
        return buildResultVo(e.getExceptionCodeEnum());
    }

    /**
     * 处理未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResultVo handleException(Throwable e){
        log.error("Throwable:",e);
        return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.UNKNOWN_EXCEPTION_CODE.getCode(),ExceptionCodeEnum.UNKNOWN_EXCEPTION_CODE.getValue());
    }


    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVo handleException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException",e);
        return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 处理未认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseBody
    public ResultVo handleUnAuthorizedException(UnAuthorizedException e){
        log.error("UnAuthorizedException:",e);
        return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.UNAUTHORIZED_USERS.getCode(),ExceptionCodeEnum.UNAUTHORIZED_USERS.getValue());
    }
//    /**
//     * 处理接口无权访问异常AccessDeniedException
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity handleAccessDeniedException(AccessDeniedException e){
//        log.error("AccessDeniedException:{}",e);
//        OpenManagerException apiError = new OpenManagerException(FORBIDDEN.value(),e.getMessage());
//        return buildResponseEntity(apiError);
//    }

    /**
     * 处理自定义异常 BadRequestException
     * @param e
     * @return
     */
	@ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
	public ResultVo badRequestException(BadRequestException e) {
        log.error("BadRequestException:",e);
        return buildResultVo(e.getExceptionCodeEnum());
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
	public ResultVo requestMethodHandler(){
	    log.error("request method error");
	    return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_METHOD_ERROR.getCode(),ExceptionCodeEnum.REQUEST_METHOD_ERROR.getValue());
    }

//    /**
//     * 处理 EntityExist
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = EntityExistException.class)
//    public ResponseEntity<OpenManagerException> entityExistException(EntityExistException e) {
//        log.error("EntityExistException:{}",e);
//        OpenManagerException apiError = new OpenManagerException(BAD_REQUEST.value(),e.getMessage());
//        return buildResultVo(apiError);

//    /**
//     * 处理 EntityNotFound
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = EntityNotFoundException.class)
//    public ResponseEntity<OpenManagerException> entityNotFoundException(EntityNotFoundException e) {
//        log.error("EntityNotFoundException:{}",e);
//        OpenManagerException apiError = new OpenManagerException(NOT_FOUND.value(),e.getMessage());
//        return buildResultVo(apiError);
//    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<OpenManagerException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
//        log.error("MethodArgumentNotValidException:{}",e);
//        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
//        StringBuffer msg = new StringBuffer(str[1]+":");
//        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        OpenManagerException apiError = new OpenManagerException(BAD_REQUEST.value(),msg.toString());
//        return buildResultVo(apiError);
//    }

    /**
     * 统一返回
     * @param exceptionCodeEnum
     * @return
     */
    private ResultVo buildResultVo(ExceptionCodeEnum exceptionCodeEnum) {
        return ResultVoBuildUtils.buildResultVo(exceptionCodeEnum.getCode(),exceptionCodeEnum.getValue());
    }


    public ResultVo exception(Exception e) {
        //参数校验get
        if (e instanceof ConstraintViolationException) {
            StringBuffer sb = new StringBuffer();
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                sb.append(item.getMessage() + ";");
            }
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(), ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue(), sb.toString());
        }

        //参数校验post
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exs = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exs.getBindingResult();
            StringBuffer sb = new StringBuffer();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage()+";");
            }
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(), ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue(), sb.toString());
        }
        return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.UNKNOWN_EXCEPTION_CODE.getCode(),ExceptionCodeEnum.UNKNOWN_EXCEPTION_CODE.getValue());
    }
}
