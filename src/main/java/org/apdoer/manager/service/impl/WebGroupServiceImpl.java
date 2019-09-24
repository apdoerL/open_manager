package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.mapper.WebGroupMapper;
import org.apdoer.manager.service.WebGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class WebGroupServiceImpl implements WebGroupService {
    private WebGroupMapper webGroupMapper;

    @Autowired
    public void setWebGroupMapper(WebGroupMapper webGroupMapper) {
        this.webGroupMapper = webGroupMapper;
    }
}
