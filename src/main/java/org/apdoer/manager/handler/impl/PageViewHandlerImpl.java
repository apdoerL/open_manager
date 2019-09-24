package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.PageViewHandler;
import org.apdoer.manager.model.pojo.WebPvCountPo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.service.PageViewService;
import org.apdoer.manager.service.SystenOperationService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/23 14:54
 */
@Service
@Slf4j
public class PageViewHandlerImpl implements PageViewHandler {
    private PageViewService pageViewService;
    private SystenOperationService systemOperationService;


    @Autowired
    public void setSystemOperationService(SystenOperationService systemOperationService) {
        this.systemOperationService = systemOperationService;
    }
    @Autowired
    public void setPageViewService(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    @Override
    public ResultVo count() {
        //获取今日pv数据
        // todo date 字段需要加索引
        WebPvCountPo pvCountPo = pageViewService.queryDataByDate(LocalDate.now().toString());
        if (pvCountPo == null){
            saveNewRecord();
        }else {
            incrRecord(pvCountPo);
        }
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    private void incrRecord(WebPvCountPo pvCountPo) {
        pvCountPo.setPvCounts(pvCountPo.getPvCounts() + 1);
        Long ipcounts = systemOperationService.queryIpCountsByduration(LocalDate.now().toString(),LocalDate.now().plusDays(1).toString());
        pvCountPo.setIpCounts(ipcounts + 1);
        pageViewService.updatePageViewData(pvCountPo);
    }

    private void saveNewRecord() {

    }

    @Override
    public ResultVo getPVData() {
        return null;
    }

    @Override
    public ResultVo getChartData() {
        return null;
    }
}
