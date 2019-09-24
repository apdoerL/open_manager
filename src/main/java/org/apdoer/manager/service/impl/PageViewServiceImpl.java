package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.WebPvCountMapper;
import org.apdoer.manager.model.pojo.WebPvCountPo;
import org.apdoer.manager.service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
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
        try {
            webPvCountMapper.updateByPrimaryKeySelective(pvCountPo);
        }catch (Exception e){
            log.error("update pv data error;reason:,",e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void insertOne(WebPvCountPo po) {
        try {
            webPvCountMapper.insert(po);
        }catch (Exception e){
            log.error("insert pv data error;reason",e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public WebPvCountPo queryDataByDate(String date) {
        try {
            return webPvCountMapper.queryByDate(date);
        }catch (Exception e){
            log.error("query data by date error reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public List<WebPvCountPo> queryByDuration(String start, String end) {
        try {
            return webPvCountMapper.queryRecordsByDuration(start,end);
        }catch (Exception e){
            log.error("query records by duration error,startTime:{},endTime:{},exception:{}",start,end,e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }
}
