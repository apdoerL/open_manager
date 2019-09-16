package org.apdoer.manager.handler.impl;

import cn.hutool.core.util.ObjectUtil;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.model.dto.ColumnInfo;
import org.apdoer.manager.model.dto.TableInfo;
import org.apdoer.manager.model.pojo.GeneConfigPo;
import org.apdoer.manager.utils.GeneCodeUtil;
import org.apdoer.manager.utils.PageUtil;
import org.apdoer.manager.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author apdoer
 * @date 2019-01-02
 */
@Component
public class GeneratCodeHandlerImpl implements GeneratCodeHandler {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Object getTables(String name, int[] startEnd) {
        // 使用预编译防止sql注入
        String sql = "select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
                "where table_schema = (select database()) " +
                "and table_name like ? order by create_time desc";
        Query query = em.createNativeQuery(sql);
        query.setFirstResult(startEnd[0]);
        query.setMaxResults(startEnd[1]-startEnd[0]);
        query.setParameter(1, StringUtils.isNotBlank(name) ? ("%" + name + "%") : "%%");
        List<Object[]> result = query.getResultList();
        List<TableInfo> tableInfos = new ArrayList<>();
        for (Object[] obj : result) {
            tableInfos.add(new TableInfo(obj[0],obj[1],obj[2],obj[3], ObjectUtil.isNotEmpty(obj[4])? obj[4] : "-"));
        }
        Query query1 = em.createNativeQuery("SELECT COUNT(*) from information_schema.tables where table_schema = (select database())");
        Object totalElements = query1.getSingleResult();
        return PageUtil.toPage(tableInfos,totalElements);
    }

    @Override
    public Object getColumns(String name) {
        // 使用预编译防止sql注入
        String sql = "select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " +
                "where table_name = ? and table_schema = (select database()) order by ordinal_position";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, StringUtils.isNotBlank(name) ? name : null);
        List<Object[]> result = query.getResultList();
        List<ColumnInfo> columnInfos = new ArrayList<>();
        for (Object[] obj : result) {
            columnInfos.add(new ColumnInfo(obj[0],obj[1],obj[2],obj[3],obj[4],obj[5],null,"true"));
        }
        return PageUtil.toPage(columnInfos,columnInfos.size());
    }

    @Override
    public void generator(List<ColumnInfo> columnInfos, GeneConfigPo genConfig, String tableName) {
        if(genConfig.getId() == null){
            throw new BadRequestException("请先配置生成器");
        }
        try {
            GeneCodeUtil.generatorCode(columnInfos,genConfig,tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
