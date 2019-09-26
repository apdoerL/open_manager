package org.apdoer.manager.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * POJO工具类，用于对象到对象的转换。
 */
public class BeanUtils {
	
	private static MapperFacade mapper;

	/**
	 * 将对象转换为另一种类型的对象，并复制所有值。
	 * 支持List、复杂对象等属性。
	 * 为深度复制（即包括嵌套属性）。
	 */
	public static <T> T convert(Object src, Class<T> desType) {
		init();
		T des =  mapper.map(src, desType);
		return des;
	}
	
	public static <S, D> List<D>  convertList(List<S> src, Class<D> desType) {
		init();
		List<D> des = mapper.mapAsList(src, desType);
		return des;
	}

	/**
	 * 初始化mapper，不需要synchronized，多次初始化不影响正确性。
	 */
	private static void init() {
		if (mapper == null) {
			MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
			mapper = mapperFactory.getMapperFacade();
		}
	}
}
