package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.WebPvCountPo;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/23 16:23
 */
public interface PageViewService {
    /**
     * 更新pv数据
     * @param pvCountPo
     */
    void updatePageViewData(WebPvCountPo pvCountPo);

    /**
     * 新增pv数据
     * @param po
     */
    void insertOne(WebPvCountPo po);

    /**
     * 根据日期查询记录
     * @param date
     * @return
     */
    WebPvCountPo queryDataByDate(String date);

    /**
     * 根据时间段查询记录
     * @param start
     * @param end
     * @return
     */
    List<WebPvCountPo> queryByDuration(String start, String end);
}
