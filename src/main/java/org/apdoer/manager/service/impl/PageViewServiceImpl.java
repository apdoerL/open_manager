package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.mapper.WebPvCountMapper;
import org.apdoer.manager.model.pojo.WebPvCountPo;
import org.apdoer.manager.service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/23 16:23
 */
@Slf4j
@Service
public class PageViewServiceImpl implements PageViewService {
    private WebPvCountMapper webPvCountMapper;

    @Autowired
    public void setWebPvCountMapper(WebPvCountMapper webPvCountMapper) {
        this.webPvCountMapper = webPvCountMapper;
    }

    @Override
    public void updatePageViewData(WebPvCountPo pvCountPo) {

    }
}
