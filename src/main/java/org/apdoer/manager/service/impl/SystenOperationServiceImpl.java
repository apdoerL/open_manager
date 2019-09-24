package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.mapper.OperationRecordMapper;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.model.vo.OperationRecordVo;
import org.apdoer.manager.service.SystenOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/20 18:56
 */
@Service
@Slf4j
public class SystenOperationServiceImpl implements SystenOperationService {
    private OperationRecordMapper recordMapper;

    @Autowired(required = false)
    public void setRecordMapper(OperationRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public void insertRecord(RecordPo recordPo) {
        recordMapper.insert(recordPo);
    }

    @Override
    public List<OperationRecordVo> queryAllRecords(OperationRecordVo operationRecordVo) {
        try {
            return recordMapper.queryRecords(operationRecordVo);
        }catch (Exception e){
            log.error("query system records error;reason:,",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public Long queryIpCountsByduration(String toString, String toString1) {
        try {
            return recordMapper.queryIpCountsByduration(toString,toString1);
        }catch (Exception e){
            log.error("query IpCountsByduration error start:{},end:{},reason:{}",toString,toString1,e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }
}
