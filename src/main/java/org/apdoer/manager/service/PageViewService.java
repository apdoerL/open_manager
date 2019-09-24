package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.WebPvCountPo;

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
}
