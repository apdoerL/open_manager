package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskLogVo {

    private Long id;

    private String taskName;

    private String beanName;

    private String params;

    private String cronExpression;

    private String exceptionDetail;

    private Long timeConsuming;

    private Integer status;

    private Date createTime;
}
