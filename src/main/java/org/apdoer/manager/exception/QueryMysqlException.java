package org.apdoer.manager.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/16 18:27
 */
public class QueryMysqlException extends RuntimeException {

    private Integer status = INTERNAL_SERVER_ERROR.value();

    public QueryMysqlException(String msg){
        super(msg);
    }

    public QueryMysqlException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
