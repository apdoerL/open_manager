package org.apdoer.manager.handler;

import org.apdoer.manager.model.vo.ResultVo;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/23 14:54
 */
public interface PageViewHandler {
    /**
     * 记录一个新的pv
     * @return
     */
    ResultVo count();

    /**
     * 获取总体pv数据
     * @return
     */
    ResultVo getPvData();

    /**
     * 获取 图表数据
     * @return
     */
    ResultVo getChartData();
}
