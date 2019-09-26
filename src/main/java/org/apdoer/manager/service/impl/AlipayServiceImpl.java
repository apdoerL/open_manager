package org.apdoer.manager.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;

import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.AlipayMapper;
import org.apdoer.manager.model.pojo.AlipayPo;
import org.apdoer.manager.service.AlipayService;
import org.apdoer.manager.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author apdoer
 * @date 2018-12-31
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    AlipayUtils alipayUtils;

    @Autowired
    private AlipayMapper alipayMapper;



    @Override
    public AlipayPo queryConfig() {
        try {
            return alipayMapper.queryById();
        }catch (Exception e){
            log.error("");
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    // cacheput
    public void updateConfig(AlipayPo alipayConfig) {
        try {
            alipayMapper.insertSelective(alipayConfig);
        }catch (Exception e){
            log.error("");
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }
}
