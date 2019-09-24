package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.PageViewHandler;
import org.apdoer.manager.model.pojo.WebPvCountPo;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.WebPvChartDataVo;
import org.apdoer.manager.model.vo.WebPvCountVO;
import org.apdoer.manager.service.PageViewService;
import org.apdoer.manager.service.SystenOperationService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.apdoer.manager.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        pageViewService.insertOne(WebPvCountPo.builder()
                .weekDay(StringUtils.getWeekDay())
                .ipCounts(1L)
                .pvCounts(1L)
                .date(LocalDate.now().toString()).build());
    }

    @Override
    public ResultVo getPvData() {
        WebPvCountPo pvCountPo = pageViewService.queryDataByDate(LocalDate.now().toString());
        List<WebPvCountPo> pos = pageViewService.queryByDuration(LocalDate.now().minusDays(6).toString(),LocalDate.now().plusDays(1).toString());
        long weeklyPv = 0, weeklyIp= 0;
        for (WebPvCountPo data : pos) {
            weeklyPv += data.getPvCounts();
            weeklyIp += data.getIpCounts();
        }
        return ResultVoBuildUtils.buildSuccessResultVo(
                WebPvCountVO.builder()
                .dailyIp(pvCountPo.getIpCounts())
                .dailyPv(pvCountPo.getPvCounts())
                .weeklyIp(weeklyIp)
                .weeklyPv(weeklyPv).build());
    }

    @Override
    public ResultVo getChartData() {
        List<WebPvCountPo> pos = pageViewService.queryByDuration(LocalDate.now().minusDays(6).toString(),LocalDate.now().plusDays(1).toString());
        List<WebPvChartDataVo> vos = new ArrayList<>(pos.size());
        pos.forEach(var-> vos.add(WebPvChartDataVo.builder()
                    .weekDays(var.getWeekDay())
                    .ipCounts(var.getIpCounts())
                    .pvCounts(var.getPvCounts()).build()));
        return ResultVoBuildUtils.buildSuccessResultVo(vos);
    }
}
