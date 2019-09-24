package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Data
@Table(name = "core_quartz_job")
public class QuartzJobPo implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 定时器名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * Bean名称
     */
    @Column(name = "bean_name")
    @NotBlank
    private String beanName;

    /**
     * 方法名称
     */
    @Column(name = "method_name")
    @NotBlank
    private String methodName;

    /**
     * 参数
     */
    @Column(name = "params")
    private String params;

    /**
     * cron表达式
     */
    @Column(name = "cron_expression")
    @NotBlank
    private String cronExpression;

    /**
     * 状态
     */
    @Column(name = "is_pause")
    private Boolean isPause = false;

    /**
     * 备注
     */
    @Column(name = "remark")
    @NotBlank
    private String remark;

    /**
     * 创建日期
     */
    @Column(name = "update_time")
    private Timestamp updateTime;

}