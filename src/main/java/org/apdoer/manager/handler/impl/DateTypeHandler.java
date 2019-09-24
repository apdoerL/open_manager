package org.apdoer.manager.handler.impl;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * @Description 自定义的TypeHandler,此项目有不少po定义为Date类型,
 *                  数据库存储为bigInt类型的时间戳,在查询的时候会存在数据匹配不上
 *              使用: 在resultMap中指定该字段的typehandler即可
 * @Author LI
 * @Date 2019/4/15 16:08
 * @Version 1.0
 */
@MappedJdbcTypes({JdbcType.BIGINT,JdbcType.TIMESTAMP})
@MappedTypes(Date.class)
public class DateTypeHandler implements TypeHandler<Date> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        long time = resultSet.getLong(s);
        return new Date(time);
    }

    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        long time = resultSet.getLong(i);
        return new Date(time);
    }

    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        long time = callableStatement.getLong(i);
        return new Date(time);
    }
}
