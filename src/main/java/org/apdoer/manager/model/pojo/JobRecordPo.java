package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 定时任务执行记录
 * @author apdoer
 * @date 2019-01-07
 */
@Entity
@Data
@Table(name = "biz_task_record")
public class JobRecordPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * Bean名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean isSuccess;

    /**
     * 异常详细
     */
    private String exceptionDetail;

    /**
     * 耗时（毫秒）
     */
    private Long time;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}
