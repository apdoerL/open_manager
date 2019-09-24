package org.apdoer.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/24 17:28
 */
@Data
@AllArgsConstructor
@NotBlank
@Builder
public class TaskUpdateVo {

    @NotNull(message = "任务id不能为空")
    private Integer id;

    @NotBlank(message = "定时任务不能为空")
    @Size(max = 25,message = "任务名不能超过25位")
    private String taskName;

    @NotBlank(message = "beanName不能为空")
    @Size(max = 25,message = "beanName不能超过25位")
    private String beanName;

    @NotBlank(message = "方法名不能为空")
    @Size(max = 25,message = "方法名不能超过25位")
    private String methodName;

    private String params;

    @NotBlank(message = "定时任务cron表达式不能为空")
    @Size(max = 25,message = "cron表达式不能超过25位")
    private String cronExpression;

    private Integer status;

    private String desc;
}
