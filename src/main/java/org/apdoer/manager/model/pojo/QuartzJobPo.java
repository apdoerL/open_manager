package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Data
@Table(name = "core_quartz_job")
public class QuartzJobPo implements Serializable {

    private static final long serialVersionUID = -2018693836474232516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String taskName;

    private String beanName;

    private String methodName;

    private String params;

    private String cronExpression;

    private Integer status;

    private String desc;

    private Timestamp updateTime;

}