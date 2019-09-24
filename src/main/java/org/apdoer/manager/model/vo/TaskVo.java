package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskVo {

    private Long id;

    private String taskName;

    private String beanName;

    private String cronExpression;

    private Integer status;

    private Date updateTime;

    private String desc;

    private String params;

}
