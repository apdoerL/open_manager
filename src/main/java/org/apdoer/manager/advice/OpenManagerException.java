package org.apdoer.manager.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Desc open manager 自定义异常
 * @author apdoer
 * @date 2018-11-23
 */
@Data
class OpenManagerException {

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private OpenManagerException() {
        timestamp = LocalDateTime.now();
    }

    public OpenManagerException(Integer status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}


