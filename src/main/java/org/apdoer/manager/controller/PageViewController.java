package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.PageViewHandler;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author apdoer
 * @date 2018-12-13
 */
@RestController
@RequestMapping("/pageView")
public class PageViewController {
    private PageViewHandler pageViewHandler;

    @Autowired
    public void setPageViewHandler(PageViewHandler pageViewHandler) {
        this.pageViewHandler = pageViewHandler;
    }


    @PostMapping("/count")
    @SystemControllerLog("记录PageView")
    public ResultVo count(){
        return pageViewHandler.count();
    }

    @GetMapping("/pv")
    @SystemControllerLog("获取pv数据")
    public ResultVo getPVData(){
        return pageViewHandler.getPVData();
    }

    @GetMapping("/pvData")
    @SystemControllerLog("获取图表数据")
    public ResultVo getData(){
        return pageViewHandler.getChartData();
    }

}
