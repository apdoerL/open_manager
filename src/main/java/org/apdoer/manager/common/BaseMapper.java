package org.apdoer.manager.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 该接口不能被扫描到，否则会出错
 * @author mayn
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}