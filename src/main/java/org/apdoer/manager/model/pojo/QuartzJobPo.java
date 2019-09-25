package org.apdoer.manager.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_quartz_job")
public class QuartzJobPo implements Serializable {
    private static final long serialVersionUID = -2018693836474232516L;

    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String beanName;

    private String cronExpression;

    private Integer status;

    private String taskName;

    private String methodName;

    private String params;

    private String desc;

    private Timestamp updateTime;

}