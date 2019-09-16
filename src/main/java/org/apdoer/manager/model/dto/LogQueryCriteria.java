package org.apdoer.manager.model.dto;

import lombok.Data;

/**
 * 日志查询类
 * @author apdoer
 * @date 2019-6-4 09:23:07
 */
@Data
public class LogQueryCriteria {

    private String username;

    private String logType;

    private String description;
}
