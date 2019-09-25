package org.apdoer.manager.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务执行记录
 * @author apdoer
 * @date 2019-01-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "biz_task_record")
public class TaskLogPo implements Serializable {

    private static final long serialVersionUID = -5100799282270590458L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    private String beanName;

    private String methodName;

    private String params;

    private String cronExpression;

    private Integer status;

    private String exceptionDetail;

    private Long timeConsuming;

    private Date createTime;
}
