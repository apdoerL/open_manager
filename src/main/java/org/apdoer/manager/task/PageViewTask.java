package org.apdoer.manager.task;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.PageViewHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @date 2018-12-25
 */
@Component
@Slf4j
public class PageViewTask {
    private PageViewHandler pageViewHandler;

    @Autowired
    public void setPageViewHandler(PageViewHandler pageViewHandler) {
        this.pageViewHandler = pageViewHandler;
    }


    public void run(){
        pageViewHandler.count();
    }
}
