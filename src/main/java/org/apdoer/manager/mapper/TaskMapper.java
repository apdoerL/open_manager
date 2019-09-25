package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Select;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.QuartzJobPo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/25 15:33
 */
public interface TaskMapper extends BaseMapper<QuartzJobPo> {
    /**
     *
     * 这里好像有个坑,必须在sql里指定别名,不然没法按照驼峰和实体类映射起来
     * 查询启用的定时任务
     * @return
     */
    @Select("select id,bean_name as beanName,cron_expression as cronExpression,status,task_name as taskName,method_name as methodName,params,`desc`,update_time as updateTime from core_quartz_job where status = 1")
    List<QuartzJobPo> queryEnabledTask();

}
